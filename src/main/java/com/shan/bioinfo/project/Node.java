package com.shan.bioinfo.project;

public class Node {
	private int id;
	private String label;
	public Node(int listCounter, String next) {
		// TODO Auto-generated constructor stub
		this.id = listCounter;
		this.label = next;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
}
