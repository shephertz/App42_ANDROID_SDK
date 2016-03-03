/**
 * -----------------------------------------------------------------------
 *     Copyright  2010 ShepHertz Technologies Pvt Ltd. All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.shephertz.app42.iam.ui;

import com.shephertz.app42.iam.utils.InAppUiUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

// TODO: Auto-generated Javadoc
/**
 * The Class BackgroundImageView.
 *
 * @author Vishnu Garg
 */
public class BackgroundImageView extends ImageView
{
  
  /** The paint. */
  private Paint paint = new Paint();
  
  /** The is interestial. */
  private boolean isInterestial;
  
  /** The metric. */
  private Matrix metric = new Matrix();
  
  /** The is drawn. */
  private boolean isDrawn;

  /**
   * Instantiates a new background image view.
   *
   * @param context the context
   * @param attrs the attrs
   * @param defStyle the def style
   */
public BackgroundImageView(Context context, AttributeSet attrs, int defStyle)
  {
    super(context, attrs, defStyle);
    init();
  }

  /**
   * Instantiates a new background image view.
   *
   * @param context the context
   * @param attrs the attrs
   */
public BackgroundImageView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  /**
   * Instantiates a new background image view.
   *
   * @param context the context
   * @param isInterestial the is interestial
   */
public BackgroundImageView(Context context, boolean isInterestial) {
    super(context);
    init();
    this.isInterestial = isInterestial;
  }

  /**
   * Inits the.
   */
private void init() {
    this.paint.setColor(-16711936);
    this.paint.setStrokeWidth(2.0F);
    this.paint.setStyle(Paint.Style.FILL_AND_STROKE);
  }

  /* (non-Javadoc)
 * @see android.widget.ImageView#onDraw(android.graphics.Canvas)
 */
protected void onDraw(Canvas canvas)
  {
    super.onDraw(canvas);
    if (this.isInterestial) {
      return;
    }
    if (this.isDrawn) {
      this.isDrawn = false;
      return;
    }
    Bitmap localBitmap = loadBitmapFromView(this);
    canvas.drawColor(0, PorterDuff.Mode.CLEAR);
    localBitmap = getRoundedCornerBitmap(localBitmap, InAppUiUtils.dp20);
    canvas.drawBitmap(localBitmap, this.metric, this.paint);
  }

  /**
   * Load bitmap from view.
   *
   * @param view the view
   * @return the bitmap
   */
public Bitmap loadBitmapFromView(View view) {
    if (view.getMeasuredHeight() <= 0) {
      view.measure(-2, -2);
    }
    Bitmap localBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), 
      Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
    this.isDrawn = true;
    view.draw(localCanvas);
    return localBitmap;
  }
  
  /**
   * Gets the rounded corner bitmap.
   *
   * @param bitmap the bitmap
   * @param pixels the pixels
   * @return the rounded corner bitmap
   */
private static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels)
  {
    if (bitmap == null) {
      return null;
    }
    Bitmap localBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), 
      Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    Paint localPaint = new Paint();
    Rect localRect = new Rect(0, 0, 
      bitmap.getWidth(), bitmap.getHeight());
    RectF localRectF = new RectF(localRect);
    localPaint.setAntiAlias(true);
    localCanvas.drawARGB(0, 0, 0, 0);
    localPaint.setColor(-16777216);
    localCanvas.drawRoundRect(localRectF, pixels, pixels, localPaint);

    localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    localCanvas.drawBitmap(bitmap, localRect, localRect, localPaint);

    return localBitmap;
  }
}