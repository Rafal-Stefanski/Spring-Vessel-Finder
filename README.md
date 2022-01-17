# Spring Vessel Finder App

<img align="right" src="https://badges.pufler.dev/visits/Rafal-Stefanski/Spring-Vessel-Finder">

#### Used:
- [X] PostgreSQL,
- [X] Hibernate,

- [X] Also:
    - Spring Boot 2, Java 8, Maven, Thymeleaf, Bootstrap
    - AIS API from https://wiki.barentswatch.net/
    - Map from leafletjs.com

#### Configuration:
- Requires Postgresql DataBase, configured for localhost,
- DataBase configuration in application.properties
- endpoint: http://localhost:8080

#### Task / Description
Zadanie konkursowe CTM Gdynia. Wykorzystując dane pobierane z AIS utwórz aplikację, która będzie wyświetlała informacje na temat jednostek morskich na mapie.

#### In progress, so far added:
- automatyczne generowanie i pobieranie tokena do aplikacji, aby aplikacja otwierała się bez konieczności generowania tokena manualnie.
- Ustawienie lokalizacji na Oslo
- Dodanie tabeli z listą zapisanych do bazy danych jednostek, widocznych na mapce.
    
***
#### Screenshots
![screen shot](https://github.com/Rafal-Stefanski/Spring-Vessel-Finder/blob/master/src/main/resources/static/screenshot_02.png)
![screen shot](https://github.com/Rafal-Stefanski/Spring-Vessel-Finder/blob/master/src/main/resources/static/screenshot_03.png)
![screen shot](https://github.com/Rafal-Stefanski/Spring-Vessel-Finder/blob/master/src/main/resources/static/screenshot_01.png)
