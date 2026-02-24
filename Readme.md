```mermaid
classDiagram
direction BT
class complaint {
   integer user_id
   integer room_id
   text description
   varchar(20) complaint_status
   timestamp complaint_date
   integer id
}
class complaint_status {
   varchar(20) status
}
class countries {
   varchar(8) country_code
   varchar(50) country
}
class floors {
   integer floor_no
}
class reviews {
   integer user_id
   integer room_id
   text description
   timestamp review_date
   integer id
}
class roles {
   varchar(1000) role
}
class room_rent_information {
   integer user_id
   integer room_id
   integer room_rent_days
   date start_date
   date end_date
   boolean meal_status
   integer total_meal_count
   integer id
}
class room_types {
   varchar(15) room_type
}
class rooms {
   varchar(15) room_type
   integer floor_no
   double precision per_day_rent_fee
   integer id
}
class transactions {
   integer user_id
   integer room_no
   date payment_date
   date start_date
   date end_date
   double precision total_amount
   double precision paid_amount
   payment_method payment_method
   transaction_type transaction_type
   integer id
}
class users {
   varchar(1000) first_name
   varchar(1000) last_name
   text email
   varchar(1000) role
   varchar(11) contact_no
   varchar(11) emergency_contact_no
   date birth_date
   text permanent_address
   varchar(50) country
   varchar(15) passport_id
   integer id
}

complaint  -->  rooms : room_id:id
complaint  -->  users : user_id:id
reviews  -->  rooms : room_id:id
reviews  -->  users : user_id:id
room_rent_information  -->  rooms : room_id:id
room_rent_information  -->  users : user_id:id
rooms  -->  floors : floor_no
rooms  -->  room_types : room_type
transactions  -->  rooms : room_no:id
transactions  -->  users : user_id:id
users  -->  countries : country
users  -->  roles : role

```