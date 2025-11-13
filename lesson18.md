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

