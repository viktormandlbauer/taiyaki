# Taiyaki Web Application

## Requirements
* Docker
    * [Get Docker](https://docs.docker.com/get-docker/)

## Already installed dependecies
* springdoc-openapi
  * /api (API json)
  * /swagger.html (Swagger API UI)

## Setup
### Set up Docker
Build Docker container
```shell
docker compose build
```
Start Docker container (with build)
```shell
docker compose up
docker compose up --build
```
Stop Docker container
```shell
docker compose stop
```
Remove Docker container
```shell
docker compose down
```

### Create and embed .ENV-file to the project
```shell
DB_URL=jdbc:mysql://localhost:3306/taiyaki_database
DB_USERNAME=taiyaki_user
DB_PASSWORD=taiyaki_password
JWT_SECRET=your_jwt_secret_key
```
