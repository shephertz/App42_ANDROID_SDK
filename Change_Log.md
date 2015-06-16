* [Release Version 3.4](https://github.com/shephertz/App42_ANDROID_SDK/blob/master/Change_Log.md#version-34)
* [Release Version 3.3](https://github.com/shephertz/App42_ANDROID_SDK/blob/master/Change_Log.md#version-33)
* [Release Version 3.2](https://github.com/shephertz/App42_ANDROID_SDK/blob/master/Change_Log.md#version-32)
* [Release Version 3.1](https://github.com/shephertz/App42_ANDROID_SDK/blob/master/Change_Log.md#version-31)
* [Release Version 3.0.1](https://github.com/shephertz/App42_ANDROID_SDK/blob/master/Change_Log.md#version-301)
* [Release Version 2.9](https://github.com/shephertz/App42_ANDROID_SDK/blob/master/Change_Log.md#version-29)
* [Release Version 2.8.1](https://github.com/shephertz/App42_ANDROID_SDK/blob/master/Change_Log.md#version-281)
* [Release Version 2.8](https://github.com/shephertz/App42_ANDROID_SDK/blob/master/Change_Log.md#version-28)
* [Release Version 2.7](https://github.com/shephertz/App42_ANDROID_SDK/blob/master/Change_Log.md#version-27)
* [Release Version 2.6.2](https://github.com/shephertz/App42_ANDROID_SDK/blob/master/Change_Log.md#version-262)
* [Release Version 2.6.1](https://github.com/shephertz/App42_ANDROID_SDK/blob/master/Change_Log.md#version-261)
* [Release Version 2.6](https://github.com/shephertz/App42_ANDROID_SDK/blob/master/Change_Log.md#version-26)
* [Release Version 2.5](https://github.com/shephertz/App42_ANDROID_SDK/blob/master/Change_Log.md#version-25)
* [Release Version 2.4](https://github.com/shephertz/App42_ANDROID_SDK/blob/master/Change_Log.md#version-24)
* [Release Version 2.3](https://github.com/shephertz/App42_ANDROID_SDK/blob/master/Change_Log.md#version-23)
* [Release Version 2.2](https://github.com/shephertz/App42_ANDROID_SDK/blob/master/Change_Log.md#version-22)
* [Release Version 2.1](https://github.com/shephertz/App42_ANDROID_SDK/blob/master/Change_Log.md#version-21)
* [Release Version 2.0.1](https://github.com/shephertz/App42_ANDROID_SDK/blob/master/Change_Log.md#version-201)
* [Release Version 2.0](https://github.com/shephertz/App42_ANDROID_SDK/blob/master/Change_Log.md#version-20)
* [Release Version 1.9](https://github.com/shephertz/App42_ANDROID_SDK/blob/master/Change_Log.md#version-19)
* [Release Version 1.6.1](https://github.com/shephertz/App42_ANDROID_SDK/blob/master/Change_Log.md#version-161)
* [Release Version 1.6](https://github.com/shephertz/App42_ANDROID_SDK/blob/master/Change_Log.md#version-16)

## Version 3.4

**Release Date:** 02-06-2015 

**Release Version:** 3.4

**The following features have been pushed to the services :**

**PUSHNOTIFICATION SERVICE**

```
trackPush
```

**ACHIEVEMENT SERVICE**

```
deleteUserAchievement 
```

**STORAGE SERVICE**

```
incrementByDocId  
```

**ABTEST SERVICE**

```
getProfileData   
```


**This release contains the following bug fix:**

```
None
```

## Version 3.3

**Release Date:** 21-05-2015 

**Release Version:** 3.3

**The following features have been pushed to the services :**

**App42Application**

```
App42Application.getCurrentActivity();
```


**This release contains the following bug fix:**

```
None
```


## Version 3.2

**Release Date:** 07-04-2015 

**Release Version:** 3.2

**The following features have been pushed to the services :**

**EVENT SERVICE**

```
App42API.enableAppALiveTrack(enable);
```


**This release contains the following bug fix:**

```
None
```

## Version 3.1

**Release Date:** 20-01-2015

**Release Version:** 3.1

**The following SDK plugin has been pushed to the latest :**

```
Campaign plugin SDK and relevant changes in Android SDK 
```

**This release contains the following bug fix:**

```
None
```

## Version 3.0.1

**Release Date:** 05-11-2014 

**Release Version:** 3.0.1

**The following Service has been pushed to the latest :**

```
Event Service
```

**This release contains the following bug fix:**

```
None
```

## Version 2.9

**Release Date:** 23-09-2014 

**Release Version:** 2.9

**The following Service has been pushed to the latest :**

```
BravoBoard Service
```

**The following features have been pushed to the services :**

**PUSH NOTIFICATION SERVICE**

```
getAllDevicesOfUser
```

**BUDDY SERVICE**

```
deleteAllMessages
getBlockedBuddyList
```

**QUERY BUILDER**

```
setCreatedOn
setUpdatedOn
setDocumentId
```


**This release contains the following bug fix:**

```
None
```

## Version 2.8.1

**Release Date:** 22-08-2014 

**Release Version:** 2.8.1

**This release contains the following bug fix:**

```
Debug and print statements removed .
```

## Version 2.8

**Release Date:** 30-07-2014 

**Release Version:** 2.8

**The following features have been pushed to the services :**


**PUSH NOTIFICATION SERVICE**

```
deleteChannel
getChannelUsersCount
getChannelUsers
getUserSubscribedChannelsCount
getUserSubscribedChannels
```
**Avatar SERVICE**

```
updateAvatar
deleteAvatarByName
deleteAllAvatars
```

**This release contains the following bug fix:**

```
None
```
## Version 2.7

**Release Date:** 20-06-2014 

**Release Version:** 2.7

**The following Services have been pushed to the latest :**

```
Timer Service
Gift Service
```

**This release contains the following bug fix:**

```
None
```

## Version 2.6.2

**Release Date:** 2-06-2014 

**Release Version:** 2.6.2

**This release contains the following bug fix:**

```
Version incompatible 
```

## Version 2.6.1

**Release Date:** 27-04-2014 

**Release Version:** 2.6.1

**This release contains the following bug fix:**

```
Bug Fix in Multipart. Remove Unused variable.
```

## Version 2.6

**Release Date:** 22-04-2014 

**Release Version:** 2.6

**The following features have been changed :**


```
If you are upgrading from previous version of App42_ANDROID_SDK and have used getRecordCount() method on storage service, you have to change its return type as Integer instead of string, because the return type of this method is changed.

**OlD Code Snippet:
String  recordCount =storageResponse.getRecordCount();

**New Code Snippet :
Integer  recordCount =storageResponse.getRecordCount();
```


**The following features have been pushed to the services :**


**PUSH NOTIFICATION SERVICE**


```
updatePushBadgeforDevice
updatePushBadgeforUser
clearAllBadgeCount
```
**STORAGE SERVICE**

```
getCountByQuery
```


**This release contains the following bug fix:**

```
None
```


## Version 2.5

**Release Date:** 09-04-2014 

**Release Version:** 2.5

**The following features have been pushed :**

```
New NokiaX enum added in push DeviceType.
```

**The following features have been pushed to the services :**

**PUSH SERVICE**

```
storeDeviceToken with DeviceType
```

**This release contains the following bug fix:**

```
None.
```

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

**USER SERVICE**

```
getUsersByProfileData
getUsersByGroup
createUserWithProfile
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
