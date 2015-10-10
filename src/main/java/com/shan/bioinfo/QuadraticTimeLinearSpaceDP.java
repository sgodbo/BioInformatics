package com.shan.bioinfo;


public class QuadraticTimeLinearSpaceDP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = " aattcg";
		String b = " acatcg";
		int m = a.length();
		int n = b.length();
		char[] aChar = a.toCharArray();
		char[] bChar = b.toCharArray();
		int fromLeft1 = 0;
		int fromTop1 = 0;
		int fromDiag1 = 0;
		int fromLeft2 = 0;
		int fromTop2 = 0;
		int fromDiag2 = 0;
		int matchQuotient = 0;
		//int[][] edMatrix = new int[m][n];
		int[] vector1 = new int[n];
		int[] vector2 = new int[n];
		for(int j = 0;j < m;j+=2){
			for(int i = 0;i < n;i++){
				matchQuotient = (aChar[i] == bChar[j]) ? 0:1;
				if(i > 0){
					fromLeft1 = vector1[i-1]+1;//edMatrix[j][i-1] + 1;
				}	else	{
					fromLeft1 = 100;
				}				
				if(j > 0){
					fromTop1 = vector2[i] + 1;
				}	else	{
					fromTop1 = 100;
				}
				if(i > 0 && j > 0){
					fromDiag1 = vector2[i-1] + matchQuotient;
				}	else	{
					fromDiag1 = 100;
				}
				vector1[i] = findMin(fromLeft1,fromTop1,fromDiag1);
							
			}
			
			for(int i = 0;i < n && j < m-1;i++){
				matchQuotient = (aChar[i] == bChar[j+1]) ? 0:1;
				if(i > 0){
					fromLeft2 = vector2[i-1]+1;//edMatrix[j][i-1] + 1;
					fromDiag2 = vector1[i-1] + matchQuotient;
				}	else	{
					fromLeft2 = 100;
					fromDiag2 = 100;
				}
				fromTop2 = vector1[i] + 1;
				
				vector2[i] = findMin(fromLeft2,fromTop2,fromDiag2);
			}
		}
		
		for(int i = 0;i < n;i++){
			System.out.print(vector1[i] + " ");
		}
		System.out.println();
		for(int i = 0;i < n;i++){
			System.out.print(vector2[i] + " ");
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
