package com.shan.bioinfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LocalAlignmentDiseasesAnnotations {

	private ArrayList<String> sequence1;
	private ArrayList<String> sequence2;
	
	LocalAlignmentDiseasesAnnotations(ArrayList<String> seq1,ArrayList<String> seq2){
		this.sequence1 = seq1;
		this.sequence2 = seq2;
	}
	public double findMaxScoreOfMatch() throws IOException {
		// TODO Auto-generated method stub
		
		sequence1.add(0," ");
		sequence2.add(0," ");
		// TODO Auto-generated method stub
		//String a = " GCTGGAAGGCAT";
		//String a = " GCAGAGCACGTC";
		//String b = " GCAGAGCACG";
		int m = sequence1.size();//a.length();
		int n = sequence2.size();//b.length();
		//char[] aChar = a.toCharArray();
		//char[] bChar = b.toCharArray();
		int fromLeft = 0;
		int fromTop = 0;
		int fromDiag = 0;
		int fromLeft2 = 0;
		int fromTop2 = 0;
		int fromDiag2 = 0;
		int matchValue = 5;
		int matchQuotient = 0;
		int matchQuotient2 = 0;
		int misMatchValue = -4;
		//int[][] edMatrix = new int[m][n];
		int[] vector1 = new int[m];
		int[] vector2 = new int[m];
		int max = 0;
		int tempMax = 0;
		int minDim = (m > n) ? n:m;
		for(int i = 0;i < n;i+=2){
			for(int j = 1;j < m;j++){
				
				if(i > 0){
					matchQuotient = (sequence1.get(j).equals(sequence2.get(i))) ? matchValue:misMatchValue;
					fromLeft = vector1[j-1] + misMatchValue;			
					fromTop = vector2[j] + misMatchValue;
					fromDiag = vector2[j-1] + matchQuotient;
					vector1[j] = findMax(fromLeft, fromTop, fromDiag);
					if(tempMax > max){
						max = tempMax;
					}
				}
			}
			for(int j = 1;j < m;j++){
				if(i!=n-1){
					matchQuotient2 = (sequence1.get(j).equals(sequence2.get(i+1))) ? matchValue:misMatchValue;
					fromLeft2 = vector2[j-1] + misMatchValue;
					fromTop2 = vector1[j] + misMatchValue;
					fromDiag2 = vector1[j-1] + matchQuotient2;
				
					vector2[j] = findMax(fromLeft2,fromTop2,fromDiag2);
					tempMax = (vector1[j] > vector2[j]) ? vector1[j]:vector2[j];}
				if(tempMax > max){
					max = tempMax;
				}
				
			}
			/*for(int z = 0;z < vector1.length;z++)
				System.out.print(vector1[z] + " ");
			System.out.println();
			for(int y = 0;y < vector2.length;y++)
				System.out.print(vector2[y] + " ");
			System.out.println();*/
		}
		
		double matchingCoefficient = (double)max/(matchValue*minDim);
		//System.out.println(matchingCoefficient);
		
		return matchingCoefficient;
	}
	private int findMax(int fromLeft, int fromTop, int fromDiag) {
		// TODO Auto-generated method stub
		int max1 = (fromLeft < fromTop) ? fromTop:fromLeft;
		int max2 = (fromTop < fromDiag) ? fromDiag:fromTop;
		int max = (max1 < max2) ? max2:max1;
		if(max < 0)
			max = 0;			 
		return max;
	}

}
