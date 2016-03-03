/**
 * -----------------------------------------------------------------------
 *     Copyright  2010 ShepHertz Technologies Pvt Ltd. All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.shephertz.app42.iam.ui;

import com.shephertz.app42.iam.utils.InAppUiUtils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

// TODO: Auto-generated Javadoc
/**
 * The Class CloseButton.
 *
 * @author Vishnu Garg
 */
public class CloseButton extends View {
	
	/** The a. */
	private Paint a = new Paint();
	
	/** The b. */
	private Paint b = new Paint();
	
	/** The c. */
	private Paint c = new Paint();
	
	/** The d. */
	private float d;
	
	/** The e. */
	private float e;
	
	/** The f. */
	private float f;
	
	/** The g. */
	private float g;
	
	/** The h. */
	private float h;
	
	/** The i. */
	private boolean i = false;

	/**
	 * Instantiates a new close button.
	 *
	 * @param context the context
	 */
	public CloseButton(Context context) {
		super(context);
		designCloseButton();
	}

	/**
	 * Instantiates a new close button.
	 *
	 * @param context the context
	 * @param attrs the attrs
	 */
	public CloseButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		designCloseButton();
	}

	/**
	 * Design close button.
	 */
	private final void designCloseButton() {
		this.a.setAntiAlias(true);
		this.a.setColor(-2236963);
		this.a.setStrokeWidth(2.0F);
		this.a.setStyle(Paint.Style.FILL_AND_STROKE);
		this.b.setAntiAlias(true);
		this.b.setColor(-6710887);
		this.b.setStrokeWidth(2.0F);
		this.b.setStyle(Paint.Style.FILL_AND_STROKE);
		this.c.setAntiAlias(true);
		this.c.setColor(-16777216);
		this.c.setStrokeWidth(3.0F);
		this.c.setStyle(Paint.Style.FILL_AND_STROKE);
		this.d = InAppUiUtils.dp30;
		this.e = (this.d * 0.3333333F);
		this.g = (this.d * 0.6666667F);
		this.f = (this.d * 0.3333333F);
		this.h = (this.d * 0.6666667F);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#performClick()
	 */
	public boolean performClick() {
		return super.performClick();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onTouchEvent(android.view.MotionEvent)
	 */
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == 0) {
			this.i = true;
			invalidate();
			return true;
		}
		if (event.getAction() == 1) {
			this.i = false;
			invalidate();
			performClick();
			return true;
		}
		return super.onTouchEvent(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onMeasure(int, int)
	 */
	protected void onMeasure(int widthMeasureSpec, int paramInt1) {
		super.onMeasure(widthMeasureSpec, widthMeasureSpec);
		setMeasuredDimension((int) this.d, (int) this.d);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onDraw(android.graphics.Canvas)
	 */
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint localPaint = this.i ? this.b : this.a;
		canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2 - 1,
				localPaint);
		canvas.drawLine(this.e, this.f, this.g, this.h, this.c);
		canvas.drawLine(this.g, this.f, this.e, this.h, this.c);
	}
}