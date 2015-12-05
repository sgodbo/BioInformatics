package com.shan.bioinfo.project;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Test1 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\SHANDEMETZ\\Downloads\\Compressed\\gene_association.goa_human\\gene_association.goa_human")));
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("outputs\\annotNameList.csv")));;
		String line = "";
		String[] lineSplits;
		String[] lineSplits1;
		String annotName;
		String annotId;
		for(int i = 0 ;i < 35;i++){
			br.readLine();
		}
		Map<String,String> annotNameMap = new HashMap<String,String>();
		br.readLine();
		while(null != (line = br.readLine())){
			lineSplits = line.split("[\t]");
			lineSplits1 = lineSplits[4].split(":");
			annotId = lineSplits1[1];
			annotName = lineSplits[9];
			annotNameMap.put(annotId, annotName);
		}
		for(Entry<String,String> entry:annotNameMap.entrySet()){
			bw = new BufferedWriter(new FileWriter(new File("outputs\\annotNameList.csv"),true));
			System.out.println(entry.getKey()+","+entry.getValue());
			bw.write(entry.getKey()+","+entry.getValue());
			bw.write("\n");
			bw.close();
		}
	}

}
