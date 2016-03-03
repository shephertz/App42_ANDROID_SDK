/**
 * -----------------------------------------------------------------------
 *     Copyright  2010 ShepHertz Technologies Pvt Ltd. All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.app42.android.sample;

import java.math.BigDecimal;

import org.json.JSONObject;

import android.content.Context;
import android.os.Handler;

import com.shephertz.app42.paas.sdk.android.App42API;
import com.shephertz.app42.paas.sdk.android.App42Exception;
import com.shephertz.app42.paas.sdk.android.game.Game;
import com.shephertz.app42.paas.sdk.android.game.ScoreBoardService;
import com.shephertz.app42.paas.sdk.android.storage.Storage;
import com.shephertz.app42.paas.sdk.android.storage.StorageService;
import com.shephertz.app42.paas.sdk.android.upload.Upload;
import com.shephertz.app42.paas.sdk.android.upload.UploadFileType;
import com.shephertz.app42.paas.sdk.android.upload.UploadService;
import com.shephertz.app42.paas.sdk.android.user.User;
import com.shephertz.app42.paas.sdk.android.user.UserService;

/**
 * The Class AsyncApp42ServiceApi.
 */
public class AsyncApp42ServiceApi {
	
	/** The user service. */
	private UserService userService;
	
	/** The storage service. */
	private StorageService storageService;
	
	/** The upload service. */
	private UploadService uploadService;
	
	/** The score board service. */
	private ScoreBoardService scoreBoardService;
	
	/** The m instance. */
	private static AsyncApp42ServiceApi mInstance = null;
	
	/**
	 * Instantiates a new async app42 service api.
	 *
	 * @param context the context
	 */
	private AsyncApp42ServiceApi(Context context) {
		App42API.initialize(context, Constants.App42ApiKey, Constants.App42ApiSecret);
		this.userService = App42API.buildUserService();
		this.storageService = App42API.buildStorageService();
		this.scoreBoardService = App42API.buildScoreBoardService();
		this.uploadService = App42API.buildUploadService();
	}

	/**
	 * Instance.
	 *
	 * @param context the context
	 * @return the async app42 service api
	 */
	/*
	 * instance of class
	 */
	public static AsyncApp42ServiceApi instance(Context context) {

		if (mInstance == null) {
			mInstance = new AsyncApp42ServiceApi(context);
		}

		return mInstance;
	}

	/**
	 * Creates the user.
	 *
	 * @param name the name
	 * @param pswd the pswd
	 * @param email the email
	 * @param callBack the call back
	 */
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
	
	/**
	 * Authenticate user.
	 *
	 * @param name the name
	 * @param pswd the pswd
	 * @param callBack the call back
	 */
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

	/**
	 * Gets the user.
	 *
	 * @param name the name
	 * @param callBack the call back
	 * @return the user
	 */
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

	/**
	 * The listener interface for receiving app42UserService events.
	 * The class that is interested in processing a app42UserService
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addApp42UserServiceListener<code> method. When
	 * the app42UserService event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see App42UserServiceEvent
	 */
	public static interface App42UserServiceListener {
		
		/**
		 * Invoked when on user is created.
		 *
		 * @param response the response
		 */
		public void onUserCreated(User response);

		/**
		 * On creation failed.
		 *
		 * @param exception the exception
		 */
		public void onCreationFailed(App42Exception exception);

		/**
		 * On get user success.
		 *
		 * @param response the response
		 */
		public void onGetUserSuccess(User response);

		/**
		 * On get user failed.
		 *
		 * @param exception the exception
		 */
		public void onGetUserFailed(App42Exception exception);

		/**
		 * On user authenticated.
		 *
		 * @param response the response
		 */
		public void onUserAuthenticated(User response);

		/**
		 * On authentication failed.
		 *
		 * @param exception the exception
		 */
		public void onAuthenticationFailed(App42Exception exception);

	}

	/**
	 * Insert json doc.
	 *
	 * @param dbName the db name
	 * @param collectionName the collection name
	 * @param json the json
	 * @param callBack the call back
	 */
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
	
