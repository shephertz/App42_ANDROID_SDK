package com.app42.android.sample;

import java.math.BigDecimal;

import org.json.JSONObject;

import android.os.Handler;

import com.shephertz.app42.paas.sdk.android.App42Exception;
import com.shephertz.app42.paas.sdk.android.ServiceAPI;
import com.shephertz.app42.paas.sdk.android.game.Game;
import com.shephertz.app42.paas.sdk.android.game.ScoreBoardService;
import com.shephertz.app42.paas.sdk.android.storage.Storage;
import com.shephertz.app42.paas.sdk.android.storage.StorageService;
import com.shephertz.app42.paas.sdk.android.upload.Upload;
import com.shephertz.app42.paas.sdk.android.upload.UploadFileType;
import com.shephertz.app42.paas.sdk.android.upload.UploadService;
import com.shephertz.app42.paas.sdk.android.user.User;
import com.shephertz.app42.paas.sdk.android.user.UserService;

public class AsyncApp42ServiceApi {
	private UserService userService;
	private StorageService storageService;
	private UploadService uploadService;
	private ScoreBoardService scoreBoardService;
	private static AsyncApp42ServiceApi mInstance = null;
	
	private AsyncApp42ServiceApi() {
		ServiceAPI sp = new ServiceAPI(Constants.App42ApiKey,
				Constants.App42ApiSecret);
		this.userService = sp.buildUserService();
		this.storageService = sp.buildStorageService();
		this.scoreBoardService = sp.buildScoreBoardService();
		this.uploadService = sp.buildUploadService();
	}

	/*
	 * instance of class
	 */
	public static AsyncApp42ServiceApi instance() {

		if (mInstance == null) {
			mInstance = new AsyncApp42ServiceApi();
		}

		return mInstance;
	}

	/*
	 * This function allows to create user using APP42 service
	 */
	public void createUser(final String name, final String pswd,
			final String email, final App42UserServiceListener callBack) {
		final Handler callerThreadHandler = new Handler();
		new Thread() {
			@Override
			public void run() {
				try {
					final User user = userService.createUser(name, pswd, email);
					callerThreadHandler.post(new Runnable() {
						@Override
						public void run() {
							callBack.onUserCreated(user);
						}
					});
				} catch (final App42Exception ex) {
					callerThreadHandler.post(new Runnable() {
						@Override
						public void run() {
							if (callBack != null) {
								callBack.onCreationFailed(ex);
							}
						}
					});

				}
			}
		}.start();
	}

	/*
	 * This function validate user's authentication with APP42
	 */
	public void authenticateUser(final String name, final String pswd,
			final App42UserServiceListener callBack) {
		final Handler callerThreadHandler = new Handler();
		new Thread() {
			@Override
			public void run() {
				try {
					final User response = userService.authenticate(name, pswd);
					callerThreadHandler.post(new Runnable() {
						@Override
						public void run() {
							callBack.onUserAuthenticated(response);
						}
					});
				} catch (final App42Exception ex) {
					callerThreadHandler.post(new Runnable() {
						@Override
						public void run() {
							if (callBack != null) {
								callBack.onAuthenticationFailed(ex);
							}
						}
					});
				}
			}
		}.start();
	}

	/*
	 * This function gets user's details from APP42.
	 */
	public void getUser(final String name, final App42UserServiceListener callBack) {
		final Handler callerThreadHandler = new Handler();
		new Thread() {
			@Override
			public void run() {
				try {
					final User response = userService.getUser(name);
					callerThreadHandler.post(new Runnable() {
						@Override
						public void run() {
							callBack.onGetUserSuccess(response);
						}
					});
				} catch (final App42Exception ex) {
					callerThreadHandler.post(new Runnable() {
						@Override
						public void run() {
							if (callBack != null) {
								callBack.onGetUserFailed(ex);
							}
						}
					});
				}
			}
		}.start();
	}

	public static interface App42UserServiceListener {
		public void onUserCreated(User response);

		public void onCreationFailed(App42Exception exception);

		public void onGetUserSuccess(User response);

		public void onGetUserFailed(App42Exception exception);

		public void onUserAuthenticated(User response);

		public void onAuthenticationFailed(App42Exception exception);

	}

	/*
	 * This function Stores JSON Document.
	 */
	public void insertJSONDoc(final String dbName, final String collectionName,
			final JSONObject json, final App42StorageServiceListener callBack) {
		final Handler callerThreadHandler = new Handler();
		new Thread() {
			@Override
			public void run() {
				try {
					final Storage response = storageService.insertJSONDocument(dbName, collectionName, json);
					callerThreadHandler.post(new Runnable() {
						@Override
						public void run() {
							callBack.onDocumentInserted(response);
						}
					});
				} catch (final App42Exception ex) {
					callerThreadHandler.post(new Runnable() {
						@Override
						public void run() {
							if (callBack != null) {
								callBack.onInsertionFailed(ex);
							}
						}
					});
				}
			}
		}.start();
	}
	
	/*
	 * This function Find JSON Document By Id.
	 */
	public void findDocByDocId(final String dbName, final String collectionName,
			final String docId, final App42StorageServiceListener callBack) {
		final Handler callerThreadHandler = new Handler();
		new Thread() {
			@Override
			public void run() {
				try {
					final Storage response = storageService.findDocumentById(dbName, collectionName, docId);
					callerThreadHandler.post(new Runnable() {
						@Override
						public void run() {
							callBack.onFindDocSuccess(response);
						}
					});
				} catch (final App42Exception ex) {
					callerThreadHandler.post(new Runnable() {
						@Override
						public void run() {
							if (callBack != null) {
								callBack.onFindDocFailed(ex);
							}
						}
					});
				}
			}
		}.start();
	}
	
