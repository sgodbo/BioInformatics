package com.shan.bioinfo.project;

import java.util.*;
import java.io.*;
import java.net.URL;

import java.net.HttpURLConnection;

public class GetAnnotationMap {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader(new File(
				"outputs\\annotationIDsList.csv")));
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
				"outputs\\annotationNamesList.csv")));
		String line = "";
		String synCheck = "</synonymtypedef>";
		while (null != (line = br.readLine())) {

			String url = "http://www.ebi.ac.uk/QuickGO/GTerm?id=GO:" + line
					+ "&format=oboxml";
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			// optional default is GET

			BufferedReader in = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null
					&& !inputLine.contains(synCheck)) {
			}

			while ((inputLine = in.readLine()) != null
					&& !inputLine.contains("<name>")) {
				response.append("\n");
				response.append(inputLine);
			}

			bw.write(line + "," + inputLine);
			bw.write("\n");
			in.close();

			// print result
			System.out.println(inputLine);
		}
		br.close();
		bw.close();
	}

}
