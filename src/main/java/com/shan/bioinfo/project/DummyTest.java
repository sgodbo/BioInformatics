package com.shan.bioinfo.project;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class DummyTest {

	public static void main(String[] args) throws IOException {
		
		Map<Integer,Integer> map1 = new HashMap<Integer,Integer>();
		map1.put(0, 1);
		map1.put(1, 2);
		map1.put(2, 3);
		map1.put(3, 4);
		map1.put(4, 5);
		map1.put(5, 6);
		
		Map<Integer,Integer> map2 = new HashMap<Integer,Integer>();
		map2.put(0, 11);
		map2.put(1, 12);
		map2.put(2, 13);
		map2.put(3, 14);
		map2.put(4, 15);
		map2.put(5, 16);
		
		for(Entry<Integer,Integer> entry:map1.entrySet()){
			int tempKey = entry.getKey();
			int tempVal = entry.getValue();
			Thread t1 = new Thread(){
				public void run(){
					
				}
			};
		}
			
		
	}
}
