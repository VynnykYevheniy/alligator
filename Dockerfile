# Используйте официальный образ Maven, который уже содержит JDK
FROM maven:3.8-openjdk-17

# Установите рабочую директорию внутри контейнера
WORKDIR /alligator

# Копируйте исходный код приложения в контейнер
COPY . /alligator/

# Соберите JAR-файл внутри контейнера
RUN mvn clean package

# Команда для запуска приложения (замените "alligator-api-1.0.jar" на имя вашего JAR-файла)
CMD ["java", "-jar", "target/alligator-0.1.jar"]
