package com.shan.bioinfo;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class CheckDisAnnotForHighlySimilar {

	public static void main(String[] args) throws IOException,FileNotFoundException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader(new File("D:\\Web Project\\outputs\\matching_coefficients0.csv")));
		String line = "";
		String[] lineSegments;
		Map<String,String> mapDisAnnot = new HashMap<String,String>();
		 
		while((line = br.readLine()) != null){
			lineSegments = line.split(",");
			if(Float.parseFloat(lineSegments[2]) >= 0.6f){
				mapDisAnnot.put(lineSegments[1], lineSegments[0]);
			}
		}
		searchForGOID(mapDisAnnot);
	}

	private static void searchForGOID(Map<String, String> mapDisAnnot) {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\SHANDEMETZ\\Desktop\\go.owl")));
		String line = "";
		while((line = br.readLine()) != null){
			line.contains(arg0)
		}
		for(Entry<String,String> entry:mapDisAnnot.entrySet()){
			entry.getKey()
		}
	}

}
