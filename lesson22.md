Parallel Test Mobile
Cách 1: 
Là chạy cùng 1 appium server nhưng vs các thread khác nhau > Chậm hơn khi dùng selenium grid
- Chạy test trên nhiều device cùng lúc > Để tiết kiệm thời gian
- Start 2 emulator lên
- <suite name="Regression" parallel="tests" thread-count="4"s
> > thread count : max cùng lúc có 4 cái devices cùng chạy;
> Làm độc lập 1 mình thì dc nhưng nếu làm cùng team sẽ ko dc vì chỉ có 1 appium server > request của ng khác gửi
lên nó sẽ k detech đúng là đang có resource naò đang test> Ko có cơ chế block lại resource đang chạy giống như selenium grid (tức là khi nó
make dc 1 cái node đang chạy nó sẽ báo đang có resource đó chạy > thì nếu có 1 ông gửi lên selenium grid yêu cầu resource 
y chang cái testscript mình đang chạy > thì selenium grid báo bận rồi chờ thằng kia xong đi > sẽ ko bị conflig. Và khi đã
có resource đang kết nối tới node 1 (appium server 1) nếu có tiếp resource yêu cầu kết nối nữa nó sẽ connect tới node 2)
> sẽ ko match cùng 1 thằng node
> 
Cách 2: Dùng Selenium grid
- Gồm có 1 cái hub và nhiều cái node
- Lên https://www.selenium.dev/ > Kéo xuống Previous Releases > Chọn Selenium 3.141.59 > Chọn selenium-server-standalone-3.141.59.jar
và download về
- NOTE: By default thì khi Appium Client Libraries send lên 1 desiredCapabilities tới thằng Hub(selenium grid)
Thì tất cả các thông số nó sẽ match dc hết chỉ có 1 thông số nó k match dc là udid > nên mình pải xây dựng 1 cái custom matcher
- Download Custom matcher: lên https://github.com/TuHuynhVan/custom-matcher-selenium-grid-with-appium/tree/master/3.149.build
- > Sau khi down 2 file đó về thì sẽ start hub lên
  > Tạo 1 folder trong project là selenium_grid: Copy 2 file vừa down về vào đây và taọ 1 file json tên hubConfig.json
copy vào
{
  "host": "192.168.1.202",
  "port": 4444,
  "newSessionWaitTimeout": 60,
  "servlets": [],
  "withoutServlets": [],
  "custom": {},
  "capabilityMatcher": "com.sdetpro.appium.grid.matcher.CustomCapabilityMatcher",
  "registry": "org.openqa.grid.internal.DefaultGridRegistry",
  "throwOnCapabilityNotPresent": true,
  "cleanUpCycle": 5000,
  "role": "hub",
  "debug": false,
  "browserTimeout": 0,
  "timeout": 90,
  "sessionTimeout": 90
  }
- Cách start hub
+ Vào thư mục selenium_grid của project mở terminate > nhập câu lệnh
java -cp "selenium-server-standalone-3.141.59.jar;selenium-grid-custom-matcher-3.141.59.jar" org.openqa.grid.selenium.GridLauncherV3 -role hub -hubConfig hubConfig.json
+ Lên browser vào địa chỉ ip:4444 ví dụ http://172.31.224.1:4444/ > Click sang Console > Ko thấy j tức là
chưa có cái node nào đăng ký lên hub hết
+ Câu lệnh đăng ký node thứ 1 lên hub
appium -p 6000 --nodeconfig node_6000_config.json 
> Tức là appium khởi động trên port 6000 và nó lấy config là cái này node_6000_config.json
> Sau đó lên hub reset lại > Vào chỗ configuration sẽ thấy node đã dc đăng ký đúng vs cái udid
+ Câu lệnh đăng ký node thứ 2 lên hub
  appium -p 7000 --nodeconfig node_7000_config.json
> Sau đó reset lại Hub sẽ thấy node thứ 2 được đăng ký
+ Tiếp theo vào DriverFactoryEx đổi chỗ
  URL appiumServerPath = new URL("http://localhost:4723/wd/hub");
thành:
  URL appiumServerPath = new URL("http://172.31.224.1:4444/wd/hub");
+ Cách chạy grid bằng mvn:
  mvn clean test "-Dsurefire.suiteXmlFiles=src/main/resources/test-suites/Parallel.xml" "-Dhub=http://172.31.224.1"
  mvn clean test "-Dsurefire.suiteXmlFiles=src/main/resources/test-suites/Parallel.xml" "-Dhub=http://172.31.224.1:4444"

appium -p 7000 --nodeconfig node_7000_config.json
