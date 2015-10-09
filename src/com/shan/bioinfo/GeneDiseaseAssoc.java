package com.shan.bioinfo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
public class GeneDiseaseAssoc {

	public static void main(String[] args) throws IOException {
		HttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		JsonFactory JSON_FACTORY =  JacksonFactory.getDefaultInstance();
		java.io.File DATA_STORE_DIR = new java.io.File(
		        System.getProperty("user.home"), ".credentials/drive-java-quickstart");
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
		Drive service = new Drive();
		  File file = service.files().get("0B-F1_EKfc_rOWVNpMGM1SFhQSDg").execute();

	      System.out.println("Title: " + file.getTitle());
	      System.out.println("Description: " + file.getDescription());
	      System.out.println("MIME type: " + file.getMimeType());
	    
		}
		
	}


