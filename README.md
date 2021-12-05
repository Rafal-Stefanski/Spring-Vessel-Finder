# Spring Vessel Finder App
***
##### Used:
- [X] PostgreSQL,
- [X] Hibernate,
- [ ] Docker,

- [X] Also:
    - Spring Boot 2, Java 8, Maven, Thymeleaf, Bootstrap
    - AIS API from https://wiki.barentswatch.net/
    - Map from leafletjs.com

##### Configuration:
- Requires Postgresql DataBase, configured for localhost,
- DataBase configuration in application.properties
- endpoint: http://localhost:8080

##### Comment
Wykorzystany kod źródłowy z livestream'a Przemka Bykowskiego. 
    Dodane:
    - automatyczne generowanie i pobieranie tokena do aplikacji, aby aplikacja otwierała się bez konieczności generowania tokena manualnie.
    - Ustawienie lokalizacji na Oslo 
    - Dodanie tabeli z listą zapisanych do bazy danych jednostek, widocznych na mapce.
    
Tylko tyle :) z powodu ograniczenia czasowego na wykonanie zadania. Nie starczyło już czasu na Dockera. 

Bedzie mi niezmiernie miło jeśli otrzymam nawet bardzo pobieżny feedback wykonanego zadania.

***
##### Task
Zadanie konkursowe CTM Gdynia. Wykorzystując dane pobierane z AIS utwórz aplikację, która będzie wyświetlała informacje na temat jednostek morskich na mapie.

***
##### Screenshots
![screen shot](https://github.com/Rafal-Stefanski/Spring-Vessel-Finder/blob/master/src/main/resources/static/screenshot_02.png)
![screen shot](https://github.com/Rafal-Stefanski/Spring-Vessel-Finder/blob/master/src/main/resources/static/screenshot_03.png)
![screen shot](https://github.com/Rafal-Stefanski/Spring-Vessel-Finder/blob/master/src/main/resources/static/screenshot_01.png)
