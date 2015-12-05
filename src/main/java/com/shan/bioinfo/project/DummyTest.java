package com.shan.bioinfo.project;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class DummyTest {

	static Iterator<Entry<Integer, Integer>> map2Itr;
	public static class Thread1 extends Thread{
		int temp;
		int threadNum;
		int iteration;
		public Thread1(int tempVal, int i, int i2){
			temp = tempVal;
			threadNum = i;
			iteration = i2;
		}
		
		public void run(){
			while(map2Itr.hasNext()){
				int temp1 = map2Itr.next().getValue();
				System.out.println(temp1 + " " + temp + " " + (temp1+temp) + " threadNum " + threadNum + " iteration num " + iteration);
				break;
			}
		}
	}
	public static void main(String[] args) throws IOException {
		
		Map<Integer,Integer> map1 = new LinkedHashMap<Integer,Integer>();
		map1.put(0, 1);
		map1.put(1, 2);
		map1.put(2, 3);
		map1.put(3, 4);
		map1.put(4, 5);
		map1.put(5, 6);
		
		Map<Integer,Integer> map2 = new LinkedHashMap<Integer,Integer>();
		map2.put(0, 11);
		map2.put(1, 12);
		map2.put(2, 13);
		map2.put(3, 14);
		map2.put(4, 15);
		map2.put(5, 16);
		
		Iterator<Entry<Integer,Integer>> map1Itr = map1.entrySet().iterator();
		int counter = 0;
		while (map1Itr.hasNext()) {
			// int tempKey = entry.getKey();
			int tempVal = map1Itr.next().getValue();
			map2Itr = map2.entrySet().iterator();
			for (int i = 0; i < 7; i++) {
				Thread1 t1 = new Thread1(tempVal, counter, i);
				t1.start();
			}
			counter++;     
		}
			
		
	}
}
