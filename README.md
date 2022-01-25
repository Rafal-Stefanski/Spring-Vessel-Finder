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

Application built as contest project. Using data received from external AIS API create an App showing location and information about ships in given area.

#### In progress, so far added:
- Automaticly generated token to receive information from external API automaticly.
- Added Oslo location.
- Added table view of ships data saved to database, appearing on the map.
    
***
#### Screenshots
![screen shot](https://github.com/Rafal-Stefanski/Spring-Vessel-Finder/blob/master/src/main/resources/static/screenshot_02.png)
![screen shot](https://github.com/Rafal-Stefanski/Spring-Vessel-Finder/blob/master/src/main/resources/static/screenshot_03.png)
![screen shot](https://github.com/Rafal-Stefanski/Spring-Vessel-Finder/blob/master/src/main/resources/static/screenshot_01.png)
