App42_BPaaS_Android_SDK
==============

App42 BPaaS Cloud API Client SDK for ANDROID

- Download the App42 BPaaS ANDROID SDK
- Unzip the file and open **App42 ANDROID Sample** project.
- Add the **App42_BPaaS_ANDROID_SDK_x.x.x.jar** into library path. **Properties -> ANDROID Build Path -> Liabraries -> Add External JARs**
- Initialize the library using :-

```
App42API.initialize("ANDROID_ACTIVITY_CONTEXT","API_KEY","SECRET_KEY");
App42API.setBaseURL("<YOUR_API_SERVER_URL>");
```

- Instantiate the service that one wants to use in the App, e.g. using User service one has to do the following :-

```
UserService userService = App42API.buildUserService();
```

- Now one can call associated method of that service e.g. user creation can be done with the following snippet :-

```
String userName = "Nick";
String pwd = "********";
String emailId = "nick@shephertz.co.in";    
userService.createUser( userName, pwd, emailId, new App42CallBack() {
	public void onSuccess(Object response) 
	{
		User user = (User)response;
		System.out.println("userName is " + user.getUserName());
		System.out.println("emailId is " + user.getEmail());
	}
	public void onException(Exception ex) 
	{
		System.out.println("Exception Message"+ex.getMessage());
	}
});   
```

- Build the project and run.
