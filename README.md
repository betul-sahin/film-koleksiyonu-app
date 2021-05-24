# Film Koleksiyonu App

Projeyi çalıştırmak için aşağıdaki adımları takip edin.
#### Adım 1 
Repositoryi bilgisayarınıza indirin.  

git clone https://github.com/betul-sahin/film-koleksiyonu-app.git

#### Adım 2
İndirdiğiniz projenin ana dizinindeyken terminalden aşağıdaki komutları sırasıyla çalıştırın.   
docker-compose -f docker-compose.yml up -d  
docker-compose up
  
#### Adım 3
Projeyi intelij Idea'da açıp data source ayarlarından postgreSql e bağlanıp. "movieappdb" adında veritabanı oluşturun ve bu db ye connection oluşturup bağlanın.  
kullanıcı adı:postgres   
şifre: mypassword   


#### Adım 4
Uygulama http://localhost:8081 de çalışacaktır.
Uygulamada tanımlı 2 kullanıcı(ahmet, ayse) ve 2 rol(User, Admın) bulunuyor.  
Admin tüm yetkilere sahipken User olanlar kayıt silme işlemi yapamıyorlar. 
Kayıt silmek istediğinizde logout olup Admin kullanıcısıyla login olmanız gerekiyor.  

Admin:    
kullanıcı adı: ayse   
şifre: password   

User:
kullanıcı adı: ahmet  
şifre: password   
