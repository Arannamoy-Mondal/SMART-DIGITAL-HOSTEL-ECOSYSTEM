- [User](#user)
- [Role](#role)
- [Floor](#floor)
- [Room type](#room-type)
- [Room](#room)
- [Meal type](#mealtype)
- [Menu Item](#menuitem)
- [Menu](#menu)
- [Room booking](#room-booking)
- [Meal Token](#meal-token)

#### User
1. post:
```
http://0.0.0.0:8001/user/signup
```
`Sample`
```json
{
    "userName": "admin",
    "password": "1234",
    "role": "admin",
    "contactNo":"0123456789",
    "firstName":"user",
    "lastName":"One",
    "permanentAddress":"Dhaka",
    "email":"user1@user.com",
    "emergencyContactNo":"0123456777",
    "birthDate":"2004-07-26",
    "passportId":"A985652585"
}
```
2. post:

```
http://0.0.0.0:8001/user/signup
```

`Sample`

```json
{
    "userName": "user1",
    "password": "1234"
}
```

3. get:

`NB: Only for admin role`

```
http://0.0.0.0:8001/user/all
```

#### Role

1. get:
`NB: Only for admin role`

```
http://0.0.0.0:8001/role/get
```

2. get:

http://0.0.0.0:8001/role/get/{id}

3. post

http://0.0.0.0:8001/role/create


`Sample`
```
{
    "role":"BaRishta"
}
```

4. put

http://0.0.0.0:8001/role/update/{id}

`Sample`
```
{
    "role":"BaRishta"
}
```

5. delete

http://0.0.0.0:8001/role/delete/{id}

#### Room Type

1. create
`NB: Only for admin role`

```
http://0.0.0.0:8001/roomType/create
```

```json
{
    "roomType":"triple seater"
}
```

2. get

```
http://0.0.0.0:8001/roomType/get/all
```

3. get

```
http://0.0.0.0:8001/roomType/get/roomType/{roomType}
```

```
http://0.0.0.0:8001/roomType/get/roomType/single%20seater
```

4. get

```
http://0.0.0.0:8001/roomType/get/id/{roomId}
```

```
http://0.0.0.0:8001/roomType/get/id/1
```

5. put

```
0.0.0.0:8001/roomType/update/{id}
```

```
0.0.0.0:8001/roomType/update/1
```

```json
{
    "roomType":"double seater"
}
```

#### Floor

1. post

```
http://0.0.0.0:8001/floor/create
```

```json
{
    "floorNo":"2"
}
```

2. get

```
http://0.0.0.0:8001/floor/get
```
3. get

```
http://0.0.0.0:8001/floor/get/floorNo/{floorNo}
```

#### Room

1. post

```
http://0.0.0.0:8001/room/create
```

```json
{
   "roomNo":"210",
    "roomType":"single seater",
    "floorNo":"2",
    "perDayRentFee":"150"
}
```

2. get

```
http://0.0.0.0:8001/room/get/all
```

3. 

```
http://0.0.0.0:8001/room/get/roomNo/{roomNo}
```

4. put

```
0.0.0.0:8001/room/update/{roomNo}
```
```json
{
   "roomNo":"210",
    "roomType":"single seater",
    "floorNo":"2",
    "perDayRentFee":"170"
}
```

#### MealType
1. post
```
0.0.0.0:8001/mealType/create
```
```json
{
   "mealType":"Breakfast"
}
```

2. get

```
0.0.0.0:8001/mealType/get/all
```

3. get
```
0.0.0.0:8001/mealType/get/mealType/{mealType}
```

4. 


#### MenuItem

1. 

```
0.0.0.0:8001/menuItem/create
```

```json
{
  "itemName":"rice",
  "description":""
}
```

2. 

```
0.0.0.0:8001/menuItem/get/itemName/{menuItemName}
```

3. 

#### Menu 

1. post

```
0.0.0.0:8001/menu/create
```

```json
{
  "day":"saturday",
  "mealType":"breakfast",
  "menuItems":[
    "lentils","rice","egg curry"
  ]
}
```

2. get

```
0.0.0.0:8001/menu/get/all
```

2. get

```
0.0.0.0:8001/menu/get/menuId/{menuId}
```

3. put

```
0.0.0.0:8001/menu/update/menuId/{menuId}
```

```json
```

#### room booking

1. post

```
0.0.0.0:8001/roomBooking/create
```

```json
{
    "userName":"admin",
    "roomNo":"210",
    "startDate":"2026-07-26",
    "endDate":"2026-08-26",
    "paymentMethod":"visa",
    "amount":"100.00"
}
```

2. get
```
0.0.0.0:8001/roomBooking/get/all
```

#### meal token
1. post
```
0.0.0.0:8001/mealToken/create
```

```json
{
    "userName":"admin",
    "roomNo":"210",
    "tokenAmount":"200",
    "amount":"200",
    "paymentMethod":"visa"
}
```

2. get

```
0.0.0.0:8001/mealToken/get/all
```

3. get

```
0.0.0.0:8001/mealToken/get/userName/admin
```