package com.shan.bioinfo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
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

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

public class GeneAnnotationGraph {

	public Map<String, ArrayList<String>> createAnnotationGeneMap() throws IOException {
		// TODO Auto-generated method stub
		FileReader fr = new FileReader(new File("C:\\Users\\SHANDEMETZ\\Downloads\\Compressed\\gene_association.goa_human\\gene_association.goa_human"));
		BufferedReader br = new BufferedReader(fr);
		String line = "";
		String[] lineSplits = null;
		
		String geneName = "";
		String annotationId = "";
		String annotationName = "";
		List<String> listOfNodes = new ArrayList<String>();
		//graph = new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		Map<String,ArrayList<String>> mapOfGenesAssocDisease = new LinkedHashMap<String,ArrayList<String>>();
		
		int j = 0;
		for(int i = 0;i < 34;i++){
			br.readLine();
		}
		
		while((line = br.readLine()) != null){
			lineSplits = line.split("\t");
			geneName = lineSplits[2];
			annotationId = Arrays.asList(lineSplits[4].split(":")).get(1);
			//annotationName = lineSplits[4];
			if(mapOfGenesAssocDisease.containsKey(annotationId)){
				ArrayList<String> listOfDiseaseAssoc = mapOfGenesAssocDisease.get(annotationId);
				listOfDiseaseAssoc.add(geneName);
				Collections.sort(listOfDiseaseAssoc);
				mapOfGenesAssocDisease.put(annotationId, listOfDiseaseAssoc);
			}	else{
				ArrayList<String> listOfDiseaseAssoc = new ArrayList<String>();
				listOfDiseaseAssoc.add(geneName);
				mapOfGenesAssocDisease.put(annotationId, listOfDiseaseAssoc);
			}
			/*if(!listOfNodes.contains(geneId)){
				listOfNodes.add(geneId);
			}*/
			j++;
			//System.out.println(j);
		}
		br.close();
		return mapOfGenesAssocDisease;
		/*BufferedWriter bw = new BufferedWriter(new FileWriter(new File("filtered_output_annotation.txt")));
		for(Entry<String,ArrayList<String>> entry:mapOfGenesAssocDisease.entrySet()){
			bw.write(entry.getKey() + "\t-\t");
			ListIterator<String> gItr = entry.getValue().listIterator();
			while(gItr.hasNext()){
				bw.write(gItr.next() + " ");
			}
			bw.write("\n");
		}*/
		
		//CreateCliqueForDisease(mapOfGenesAssocDisease);
		

	
	}

}
