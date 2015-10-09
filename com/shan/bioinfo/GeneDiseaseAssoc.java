package com.shan.bioinfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GeneDiseaseAssoc {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileReader fr = new FileReader(new File("C:\\Users\\SHANDEMETZ\\Downloads\\Compressed\\gene_association.goa_human\\gene_association.goa_human"));
		BufferedReader br = new BufferedReader(fr);
		String line = "";
		while((line = br.readLine()) != null){
			if(line.contains("ATP7B")){
				System.out.println(line);
			}
		}
			
		}
		
	}


