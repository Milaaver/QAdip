# Курсовой проект по модулю «Автоматизация тестирования» для профессии «Инженер по тестированию»

Курсовой проект представляет собой автоматизацию тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.

### Документы проекта

* [План автоматизации](docs/Plan.MD)
* [Отчет о тестировании веб-сервиса для приобретения путевок](docs/Report.md)
* [Что было запланировано и что было сделано](docs/Summary.md)

## Начало работы

Выполнить `git clone` проекта на локальный компьютер.

### Prerequisites

На локальном компьютере должен быть установлен Git, Java, Docker.

### Установка и запуск

1.	Запустить Docker
2.	Выполнить docker-compose.yml в папке проекта `docker-compose up -d`
3. Тестирование с использованием СУБД MySQL:        
3.1. Запустить SUT, выполнить `java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app_db -jar artifacts/aqa-shop.jar`  
3.2. В *nix системах запуск тестов `./gradlew clean test -Dselenide.headless=true -Ddatasource.url=jdbc:mysql://localhost:3306/app_db`  
    В Windows запуск тестов`gradlew clean test -Dselenide.headless=true -Ddatasource.url=jdbc:mysql://localhost:3306/app_db`
4.  Тестирование с использованием СУБД PostgresSQL:  
4.1. Запустить SUT, выполнить `java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app_db -jar artifacts/aqa-shop.jar`  
4.2. В *nix системах запуск `./gradlew clean test -Dselenide.headless=true -Ddatasource.url=jdbc:postgresql://localhost:5432/app_db`  
    В Windows запуск `gradlew clean test -Dselenide.headless=true -Ddatasource.url=jdbc:postgresql://localhost:5432/app_db`
5.  Для генерации отчета после прохождения выполнить `gradlew allureReport`. Лог выполнения команды покажет,
    где расположен отчет: `..\build\reports\allure-report\allureReport\index.html`
6. Остановить SUT `CTRL+C`
7. Остановить БД `docker-compose stop`, выполнить `docker-compose down`

## Лицензия

GNU General Public License