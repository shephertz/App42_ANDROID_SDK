App42_ANDROID_SDK_SAMPLE
=================

App42 Cloud API Client SDK Sample for Android

# Steps to use sample : 

1. [Register] (https://apphq.shephertz.com/register) with App42 platform.
2. Create an app once you are on Quick start page after registration.
3. If you are already registered, login to [AppHQ] (http://apphq.shephertz.com/register/app42Login) console and create an app from App Manager Tab.
4. [Download](https://github.com/shephertz/App42_ANDROID_SDK/archive/master.zip) __App42 ANDROID SDK__ and unzip it on your local machine.
5. Import the sample project (App42-Android-SampleAPITest) in your eclipse from  `...\Downloads\App42_ANDROID_SDK-master\sample\App42-Android-SampleAPITest`).
6. Sample contains __Constants.java__ file.
7. Open __Constants.java__ file and just copy the __api-Key__ and __secret-Key__ that you have recieved in step 2 or 3 from AppHq console and paste it in Constants.java file as shown below : 

```
public string apiKey  = "<YOUR_API_KEY>";
public string secretKey = "<YOUR_SECRET_KEY>";
```

8. Constants.java file contains all the constants used in scripts (e.g gamename, dbname etc.) edit the variables as your requirements.
9. Save the source code, and run on your android device or emulator.

[Documentation and API guide] (http://api.shephertz.com/app42-dev/android-backend-apis.php)
