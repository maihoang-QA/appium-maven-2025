- Start emulator with default setting
emulator -avd device_1 -wipe-data
Testcase
- Home Page:
+ HomePage_001: Make sure "App purpose" displayed
+ HomePage_002: Make sure "Support" channel displayed

- Login:
+ Login_001: Make sure "missing email and password" displayed
+ Login_002: Make sure "missing email" displayed
+ Login_003: Make sure "missing password" displayed
+ Login_004: User can login with correct creds

- Form
+ Form_001: what user input can be displayed
+ Form_002: user can switch on/off and text displayed
+ Form_003: user can switch on/off and text displayed
+  Form_004: user can selected dropdown webdriverio/appium/this app is awesome
+ Form_005: Active/inactive button works properly

-Swipe
+ Swipe_001:User can swipe and texts are displayed  correctly
+ Swipe_002: Swipe vertically to see the icon at the end (check app có đúng 6 cai icon vs nội dung đúng ko)

- Webview
+ Make sure the menu text and hyperlink displayed correctly (Check caí text menu hyplik hiển thị đung sau
đó switch về nativeapp)

- Mục đích build jar file là để đóng gói project qua máy nào có java và appium là có thể chạy lun project r
- 