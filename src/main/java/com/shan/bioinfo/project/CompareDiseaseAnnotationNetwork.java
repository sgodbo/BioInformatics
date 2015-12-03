package com.shan.bioinfo.project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.Map.Entry;

public class CompareDiseaseAnnotationNetwork {

	Map<String, ArrayList<String>> mapAnnotationGene;
	Map<String, ArrayList<String>> mapDiseaseGene;
	static int sizeOfBagOfGenes;
	//static Iterator<Entry<String, ArrayList<String>>> dMapItr;
	//static Set<Entry<String, ArrayList<String>>> aMapItr;
	static Map<String, ArrayList<String>> synchronizedDisMap;

	static class ThreadApproach extends Thread {
		
		int counterForThread;
		boolean append = false;
		Entry<String,ArrayList<String>> entryAnnot;
		
		public ThreadApproach(Entry<String, ArrayList<String>> entry,
				int counter, int ctr) {
			// TODO Auto-generated constructor stub
			this.counterForThread = counter;
			if(ctr > 0){
				append = true;
			}
			this.entryAnnot = entry;
		}

		


		@SuppressWarnings("unchecked")
		public void run() {
			// TODO Auto-generated method stub

			//LocalAlignmentDiseasesAnnotations l1;
			ArrayList<String> string1;
			ArrayList<String> string2;
			double threshholdScore = 1.3d;

			try {

				BufferedWriter bw;
				bw = new BufferedWriter(new FileWriter(new File(
						"outputs/matching_coefficients" + counterForThread
								+ ".csv"),append));
				
				//while (dMapItr.hasNext()) {
				for(Entry<String,ArrayList<String>> entry: synchronizedDisMap.entrySet()){
					//Entry<String,ArrayList<String>> entry = dMapItr.next();
					string1 = entry.getValue();
					int l1 = string1.size();
					List<String> copyOfString1 = new ArrayList<String>();
					copyOfString1.addAll(string1);
						//Entry<String,ArrayList<String>> entry2 = aMapItr.next();
						string2 = entryAnnot.getValue();
						int l2 = string2.size();
						/*l1 = new LocalAlignmentDiseasesAnnotations(
								(ArrayList<String>) string1.clone(),
								(ArrayList<String>) string2.clone());*/
						
						List<String> copyOfString2 = new ArrayList<String>();
						copyOfString2.addAll(string2);
						copyOfString1.retainAll(copyOfString2);
						double score = 0.0d;
						if (copyOfString1.size() > 1) {
							StatSignificance s1 = new StatSignificance(
									copyOfString1.size(), l2, l1, sizeOfBagOfGenes);
							score = s1.calculateStatSignificance();}
						if (score > threshholdScore) {
							ListIterator<String> commItr = copyOfString1.listIterator();
							bw.write(entry.getKey() + " " + entryAnnot.getKey()+" "+l1+" "+l2+" "+copyOfString1.size()+" "+score+" ");
							while(commItr.hasNext()){ 
								bw.write(commItr.next() + " ");
							}
							bw.write("\n");
						}
					
				}
				bw.close();
				Thread.sleep(1000);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		//long startTime = System.currentTimeMillis();
		/*GeneAnnotationGraph g1 = new GeneAnnotationGraph();
		g1.createAnnotationGeneMap();
		GeneDiseaseGraph g2 = new GeneDiseaseGraph();
		g2.createDiseaseGeneMap();
		List<String> listOfGenes1 = g1.getListOfGenes();
		List<String> listOfGenes2 = g2.getListOfGenes();*/
		ObjectInputStream ois1 = new ObjectInputStream(new FileInputStream(new File("outputs\\annotGeneMap.txt")));
		ObjectInputStream ois2 = new ObjectInputStream(new FileInputStream(new File("outputs\\disGeneMap.txt")));
		ObjectInputStream ois3 = new ObjectInputStream(new FileInputStream(new File("outputs\\geneList1.txt")));
		ObjectInputStream ois4 = new ObjectInputStream(new FileInputStream(new File("outputs\\geneList2.txt")));
		List<String> listOfGenes1 = (List<String>) ois3.readObject();
		List<String> listOfGenes2 = (List<String>) ois4.readObject();
		sizeOfBagOfGenes = (listOfGenes1.size() > listOfGenes2.size()) ? listOfGenes1.size():listOfGenes2.size();
		Map<String, ArrayList<String>> mapAnnotationGene = (Map<String, ArrayList<String>>) ois1.readObject();
		Map<String, ArrayList<String>> mapDiseaseGene = (Map<String, ArrayList<String>>) ois2.readObject();

		ois1.close();
		ois2.close();
		ois3.close();
		ois4.close();
		
		
		//aMapItr = mapAnnotationGene.entrySet();
		int ctr = 0;
		for (Entry<String, ArrayList<String>> entry : mapAnnotationGene
				.entrySet()) {
			//dMapItr = mapDiseaseGene.entrySet().iterator();
			//ExecutorService execs = Executors.newFixedThreadPool(10);
			synchronizedDisMap = Collections.synchronizedMap(mapDiseaseGene);
			ThreadApproach[] tArr = new ThreadApproach[10];
			for (int counter = 0; counter < 10; counter++) {
				tArr[counter] = new ThreadApproach(entry, counter, ctr);
				tArr[counter].start();
			}
			/*for(int i = 0;i < 10;i++){
				tArr[i].stop();
			}*/
			
			ctr++;
			System.out.println(ctr);
		}
		

	}

}
