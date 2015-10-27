package com.shan.bioinfo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestClass {

	public static class execution{
		
		ListIterator<String> strItr;
		public execution(ListIterator<String> sItr) {
			// TODO Auto-generated constructor stub
			this.strItr = sItr;			
		}

		private void print() {
			// TODO Auto-generated method stub
			while(strItr.hasNext()){
				System.out.print(strItr.next());
			}
		}
	}
	public static class threadEx implements Runnable{
		
		execution e1;
		public threadEx(execution e1) {
			this.e1 = e1;
		}
		public void run() {
			// TODO Auto-generated method stub
			synchronized (e1) {
				e1.print();
			}
		}
		
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		long startTime = System.currentTimeMillis();
		String string1 = "GCTGGAAGGCAT";
		String string2 = "GCAGAGCACG";
		List<String> s1 = Arrays.asList(string1.split(""));
		/*ArrayList<String> seq1 = new ArrayList<String>();
		seq1.addAll(Arrays.asList(string1.split("")));
		ArrayList<String> seq2 = new ArrayList<String>();
		seq2.addAll(Arrays.asList(string2.split("")));
		LocalAlignmentDiseasesAnnotations l1 = new LocalAlignmentDiseasesAnnotations(seq1, seq2);
		//System.out.println(l1.findMaxScoreOfMatch());
		l1.findMaxScoreOfMatch();

		System.out.println();
		System.out.println();System.out.println();
		LocalAlignment l2 = new LocalAlignment();
		l2.main(args);*/
		ExecutorService exec = Executors.newFixedThreadPool(3);

		ListIterator<String> sItr = s1.listIterator();
		execution e1 = new execution(sItr);
		exec.execute(new threadEx(e1));
		System.out.println(System.currentTimeMillis()-startTime);
	}

}
