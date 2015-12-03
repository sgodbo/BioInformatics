package com.shan.bioinfo.project;

public class CombinationsCalc {

	private int topNum;
	private int bottomNum;

	public CombinationsCalc(int num1, int num2) {
		// TODO Auto-generated constructor stub
		this.topNum = num1;
		this.bottomNum = num2;
	}

	public long calculateResult() {
		double incSum = 0;
		double decSum = 0;
		for (int i = 0; i < bottomNum; i++) {
			incSum += Math.log10(topNum - i);
			decSum += Math.log10(i + 1);
		}

		//return incSum - decSum;
		return (long) Math.exp(incSum-decSum);
	}
	
	public double calculateResultInPower() {
		double incSum = 0;
		double decSum = 0;
		for (int i = 0; i < bottomNum; i++) {
			incSum += Math.log10(topNum - i);
			decSum += Math.log10(i + 1);
		}
		if(incSum - decSum == 0){
			return 1.0;
		}	else{
			return incSum - decSum;
		}
		//return (long) Math.exp(incSum-decSum);
	}
	
}
