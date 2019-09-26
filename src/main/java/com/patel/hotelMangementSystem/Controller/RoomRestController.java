package com.patel.hotelMangementSystem.Controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.patel.hotelMangementSystem.Model.Room;
import com.patel.hotelMangementSystem.Service.MapErrorsToFields;
import com.patel.hotelMangementSystem.Service.RoomService;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/object/room")
public class RoomRestController {

	@Autowired
	private RoomService roomService;

	@Autowired
	private MapErrorsToFields mapErrorsToFields;

	@PostMapping(path = "/create")
	public ResponseEntity<?> createRoomService(@Valid @RequestBody Room room, BindingResult bindingResult) {

		ResponseEntity<?> errorMap = mapErrorsToFields.mapHotelError(bindingResult);
		Room roomToDB = roomService.createRoom(room);
		return new ResponseEntity<Room>(room, HttpStatus.CREATED);
	}

	@GetMapping(path = "/get/{roomUniqueId}")
	public ResponseEntity<?> getRoomByUniqueId(@PathVariable(value = "roomUniqueId") String roomUniqueId) {
		Room room = roomService.getRoomByUniqueID(roomUniqueId);
		return new ResponseEntity<Room>(room, HttpStatus.OK);
	}

	@GetMapping(path = "/get")
	public ResponseEntity<?> getAllRoom() {
		List<Room> roomList = roomService.getAllRoom();
		return new ResponseEntity<List<Room>>(roomList, HttpStatus.OK);
	}

	@DeleteMapping(path = "/delete/{roomUniqueId}")
	public ResponseEntity<?> deleteRoomByUniqueId(@PathVariable(value = "roomUniqueId") String roomUniqueId) {
		String response = roomService.deleteRoom(roomUniqueId);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@PatchMapping(path = "/update/{roomUniqueId}")
	public ResponseEntity<?> updateRoomByUniqueId(@PathVariable(value = "roomUniqueId") String roomUniqueId,
			@RequestBody String room) throws JsonParseException, JsonMappingException, IOException {
		Room roomResponse = roomService.updateRoomByUniqueId(room, roomUniqueId);
		return new ResponseEntity<Room>(roomResponse, HttpStatus.OK);
	}
}
