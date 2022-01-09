package model;

public class Cliente {
	
	String name;
	String checkIn;
	String checkOut;
	int roomNum;
	boolean entradas;
	boolean activo;
	
	public int getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}
	public String getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}
	public boolean isEntradas() {
		return entradas;
	}
	public void setEntradas(boolean entradas) {
		this.entradas = entradas;
	}
	public boolean isactivo() {
		return activo;
	}
	public void setactivo(boolean activo) {
		this.activo = activo;
	}

}
