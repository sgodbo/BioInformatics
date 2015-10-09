package com.shan.bioinfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GeneDiseaseAssoc {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		URL url = new URL("https://drive.google.com/open?id=0B-F1_EKfc_rOWVNpMGM1SFhQSDg");
        HttpURLConnection connection = (HttpURLConnection) url
                .openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("GET");
        InputStreamReader str = new InputStreamReader(connection.getInputStream());
		//FileReader fr = new FileReader(new File("C:\\Users\\SHANDEMETZ\\Downloads\\Compressed\\gene_association.goa_human\\gene_association.goa_human"));
		BufferedReader br = new BufferedReader(str);
		String line = "";
		while((line = br.readLine()) != null){
			System.out.println(line);
		}
			
		}
		
	}


