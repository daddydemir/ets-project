# ets-project


> Bu proje kapsamında **Uçak Bileti** ve **Otel Rezervasyonu** işlemleri yapılabilmektedir.


Uçuş seferlerini ve Otelleri listeleyebilir ancak rezervasyon ve satın alma işlemleri için oturum açma zorunluluğu vardır. Bu sayede daha sonra rezervasyon ve satın alma işlemlerinin iptal edilmesi mümkündür. Her oturum açma işlemi kayıt altına alınmaktadır ve kullanıcılar bu geçmişi görüntüleyebilirler.  Kullanıcılar isteğe göre profil resmini güncelleyebilmektedir. 

---

Projenin daha iyi anlaşılabilmesi açısından temel işlemlerin yapılabildiği bir frontend uygulaması geliştirdim. Bu uygulama ile uçuş seferlerini ve otelleri listeleyebilir ve satın alma işlemi gerçekleştirilebilir. Son derste anlatılan **Rest Templete** konusunu araştırdım ve bunu projemde uygulamak adına profil resmini güncelleme seçeneği ekledim. Ücretsiz bir  depolama hizmeti sunan Cloudinary servisini kullanarak bu işlemi yaptım. Frontend tarafında adres girilirken kullanmak için Elasticsearch kullandım fakat frontend tarafında bunu uygulayamadım. Authentication için Jwt kullandım. Veri tabanı için kendi mysql sunucumu kullandım. Frontend uygulamasını Angular ile yazdım.  Endpointler için Swagger ile dökümantasyon oluşturdum, derste genelde **Postman** üzerinden konuşuldu ancak ben open source alternatifi olan **Insomnia** 'yı kullanarak endpointleri test ettim.

- [x] Rest Templete
- [x] MySql
- [x] Docker (ElasticSearch)
- [x] JWT Token
- [x] Swagger
- [x] Frontend (Angular)

---
### Relational Dabatase Diagrams
![ER1](https://user-images.githubusercontent.com/42716195/196917981-09ecfa63-41e2-4635-a44b-33984caa6251.png)

---

### OUTPUTS


![swagger](https://user-images.githubusercontent.com/42716195/196918250-f0092bd6-feae-4869-98d1-6a10352e64db.png)

![otel_reservation](https://user-images.githubusercontent.com/42716195/196918358-03ca3095-1c9e-49ad-817b-2aa16f2d4086.png)

![plane-ticket](https://user-images.githubusercontent.com/42716195/196918399-16349430-f07a-44d7-8a1d-71c578b87a85.png)

![reservation-delete](https://user-images.githubusercontent.com/42716195/196918502-cb70fa2c-7c14-4839-a367-4faa2eb12fb0.png)

![ticket-delete](https://user-images.githubusercontent.com/42716195/196918563-0bb66230-0d98-4597-b6f0-675e3791abe5.png)

![session-history](https://user-images.githubusercontent.com/42716195/196918593-adeef6fb-a73a-412b-850c-283ed06e2938.png)

![update-photo](https://user-images.githubusercontent.com/42716195/196918620-7b2c5b9e-5094-4e23-b564-2153f301b755.png)

--- 
## POSTMAN ALTERNATIVE
![insomnia](https://user-images.githubusercontent.com/42716195/196919702-2a78ca9e-c548-474a-a13d-c55b0ba1d87c.png)
