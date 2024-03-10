Java Enterprise Test Project 
===============================

## Поставленная задача ##

Разработать Spring Boot/JPA Enterprise на основе технологий Java: сборка Maven либо Gradle, Spring MVC, Postgresql или H2.
Минималистический UI приветствуется, но не обязателен

Разработать API для CRUD операций над отверстиями в детали.
Мебельная деталь это параллелепипед с высотой, как правило, от 4 до 50мм
Отверстия могут располагаться в любую грань детали, но только по нормали к грани (отверстия под углом к граням детали исключаем)
У отверстия есть параметры диаметр, глубина, скорость входа сверла, скорость выхода (скорость условна), координаты. 
Координаты отверстия могут задаваться через переменные, в том числе через паттерны, (L/2 + B*0.5 +H/3), 
при этом должны поддерживаться простые операции над переменными +-/*,
переменных может быть неограниченное количество, паттерн должен поддерживать редактирование

## Локальный Запуск Проекта с Использованием Docker Compose

**1. Установите Docker и Docker Compose:**
- [Руководство по Установке Docker](https://docs.docker.com/get-docker/)
- [Руководство по Установке Docker Compose](https://docs.docker.com/compose/install/)

**2. Клонируйте Репозиторий:**
   ```bash
   git clone https://github.com/Ladzislau/drillingd
   cd drillingd
   ```

**3. Запустите Docker Compose:**

   ```bash
docker compose up
   ```
Эта команда создаст и запустит контейнер postgresql в качестве БД.

**4. Запустите Spring Boot приложение:**

```bash
./gradlew bootRun
   ```

Откройте веб-браузер и перейдите по адресу http://localhost:8080/swagger-ui/index.html, чтобы получить доступ к Swagger UI, где вы можете изучить API и использовать интерактивную документацию.


**5. Документация Swagger UI:**

Откройте веб-браузер и перейдите по адресу http://localhost:8080/swagger-ui/index.html, чтобы получить доступ к Swagger UI, где вы можете изучить API и использовать интерактивную документацию.
