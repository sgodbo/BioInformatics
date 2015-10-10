package com.shan.bioinfo;


public class QuadraticTimeQuadraticSpaceDP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = " aattcg";
		String b = " acatcg";
		int m = a.length();
		int n = b.length();
		char[] aChar = a.toCharArray();
		char[] bChar = b.toCharArray();
		int fromLeft = 0;
		int fromTop = 0;
		int fromDiag = 0;
		int matchQuotient = 0;
		int[][] edMatrix = new int[m][n];
		for(int j = 0;j < m;j++){
			for(int i = 0;i < n;i++){
				matchQuotient = (aChar[i] == bChar[j]) ? 0:1;
				if(i > 0){
					fromLeft = edMatrix[j][i-1] + 1;
				}	else	{
					fromLeft = 100;
				}				
				if(j > 0){
					fromTop = edMatrix[j-1][i] + 1;
				}	else	{
					fromTop = 100;
				}
				if(i > 0 && j > 0){
					fromDiag = edMatrix[j-1][i-1] + matchQuotient;
				}	else	{
					fromDiag = 100;
				}
				edMatrix[j][i] = findMin(fromLeft,fromTop,fromDiag);
			}
		}
		
		for(int i = 0;i < m;i++){
			for(int j = 0;j< n;j++){
				System.out.print(edMatrix[i][j] + " ");
			}
			System.out.println();
		}
		
	}

	private static int findMin(int fromLeft, int fromTop, int fromDiag) {
		// TODO Auto-generated method stub
		int min1 = (fromLeft > fromTop) ? fromTop:fromLeft;
		int min2 = (fromTop > fromDiag) ? fromDiag:fromTop;
		int min = (min1 > min2) ? min2:min1;
		if(min == 100)
			return 0; 
		return min;
	}

}
