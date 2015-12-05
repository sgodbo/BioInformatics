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
				"outputs\\annotNameList.csv")));
		BufferedReader br2 = new BufferedReader(new FileReader(new File(
				"outputs\\diseaseNameList.csv")));
		BufferedReader br3;
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
				"outputs\\diseaseFunctionScoreInPubMed2015.csv")));
		Map<String, String> diseaseMap = new HashMap<String, String>();
		Map<String, String> annotationMap = new HashMap<String, String>();
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
		for (int i = 0; i < 13; i++) {
			br3 = new BufferedReader(new FileReader(new File(
					"outputs\\matching_coefficients" + i + ".csv")));
			while (null != (line3 = br3.readLine())) {
				line3Splits = line3.split(" ");
				String diseaseId = line3Splits[0];
				String annotId = line3Splits[1];
				String diseaseName = diseaseMap.get(diseaseId);
				String annotName = annotationMap.get(annotId);
				ArrayList<String> disAndAnnot = new ArrayList<String>();
				disAndAnnot.add(diseaseName);
				disAndAnnot.add(annotName);
				// listOfDisAnnots.add(disAndAnnot);
				// diseaseAnnotationMap.put(diseaseName, annotName);

				diseaseString = disAndAnnot.get(0);
				annotationString = disAndAnnot.get(1);
				diseaseString = diseaseString.replaceAll(" ", "%20");
				annotationString = annotationString.replaceAll(" ", "%20");
				if ((null != diseaseString && !diseaseString.isEmpty())
						&& (null != annotationString && !annotationString
								.isEmpty())) {
					disScore = q1.countPaperHits(diseaseString);
					funcScore = q1.countPaperHits(annotationString);
					comScore = q1.countPaperHits(diseaseString + "%20"
							+ annotationString);

					StatSignificance s1 = new StatSignificance(comScore,
							disScore, funcScore, n);
					finalScore = s1.calculateStatSignificance();
					if (finalScore > 1.3d) {
						diseaseString = diseaseString.replaceAll("%20", " ");
						annotationString = annotationString.replaceAll("%20",
								" ");
						bw = new BufferedWriter(
								new FileWriter(
										new File(
												"outputs\\diseaseFunctionScoreInPubMed2015.csv"),
										true));
						System.out
								.println(diseaseId + "," + annotId
										+ "," + disScore + "," + funcScore
										+ "," + comScore + "," + finalScore);
						bw.write(diseaseId + "," + annotId + ","
								+ disScore + "," + funcScore + "," + comScore
								+ "," + finalScore);
						bw.write("\n");

						bw.close();
					}
				}

			}
		}

	}
}
