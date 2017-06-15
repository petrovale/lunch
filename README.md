## The voting system to decide where to lunch

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed


## REST API

Documentation for REST

### Restaurant Requests

#### Get All Restaurants

##### Request:

```
GET /lunch/rest/profile/restaurants

Host: localhost:8080
Auth: 
Content-type: application/json
Accept: application/json
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