# Használj egy alapvető Java képet
FROM openjdk:11

# Másold be a tesztkódodat a konténerbe
COPY src /app/src
COPY pom.xml /app/pom.xml
COPY testng.xml /app/testng.xml

# Állítsd be a munkakönyvtárat
WORKDIR /app

# Telepítsd a szükséges függőségeket a Maven segítségével
RUN apt-get update && apt-get install -y maven
RUN mvn clean install -DskipTests

# Futtasd a teszteket és másold ki a report fájlokat a konténerből
CMD ["sh", "-c", "mvn test && cp -r /app/rest-assured/target/surefire-reports ./reports"]

