* [Release Version 2.4](https://github.com/shephertz/App42_ANDROID_SDK/blob/master/Change_Log.md#version-24)
* [Release Version 2.3](https://github.com/shephertz/App42_ANDROID_SDK/blob/master/Change_Log.md#version-23)
* [Release Version 2.2](https://github.com/shephertz/App42_ANDROID_SDK/blob/master/Change_Log.md#version-22)
* [Release Version 2.1](https://github.com/shephertz/App42_ANDROID_SDK/blob/master/Change_Log.md#version-21)
* [Release Version 2.0.1](https://github.com/shephertz/App42_ANDROID_SDK/blob/master/Change_Log.md#version-201)
* [Release Version 2.0](https://github.com/shephertz/App42_ANDROID_SDK/blob/master/Change_Log.md#version-20)
* [Release Version 1.9](https://github.com/shephertz/App42_ANDROID_SDK/blob/master/Change_Log.md#version-19)
* [Release Version 1.6.1](https://github.com/shephertz/App42_ANDROID_SDK/blob/master/Change_Log.md#version-161)
* [Release Version 1.6](https://github.com/shephertz/App42_ANDROID_SDK/blob/master/Change_Log.md#version-16)


## Version 2.4

**Release Date:** 04-04-2014 

**Release Version:** 2.4

**The following feature has been deleted to the service :**

**USER SERVICE**

```
resetUserPassword With Credentials
```

**The following features have been pushed to the services :**

**PUSH SERVICE**

```
unsubscribeDevice
resubscribeDevice
deleteAllDevices
sendPushMessageToDevice
```

**REVIEW SERVICE**

```
getAllReviewsByUser
```

**SCOREBOARD SERVICE**

```
getUsersWithScoreRange
```


**This release contains the following bug fix:**

```
None.
```



## Version 2.3

**Release Date:** 20-03-2014 

**Release Version:** 2.3

**The following features have been changed :**

```
If you are upgrading from previous version of App42_ANDROID_SDK_2.3 and have used setQuery method on any service, you have to set App42API.setDbName instead of passing it in method parameter.

**OlD Code Snippet:
setQuery("dbName","collectionName","query");

**New Code Snippet :
App42API.setDbName("dbName");
setQuery("collectionName","query");
```

**The following features have been pushed to the services :**

**STORAGE SERVICE**

```
addOrUpdateKeys
addAttachmentToDocs
insertJSONDocument(With Attach File)
```

**User SERVICE**

```
addJSONObject(Add Extra Information while creating user)
```

**SCOREBOARD SERVICE**

```
addJSONObject(Add Extra Information of user while saves score)
```


**This release contains the following bug fix:**

```
None.
```

## Version 2.2

**Release Date:** 03-02-2014 

**Release Version:** 2.2

**The following features have been pushed :**

```
Meta info in UserService (getUser,getUsersByRole,getUserByEmailId) and ScoreBoardService(getTopNRankers,getTopNTargetRankers).
```

**The following features have been pushed to the services :**

**PUSH SERVICE**

```
sendMessageToInActiveUsers
scheduleMessageToUser
```

**STORAGE SERVICE**

```
updateDocumentByQuery
```

**REVIEW SERVICE**

```
deleteReviewByReviewId
deleteCommentByCommentId
```

**BUDDY SERVICE**

```
unFriend
deleteMessageById
deleteMessageByIds
```

**SCOREBOARD SERVICE**

```
getTopNTargetRankers
```

**GALLERY SERVICE**

```
updatePhoto
```

**This release contains the following bug fix:**

```
None.
```

## Version 2.1

**Release Date:** 09-01-2014 

**Release Version:** 2.1

**The following features have been pushed :**

```
None
```

**The following features have been pushed to the services :**

**AVATAR SERVICE**

```
createAvatar(With InputStream)
```
**This release contains the following bug fix:**

```
Crash report bug fix.
```

## Version 2.0.1

**Release Date:** 24-12-2013

**Release Version:** 2.0.1

**The following features have been pushed :**

```
None
```

**The following features have been pushed to the services :**

```
None
```
**This release contains the following bug fix:**

```
Crash report bug fix.
```

## Version 2.0

**Release Date:** 12-12-2013

**Release Version:** 2.0

**The following features have been pushed :**

```
Caching and Offline Storage .
```

**The following features have been pushed to the services :**

**SCOREBOARD SERVICE**

```
getTopNRankersFromFacebook (With date range)
```

**This release contains the following bug fix:**

```
None
```

## Version 1.9

**Release Date:** 25-11-2013

**Release Version:** 1.9

**The following features have been pushed :**

```
Set logged in user
Log uncaught exception
```

**The following features have been pushed to the services :**

**PUSH SERVICE**

```
sendPushToTargetUsers
```

**STORAGE SERVICE**

```
saveOrUpdateDocumentByKeyValue
```

**This release contains the following bug fix:**

```
None
```


## Version 1.6.1

**Release Date:** 7-08-2013

**Release Version:** 1.6.1

**The following feature have been pushed to the latest :**

```
None
```

**This release contains the following bug fix:**

```
deleteDocumentByKeyValue
```

## Version 1.6

**Release Date:** 05-08-2013

**Release Version:** 1.6

**The following feature have been pushed to the latest :**



**PUSHNOTIFICATION SERVICE**
```
unsubscribeDeviceToChannel
```

**UPLOAD SERVICE**

```
UploadFileForFriend
UploadFileForFriends
UploadFileForGroup
````


**BUDDY SERVICE**

```
sendMessageToGroup
sendMessageToFriend
sendMessageToFriends
getAllMessages
getAllMessagesFromBuddy
getAllMessagesFromGroup
```

**This release contains the following bug fix:**

```
None
```
