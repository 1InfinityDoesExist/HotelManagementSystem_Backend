package com.patel.hotelMangementSystem.Exception;

public class RoomUniqueIdExceptionResponse {
	private String roomUniqueId;

	public RoomUniqueIdExceptionResponse(String roomUniqueId) {
		super();
		this.roomUniqueId = roomUniqueId;
	}

	public String getRoomUniqueId() {
		return roomUniqueId;
	}

	public void setRoomUniqueId(String roomUniqueId) {
		this.roomUniqueId = roomUniqueId;
	}

}
