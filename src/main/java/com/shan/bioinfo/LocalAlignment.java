package com.shan.bioinfo;

import java.util.*;

public class LocalAlignment {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> seq1 = new ArrayList<String>();
		List<String> seq2 = new ArrayList<String>();

		// TODO Auto-generated method stub
		String a = " GCTGGAAGGCAT";
		//String a = " GCAGAGCACGTC";
		String b = " GCAGAGCACG";
		int m = a.length();
		int n = b.length();
		char[] aChar = a.toCharArray();
		char[] bChar = b.toCharArray();
		int fromLeft = 0;
		int fromTop = 0;
		int fromDiag = 0;
		int matchValue = 5;
		int matchQuotient = 0;
		int misMatchValue = -4;
		int[][] edMatrix = new int[m][n];
		int max = 0;
		int minDim = (m > n) ? n:m;
		for(int i = 1;i < n;i++){
			for(int j = 1;j < m;j++){
				matchQuotient = (aChar[j] == bChar[i]) ? matchValue:misMatchValue;
				fromLeft = edMatrix[j][i-1] + misMatchValue;			
				fromTop = edMatrix[j-1][i] + misMatchValue;
				fromDiag = edMatrix[j-1][i-1] + matchQuotient;
				
				edMatrix[j][i] = findMax(fromLeft,fromTop,fromDiag);
				if(edMatrix[j][i] > max){
					max = edMatrix[j][i];
				}
			}
		}
		
		for(int i = 0;i < n;i++){
			for(int j = 0;j< m;j++){
				System.out.print(edMatrix[j][i] + " ");
			}
			System.out.println();
		}
		
		//System.out.println((float)max/(matchValue*minDim));
		
	
	}
	private static int findMax(int fromLeft, int fromTop, int fromDiag) {
		// TODO Auto-generated method stub
		int max1 = (fromLeft < fromTop) ? fromTop:fromLeft;
		int max2 = (fromTop < fromDiag) ? fromDiag:fromTop;
		int max = (max1 < max2) ? max2:max1;
		if(max < 0)
			max = 0;			 
		return max;
	}

}
