package com.app42.android.sample;

/*
 * This class contains all constants variables that require in application.
 */
public class Constants {
	
	/*
	 * For getting API_KEY & SECRET_KEY, just login or register to AppHQ Console (http://apphq.shephertz.com/). 
	 */
	 static final String App42ApiKey = "YOUR_API_KEY";  // API_KEY received From AppHQ console, When You create your first app in AppHQ Console.
	 static final String App42ApiSecret = "YOUR_SECRET_KEY"; // SECRET_KEY received From AppHQ console, When You create your first app in AppHQ Console.
	 
	 /*
	  * For creating Database from AppHQ console, just go to (Technical Service Manager -> Storage Service -> click "Add DB".)
	  */
	 static final String App42DBName = "APP42_ANDROID";  // Change it as your requirement. (Note that, only one DataBase can be created through same API_KEY & SECRET_KEY);
	
	 static final String CollectionName = "AndroidSample"; // Change it as your requirement. 
	
	 /*
	  * For Creating Game, just go to (Business Service Manager -> Game Service -> select Game -> click "Add Game".)
	  */
	 static final String App42GameName = "GameName"; // Change it as your requirement. (You have to create game through AppHQ console.);
	 
	 static final String IntentUserName = "intentUserName";
	 static final String App42AndroidPref="App42AndroidPreferences";
	 static final String UserName = "TestUser";
}