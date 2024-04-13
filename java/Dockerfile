FROM openjdk:latest

COPY . /app/
WORKDIR /app
ADD https://jdbc.postgresql.org/download/postgresql-42.7.3.jar /app/postgresql.jar

RUN javac src/*.java

CMD ["java", "-cp", "src/:postgresql.jar", "Main"]