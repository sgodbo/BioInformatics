package com.shan.bioinfo.project;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.xml.sax.SAXException;

public class CheckForDisAnnotSimInPubMed {

	public static void main(String[] args) throws IOException,
			XPathExpressionException, ParserConfigurationException,
			SAXException {
		BufferedReader br1 = new BufferedReader(new FileReader(new File(
				"outputs\\annotationNamesList.csv")));
		BufferedReader br2 = new BufferedReader(new FileReader(new File(
				"outputs\\diseaseNamesList.csv")));
		BufferedReader br3;
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
				"outputs\\diseaseFunctionScoreInPubMed2012.txt")));
		Map<String, String> diseaseMap = new HashMap<String, String>();
		Map<String, String> annotationMap = new HashMap<String, String>();
		Map<String, String> diseaseAnnotationMap = new HashMap<String, String>();
		String line1 = "";
		String[] line1Splits;
		String line2 = "";
		String[] line2Splits;
		String line3 = "";
		String[] line3Splits;
		String diseaseString = "";
		int disScore = 0;
		String annotationString = "";
		int funcScore = 0;
		int comScore = 0;
		double finalScore = 0.0d;
		int n = 20000000;
		PubMedQuery q1 = new PubMedQuery();
		while ((line1 = br1.readLine()) != null) {
			line1Splits = line1.split(",");
			annotationMap.put(line1Splits[0], line1Splits[1]);
		}
		br1.close();
		while ((line2 = br2.readLine()) != null) {
			line2Splits = line2.split(",");
			diseaseMap.put(line2Splits[0], line2Splits[1]);
		}
		br2.close();
		for (int i = 0; i < 15; i++) {
			br3 = new BufferedReader(new FileReader(new File(
					"outputs\\oldOutputs\\matching_coefficients" + i + ".csv")));
			while (null != (line3 = br3.readLine())) {
				line3Splits = line3.split(",");
				String diseaseId = line3Splits[0];
				String annotId = line3Splits[1];
				String diseaseName = diseaseMap.get(diseaseId);
				String annotName = annotationMap.get(annotId);
				diseaseAnnotationMap.put(diseaseName, annotName);
			}
		}

		for (Entry<String, String> entry : diseaseAnnotationMap.entrySet()) {
			diseaseString = entry.getKey();
			annotationString = entry.getValue();
			diseaseString = diseaseString.replaceAll(" ", "%20");
			annotationString = annotationString.replaceAll(" ", "%20");
			disScore = q1.countPaperHits(diseaseString);
			funcScore = q1.countPaperHits(annotationString);
			comScore = q1.countPaperHits(diseaseString + "%20"
					+ annotationString);
			
			bw.write(diseaseString + "," + annotationString + ","
					+ "," + disScore + "," + funcScore + "," + comScore);
			System.out.println(diseaseString + "," + annotationString + ","
					+ "," + disScore + "," + funcScore + "," + comScore);
			bw.write("\n");
			/*StatSignificance s1 = new StatSignificance(cummScore, disScore,
					funcScore, n);
			finalScore = s1.calculateStatSignificance();
			if (finalScore > 0.0d) {
				diseaseString = diseaseString.replaceAll("%20", " ");
				annotationString = annotationString.replaceAll("%20", " ");
				bw.write(diseaseString + "," + annotationString + ","
						+ finalScore);
				System.out.println(diseaseString + "," + annotationString + ","
						+ finalScore);
				bw.write("\n");
			}*/
			System.out.println();
		}
		bw.close();
	}
}
