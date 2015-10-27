package com.shan.bioinfo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import java.util.Map.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.collect.MapDifference;

public class CompareDiseaseAnnotationNetwork {

	Map<String, ArrayList<String>> mapAnnotationGene;
	Map<String, ArrayList<String>> mapDiseaseGene;
	static Iterator<Entry<String, ArrayList<String>>> dMapItr;
	static Set<Entry<String, ArrayList<String>>> aMapItr;

	static class ThreadApproach implements Runnable {
		int counterForThread;

		public ThreadApproach(int counter) {
			// TODO Auto-generated constructor stub
			this.counterForThread = counter;
		}

		@SuppressWarnings("unchecked")
		public synchronized void run() {
			// TODO Auto-generated method stub

			LocalAlignmentDiseasesAnnotations l1;
			ArrayList<String> string1;
			ArrayList<String> string2;
			double score;
			double threshholdScore = 0.0d;

			try {

				BufferedWriter bw;
				bw = new BufferedWriter(new FileWriter(new File(
						"outputs/matching_coefficients" + counterForThread
								+ ".csv")));
				while (dMapItr.hasNext()) {
					Entry<String,ArrayList<String>> entry = dMapItr.next();
					string1 = entry.getValue();
					for (Entry<String,ArrayList<String>> entry2:aMapItr) {
						//Entry<String,ArrayList<String>> entry2 = aMapItr.next();
						string2 = entry2.getValue();
						l1 = new LocalAlignmentDiseasesAnnotations(
								(ArrayList<String>) string1.clone(),
								(ArrayList<String>) string2.clone());
						score = l1.findMaxScoreOfMatch();
						if (score > threshholdScore) {
							bw.write(entry.getKey() + "," + entry2.getKey()
									+ "," + score + "\n");
							// bw.write("\n");
						}
					}
				}
				bw.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		long startTime = System.currentTimeMillis();
		GeneAnnotationGraph g1 = new GeneAnnotationGraph();
		GeneDiseaseGraph g2 = new GeneDiseaseGraph();
		Map<String, ArrayList<String>> mapAnnotationGene = g1
				.createAnnotationGeneMap();
		Map<String, ArrayList<String>> mapDiseaseGene = g2
				.createDiseaseGeneMap();
		ArrayList<String> string2;
		ArrayList<String> string1;
		LocalAlignmentDiseasesAnnotations l1;
		double score;
		double threshholdScore = 0.0d;

		ExecutorService execs = Executors.newFixedThreadPool(15);
		dMapItr = mapDiseaseGene.entrySet().iterator();
		aMapItr = mapAnnotationGene.entrySet();
		for (int counter = 0; counter < 15; counter++) {
			execs.execute(new ThreadApproach(counter));
		}
		execs.shutdown();
		System.out.println(System.currentTimeMillis() - startTime);

	}

}
