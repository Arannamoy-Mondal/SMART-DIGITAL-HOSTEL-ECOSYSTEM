package com.backend.backend.mapper;

import com.backend.backend.dto.RoomTypeRequest;
import com.backend.backend.model.RoomType;

public class RoomTypeMapper {
    public static RoomType mapToRoomType(RoomTypeRequest roomTypeRequest){
        RoomType roomType=new RoomType();
        roomType.setRoomType(roomTypeRequest.getRoomType());
        return roomType;
    }
}
