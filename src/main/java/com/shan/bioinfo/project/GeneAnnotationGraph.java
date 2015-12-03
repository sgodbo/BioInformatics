package com.shan.bioinfo.project;

import java.io.*;
import java.util.*;

public class GeneAnnotationGraph {

	private Map<String, ArrayList<String>> mapOfGenesAssocDisease;
	private List<String> listOfGenes;

	public Map<String, ArrayList<String>> createAnnotationGeneMap()
			throws IOException {
		// TODO Auto-generated method stub
		File raf = new File("outputs\\annotGeneMap.txt");
		File raf2 = new File("outputs\\geneList1.txt");
		FileReader fr = new FileReader(
				new File(
						"C:\\Users\\SHANDEMETZ\\Downloads\\Compressed\\gene_association.goa_human\\gene_association.goa_human"));
		BufferedReader br = new BufferedReader(fr);
		
		String line = "";
		String[] lineSplits = null;

		String geneName = "";
		String annotationId = "";
		mapOfGenesAssocDisease = new LinkedHashMap<String, ArrayList<String>>();
		listOfGenes = new ArrayList<String>();
		for (int i = 0; i < 34; i++) {
			br.readLine();
		}

		while ((line = br.readLine()) != null) {
			lineSplits = line.split("\t");
			geneName = lineSplits[2];
			annotationId = Arrays.asList(lineSplits[4].split(":")).get(1);
			// annotationName = lineSplits[4];
			
			
			if (mapOfGenesAssocDisease.containsKey(annotationId)) {
				ArrayList<String> listOfDiseaseAssoc = mapOfGenesAssocDisease
						.get(annotationId);
				listOfDiseaseAssoc.add(geneName);
				// Collections.sort(listOfDiseaseAssoc);
				if (!listOfGenes.contains(geneName)) {
					listOfGenes.add(geneName);
				}
				mapOfGenesAssocDisease.put(annotationId, listOfDiseaseAssoc);
			} else {
				ArrayList<String> listOfDiseaseAssoc = new ArrayList<String>();
				listOfDiseaseAssoc.add(geneName);
				if (!listOfGenes.contains(geneName)) {
					listOfGenes.add(geneName);
				}
				mapOfGenesAssocDisease.put(annotationId, listOfDiseaseAssoc);
			}
		}
		br.close();
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(raf));
		oos.writeObject(mapOfGenesAssocDisease);
		oos.close();
		
		ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream(raf2));
		oos2.writeObject(listOfGenes);
		oos2.close();
		//bw.close();
		return mapOfGenesAssocDisease;
	}

	public Map<String, ArrayList<String>> getMapOfGenesAssocDisease() {
		return mapOfGenesAssocDisease;
	}

	public void setMapOfGenesAssocDisease(
			Map<String, ArrayList<String>> mapOfGenesAssocDisease) {
		this.mapOfGenesAssocDisease = mapOfGenesAssocDisease;
	}

	public List<String> getListOfGenes() {
		return listOfGenes;
	}

	public void setListOfGenes(List<String> listOfGenes) {
		this.listOfGenes = listOfGenes;
	}

}
