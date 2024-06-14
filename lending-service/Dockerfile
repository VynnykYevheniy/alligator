# Используйте официальный образ Maven, который уже содержит JDK
FROM maven:3.8-openjdk-17-slim

# Установка переменных среды
ENV DATABASE_URL=jdbc:postgresql://dpg-cnpg4fn79t8c73b8bf7g-a.ohio-postgres.render.com/alligatordb
ENV DATABASE_USERNAME=root
ENV DATABASE_PASSWORD=YZdaB7sN0KXgFZWmW11L1JYpVqbMeTZV

# Установите рабочую директорию внутри контейнера
WORKDIR /alligator

# Скопируйте файл pom.xml из родительской директории
COPY ../pom.xml /alligator/pom.xml

# Установите рабочую директорию внутри контейнера
WORKDIR /alligator/lending-service

# Копируйте исходный код приложения в контейнер
COPY ../lending-service /alligator/lending-service/

# Соберите JAR-файл внутри контейнера
RUN mvn clean package

# Команда для запуска приложения (замените "alligator-api-1.0.jar" на имя вашего JAR-файла)
CMD ["java", "-jar", "target/lending-service-0.1.jar"]
