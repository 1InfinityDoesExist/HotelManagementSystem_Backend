package com.patel.hotelMangementSystem.Service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.patel.hotelMangementSystem.Exception.RoomUniqueIdException;
import com.patel.hotelMangementSystem.Model.Hotel;
import com.patel.hotelMangementSystem.Model.Room;
import com.patel.hotelMangementSystem.Repository.HotelRepository;
import com.patel.hotelMangementSystem.Repository.RoomRepository;
import com.patel.hotelMangementSystem.Utility.ReflectionUtil;

@Service
public class RoomService {

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private HotelRepository hotelRepository;

	@PostConstruct
	public void setUp() {
		objectMapper.registerModule(new JavaTimeModule());
	}

	ReflectionUtil refUtil = ReflectionUtil.getInstance();

	public Room createRoom(Room room) {
		try {
			Long totalFare = 0l;

			room.setStatus("AVAILABLE");
			String acNonAcStatus = room.getRoomColdness();
			if (acNonAcStatus.equals("AC")) {
				totalFare = totalFare + 5000;
			} else {
				totalFare = totalFare + 3000;
			}

			Long noOfBeds = room.getNoOfBeds();
			if (noOfBeds == 1) {
				totalFare = totalFare + 2000;
			} else {
				totalFare = totalFare + 4000;
			}
			
			room.setTotalCost(totalFare + 10000l); // 10000 is depositte;
			room.setDeleteFlag(false);
			room.setNotes("Highly Recommended For Couples");
			room.setRoomConditonStatus("PERFECT_CONDITION");
			// Room Uniqueness...!!!
			Long hotelId = room.getHotelRoom().getId();
			System.out.println(hotelId);
			Hotel hotel = hotelRepository.getHotelByPrimarykey(hotelId);
			System.out.println(hotel);
			Random rand = new Random();
			room.setRoomUniqueId(hotel.getHotelUniqueId() + "_" + rand.nextInt(100));
			room.setHotelName(hotel.getName());
			System.out.println(room);
			Room roomToDB = roomRepository.save(room);

			return roomToDB;
		} catch (Exception e) {
			throw new RoomUniqueIdException("Room Instance CouldNot be Persisted In The Database");
		}
	}

	public Room getRoomByUniqueID(String roomUniqueId) {
		Room room = roomRepository.getRoomByUniqueId(roomUniqueId);
		if (room == null) {
			throw new RoomUniqueIdException("Sorry No Room With Id:- " + roomUniqueId + "Found In The Database");
		}
		return room;
	}

	public List<Room> getAllRoom() {
		List<Room> roomList = roomRepository.getAllRooms();
		if (roomList == null || roomList.size() == 0) {
			throw new RoomUniqueIdException("Room Instance Could Not Be Found In The Database");
		}
		return roomList;
	}

	public String deleteRoom(String roomUniqueId) {
		Room room = roomRepository.getRoomByUniqueId(roomUniqueId);
		if (room == null) {
			throw new RoomUniqueIdException("Sorry No Room With Id:- " + roomUniqueId + "Found In The Database");
		}
		roomRepository.deleteRoomByUniqueId(roomUniqueId);
		return "SuccessFully Deleted";
	}

	public Room updateRoomByUniqueId(String room, String roomUniqueId)
			throws JsonParseException, JsonMappingException, IOException {
		Room roomFromDB = roomRepository.getRoomByUniqueId(roomUniqueId);
		if (roomFromDB == null) {
			throw new RoomUniqueIdException("Sorry No Room With Id:-" + roomUniqueId + "Found In The Database");
		}
		// String hotelUniqueIdFromDB = roomFromDB.getHotelUniqueId();
		Room roomObject = objectMapper.readValue(room, Room.class);
		// String hotelUniqueIdFromPayload = roomObject.getHotelUniqueId();

		JSONParser parser = new JSONParser();
		try {
			JSONObject obj = (JSONObject) parser.parse(room);
			for (Iterator iterator = ((Map<String, String>) obj).keySet().iterator(); iterator.hasNext();) {
				String propName = (String) iterator.next();
				refUtil.getSetterMethod("Room", propName).invoke(roomFromDB, obj.get(propName));
			}
		} catch (ParseException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Long totalFare = 0l;
		String acNonAcStatus = roomFromDB.getRoomColdness();
		if (acNonAcStatus.equals("AC")) {
			totalFare = totalFare + 5000;
		} else {
			totalFare = totalFare + 3000;
		}

		Long noOfBeds = roomFromDB.getNoOfBeds();
		if (noOfBeds == 1) {
			totalFare = totalFare + 2000;
		} else {
			totalFare = totalFare + 4000;
		}
		roomFromDB.setTotalCost(totalFare + 10000l); // 10000 is depositte;

		Room roomResponse = roomRepository.save(roomFromDB);
		return roomResponse;
	}

}
