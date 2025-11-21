I. Data Driven
1. Cách 1
- TestNG cung cấp 1 kỹ thuật gọi là Data Provider để có thể apply dc vs Data Driven
-     @Test(dataProvider = "loginCredsData")
-     public void loginWithCorrectCreds(String username, String password) {

//Fill login Forms
loginPage.inputUsername(username)
.inputPassword(password)
.clickLoginBtn();
@DataProvider
public  Object[][] loginCredsData(){
return new Object[][]{
{"mai@gmail.com", "12345678"},
{"Anne", "12345678"},
};
> Chạy method vs 2 bộ data khác nhau

2. Cách 2
- Ko muốn truyền String username, String password vào loginWithCorrectCreds() mà sẽ truyền
userCreds > Sau đó từ userCreds ms get ra user và password
> Giữ thông tin userCreds ở trong 1 file Json
> Sẽ có Lib của gg là Gson > Có chức năng chuyển từ 1 json format sang 1 object data

II. Base Test Class
- Manage được các appium session (khởi tạo và đóng dc tất cả các appium session trong quá trình test)
- Manage dc chuyện tất cả các session need to be isolated
- Dùng Collections.synchronizedList 


