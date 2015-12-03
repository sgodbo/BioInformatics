package com.shan.bioinfo.project;


public class StatSignificance {
	
	private int k;
	private int s;
	private int m;
	private int n;
	StatSignificance(int k,int s,int m,int n) {
		this.k = k;
		this.s = s;
		this.m = m;
		this.n = n;}
	public double calculateStatSignificance(){
		
		int start = 0;
		int end = 0;
		int smaller = s<m?s:m;
		int larger = s>m?s:m;
		int remS2 = n - smaller;
		if(k < smaller/2){
			start = 1;
			end = k;
		}	else{
			start = k+1;
			end = smaller;
		}
		double sum1 = 0.0d;
		
		CombinationsCalc c1 = new CombinationsCalc(n, larger);
		double totalExp = c1.calculateResultInPower();
		
		for (int j = start-1; j < end; j++) {
			CombinationsCalc c2 = new CombinationsCalc(smaller, j);
			double chooseIFromS1 = c2.calculateResultInPower();
			CombinationsCalc c3 = new CombinationsCalc(remS2, larger - j);
			double chooseFromS2 = c3.calculateResultInPower();
			sum1 += Math.pow(10, (chooseIFromS1 + chooseFromS2 - totalExp));
			//System.out.println(sum1);
		}
		
		
		/*double constantTerms = (smaller + 0.5) * Math.log10(smaller)
				+ (n - smaller + 0.5) * Math.log10(n - smaller)
				+ (larger + 0.5) * Math.log10(larger) + (n - larger + 0.5)
				* Math.log10(n - larger) - (n + 0.5) * Math.log10(n) - 0.39917731823596d;
		for (int j = start; j < end+1; j++) {
			double changingTerms = (smaller - j + 0.5)
					* Math.log10(smaller - j) + (j + 0.5) * Math.log10(j)
					+ (n - smaller - larger + j + 0.5)
					* Math.log10(n - smaller - larger + j) + (s - j + 0.5)
					* Math.log10(s - j);
			double diff = constantTerms - changingTerms;
			double b1 = Math.pow(10,diff); 
			sum1 += b1;
			//System.out.println(sum1);
		}*/
		//System.out.println(k);
		//System.out.println(s);
		//System.out.println(m);
		//System.out.println(sum1);
		double result = 0.0d;
		if(k == end){
			double temp = 1+Math.log10(sum1);
			result += temp;
		}	else{
			double temp = -Math.log10(sum1);
			result += temp;
		}
		
		//System.out.println("result -> "+result);
		//System.out.println();
		//System.out.println(Math.log10(sum)+","+Math.log10(sum1));
		return result;
	}
	
	

}
