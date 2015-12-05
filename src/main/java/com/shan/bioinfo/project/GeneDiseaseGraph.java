package com.shan.bioinfo.project;

import java.io.*;
import java.util.*;

public class GeneDiseaseGraph {

	private List<String> listOfGenes;
	private Map<String, ArrayList<String>> mapOfGenesAssocDisease;

	public void createDiseaseGeneMap() throws IOException {
		File raf = new File("outputs\\disGeneMap.txt");
		File raf2 = new File("outputs\\geneListDis.txt");
		FileReader fr = new FileReader(
				"C:\\Users\\SHANDEMETZ\\Downloads\\Compressed\\all_gene_disease_associations.txt\\all_gene_disease_associations.txt");
		BufferedReader br = new BufferedReader(fr);
		//BufferedWriter bw = new BufferedWriter(new FileWriter(new File("outputs\\diseaseNamesList.csv")));
		String line = "";
		String[] lineSplits = null;
		String geneId = "";
		String geneName = "";
		String diseaseId = "";
		String diseaseName = "";
		listOfGenes = new ArrayList<String>();
		mapOfGenesAssocDisease = new LinkedHashMap<String, ArrayList<String>>();
		//Map<String,String> mapOfDiseaseIdName = new HashMap<String,String>();
		br.readLine();
		while ((line = br.readLine()) != null) {
			lineSplits = line.split("\t");
			geneId = lineSplits[0];
			geneName = lineSplits[1];
			diseaseId = Arrays.asList(lineSplits[3].split(":")).get(1);
			diseaseName = lineSplits[4];
			/*if(!mapOfDiseaseIdName.containsKey(diseaseId)){
				mapOfDiseaseIdName.put(diseaseId, diseaseName);
				bw.write(diseaseId+","+diseaseName);
				bw.write("\n");
			}*/
			
			if (mapOfGenesAssocDisease.containsKey(diseaseId)) {
				ArrayList<String> listOfDiseaseAssoc = mapOfGenesAssocDisease
						.get(diseaseId);
				listOfDiseaseAssoc.add(geneName);
				if (!listOfGenes.contains(geneName)) {
					listOfGenes.add(geneName);
				}
				// Collections.sort(listOfDiseaseAssoc);
				mapOfGenesAssocDisease.put(diseaseId, listOfDiseaseAssoc);
			} else {
				ArrayList<String> listOfDiseaseAssoc = new ArrayList<String>();
				listOfDiseaseAssoc.add(geneName);
				if (!listOfGenes.contains(geneName)) {
					listOfGenes.add(geneName);
				}
				mapOfGenesAssocDisease.put(diseaseId, listOfDiseaseAssoc);
			}
		}
		br.close();
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(raf));
		oos.writeObject(mapOfGenesAssocDisease);
		oos.close();
		
		ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream(raf2));
		oos2.writeObject(listOfGenes);
		oos2.close();

	}

	public List<String> getListOfGenes() {
		return listOfGenes;
	}

	public void setListOfGenes(List<String> listOfGenes) {
		this.listOfGenes = listOfGenes;
	}

	public Map<String, ArrayList<String>> getMapOfGenesAssocDisease() {
		return mapOfGenesAssocDisease;
	}

	public void setMapOfGenesAssocDisease(
			Map<String, ArrayList<String>> mapOfGenesAssocDisease) {
		this.mapOfGenesAssocDisease = mapOfGenesAssocDisease;
	}

}
