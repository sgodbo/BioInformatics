package com.shan.bioinfo.project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

public class CheckForDisAnnotSimInPubMed {

	static Map<String, String[]> diseaseMap = new HashMap<String, String[]>();
	static Map<String, String[]> annotationMap = new HashMap<String, String[]>();

	public static void main(String[] args)
			throws IOException, XPathExpressionException, ParserConfigurationException, SAXException {
		BufferedReader br1 = new BufferedReader(new FileReader(new File("outputs/annotNameList.csv")));
		BufferedReader br2 = new BufferedReader(new FileReader(new File("outputs/diseaseNameList.csv")));
		String line1 = "";
		String[] line1Splits;
		String line2 = "";
		String[] line2Splits;
		while ((line1 = br1.readLine()) != null) {
			line1Splits = line1.split(",");
			annotationMap.put(line1Splits[0], new String[] { line1Splits[1], "" });
		}
		br1.close();
		while ((line2 = br2.readLine()) != null) {
			line2Splits = line2.split(",");
			diseaseMap.put(line2Splits[0], new String[] { line2Splits[1], "" });
		}
		br2.close();
		for (int i = 0; i < 13; i++) {
			new MyThread(i).start();
		}

	}

	private static class MyThread extends Thread {
		int id;

		MyThread(int threadId) {
			id = threadId;
		}

		@Override
		public void run() {
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
			BufferedReader br3 = null;
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(
						new FileWriter(new File("outputs/diseaseFunctionScoreInPubMed2015_" + id + ".csv")));

				br3 = new BufferedReader(new FileReader(new File("outputs/matching_coefficients" + id + ".csv")));
				while (null != (line3 = br3.readLine())) {
					try {
						line3Splits = line3.split(" ");
						String diseaseId = line3Splits[0];
						String annotId = line3Splits[1];
						String diseaseName = diseaseMap.get(diseaseId)[0];
						String annotName = annotationMap.get(annotId)[0];
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
								&& (null != annotationString && !annotationString.isEmpty())) {
							if (diseaseMap.get(diseaseId)[1].equals("")) {
								disScore = q1.countPaperHits(diseaseString);
								diseaseMap.get(diseaseId)[1] = "" + disScore;
							} else {
								disScore = Integer.valueOf(diseaseMap.get(diseaseId)[1]);
							}
	
							if (annotationMap.get(annotId)[1].equals("")) {
								funcScore = q1.countPaperHits(annotationString);
								annotationMap.get(annotId)[1] = "" + funcScore;
							} else {
								funcScore = Integer.valueOf(annotationMap.get(annotId)[1]);
							}
							comScore = q1.countPaperHits(diseaseString + "%20" + annotationString);
	
							StatSignificance s1 = new StatSignificance(comScore, disScore, funcScore, n);
							finalScore = s1.calculateStatSignificance();
							if (finalScore > 1.3d) {
								diseaseString = diseaseString.replaceAll("%20", " ");
								annotationString = annotationString.replaceAll("%20", " ");
								//bw = new BufferedWriter(new FileWriter(new File("outputs/diseaseFunctionScoreInPubMed2015.csv"), true));
								System.out.println(id+"-->ThreadID  "+diseaseId + "," + annotId + "," + disScore + "," + funcScore + ","
										+ comScore + "," + finalScore);
								bw.write(diseaseId + "," + annotId + "," + disScore + "," + funcScore + "," + comScore + ","
										+ finalScore+"\n");
								bw.flush();
							}
						}
					} catch (Exception e) {
						System.out.print(id+"-->ThreadID  " + "," + "," + disScore + "," + funcScore + ","
								+ comScore + "," + finalScore);
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					br3.close();
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
}
