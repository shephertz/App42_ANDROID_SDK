package com.app42.android.sample;

import java.io.InputStream;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.app42.api.test.R;
import com.shephertz.app42.paas.sdk.android.App42Exception;
import com.shephertz.app42.paas.sdk.android.upload.Upload;
import com.shephertz.app42.paas.sdk.android.upload.UploadFileType;

public class UploadSample extends Activity implements
		AsyncApp42ServiceApi.App42UploadServiceListener {

	private AsyncApp42ServiceApi asyncService;

	private ProgressDialog progressDialog;

	private String fileName = "IMAGE" + new Date().getTime();
	private String Description = "This Is IMAGE File";
	private final int RESULT_LOAD_IMAGE = 1;

	ImageView imageView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.upload);
		imageView = (ImageView) findViewById(R.id.cdnImage);
		asyncService = AsyncApp42ServiceApi.instance();
	}

	public void onPreviousClicked(View view) {
		Intent mainIntent = new Intent(this, StorageSample.class);
		this.startActivity(mainIntent);
	}

	public void onNextClicked(View view) {
		Intent mainIntent = new Intent(this, LeaderboardSample.class);
		this.startActivity(mainIntent);
	}

	public void onUploadFileClicked(View view) {
		browsePhoto();
	}

	public void uploadImage(String imagePath) {
		progressDialog = ProgressDialog.show(this, "",
				"Please Wait.. Uploading...");
		progressDialog.setCancelable(true);
		asyncService.uploadImage(fileName, imagePath, UploadFileType.IMAGE,
				Description, this);
	}

	/*
	 * Browse Gallery for choosing the Image to upload.
	 */
	private void browsePhoto() {
		Intent i = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(i, RESULT_LOAD_IMAGE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK
				&& null != data) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };
			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();
			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();
			uploadImage(picturePath);
		}
	}

	public void loadImageFromUrl(final String url, final ImageView img) {
		final Handler callerThreadHandler = new Handler();
		new Thread() {
			@Override
			public void run() {
				final Bitmap bitmap = loadBitmap(url);
				callerThreadHandler.post(new Runnable() {
					@Override
					public void run() {
						progressDialog.dismiss();
						img.setImageBitmap(bitmap);
					}
				});
			}
		}.start();
	}

	// decodes image and scales it to reduce memory consumption
	private Bitmap loadBitmap(String url) {
		try {
			// decode image size
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			if (url.startsWith("http")) {
				InputStream in = new java.net.URL(url).openStream();
				BitmapFactory.decodeStream(in, null, o);
			} else
				BitmapFactory.decodeFile(url, o);
			int width_tmp = o.outWidth, height_tmp = o.outHeight;
			int scale = 1;
			while (true) {
				if (width_tmp / 2 < 150 && height_tmp / 2 < 150)
					break;
				width_tmp /= 2;
				height_tmp /= 2;
				scale *= 2;
			}
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			o2.inPreferredConfig = Bitmap.Config.RGB_565;
			o2.inJustDecodeBounds = false;
			if (url.startsWith("http")) {
				InputStream in = new java.net.URL(url).openStream();
				return BitmapFactory.decodeStream(in, null, o2);
			} else
				return BitmapFactory.decodeFile(url, o2);
		} catch (Exception e) {
		}
		return null;
	}

	public void onGetFilesClicked(View view) {
		progressDialog = ProgressDialog.show(this, "", "Getting Image..");
		progressDialog.setCancelable(true);
		asyncService.getImage(fileName, this);
	}

	@Override
	public void onUploadImageSuccess(Upload response) {
		// TODO Auto-generated method stub
		progressDialog.dismiss();
		createAlertDialog("SUccessFully Uploaded : " + response);
	}

	@Override
	public void onUploadImageFailed(App42Exception ex) {
		// TODO Auto-generated method stub
		progressDialog.dismiss();
		createAlertDialog("Exception Occurred : " + ex.getMessage());
	}

	@Override
	public void onGetImageSuccess(Upload response) {
		// TODO Auto-generated method stub
		String imageUrl = response.getFileList().get(0).getUrl();
		loadImageFromUrl(imageUrl, imageView);
	}

	@Override
	public void onGetImageFailed(App42Exception ex) {
		// TODO Auto-generated method stub
		progressDialog.dismiss();
		createAlertDialog("Exception Occurred : " + ex.getMessage());
	}

	public void createAlertDialog(String msg) {
		AlertDialog.Builder alertbox = new AlertDialog.Builder(
				UploadSample.this);
		alertbox.setTitle("Response Message");
		alertbox.setMessage(msg);
		alertbox.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			// do something when the button is clicked
			public void onClick(DialogInterface arg0, int arg1) {
			}
		});
		alertbox.show();
	}

}
