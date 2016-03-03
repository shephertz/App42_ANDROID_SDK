/**
 * -----------------------------------------------------------------------
 *     Copyright  2010 ShepHertz Technologies Pvt Ltd. All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.shephertz.app42.iam.ui;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.Configuration;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shephertz.app42.iam.InAppManager;
import com.shephertz.app42.iam.model.App42Action;
import com.shephertz.app42.iam.model.InAppAction;
import com.shephertz.app42.iam.model.InAppData;
import com.shephertz.app42.iam.model.SurveyData;
import com.shephertz.app42.iam.model.SurveyData.Choice;
import com.shephertz.app42.iam.model.SurveyData.Question;
import com.shephertz.app42.iam.utils.InAppUiUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class SurveyDialog.
 *
 * @author Vishnu
 */
public class SurveyDialog implements InAppAction {

	/** The current activity. */
	private Activity currentActivity;
	
	/** The Radio button. */
	private final String RadioButton = "radioButton";
	
	/** The Check box. */
	private final String CheckBox = "checkBox";
	
	/** The Question. */
	private final String Question = "question";
	
	/** The Answers. */
	private final String Answers = "answers";
	
	/** The question answers. */
	private ArrayList<String> questionAnswers;
	
	/** The current question id. */
	private String currentQuestionId = null;

	/**
	 * Instantiates a new survey dialog.
	 *
	 * @param currentActivity the current activity
	 */
	public SurveyDialog(Activity currentActivity) {
		super();
		questionAnswers = new ArrayList<String>();
		this.currentActivity = currentActivity;
	}

	/**
	 * Show survey dialog.
	 *
	 * @param inAppData the in app data
	 */
	public void showSurveyDialog(final InAppData inAppData) {
		SurveyData surveyData=(SurveyData) inAppData;
		List<Question> surveyQuestions = surveyData.getQuestions();
		if (surveyQuestions == null || surveyQuestions.size() == 0)
			return;
		questionAnswers.clear();
		Question question = surveyQuestions.get(0);
		currentQuestionId = question.getQuestionId();
		if (question.getQuestionType().equals(RadioButton)) {
			createCustom(android.R.layout.simple_list_item_single_choice,
					ListView.CHOICE_MODE_SINGLE, surveyData);
		} else if (question.getQuestionType().equals(CheckBox)) {
			createCustom(android.R.layout.simple_list_item_multiple_choice,
					ListView.CHOICE_MODE_MULTIPLE, surveyData);
		}
	}

