## The voting system to decide where to lunch

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/df7dfc76c727400497f8a2e05482deb7)](https://www.codacy.com/app/petrovaleOrganization/lunch?utm_source=github.com&utm_medium=referral&utm_content=petrovale/lunch&utm_campaign=badger)

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

# REST API

Documentation for REST

# Restaurant Requests

## Get all the restaurants with voices for today

##### Request:

```
HTTP/1.1
GET /lunch/rest/profile/restaurants/by?date=2017-06-18
Host: localhost:8080
Authorization: Basic
```
##### Returns:
```
HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
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
```

## Get all the restaurants with votes for all time


##### Request:

```
GET /lunch/rest/profile/restaurants
Host: localhost:8080
Auth: Basic 
```

##### Returns:

```
HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Thu, 15 Jun 2017 19:36:07 GMT

[
   {
      "id":100002,
      "name":"Black Thai",
      "vote":13
   },
   {
      "id":100003,
      "name":"White Rabbit",
      "vote":0
   },
   {
      "id":100004,
      "name":"Уголек",
      "vote":5
   }
]
```

##### Error:

```
HTTP 204 NO CONTENT
```
## Get restaurant
Request:
```
HTTP/1.1
GET /lunch/rest/profile/restaurants/100003
Host: localhost:8080
Authorization: Basic
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
   "vote": 0,
   "registered": "2017-06-20T17:23:39.656+0000"
}
```
Error:
```
```
## Add restaurant
Request:
```
HTTP/1.1
POST /lunch/rest/admin/restaurants
Host: localhost:8080
Content-Type: application/json
Authorization: Basic
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
   "vote": 0,
   "registered": "2017-06-20T18:08:42.917+0000"
}
```
Error:
```
```
## Remove restaurant
Request:
```
DELETE /lunch/rest/admin/restaurants/100004 HTTP/1.1
Host: localhost:8080
Authorization: Basic
```
Returns:
```
HTTP/1.1 200 

```
Error:
```
```
## Update restaurant
Request:
```
PUT /lunch/rest/admin/restaurants/100004 HTTP/1.1
Host: localhost:8080
Authorization: Basic 
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
```

# Lunch Requests
## Lunch in the restaurant today

Request:

```
GET /lunch/rest/profile/restaurants/100003/lunch?date=2017-05-30 HTTP/1.1
Host: localhost:8080
Authorization: Basic
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

```

# Meal Requests

## Meal for a restaurant

Request:
```
GET /lunch/rest/profile/restaurants/100004/meals/100012
Host: localhost:8080
Auth: Basic 
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
```
# Vote Requests

## Vote for a restaurant

Request:
```
POST http://localhost:8080/lunch/rest/profile/restaurants/100003/vote HTTP/1.1
Content-Type: application/json
Host: localhost:8080
Authorization: Basic
```
Returns:
```
{
   "id": 100017,
   "registered": "2017-06-19T16:39:09.342+0000"
}
```
Error:
```
HTTP 404 NOT FOUND
```
# Login
## Authorization
All available resources are exposed via REST URIs,
which, together with HTTP verbs, can cover all the actions that can be performed and need to be secured. 
2 roles are configured:

* /rest/admin - available for ROLE_ADMIN

* /rest/profile - available for ROLE_USER and ROLE_ADMIN

```
Username:admin@gmail.com
Password:admin
Roles: ["ROLE_USER","ROLE_ADMIN"]
```
```
Username:user@yandex.ru
Password:password
Role: ["ROLE_USER"]
```
