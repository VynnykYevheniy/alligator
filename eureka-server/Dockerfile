# Используйте официальный образ Maven, который уже содержит JDK
FROM maven:3.8-openjdk-17-slim

# Установите рабочую директорию внутри контейнера
WORKDIR /alligator

# Скопируйте файл pom.xml из родительской директории
COPY ../pom.xml /alligator/pom.xml

# Установите рабочую директорию внутри контейнера
WORKDIR /alligator/eureka-server/

# Скопируйте родительский pom.xml в рабочую директор ию
COPY ../eureka-server /alligator/eureka-server/

# Соберите JAR-файл внутри контейнера
RUN mvn clean package

# Команда для запуска Eureka Server
CMD ["java", "-jar", "target/eureka-server-0.1.jar"]