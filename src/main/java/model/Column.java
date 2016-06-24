package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Column {
	private String name;
	private String type;
	private int length;
	
	@JsonIgnore
	private boolean modify;
	
	public boolean isModify() {
		return modify;
	}
	public void setModify(boolean modify) {
		this.modify = modify;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	
	
}
