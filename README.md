## The voting system to decide where to lunch

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/df7dfc76c727400497f8a2e05482deb7)](https://www.codacy.com/app/petrovaleOrganization/lunch?utm_source=github.com&utm_medium=referral&utm_content=petrovale/lunch&utm_campaign=badger)
[![Build Status](https://travis-ci.org/petrovale/lunch.svg?branch=master)](https://travis-ci.org/petrovale/lunch)

Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.

The task is:

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.

As a result, provide a link to github repository. It should contain the code, README.md with API documentation and couple curl commands to test it.

# Building and Running the voting system

Setting database postgres
* database.url=jdbc:postgresql://localhost:5432/lunch
* database.username=user
* database.password=password

Set the following user environment variables
* LUNCH_ROOT : enter your application installation path


# REST API

Documentation for REST

# Restaurant Requests

## Get all the restaurants with Lunches by date
##### Request:
```
HTTP/1.1
GET /lunch/rest/profile/restaurants/lunches/by-date?date=2017-05-30
Host: localhost:8080
Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=
```
##### Returns:
```
HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8
Date: Tue, 27 Jun 2017 07:53:26 GMT
[
      {
      "id": 100002,
      "name": "Black Thai",
      "registered": "2017-06-27T19:37:27.933+0000",
      "meals": [
                  {"id": 100005, "date": "2017-05-30", "description": "борщ", "restaurantId": 100002, "price": 500},
                  {"id": 100007, "date": "2017-05-30", "description": "котлета кур", "restaurantId": 100002, "price": 700},
                  {"id": 100006, "date": "2017-05-30", "description": "каша гречневая", "restaurantId": 100002, "price": 300}
      ]
   },
      {
      "id": 100003,
      "name": "White Rabbit",
      "registered": "2017-06-27T19:37:27.936+0000",
      "meals": [
                  {"id": 100008, "date": "2017-05-30", "description": "омлет со спаржей", "restaurantId": 100003, "price": 500},
                  {"id": 100009, "date": "2017-05-30", "description": "салат по сезону", "restaurantId": 100003, "price": 100},
                  {"id": 100010, "date": "2017-05-30", "description": "каша молочная", "restaurantId": 100003, "price": 500}
      ]
   },
      {
      "id": 100004,
      "name": "Уголек",
      "registered": "2017-06-27T19:37:27.937+0000",
      "meals": [
                  {"id": 100013, "date": "2017-05-30", "description": "суп -харчо", "restaurantId": 100004, "price": 300},
                  {"id": 100011, "date": "2017-05-30", "description": "суп фасолевый", "restaurantId": 100004, "price": 200}
      ]
   }
]
```
##### Error:
```
HTTP/1.1 500 
Content-Type: application/json;charset=UTF-8
{
   "url": "http://localhost:8080/lunch/rest/restaurants/lunches/by-date",
   "cause": "MissingServletRequestParameterException",
   "details": ["Required LocalDate parameter 'date' is not present"]
}
```

## Get all the restaurants with votes by date

##### Request:

```
HTTP/1.1
GET /lunch/rest/restaurants/votes/by-date?date=2017-06-18
Host: localhost:8080
Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=
```
##### Returns:
```
HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Sat, 20 Jun 2017 13:09:33 GMT
[
      {
      "id": 100003,
      "name": "White Rabbit",
      "vote": 1,
      "registered": "2017-06-20T14:48:04.918+0000"
   },
      {
      "id": 100002,
      "name": "Black Thai",
      "vote": 0,
      "registered": "2017-06-20T14:48:04.916+0000"
   },
      {
      "id": 100004,
      "name": "Уголек",
      "vote": 1,
      "registered": "2017-06-20T14:48:04.919+0000"
   }
]
```

##### Error:
```
HTTP/1.1 500 
Content-Type: application/json;charset=UTF-8
{
"url":"http://localhost:8080/lunch/rest/profile/restaurants/votes/by-date",
"cause":"MissingServletRequestParameterException",
"details":["Required Date parameter 'date' is not present"]
}
```

## Get all the restaurants

##### Request:

```
GET /lunch/rest/restaurants
Host: localhost:8080
Auth: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=
```

##### Returns:

```
HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Thu, 15 Jun 2017 19:36:07 GMT

[
      {
      "id": 100002,
      "name": "Black Thai",
   },
      {
      "id": 100003,
      "name": "White Rabbit",
   },
      {
      "id": 100004,
      "name": "Уголек",
   }
]
```

## Get restaurant
Request:
```
HTTP/1.1
GET /lunch/rest/restaurants/100003
Host: localhost:8080
Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=
```
Returns:
```
HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Tue, 20 Jun 2017 17:43:14 GMT
{
   "id": 100003,
   "name": "White Rabbit",
   "registered": "2017-06-20T17:23:39.656+0000"
}
```
Error:
```
HTTP/1.1 422 
Content-Type: application/json;charset=UTF-8
{
   "url": "http://localhost:8080/lunch/rest/restaurants/100007",
   "cause": "NotFoundException",
   "details": ["Not found entity with id=100007"]
}
```
## Add restaurant
Request:
```
HTTP/1.1
POST /lunch/rest/admin/restaurants
Host: localhost:8080
Content-Type: application/json
Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
{
   "name": "Black Rabbit"
}
```
Returns:
```
HTTP/1.1 201 
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Tue, 20 Jun 2017 18:08:42 GMT
{
   "id": 100019,
   "name": "Black Rabbit",
   "registered": "2017-06-20T18:08:42.917+0000"
}
```
Error:
```
HTTP/1.1 409 
Content-Type: application/json;charset=UTF-8
{
   "url": "http://localhost:8080/lunch/rest/admin/restaurants",
   "cause": "PSQLException",
   "details": ["Restaurant with this name already exists"]
}
```
## Remove restaurant
Request:
```
DELETE /lunch/rest/admin/restaurants/100004 HTTP/1.1
Host: localhost:8080
Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
```
Returns:
```
HTTP/1.1 200 

```
Error:
```
{
   "url": "http://localhost:8080/lunch/rest/admin/restaurants/100005",
   "cause": "NotFoundException",
   "details": ["Not found entity with id=100005"]
}
```
## Update restaurant
Request:
```
PUT /lunch/rest/admin/restaurants/100004 HTTP/1.1
Host: localhost:8080
Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu 
{
   "name": "Update Black Rabbit"
}
```
Returns:
```
HTTP/1.1 200 
```
Error:
```
HTTP/1.1 409 
Content-Type: application/json;charset=UTF-8
{
   "url": "http://localhost:8080/lunch/rest/admin/restaurants/100009",
   "cause": "PSQLException",
   "details": ["Restaurant with this name already exists"]
}
```

# Lunch Requests
## Get Lunch in the restaurant by date

Request:

```
HTTP/1.1
GET /lunch/rest/restaurants/100003/lunch?date=2017-05-30
Host: localhost:8080
Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=
```

Returns:

```
HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Mon, 19 Jun 2017 20:07:55 GMT

[
      {
      "id": 100008,
      "date": "2017-05-29T21:00:00.000+0000",
      "description": "омлет со спаржей",
      "restaurantId": 100003,
      "price": 500
   },
      {
      "id": 100009,
      "date": "2017-05-29T21:00:00.000+0000",
      "description": "салат по сезону",
      "restaurantId": 100003,
      "price": 100
   },
      {
      "id": 100010,
      "date": "2017-05-29T21:00:00.000+0000",
      "description": "каша молочная",
      "restaurantId": 100003,
      "price": 500
   }
]
```

Error:

```
HTTP/1.1 500 
Content-Type: application/json;charset=UTF-8
{
   "url": "http://localhost:8080/lunch/rest/restaurants/100002/lunch",
   "cause": "MissingServletRequestParameterException",
   "details": ["Required Date parameter 'date' is not present"]
}
```

# Meal Requests

## Get Meal for a restaurant

Request:
```
GET /lunch/rest/profile/restaurants/100004/meals/100012
Host: localhost:8080
Auth: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=
```
Returns:
```
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Mon, 19 Jun 2017 19:03:45 GMT

{
   "id": 100012,
   "date": "2017-05-29",
   "description": "рыба запеченная",
   "restaurantId": 100004,
   "price": 1000
}
```
Error:
```
HTTP/1.1 422 
Content-Type: application/json;charset=UTF-8
{
   "url": "http://localhost:8080/lunch/rest/restaurants/100004/meals/100013",
   "cause": "NotFoundException",
   "details": ["Not found entity with id=100013"]
}
```
## Add Meal for the restaurant
Request:
```
HTTP/1.1
POST /lunch/rest/admin/restaurants/100002/meals
Host: localhost:8080
Content-Type: application/json;charset=UTF-8
Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
{
   "description": "Новый обед",
   "price": 500
}
```
Returns:
```
HTTP/1.1 201 
Content-Type: application/json;charset=UTF-8
Date: Sat, 24 Jun 2017 13:09:33 GMT
{
   "id": 100019,
   "date": "2017-06-24",
   "description": "Новый обед",
   "price": 500,
}
```
Error:
```
HTTP/1.1 409 
Content-Type: application/json;charset=UTF-8
{
   "url": "http://localhost:8080/lunch/rest/admin/restaurants/100007/meals",
   "cause": "PSQLException",
   "details": Подробности: Ключ (restaurant_id)=(100007) отсутствует в таблице \"restaurants\"."]
}
```
## Update Meal for the restaurant
Request:
```
HTTP/1.1
PUT /lunch/rest/admin/restaurants/100002/meals
Host: localhost:8080
Content-Type: application/json;charset=UTF-8
Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
{
   "id": 100007,
   "description": "Обновленный обед",
   "price": 500
}
```
Returns:
```
HTTP/1.1 200 
Date: Sun, 25 Jun 2017 08:55:24 GMT
```
Error:
```
HTTP/1.1 422 
Content-Type: application/json;charset=UTF-8
Date: Sun, 25 Jun 2017 09:13:18 GMT
{
   "url": "http://localhost:8080/lunch/rest/admin/restaurants/100002/meals",
   "cause": "ValidationException",
   "details": ["description must not be empty"]
}
```
## Delete Meal
Request:
```
HTTP/1.1
DELETE /lunch/rest/admin/restaurants/100002/meals/100005
Host: localhost:8080
Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
```
Returns:
```
HTTP/1.1 200 
Date: Tue, 27 Jun 2017 20:26:51 GMT
```
Error:
```
HTTP/1.1 422 
Content-Type: application/json;charset=UTF-8
{
   "url": "http://localhost:8080/lunch/rest/admin/restaurants/100002/meals/100005",
   "cause": "NotFoundException",
   "details": ["Not found entity with id=100005"]
}
```
# Vote Requests

## Vote for a restaurant

Request:
```
HTTP/1.1
POST /lunch/rest/profile/restaurants/100003/vote
Host: localhost:8080
Content-Type: application/json
Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=
```
Returns:
```
HTTP/1.1 201 
Date: Sat, 24 Jun 2017 13:30:49 GMT
Content-Type: application/json;charset=UTF-8
{
   "id": 100017
}
```
Error:
```
HTTP/1.1 451 
Content-Type: application/json;charset=UTF-8
Date: Sat, 24 Jun 2017 13:27:05 GMT
{
   "url": "http://localhost:8080/lunch/rest/profile/restaurants/100002/vote",
   "cause": "ApplicationException",
   "details": ["Repeated voting is allowed until 11:00"]
}
```
# Login
## Authorization
All available resources are exposed via REST URIs,
which, together with HTTP verbs, can cover all the actions that can be performed and need to be secured. 
2 roles are configured:

* /rest/admin - available for ROLE_ADMIN

* /rest/ - available for ROLE_USER and ROLE_ADMIN

```
Username:admin@gmail.com
Password:admin
Roles: ["ROLE_USER","ROLE_ADMIN"]
Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
```
```
Username:user@yandex.ru
Password:password
Role: ["ROLE_USER"]
Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=
```

### User
CURL:

```
curl 'http://localhost:8080/lunch/rest/profile -u admin@gmail.com:admin
curl 'http://localhost:8080/lunch/rest/profile' -i -H'Authorization:Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
curl 'http://localhost:8080/lunch/rest/admin/users/by?email=admin@gmail.com' -i -H'Authorization:Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'
curl 'http://localhost:8080/lunch/rest/admin/users' -i -d'{"name" : "NewUser", "email" : "new@mail.ru","password" : "123456"}' -H'Authorization:Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' -H'Content-Type: application/json'
```

## Add User
Request:
```
HTTP/1.1
POST /lunch/rest/admin/users
Host: localhost:8080
Content-Type: application/json
Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
{
   "name": "New",
   "email": "new@yandex.ru",
   "password": "newPassword",
   "roles": ["ROLE_USER"]
}

```
Returns:
```
HTTP/1.1 201 
Content-Type: application/json;charset=UTF-8
Date: Sat, 24 Jun 2017 13:49:28 GMT
{
   "id": 100017,
   "name": "New",
   "email": "new@yandex.ru",
   "password": "newPassword",
   "enabled": true,
   "registered": "2017-06-24T13:49:28.418+0000",
   "roles": ["ROLE_USER"]
}
```
Error:
```
HTTP/1.1 422 
Content-Type: application/json;charset=UTF-8
{
   "url": "http://localhost:8080/lunch/rest/admin/users",
   "cause": "ValidationException",
   "details": ["Password must not be empty"]
}
```