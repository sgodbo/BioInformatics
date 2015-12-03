package com.shan.bioinfo.project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

public class TestPubMed {
	
	public static void main(String[] args) throws IOException{
		

		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(12);
		list.add(13);
		list.add(14);
		List<Integer> newList = new ArrayList<Integer>();
		newList.addAll(list);
		for(int i = 0;i < newList.size();i++){
			System.out.println(newList.get(i) + ",");
		}
	}

}
