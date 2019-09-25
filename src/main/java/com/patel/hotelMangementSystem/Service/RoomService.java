package com.patel.hotelMangementSystem.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patel.hotelMangementSystem.Exception.RoomUniqueIdException;
import com.patel.hotelMangementSystem.Model.Room;
import com.patel.hotelMangementSystem.Repository.RoomRepository;
import com.patel.hotelMangementSystem.Utility.ReflectionUtil;

@Service
public class RoomService {

	@Autowired
	private RoomRepository roomRepository;
	ReflectionUtil refUtil = ReflectionUtil.getInstance();

	public Room createRoom(Room room) {
		try {
			Room roomToDB = roomRepository.save(room);
			return roomToDB;
		} catch (Exception e) {
			// TODO Auto-generated catch block
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

	public Room updateRoomByUniqueId(String room, String roomUniqueId) {
		Room roomFromDB = roomRepository.getRoomByUniqueId(roomUniqueId);
		if (roomFromDB == null) {
			throw new RoomUniqueIdException("Sorry No Room With Id:-" + roomUniqueId + "Found In The Database");
		}
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
		Room roomResponse = roomRepository.save(roomFromDB);
		return roomResponse;
	}

}
