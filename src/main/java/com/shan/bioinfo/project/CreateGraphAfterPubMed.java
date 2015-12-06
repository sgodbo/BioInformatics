package com.shan.bioinfo.project;

import java.util.*;
import java.util.Map.Entry;
import java.io.*;

import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.util.io.gml.GMLWriter;

public class CreateGraphAfterPubMed {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader(new File("outputs/diseaseFunctionScoreInPubMed2015.csv")));
		ObjectInputStream ois1 = new ObjectInputStream(new FileInputStream(new File("outputs\\annotGeneMap.txt")));
		ObjectInputStream ois2 = new ObjectInputStream(new FileInputStream(new File("outputs\\disGeneMap.txt")));
		
		Map<String, ArrayList<String>> mapAnnotationGene = (Map<String, ArrayList<String>>) ois1.readObject();
		Map<String, ArrayList<String>> mapDiseaseGene = (Map<String, ArrayList<String>>) ois2.readObject();
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("outputs/grphEdgeList.txt")));

		ois1.close();
		ois2.close();
		/*Graph graph = new Graph();
		GMLWriter gw1 = new GMLWriter(graph);*/
		for(Entry<String,ArrayList<String>> entry:mapAnnotationGene.entrySet()){
			String key = entry.getKey();
			ArrayList<String> value = entry.getValue();
			ListIterator<String> vItr = value.listIterator();
			bw.write(key);
			while(vItr.hasNext()){
				bw.write(" " + vItr.next());
			}
			bw.write("\n");
		}
		String line = "";
		String[] lineSplits;
		String diseaseId;
		String annotId;
		ArrayList<String> disGeneList = new ArrayList<String>();
		ArrayList<String> annotGeneList = new ArrayList<String>();
		List<Node> listOfNodes = new ArrayList<Node>();
		
		List<Edge> listOfEdges = new ArrayList<Edge>();
		int listCounter = 0;
		while(null != (line = br.readLine())){
			lineSplits = line.split(",");
			diseaseId = lineSplits[0];
			annotId = lineSplits[1];
			disGeneList = mapDiseaseGene.get(diseaseId);
			annotGeneList = mapAnnotationGene.get(annotId);
			annotGeneList.retainAll(disGeneList);
			ListIterator<String> aItr = annotGeneList.listIterator();
			String tempGene = "";
			List<Node> tempListOfNodes = new ArrayList<Node>();
			while (aItr.hasNext()) {
				tempGene = aItr.next();
				Node n = new Node(listCounter, tempGene);
				if (listOfNodes.isEmpty() || !containsNode(listOfNodes, tempGene)) {
					listOfNodes.add(n);
					listCounter++;
				}
				tempListOfNodes.add(n);
			}
			for(int i = 0;i < tempListOfNodes.size();i++){
				for(int j = i+1;j < tempListOfNodes.size();j++){
					Edge e = new Edge(tempListOfNodes.get(i).getId(),tempListOfNodes.get(j).getId(),1);
					listOfEdges.add(e);
				}
			}
		}
		br.close();
		for(int i = 0;i < listOfEdges.size();i++){
			bw.write(listOfEdges.get(i).getSource() + " "+listOfEdges.get(i).getTarget());
			bw.write("\n");
		}
		
		bw.close();
		
	}

	private static boolean containsNode(List<Node> listOfNodes, String tempGene) {
		// TODO Auto-generated method stub
		ListIterator<Node> nodeItr = listOfNodes.listIterator();
		boolean isPresent = false;
		while(nodeItr.hasNext()){
			Node n = nodeItr.next();
			if(n.getLabel().equals(tempGene)){
				isPresent = true;
				break;}
		}
		return isPresent;
	}


}
