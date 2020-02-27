package edu.mum.cs.cs544.exercises;

public class Office {
	
	private int roomnumber;
	private String building;
	
	public Office() {
	}
	
	public Office(int roomnumber, String building) {
		super();
		this.roomnumber = roomnumber;
		this.building = building;
	}
	
	public int getRoomnumber() {
		return roomnumber;
	}
	
	public void setRoomnumber(int roomnumber) {
		this.roomnumber = roomnumber;
	}
	
	public String getBuilding() {
		return building;
	}
	
	public void setBuilding(String building) {
		this.building = building;
	}

}