	/*
	 * This function Find JSON Document By Id.
	 */
	public void updateDocByKeyValue(final String dbName,
			final String collectionName, final String key, final String value,
			final JSONObject newJsonDoc, final App42StorageServiceListener callBack) {
		final Handler callerThreadHandler = new Handler();
		new Thread() {
			@Override
			public void run() {
				try {
					final Storage response = storageService.updateDocumentByKeyValue(dbName, collectionName, key, value, newJsonDoc);
					callerThreadHandler.post(new Runnable() {
						@Override
						public void run() {
							callBack.onUpdateDocSuccess(response);
						}
					});
				} catch (final App42Exception ex) {
					callerThreadHandler.post(new Runnable() {
						@Override
						public void run() {
							if (callBack != null) {
								callBack.onUpdateDocFailed(ex);
							}
						}
					});
				}
			}
		}.start();
	}

	public static interface App42StorageServiceListener 
	{
		
		public void onDocumentInserted(Storage response);
	
		public void onUpdateDocSuccess(Storage response);

		public void onFindDocSuccess(Storage response);

		public void onInsertionFailed(App42Exception ex);
		
		public void onFindDocFailed(App42Exception ex);

		public void onUpdateDocFailed(App42Exception ex);
	}

	
	
	/*
	 * This function Saves User Score for the Given GameName.
	 */
	public void saveScoreForUser(final String gameName,
			final String gameUserName, final BigDecimal gameScore, final App42ScoreBoardServiceListener callBack) {
		final Handler callerThreadHandler = new Handler();
		new Thread() {
			@Override
			public void run() {
				try {
					final Game response = scoreBoardService.saveUserScore(gameName, gameUserName, gameScore);
					callerThreadHandler.post(new Runnable() {
						@Override
						public void run() {
							callBack.onSaveScoreSuccess(response);
						}
					});
				} catch (final App42Exception ex) {
					callerThreadHandler.post(new Runnable() {
						@Override
						public void run() {
							if (callBack != null) {
								callBack.onSaveScoreFailed(ex);
							}
						}
					});
				}
			}
		}.start();
	}
	
	
	/*
	 * This function Retrieves Top N(max no.) Rankers.
	 */
	public void getLeaderBoard(final String gameName,
			final int max, final App42ScoreBoardServiceListener callBack) {
		final Handler callerThreadHandler = new Handler();
		new Thread() {
			@Override
			public void run() {
				try {
					final Game response = scoreBoardService.getTopNRankers(gameName, max);
					callerThreadHandler.post(new Runnable() {
						@Override
						public void run() {
							callBack.onLeaderBoardSuccess(response);
						}
					});
				} catch (final App42Exception ex) {
					callerThreadHandler.post(new Runnable() {
						@Override
						public void run() {
							if (callBack != null) {
								callBack.onLeaderBoardFailed(ex);
							}
						}
					});
				}
			}
		}.start();
	}
	
	public static interface App42ScoreBoardServiceListener 
	{
		void onSaveScoreSuccess(Game response);

		void onSaveScoreFailed(App42Exception ex);
		
		void onLeaderBoardSuccess(Game response);
        
		void onLeaderBoardFailed(App42Exception ex);
	}
	
	/*
	 * This function Uploads File On App42 Cloud.
	 */
	public void uploadImage(final String name,
			final String filePath, final UploadFileType fileType, final String description, final App42UploadServiceListener callBack) {
		final Handler callerThreadHandler = new Handler();
		new Thread() {
			@Override
			public void run() {
				try {
					final Upload response = uploadService.uploadFile(name, filePath, UploadFileType.IMAGE, description);
					callerThreadHandler.post(new Runnable() {
						@Override
						public void run() {
							callBack.onUploadImageSuccess(response);
						}
					});
				} catch (final App42Exception ex) {
					callerThreadHandler.post(new Runnable() {
						@Override
						public void run() {
							if (callBack != null) {
								callBack.onUploadImageFailed(ex);
							}
						}
					});
				}
			}
		}.start();
	}
	
	
	/*
	 * This function Uploads File On App42 Cloud.
	 */
	public void getImage(final String fileName, final App42UploadServiceListener callBack) {
		final Handler callerThreadHandler = new Handler();
		new Thread() {
			@Override
			public void run() {
				try {
					final Upload response = uploadService.getFileByName(fileName);
					callerThreadHandler.post(new Runnable() {
						@Override
						public void run() {
							callBack.onGetImageSuccess(response);
						}
					});
				} catch (final App42Exception ex) {
					callerThreadHandler.post(new Runnable() {
						@Override
						public void run() {
							if (callBack != null) {
								callBack.onGetImageFailed(ex);
							}
						}
					});
				}
			}
		}.start();
	}
	
	
	public static interface App42UploadServiceListener 
	{
		void onUploadImageSuccess(Upload response);

		void onUploadImageFailed(App42Exception ex);

		void onGetImageSuccess(Upload response);

		void onGetImageFailed(App42Exception ex);
	}

}