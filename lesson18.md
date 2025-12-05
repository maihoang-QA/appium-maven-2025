- mvn clean install
+ Remove build cũ ra trước khi fetch build mới về
+ Install là nó sẽ fetch dependencies nếu chưa có rồi nó sẽ đi build dự án 
- Maven repository type:
+ Có 2 loại:
+ Local Repository (nằm ở folder .m2 folder ở trên máy tính mà mình đang chạy project đó)
+ Remote Repositoies : Intenal Repository và Central Repository
+ Central Repository : Nơi chứa những file .jar theo đường dẫn https://repo.maven.apache.org/maven2/
+ Internal Repository: Là cái maven repository do công ty mình dựng lên (hay dùng nexus maven repository)
+ Follow maven khi fetch dependencies như nào:
+ Khi mà đưa thông tin của project vào trong file pom, nó pải check xem local đã có chưa? nếu có rồi
sẽ lấy từ local, chưa có sẽ lấy từ remote repositories

2. POM (Page Object Modle)
- Là cái cách mình trừu tượng hóa lên 1 page, 1 screen hoặc 1 phần của screen thành 1 class trong
lập trình hướng đối tượng
- Mục đích: Re-use (tính dùng lại). Nếu ko có nó thì nếu có thay đổi  DOM tree thì mình pải đi thay đổi code ở rất nhiều
nơi khác nhau
- 1 Object dc sinh ra sẽ có 1 behavior và 1 state (UI)

3. Framework testing 
- Giúp manage việc test: JUNIT và TESTNG
- Để chạy dc TestNG vs project cần 1 cái là Maven Surefire Plugin (run vs maven command )
- Copy plugin theo https://maven.apache.org/surefire/maven-surefire-plugin/examples/testng.html
  <plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-surefire-plugin</artifactId>
  <version>3.5.4</version>
  <configuration>
  <suiteXmlFiles>
  <suiteXmlFile>testng.xml</suiteXmlFile>
  </suiteXmlFiles>
  </configuration>
  </plugin>
- Before Method: Method chạy trước khi tất cả các method còn lai chạy
- 2 Class đều có Before Test thì trc khi chạy method trong 2 class thì n sẽ chaỵ hết
tất cả Before Test > sau đó n mới chạy đến method trong 2 class
- Befor Class là khi nào n chạy Class đó mới chạy
- After Class xong chạy đến After Test. Nhưng After Test sẽ ko chạy cùng lúc như Before Test
vì sẽ có thằng chạy xong sớm chạy xong muôn (After Class 1 > After test 1 rồi đến After Class 2 > After test 2)
- Before Suite: Chạy trc khi Suite dc thực thi
- After suite: Chaỵ sau khi suite dc thực thi
- Command to run: mvn clean test (nếu ko clean n sẽ ko run những cái mới mà mình tạo ra)
  mvn clean test "-DsuiteXmlFile=src/main/resources/test-suites/Regression.xml"
- Mong muốn verify dc tất cả các assert trong màn hình mà ko pải kiểu sai 1 assert thì chương trình sẽ
dừng lại > Dùng Soft Assert từ TestNG
- Lưu ý: Khi dùng SoftAssert thì đêns cuối pải dùng softAssert.assertAll() > nếu ko 
dù có sai cũng thành pass vì n sẽ ko verify cái assert nào cả
  SoftAssert softAssert = new SoftAssert();

                String customErrMsg = "[ERR] Login msg title incorrect";
                softAssert.assertTrue(isTitleCorrect, customErrMsg + " | assertEquals");
                softAssert.assertEquals(actualLoginMsg, "success", "[ERR] Login msg tittle incorrect!");
                softAssert.assertAll();
- Còn nếu chỉ dùng assert thông thường thì fail 1 cái n sẽ dừng lại > dùng để test
tính năng quan trọng mà k cần quan tâm các case sau
  assert.assertEquals(actualLoginMsg, "success", "[ERR] Login msg tittle incorrect!");

- TestNG chạy test method theo thứ tự của bảng chữ cái
- Muốn chạy caí nào đầu tiên thì đặt Priority = 1
@Test(priority = 1) (priority có giá trị default = 0, tức là ko để j chỉ có @Test thôi)
Nó sẽ chạy ưu tiên 1 trước
Còn nếu bỏ hết priority thì nó sẽ lại chạy theo bảng chữ cái
- Dùng depend 
@Test(priority = 1, dependsOnMethods = {"a2Void"}) > Tức là a2Void pass n mới chạy thằng này
còn a2Void fail nó sẽ ko chạy thằng a1void
- Nếu ko mún cái test nào chạy thì dùng @Ignore
- DependON sẽ ưu tiên hơn priority vì dù có để priority bao nhiêu mà đã có DependON thì nó sẽ chạy cái
method DependON trước, pass ms chạy tiếp fail thì dừng chứ ko quan tâm đến priority