	/**
	 * Find doc by doc id.
	 *
	 * @param dbName the db name
	 * @param collectionName the collection name
	 * @param docId the doc id
	 * @param callBack the call back
	 */
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
	
	/**
	 * Update doc by key value.
	 *
	 * @param dbName the db name
	 * @param collectionName the collection name
	 * @param key the key
	 * @param value the value
	 * @param newJsonDoc the new json doc
	 * @param callBack the call back
	 */
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

	/**
	 * The listener interface for receiving app42StorageService events.
	 * The class that is interested in processing a app42StorageService
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addApp42StorageServiceListener<code> method. When
	 * the app42StorageService event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see App42StorageServiceEvent
	 */
	public static interface App42StorageServiceListener 
	{
		
		/**
		 * On document inserted.
		 *
		 * @param response the response
		 */
		public void onDocumentInserted(Storage response);
	
		/**
		 * On update doc success.
		 *
		 * @param response the response
		 */
		public void onUpdateDocSuccess(Storage response);

		/**
		 * On find doc success.
		 *
		 * @param response the response
		 */
		public void onFindDocSuccess(Storage response);

		/**
		 * On insertion failed.
		 *
		 * @param ex the ex
		 */
		public void onInsertionFailed(App42Exception ex);
		
		/**
		 * On find doc failed.
		 *
		 * @param ex the ex
		 */
		public void onFindDocFailed(App42Exception ex);

		/**
		 * On update doc failed.
		 *
		 * @param ex the ex
		 */
		public void onUpdateDocFailed(App42Exception ex);
	}

	
	
	/**
	 * Save score for user.
	 *
	 * @param gameName the game name
	 * @param gameUserName the game user name
	 * @param gameScore the game score
	 * @param callBack the call back
	 */
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
	
	
	/**
	 * Gets the leader board.
	 *
	 * @param gameName the game name
	 * @param max the max
	 * @param callBack the call back
	 * @return the leader board
	 */
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
	
	/**
	 * The listener interface for receiving app42ScoreBoardService events.
	 * The class that is interested in processing a app42ScoreBoardService
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addApp42ScoreBoardServiceListener<code> method. When
	 * the app42ScoreBoardService event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see App42ScoreBoardServiceEvent
	 */
	public static interface App42ScoreBoardServiceListener 
	{
		
		/**
		 * On save score success.
		 *
		 * @param response the response
		 */
		void onSaveScoreSuccess(Game response);

		/**
		 * On save score failed.
		 *
		 * @param ex the ex
		 */
		void onSaveScoreFailed(App42Exception ex);
		
		/**
		 * On leader board success.
		 *
		 * @param response the response
		 */
		void onLeaderBoardSuccess(Game response);
        
		/**
		 * On leader board failed.
		 *
		 * @param ex the ex
		 */
		void onLeaderBoardFailed(App42Exception ex);
	}
	
	/**
	 * Upload image.
	 *
	 * @param name the name
	 * @param filePath the file path
	 * @param fileType the file type
	 * @param description the description
	 * @param callBack the call back
	 */
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
	
	
	/**
	 * Gets the image.
	 *
	 * @param fileName the file name
	 * @param callBack the call back
	 * @return the image
	 */
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
	
	
	/**
	 * The listener interface for receiving app42UploadService events.
	 * The class that is interested in processing a app42UploadService
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addApp42UploadServiceListener<code> method. When
	 * the app42UploadService event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see App42UploadServiceEvent
	 */
	public static interface App42UploadServiceListener 
	{
		
		/**
		 * On upload image success.
		 *
		 * @param response the response
		 */
		void onUploadImageSuccess(Upload response);

		/**
		 * On upload image failed.
		 *
		 * @param ex the ex
		 */
		void onUploadImageFailed(App42Exception ex);

		/**
		 * On get image success.
		 *
		 * @param response the response
		 */
		void onGetImageSuccess(Upload response);

		/**
		 * On get image failed.
		 *
		 * @param ex the ex
		 */
		void onGetImageFailed(App42Exception ex);
	}

}