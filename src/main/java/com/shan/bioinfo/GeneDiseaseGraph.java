package com.shan.bioinfo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
public class GeneDiseaseGraph {
	static WeightedGraph<String, DefaultWeightedEdge> graph;
	

	public Map<String, ArrayList<String>> createDiseaseGeneMap() throws IOException {
		FileReader fr = new FileReader("C:\\Users\\SHANDEMETZ\\Downloads\\Compressed\\all_gene_disease_associations.txt\\all_gene_disease_associations.txt");
		BufferedReader br = new BufferedReader(fr);
		String line = "";
		String[] lineSplits = null;
		String geneId = "";
		String geneName = "";
		String diseaseId = "";
		String diseaseName = "";
		List<String> listOfNodes = new ArrayList<String>();
		graph = new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		Map<String,ArrayList<String>> mapOfGenesAssocDisease = new LinkedHashMap<String,ArrayList<String>>();
		int i = 0;
		int j = 0;
		br.readLine();
		while((line = br.readLine()) != null){
			lineSplits = line.split("\t");
			geneId = lineSplits[0];
			geneName = lineSplits[1];
			diseaseId = Arrays.asList(lineSplits[3].split(":")).get(1);
			diseaseName = lineSplits[4];
			if(mapOfGenesAssocDisease.containsKey(diseaseId)){
				ArrayList<String> listOfDiseaseAssoc = mapOfGenesAssocDisease.get(diseaseId);
				listOfDiseaseAssoc.add(geneName);
				Collections.sort(listOfDiseaseAssoc);
				mapOfGenesAssocDisease.put(diseaseId, listOfDiseaseAssoc);
			}	else{
				ArrayList<String> listOfDiseaseAssoc = new ArrayList<String>();
				listOfDiseaseAssoc.add(geneName);
				mapOfGenesAssocDisease.put(diseaseId, listOfDiseaseAssoc);
			}
			/*if(!listOfNodes.contains(geneId)){
				listOfNodes.add(geneId);
			}*/
			j++;
			//System.out.println(j);
		}
		br.close();
		/*BufferedWriter bw = new BufferedWriter(new FileWriter(new File("filtered_output_disease.txt")));
		for(Entry<String,ArrayList<String>> entry:mapOfGenesAssocDisease.entrySet()){
			bw.write(entry.getKey() + "\t-\t");
			ListIterator<String> gItr = entry.getValue().listIterator();
			while(gItr.hasNext()){
				bw.write(gItr.next() + " ");
			}
			bw.write("\n");
		}*/
		return mapOfGenesAssocDisease;
		//CreateCliqueForDisease(mapOfGenesAssocDisease);
		

	}

	private static void CreateCliqueForDisease(
			Map<String, ArrayList<String>> mapOfGenesAssocDisease) {
		// TODO Auto-generated method stub
		for(Entry<String,ArrayList<String>> entry:mapOfGenesAssocDisease.entrySet()){
			//bw.write(entry.getKey() + "\t-\t");
			System.out.println(entry.getKey());
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			ArrayList<String> value = entry.getValue();
			for(int i = 0;i < value.size();i++){
				for(int j = i+1;j < value.size();j++){
					if(!graph.containsEdge(value.get(i),value.get(j))){ 
						if(!graph.containsEdge(value.get(j),value.get(i))){
							DefaultWeightedEdge e1 = graph.addEdge(value.get(i),value.get(j));
							graph.setEdgeWeight(e1, 1);
							System.out.println(e1.toString());
							}	else{
								DefaultWeightedEdge e1 = graph.getEdge(value.get(j),value.get(i));
								graph.setEdgeWeight(e1, graph.getEdgeWeight(e1));
								System.out.println(e1.toString());
							}
						
						
					}	else{
						DefaultWeightedEdge e1 = graph.getEdge(value.get(i),value.get(j));
						graph.setEdgeWeight(e1, graph.getEdgeWeight(e1));
						System.out.println(e1.toString());
					}
					
				}
			}
		}
	}


}
