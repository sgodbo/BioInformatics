package com.shan.bioinfo.project;

public class Edge {
	private int source;
	private int target;
	private int value;
	public Edge(int i, int j, int k) {
		// TODO Auto-generated constructor stub
		this.source = i;
		this.target = j;
		this.value = k;
	}
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
	}
	public int getTarget() {
		return target;
	}
	public void setTarget(int target) {
		this.target = target;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}
