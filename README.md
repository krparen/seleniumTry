# Как запустить парсер

* Скачать Chrome Webdriver c https://chromedriver.storage.googleapis.com/index.html?path=80.0.3987.106/ <br>
Там zip-архив с exe-шником, exe-шник куда-нибудь распаковать из архива
* Залезть в файл ExampleController в коде и в методе *postConstruct()* в строчке
<pre><code>System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");</code></pre>
указать вторым аргументом путь к распакованному Chrome WebDriver
* Запустить Spring Boot приложение SeleniumApplication.java
* Пойти в Postman, сделать запрос по адресу http://localhost:8080/getCheapestTicket
* После этого должен запуститься браузер, в котором автоматически распарсится инфа о самом дешёвом билете из 
Москвы в Новосибирск на сегодняшний день
 

