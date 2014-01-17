package com.app42.android.sample;

import java.math.BigDecimal;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.app42.api.test.R;
import com.shephertz.app42.paas.sdk.android.App42Exception;
import com.shephertz.app42.paas.sdk.android.game.Game;

public class LeaderboardSample extends Activity implements
AsyncApp42ServiceApi.App42ScoreBoardServiceListener {

	private AsyncApp42ServiceApi asyncService;

	private ProgressDialog progressDialog;
	
	ListView list;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.leaderboard);
		list=(ListView)findViewById(R.id.leaderBoardList);
		asyncService = AsyncApp42ServiceApi.instance();
	}
	
	public void onPreviousClicked(View view) {
		Intent mainIntent = new Intent(this, UploadSample.class);
		this.startActivity(mainIntent);
	}
	
	public void onNextClicked(View view) {
		Intent mainIntent = new Intent(this, MainActivity.class);
		this.startActivity(mainIntent);
	}
	
	public void onGetScoreClicked(View viewe){
		progressDialog = ProgressDialog.show(this, "", "Loading..");
		progressDialog.setCancelable(true);
		asyncService.getLeaderBoard(Constants.App42GameName, 6, this);
	}
	
	public void onSaveScoreClicked(View viewe){
		progressDialog = ProgressDialog.show(this, "", "Saving Score..");
		progressDialog.setCancelable(true);
		asyncService.saveScoreForUser(Constants.App42GameName, Constants.UserName, new BigDecimal(10000), this);
	}
	
	@Override
	public void onSaveScoreSuccess(Game response) {
		// TODO Auto-generated method stub
		progressDialog.dismiss();
		createAlertDialog("Score SuccessFully Saved, For UserName : "+ response.getScoreList().get(0).getUserName()
				+  " With Score : " + response.getScoreList().get(0).getValue());
	}

	@Override
	public void onSaveScoreFailed(App42Exception ex) {
		// TODO Auto-generated method stub
		progressDialog.dismiss();
		createAlertDialog("Exception Occurred : "+ ex.getMessage());
	}

	@Override
	public void onLeaderBoardSuccess(Game response) {
		// TODO Auto-generated method stub
		progressDialog.dismiss();
		ArrayList<String> rankList = new ArrayList<String>();
		ArrayList<String> nameList = new ArrayList<String>();
		ArrayList<String> scoreList = new ArrayList<String>();
		for(int i=0; i<response.getScoreList().size(); i++){
			int rank = i+1;
			rankList.add(rank+"");
			String name = response.getScoreList().get(i).getUserName();
			nameList.add(name);
			BigDecimal scoreValue = response.getScoreList().get(i).getValue();
			scoreList.add(scoreValue+"");
		}
		LeaderboardAdapter adapter = new LeaderboardAdapter(LeaderboardSample.this, nameList, rankList, scoreList);
		list.setAdapter(adapter);
	}


	@Override
	public void onLeaderBoardFailed(App42Exception ex) {
		// TODO Auto-generated method stub
		progressDialog.dismiss();
		createAlertDialog("Exception Occurred : "+ ex.getMessage());
	}
	
	public void createAlertDialog(String msg) {
		AlertDialog.Builder alertbox = new AlertDialog.Builder(
				LeaderboardSample.this);
		alertbox.setTitle("Response Message");
		alertbox.setMessage(msg);
		alertbox.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			// do something when the button is clicked
			public void onClick(DialogInterface arg0, int arg1) 
			{
			}
		});
		alertbox.show();
	}



}
