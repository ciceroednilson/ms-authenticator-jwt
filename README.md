# ms-authenticator-jwt

## βοΈ Source

The code of project stay in source folder.

* [source/ms-authenticator-jwt](source/ms-authenticator-jwt)

## π Starting

This project have the objective to create the flow for creating user and generate JWT.

## π  Tools and Technologies

<ol>
  <li>Java</li>
  <li>Intellij</li>
  <li>Postman</li>
  <li>Mysql</li>
  <li>Mysql Workbench</li>
  <li>Gradle</li>
  <li>Docker</li>
</ol>

## π Requirements to execute project

<ol>
  <li>Java</li>
  <li>Intellij</li>
  <li>Postman</li>
  <li>Mysql</li>
  <li>Mysql Workbench</li>
  <li>Docker</li>
</ol>

Docker used to execute MYSQL.

##  π§ Links to install tools

[Java](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

[Intellij](https://www.jetbrains.com/idea/download/#section=linux)

[Postman](https://www.postman.com/downloads/)

[Mysql (Docker)](https://hub.docker.com/_/mysql)

[Mysql Workbench](https://www.mysql.com/products/workbench/)

[Docker](https://docs.docker.com/engine/install/ubuntu/)

## βοΈ Architecture of project

![diagrams](diagrams/archicture.png "archicture")

## βοΈ Create Data Base

* [scripts-data-base/1-create-data-base.sql](scripts-data-base/1-create-data-base.sql)

~~~~sql
/*DATA BASE*/
CREATE DATABASE `db_authenticator` /*!40100 DEFAULT CHARACTER SET latin1 */;
~~~~

* [scripts-data-base/2-create-table.sql](scripts-data-base/2-create-table.sql)


~~~~sql

/*TABLE*/
CREATE TABLE IF NOT EXISTS tb_user(
   `id` 	   INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   `name`      VARCHAR(250) NOT NULL,
   `email`     VARCHAR(250) NOT NULL,
   `password`  VARCHAR(250) NOT NULL,
   `enable`    CHAR(1)      NOT NULL,
   `register_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
~~~~

## π© Testing to API.

1 - Create User

~~~~shell
curl --location --request POST 'http://localhost:8080/user/register' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "CΓ­cero 2",
"email": "ciceroednilson@postman.com",
"password": "brazil@1234"
}'
~~~~

![postman](images/postman-one.png "request")


2- Generate JWT

~~~~shell
curl --location --request POST 'http://localhost:8080/authenticate' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "ciceroednilson@gmail.com",
    "password": "brazil@123"
}'
~~~~


![postman](images/postman-two.png "request")

3 - Find all users

~~~~shell
curl --location --request GET 'http://localhost:8080/user/all' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjaWNlcm9lZG5pbHNvbkBnbWFpbC5jb20iLCJpYXQiOjE2NzY3MjQ4NTEsImV4cCI6MTY3Njc0Mjg1MX0.UxPsqEGDQvr-9gBZkWAG_IkARQr4sdTkros8WVlbv65DRu2FARP8IR_vU0rHDKeF4IYwrmgYXnO8bwhL_nRWAQ' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "ciceroednilson@gmail.com",
    "password": "brazil@123"
}'
~~~~

![postman](images/postman-three.png "request")

## π Version

1.0

## βοΈ Author

CΓ­cero Machado - ciceroednilson@gmail.com
