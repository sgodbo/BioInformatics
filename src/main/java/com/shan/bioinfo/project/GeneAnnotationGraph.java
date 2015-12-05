package com.shan.bioinfo.project;

import java.io.*;
import java.util.*;

public class GeneAnnotationGraph {

	private Map<String, ArrayList<String>> mapOfGenesAssocAnnot;
	private List<String> listOfGenes;

	public Map<String, ArrayList<String>> createAnnotationGeneMap()
			throws IOException {
		// TODO Auto-generated method stub
		File raf = new File("outputs\\annotGeneMap.txt");
		File raf2 = new File("outputs\\geneListAnnot.txt");
		FileReader fr = new FileReader(
				new File(
						"C:\\Users\\SHANDEMETZ\\Downloads\\Compressed\\gene_association.goa_human\\gene_association.goa_human"));
		BufferedReader br = new BufferedReader(fr);
		
		String line = "";
		String[] lineSplits = null;

		String geneName = "";
		String annotationId = "";
		mapOfGenesAssocAnnot = new LinkedHashMap<String, ArrayList<String>>();
		listOfGenes = new ArrayList<String>();
		for (int i = 0; i < 34; i++) {
			br.readLine();
		}

		while ((line = br.readLine()) != null) {
			lineSplits = line.split("\t");
			geneName = lineSplits[2];
			annotationId = Arrays.asList(lineSplits[4].split(":")).get(1);
			// annotationName = lineSplits[4];
			
			
			if (mapOfGenesAssocAnnot.containsKey(annotationId)) {
				ArrayList<String> listOfDiseaseAssoc = mapOfGenesAssocAnnot
						.get(annotationId);
				listOfDiseaseAssoc.add(geneName);
				// Collections.sort(listOfDiseaseAssoc);
				if (!listOfGenes.contains(geneName)) {
					listOfGenes.add(geneName);
				}
				mapOfGenesAssocAnnot.put(annotationId, listOfDiseaseAssoc);
			} else {
				ArrayList<String> listOfDiseaseAssoc = new ArrayList<String>();
				listOfDiseaseAssoc.add(geneName);
				if (!listOfGenes.contains(geneName)) {
					listOfGenes.add(geneName);
				}
				mapOfGenesAssocAnnot.put(annotationId, listOfDiseaseAssoc);
			}
		}
		br.close();
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(raf));
		oos.writeObject(mapOfGenesAssocAnnot);
		oos.close();
		
		ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream(raf2));
		oos2.writeObject(listOfGenes);
		oos2.close();
		//bw.close();
		return mapOfGenesAssocAnnot;
	}

	public Map<String, ArrayList<String>> getMapOfGenesAssocDisease() {
		return mapOfGenesAssocAnnot;
	}

	public void setMapOfGenesAssocDisease(
			Map<String, ArrayList<String>> mapOfGenesAssocDisease) {
		this.mapOfGenesAssocAnnot = mapOfGenesAssocDisease;
	}

	public List<String> getListOfGenes() {
		return listOfGenes;
	}

	public void setListOfGenes(List<String> listOfGenes) {
		this.listOfGenes = listOfGenes;
	}

}
