# Используйте официальный образ Maven, который уже содержит JDK
FROM maven:3.8-openjdk-17-slim

# Установите рабочую директорию внутри контейнера
WORKDIR /alligator

# Скопируйте файл pom.xml из родительской директории
COPY ../pom.xml /alligator/pom.xml

# Установите рабочую директорию внутри контейнера
WORKDIR /alligator/api-gateway/

# Копируйте исходный код приложения в контейнер
COPY ../api-gateway /alligator/api-gateway/

# Соберите JAR-файл внутри контейнера
RUN mvn clean package

# Команда для запуска API Gateway
CMD ["java", "-jar", "target/api-gateway-0.1.jar"]