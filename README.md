App42_ANDROID_SDK
=================

1. [Register] (https://apphq.shephertz.com/register) with App42 platform.
2. Create an app once you are on Quick start page after registration.
3. If you are already registered, login to [AppHQ] (http://apphq.shephertz.com/register/app42Login) console and create an app from App Manager Tab.

__Download And Set Up SDK :-__

1). [Download] (https://github.com/shephertz/App42_ANDROID_SDK/archive/master.zip) Android SDK

2). Unzip downloaded Zip file. Unzip folder contains version folder of jar and sample folder.

3). Version folder will contain App42_ANDROID_SDK_x.x.x.jar and docs.

4). Then add App42_ANDROID_SDK_x.x.x.jar in your lib folder.
 
5). Put the jar file in class path of your Android project.

__Initializing SDK :-__
You have to instantiate App42API by putting your ApiKey/SecretKey to initialize the SDK.

```
App42API.initialize("ANDROID_APPLICATION_CONTEXT","YOUR_API_KEY","YOUR_SECRET_KEY"); 
```

__Using App42 Services :-__
 you have to build target service that you want to use in your app. For example, User Service can be build with following snippet. Similarly you can build other service also with same notation.
 
```
UserService userService = App42API.buildUserService();
//Similarly you can build other services like App42API.buildXXXXService()
```


