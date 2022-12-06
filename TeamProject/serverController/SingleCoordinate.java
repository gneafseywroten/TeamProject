package serverController;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class SingleCoordinate {
	private boolean occupied;
	private boolean firedAt = false;
	protected int x;
	protected int y;
	private int coord_index;
	protected char status;
	private String coordName;
	private String[] rows = {"A","B","C","D","E","F","G","H","I","J"};
	private String[] cols = {"1","2","3","4","5","6","7","8","9","10"};
	private JButton button;
	private String shipType;
	
	public SingleCoordinate(int coord_index, boolean occupied, char status) {
		//this.x = x;
		//this.y = y;
		this.coord_index = coord_index;
		this.occupied = occupied;
		this.status = status;
		this.shipType = "none";
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getCoordIndex() {
		return coord_index;
	}
	
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	
	public boolean isOccupied() {
		return occupied;
	}
	
	public void setFiredAt(boolean firedAt) {
		this.firedAt = firedAt;
	}
	
	public boolean isFired() {
		return firedAt;
	}
	
	public void setStatus(char status) {
		this.status = status;
	}
	
	public char getStatus() {
		return status;
	}
	
	public void setName() {
		String tempName = new String();
		tempName = rows[x-1]+"-"+cols[y-1];
		this.coordName = tempName;
	}
	
	public String getName() {
		return coordName;
	}
	
	public void setButton(JButton button) {
		this.button = button;
	}
	
	public JButton getButton() {
		return button;
	}
	
	public void setshipType(String shipType) {
		this.shipType = shipType;
	}
	
	public String getshipType() {
		return shipType;
	}

}
