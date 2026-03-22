package com.backend.backend;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.backend.backend.model.Floor;
import com.backend.backend.model.PaymentMethod;
import com.backend.backend.model.PaymentPurpose;
import com.backend.backend.model.Room;
import com.backend.backend.model.RoomType;
import com.backend.backend.model.Transaction;
import com.backend.backend.model.TransactionType;
import com.backend.backend.model.User;
import com.backend.backend.repo.FloorRepo;
import com.backend.backend.repo.RoomRepo;
import com.backend.backend.repo.RoomTypeRepo;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
class BackendApplicationTests {

	@Autowired
	private FloorRepo floorRepo;

	@Autowired
	private RoomRepo roomRepo;
	
	@Autowired
	private RoomTypeRepo roomTypeRepo;


	@Test
	void contextLoads() {
	}

	@Test
	void createRoom(){

		var roomType1=RoomType.builder().roomType("single seater").build();
		var roomType2=RoomType.builder().roomType("double seater").build();
		var roomType3=RoomType.builder().roomType("triple seater").build();
		var floor10=Floor.builder().floorNo(10).build();
		var room1=Room.builder().roomNo(1001).perDayRentFee(350).roomType(roomType1).floor(floor10).build();
		var room2=Room.builder().roomNo(1002).perDayRentFee(200).roomType(roomType2).floor(floor10).build();
		var room3=Room.builder().roomNo(1003).perDayRentFee(200).roomType(roomType3).floor(floor10).build();
		roomTypeRepo.save(roomType1);
		roomTypeRepo.save(roomType2);
		roomTypeRepo.save(roomType3);
		floorRepo.save(floor10);
		roomRepo.save(room1);
		roomRepo.save(room2);
		roomRepo.save(room3);



		
	}

	@Test
	@Transactional
	void getRooms(){
		System.out.println("\ngetRooms Output\n*****************************************\n"+
		
		"*****************************************\n");
		for(Room room:roomRepo.findAll()){
			System.out.println(room);
			System.out.println("\n");
		}
	}

	@Test
	public void makeTransaction(){
		Room room=Room.builder()
		.perDayRentFee(100)
		.roomNo(1001)
		.build();
		User user=User.builder().userName("user1").build();
		PaymentMethod paymentMethod=PaymentMethod.builder().paymentMethod("Bkash").build();
		TransactionType transactionType=TransactionType.builder()
		.transactionType("DEBIT").build();
		PaymentPurpose paymentPurpose=PaymentPurpose.builder()
		.paymentPurpose("Payment for room rent")
		.build();
		Transaction transaction=Transaction.builder()
		.room(room)
		.user(user)
		.paymentMethod(paymentMethod)
		.paymentPurpose(paymentPurpose)
		.build();
		
		System.out.println("\n Transaction ........... "+transaction);
	}
}
