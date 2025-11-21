I. Cài đặt Allure Commandline vào máy (ko thể dùng maven)
Set-ExecutionPolicy RemoteSigned -Scope CurrentUser
iwr -useb get.scoop.sh | iex
> Xong rồi kiểm tra 
allure --version
> Thêm vào maven
<dependency>
<groupId>io.qameta.allure</groupId>
<artifactId>allure-testng</artifactId>
<version>2.17.0</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.aspectj/aspectjweaver -->
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.9.6</version>
    <scope>runtime</scope>
</dependency>

- Copy cái naỳ vào phần plugin
  <argLine>
  -javaagent:"${settings.localRepository}/org/aspectj/aspectjweaver/1.9.6/aspectjweaver-1.9.6.jar"
  </argLine>

<plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.0.0-M5</version>
                <configuration>
                    <suiteXmlFiles>
                        <suiteXmlFile>${suiteXmlFile}</suiteXmlFile>
                    </suiteXmlFiles>
                    <argLine>
                        -javaagent:"${settings.localRepository}/org/aspectj/aspectjweaver/1.9.6/aspectjweaver-1.9.6.jar"
                    </argLine>
                </configuration>


- Sau khi run xong project thì gõ
  allure generate allure-results --clean
  allure open
> Để mở dc report allure
> Khi có lỗi mới chụp còn không có lỗi sẽ không chụp
> Còn muốn khi pass hay fail j đều chụp hết thì sẽ ko if chỗ này
>  if (result.getStatus() == ITestResult.FAILURE) {
> 
- Câu lệnh chạy xóa toàn bộ dữ liệu của allure-results để sau khi Run thì allure sẽ generate đúng report của lần chạy đó
  Remove-Item allure-results\* -Recurse -Force
- Cách thêm diễn giải Step ở allure report
  1.
  @Step("Click on login label")
  public void clickLoginLabel(){
  appiumDriver.findElement(loginLabelSel).click();
  }
  2.
@Step("Input username as {username}")
public LoginPage inputUsername(String username){
appiumDriver.findElement(usernameSel).sendKeys(username);
return this;
}

    @Step("Input username as {password}")
    public LoginPage inputPassword(String password){
        appiumDriver.findElement(passwordSel).sendKeys(password);
        return this;
    }

    @Step("Click on login btn")
    public LoginPage clickLoginBtn(){
        appiumDriver.findElement(loginBtnSel).click();
        return this;
    }

II. Test Flow

