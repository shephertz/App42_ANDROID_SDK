App42_ANDROID_SDK
=================

1. [Register] (https://apphq.shephertz.com/register) with App42 platform.
2. Create an app once you are on Quick start page after registration.
3. If you are already registered, login to [AppHQ] (http://apphq.shephertz.com/register/app42Login) console and create an app from App Manager Tab.

__Download And Set Up SDK :-__

1). [Download] (https://github.com/shephertz/App42_ANDROID_SDK/archive/master.zip) Android SDK

2). Unzip downloaded Zip file. Unzip folder contains version folder of jar and sample folder.

3). Click on Campaign API, It conains the library project of App42 MA SDK.

4)  Add library project based on your IDE e.g Eclipse or Android Studio.
 
5). Configure your project based on IDE.

__Initializing SDK :-__
You have to instantiate App42API by putting your ApiKey/SecretKey to initialize the SDK. Create a project application class and Extends it by App42Application.Add the same name in Android Manifest.xml file.This is mandatory if you want to track AppSession Automatically. for e.g.

```
public class MyApp extends App42Application {
	
	/* (non-Javadoc)
	 * @see com.shephertz.app42.paas.sdk.android.app.App42Application#onCreate()
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		 App42API.initialize(
		   		 this,
		   		 "Api Key",
		   		 "Secret Key");
		   		App42Log.setDebug(true);
		   		String userId=Settings.Secure.getString(
					getContentResolver(), "android_id");
		   		//Can change userId here
		   		App42API.setLoggedInUser(userId);
		   		App42CampaignAPI.initWithListener(new InAppListener(), 5);
		   		App42API.enableAppStateEventTracking(true);	
	}
	}
```

```
// Add same in AndroidManifest.xml file.
  <application
        android:name="com.shephertz.app42.ma.sample.MyApp"
        android:icon="@drawable/ic_launcher"
        android:label="@string/app_name" >
	
```

__UI Customization:-__ You can easily customize you inApp UI by making changes in layouts available in res folder or library project or by making changes in UI classes of
"com.shephertz.app42.iam.ui" package of library project.

__App Virality Integration__:- Add following Activity and Receiver in AndroidManifest.xml file to use AppVirality feature.

```
 <activity
            android:name="com.shephertz.app42.iam.ui.ViralityActivity"
            android:label="@string/app_name"
            android:screenOrientation="portrait" >
        </activity>

        <receiver
            android:name="com.shephertz.app42.virality.App42ViralityReceiver"
            android:exported="true" >
            <intent-filter>
                <action android:name="com.android.vending.INSTALL_REFERRER" />
            </intent-filter>
        </receiver>
```
 
__Invoke following code__

Below code snippet required to show the share button inside the application

```
if (App42CampaignAPI.isViralityAvailable()) {
    // Show share button
}
```
if user clicks on share button, call the below function 

```
Intent intent = new Intent(this, ViralityActivity.class);
    startActivity(intent);
```




