package com.shan.bioinfo.project;

public class SterlingsApprox {
	
	double numForFact;
	public SterlingsApprox(int n) {
		// TODO Auto-generated constructor stub
		this.numForFact = (double)n; 
	}

	public double calculateFact() {
		// TODO Auto-generated method stub
		double rootTwoPi = 2.5071326821120348744018252306904d;
		return Math.log10(rootTwoPi) + (numForFact+0.5)*Math.log10(numForFact) - Math.log10(numForFact);
	}

}