	/**
	 * Creates the custom.
	 *
	 * @param resource the resource
	 * @param choiceMode the choice mode
	 * @param surveyData the survey data
	 */
	public void createCustom(final int resource, int choiceMode,
			final SurveyData surveyData) {
//		final Dialog dialog = new Dialog(currentActivity,
//				android.R.style.Theme_Light_NoTitleBar_Fullscreen);
		
		final Question question = surveyData.getQuestions().get(0);
		final String[] answersArr = getAnswersArray(question.getmChoices());
		
		final Dialog dialog = new Dialog(currentActivity,android.R.style.Theme_Light_NoTitleBar_Fullscreen);
		RelativeLayout relativeLayoutParent = new RelativeLayout(currentActivity);
		RelativeLayout.LayoutParams relativeLayoutParamsParent = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
		relativeLayoutParent.setBackgroundColor(Color.rgb(0, 110, 169));
		TextView title = getQuestionTitleText(surveyData.getTitle());
		title.setId(122);
		RelativeLayout.LayoutParams params = getLayoutParams(
				RelativeLayout.ALIGN_PARENT_TOP,
				RelativeLayout.CENTER_IN_PARENT);
		
		
		RelativeLayout relativeLayoutChild = new RelativeLayout(currentActivity);
		relativeLayoutChild.setBackgroundColor(Color.rgb(240, 240, 240));
		
		RelativeLayout.LayoutParams relativeLayoutChildParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
		int screenOrientation=currentActivity.getResources().getConfiguration().orientation;
		if(screenOrientation==Configuration.ORIENTATION_PORTRAIT){
			relativeLayoutChildParams.setMargins(
					InAppUiUtils.dpToPx(currentActivity, 40),
					InAppUiUtils.dpToPx(currentActivity, 25),
					InAppUiUtils.dpToPx(currentActivity, 40),
					InAppUiUtils.dpToPx(currentActivity, 60));
			params.setMargins(0, InAppUiUtils.dpToPx(currentActivity, 25), 0, 0);
		}
		else{
			params.setMargins(0, InAppUiUtils.dpToPx(currentActivity, 10), 0, 0);
			relativeLayoutChildParams.setMargins(
					InAppUiUtils.dpToPx(currentActivity, 40),
					InAppUiUtils.dpToPx(currentActivity, 10),
					InAppUiUtils.dpToPx(currentActivity, 40),
					InAppUiUtils.dpToPx(currentActivity, 20));
		}
		//params.setMargins(0, InAppUiUtils.dpToPx(currentActivity, 20), 0, 0);
		relativeLayoutParent.addView(title, params);
	
		relativeLayoutChildParams.addRule(RelativeLayout.BELOW, title.getId());

		dialog.setCancelable(true);

		TextView name = getQuestionText(question.getQuestionText());
		RelativeLayout.LayoutParams params1 = getLayoutParams(
				RelativeLayout.ALIGN_PARENT_TOP,
				RelativeLayout.CENTER_IN_PARENT);
		relativeLayoutChild.addView(name, params1);
		params1.setMargins(0, InAppUiUtils.dpToPx(currentActivity, 10), 0,
				InAppUiUtils.dpToPx(currentActivity, 10));

		LinearLayout linlay = getLinearlayoutForButtons();
		Button btnSubmit = getButton("Submit", Color.rgb(41, 41, 41), 0);
		Button btnCancel = getButton("Cancel", Color.rgb(204, 0, 0), 20);
		linlay.addView(btnCancel);
		linlay.addView(btnSubmit);

		RelativeLayout.LayoutParams params3 = getLayoutParams(
				RelativeLayout.ALIGN_PARENT_BOTTOM,
				RelativeLayout.CENTER_IN_PARENT);
		relativeLayoutChild.addView(linlay, params3);
		final ListView contactListView = new ListView(currentActivity);
		contactListView.setBackgroundColor(Color.WHITE);
		contactListView
				.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
		RelativeLayout.LayoutParams params2 = getLayoutParams(
				RelativeLayout.ABOVE, linlay.getId(), RelativeLayout.BELOW,
				name.getId());
		relativeLayoutChild.addView(contactListView, params2);
		relativeLayoutParent.addView(relativeLayoutChild,
				relativeLayoutChildParams);
		dialog.setContentView(relativeLayoutParent, relativeLayoutParamsParent);
		// ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(
		// currentActivity, resource, android.R.id.text1, answersArr);
		ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(
				currentActivity, resource, answersArr) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				LayoutInflater inflater = LayoutInflater.from(currentActivity);
				View view = inflater.inflate(resource, null);

				TextView text = (TextView) view
						.findViewById(android.R.id.text1);
				text.setTextColor(Color.rgb(48, 48, 48));
				text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
				text.setPadding(InAppUiUtils.dpToPx(currentActivity, 40), 0, 0, 0);
				text.setText(answersArr[position]);
				return view;
			}
		};
		contactListView.setAdapter(itemsAdapter);
		contactListView.setChoiceMode(choiceMode);
		btnSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				SparseBooleanArray checked = contactListView
						.getCheckedItemPositions();
				for (int i = 0; i < checked.size(); i++) {
					int position = checked.keyAt(i);
					if (checked.valueAt(i)) {
						String optionID = getOptionIdByText(question
								.getmChoices().get(position).getText(),
								question.getmChoices());
						questionAnswers.add(optionID);
					}
				}
				if (questionAnswers.size() > 0) {
					dialog.dismiss();
					performAccpetAction(surveyData.getCampName(),
							surveyData.getAction());
				} else {
					Toast.makeText(currentActivity,
							"Please Select your option", Toast.LENGTH_SHORT)
							.show();
				}
			}
		});
		btnCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
				performCancelAction(surveyData.getCampName(),
						surveyData.getCancelAction());
			}
		});
		App42Action action = new App42Action("trackEvent", "CampaignViewed_"
				+ surveyData.getCampName(), new JSONObject());
		InAppManager.performActions(action);
		dialog.show();
	}

	/**
	 * Gets the question title text.
	 *
	 * @param text the text
	 * @return the question title text
	 */
	private TextView getQuestionTitleText(String text) {
		TextView name = new TextView(currentActivity);
		name.setText(text);
		name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
		// name.setTypeface(null, Typeface.BOLD);
		name.setTextColor(Color.rgb(255, 255, 255));
		name.setId(11);
		return name;
	}

	/**
	 * Gets the question text.
	 *
	 * @param text the text
	 * @return the question text
	 */
	private TextView getQuestionText(String text) {
		TextView name = new TextView(currentActivity);
		name.setText(text);
		name.setTextSize(22);
		name.setTextColor(Color.rgb(62, 62, 62));
		name.setId(11);
		return name;
	}

	/**
	 * Gets the layout params.
	 *
	 * @param rule1 the rule1
	 * @param rule2 the rule2
	 * @return the layout params
	 */
	private RelativeLayout.LayoutParams getLayoutParams(int rule1, int rule2) {
		RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params1.addRule(rule1);
		params1.addRule(rule2);
		return params1;
	}

	/**
	 * Gets the layout params.
	 *
	 * @param rule1 the rule1
	 * @param viewId1 the view id1
	 * @param rule2 the rule2
	 * @param viewId2 the view id2
	 * @return the layout params
	 */
	private RelativeLayout.LayoutParams getLayoutParams(int rule1, int viewId1,
			int rule2, int viewId2) {
		RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params1.addRule(rule1, viewId1);
		params1.addRule(rule2, viewId2);
		return params1;
	}

	/**
	 * Gets the linearlayout for buttons.
	 *
	 * @return the linearlayout for buttons
	 */
	private LinearLayout getLinearlayoutForButtons() {
		LinearLayout linlay = new LinearLayout(currentActivity);
		LinearLayout.LayoutParams relativeLayoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		linlay.setLayoutParams(relativeLayoutParams);
		linlay.setOrientation(LinearLayout.HORIZONTAL);
		linlay.setId(13);
		linlay.setBackgroundColor(Color.rgb(240, 240, 240));
		return linlay;
	}

	/**
	 * Gets the button.
	 *
	 * @param text the text
	 * @param color the color
	 * @param righntpadding the righntpadding
	 * @return the button
	 */
	private Button getButton(String text, int color, int righntpadding) {
		Button btnSubmit = new Button(currentActivity);
		btnSubmit.setText(text);
		btnSubmit.setTextColor(Color.rgb(243, 243, 243));
		btnSubmit.setBackgroundColor(color);
		btnSubmit.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		params.setMargins(0, InAppUiUtils.dpToPx(currentActivity, 10),
				InAppUiUtils.dpToPx(currentActivity, righntpadding), InAppUiUtils.dpToPx(currentActivity, 10));
		btnSubmit.setLayoutParams(params);
		return btnSubmit;
	}

	/**
	 * Gets the answers array.
	 *
	 * @param mChoices the m choices
	 * @return the answers array
	 */
	private String[] getAnswersArray(List<Choice> mChoices) {
		String[] choiceArr = new String[mChoices.size()];
		for (int i = 0; i < mChoices.size(); i++) {
			choiceArr[i] = mChoices.get(i).getText();
		}
		return choiceArr;
	}

	/**
	 * Gets the option id by text.
	 *
	 * @param text the text
	 * @param mChoices the m choices
	 * @return the option id by text
	 */
	private String getOptionIdByText(String text, List<Choice> mChoices) {
		for (int i = 0; i < mChoices.size(); i++) {
			if (text.equalsIgnoreCase(mChoices.get(i).getText())) {
				return mChoices.get(i).getValue();
			}
		}
		return mChoices.get(0).getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shephertz.app42.iam.ui.IAMAction#performCancelAction(java.lang.String
	 * , com.shephertz.app42.iam.data.App42Action)
	 */
	@Override
	public void performCancelAction(String campName, App42Action app42Action) {
		JSONObject properties = new JSONObject();
		try {
			properties.put(Question, currentQuestionId);
			app42Action.setProperties(properties);
			InAppManager.performActions(app42Action);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shephertz.app42.iam.ui.IAMAction#performAccpetAction(java.lang.String
	 * , com.shephertz.app42.iam.data.App42Action)
	 */
	@Override
	public void performAccpetAction(String campName, App42Action app42Action) {
		// TODO Auto-generated method stub
		JSONObject properties = new JSONObject();
		try {
			properties.put(Question, currentQuestionId);
			String answers = "";
			if (questionAnswers.size() > 1) {
				for (int i = 1; i < questionAnswers.size(); i++)
					answers = answers + questionAnswers.get(i) + ",";
			}
			properties.put(Answers, answers + questionAnswers.get(0));
			app42Action.setProperties(properties);
			InAppManager.performActions(app42Action);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InAppManager.performActions(app42Action);
	}

}
