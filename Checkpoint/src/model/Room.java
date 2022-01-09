package model;


public class Room {
	
	int number;
	String client;
	String type;
	boolean free;
	boolean cleaned;
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isFree() {
		return free;
	}
	public void setFree(boolean free) {
		this.free = free;
	}
	public boolean isCleaned() {
		return cleaned;
	}
	public void setCleaned(boolean cleaned) {
		this.cleaned = cleaned;
	}

}
