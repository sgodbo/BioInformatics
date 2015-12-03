package com.shan.bioinfo.project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class TestClass {
	public static void main(String[] inputString) {
		

		int k = 1;
		int s = 88;
		int M = 401;
		int N = 17611;
		/*int k = 80;
		int s = 100;
		int M = 100;
		int N = 17611;*/
		int start = 0;
		int end = 0;
		int smaller = s<M?s:M;
		int larger = s>M?s:M;
		int remS2 = N - smaller;
		if(k < smaller/2){
			start = 1;
			end = k;
		}	else{
			start = k+1;
			end = smaller;
		}
		long startTime = System.currentTimeMillis();
		double sum1 = 0;
		CombinationsCalc c1 = new CombinationsCalc(N, larger);
		double totalExp = c1.calculateResultInPower();
		
		for (int j = start-1; j < end; j++) {
			CombinationsCalc c2 = new CombinationsCalc(smaller, j);
			double chooseIFromS1 = c2.calculateResultInPower();
			CombinationsCalc c3 = new CombinationsCalc(remS2, larger - j);
			double chooseFromS2 = c3.calculateResultInPower();
			sum1 += Math.pow(10, (chooseIFromS1 + chooseFromS2 - totalExp));
			//System.out.println(sum1);
		}
		
		System.out.println(-Math.log10(sum1));
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(System.currentTimeMillis()-startTime);
		double sum2 = 0.0d;
		//BigDecimal sum2 = new BigDecimal(0);
		long startTime1 = System.currentTimeMillis(); 
		/*double totalExp1 = (N+0.5)*Math.log10(N) - (N-larger+0.5)*Math.log10(N-larger) - (larger+0.5)*Math.log10(larger) - Math.log10(2.50713268); 
			
		for (int j = start; j < end; j++) {
			double chooseIFromS1 = (smaller+0.5)*Math.log10(smaller) - (smaller-j+0.5)*Math.log10(smaller-j) - (j+0.5)*Math.log10(j) - Math.log10(2.50713268);
			double chooseFromS2 = (remS2+0.5)*Math.log10(remS2) - (remS2-larger+j+0.5)*Math.log10(remS2-larger) - (larger-j+0.5)*Math.log10(larger-j) - Math.log10(2.50713268);
			sum2 += Math.pow(10, (chooseIFromS1 + chooseFromS2 - totalExp1));
			System.out.println(sum2);
		}*/
		/*double totalExp1 = new SterlingsApprox(N).calculateFact() - new SterlingsApprox(larger).calculateFact() - new SterlingsApprox(N-larger).calculateFact(); 
		
		for (int j = start; j < end; j++) {
			double chooseIFromS1 = new SterlingsApprox(smaller).calculateFact() - new SterlingsApprox(j).calculateFact() - new SterlingsApprox(smaller-j).calculateFact();
			double chooseFromS2 = new SterlingsApprox(remS2).calculateFact() - new SterlingsApprox(larger-j).calculateFact() - new SterlingsApprox(remS2-larger+j).calculateFact();
			sum2 += Math.pow(10, (chooseIFromS1 + chooseFromS2 - totalExp1));
			System.out.println(sum2);
		}*/
		double constantTerms = (smaller + 0.5) * Math.log10(smaller)
				+ (N - smaller + 0.5) * Math.log10(N - smaller)
				+ (larger + 0.5) * Math.log10(larger) + (N - larger + 0.5)
				* Math.log10(N - larger) - 0.39917731823596d;
		/*for (int j = start; j < end; j++) {
			double changingTerms = (smaller - k + 0.5)
					* Math.log10(smaller - k) + (k + 0.5) * Math.log10(k)
					+ (N - smaller - larger + k + 0.5)
					* Math.log10(N - smaller - larger + k) + (s - k + 0.5)
					* Math.log10(s - k);
			BigDecimal b1 = new BigDecimal(Math.pow(10, changingTerms)); 
			sum2.add(b1);
			//System.out.println(sum1);
		}*/
		for (int j = start; j < end+1; j++) {
			/*double chooseIFromS1 = new SterlingsApprox(smaller).calculateFact()
					- new SterlingsApprox(j).calculateFact()
					- new SterlingsApprox(smaller - j).calculateFact();
			double chooseFromS2 = new SterlingsApprox(remS2).calculateFact()
					- new SterlingsApprox(larger - j).calculateFact()
					- new SterlingsApprox(remS2 - larger + j).calculateFact();*/
			double changingTerms = (smaller - j + 0.5)
					* Math.log10(smaller - j) + (j + 0.5) * Math.log10(j)
					+ (N - smaller - larger + j + 0.5)
					* Math.log10(N - smaller - larger + j) + (s - j + 0.5)
					* Math.log10(s - j);
			double diff = constantTerms - changingTerms - (N + 0.5) * Math.log10(N);
			double b1 = Math.pow(10, diff);
			sum2 += (b1);
			//System.out.println(sum2);
		}
		double result = 0.0d;
		if(k == end){
			double temp = 1+Math.log10(sum2);
			result += temp;
		}	else{
			double temp = -Math.log10(sum2) ;
			result += temp;
		}
		System.out.println(sum2);
		System.out.println(constantTerms);
		System.out.println(result);
		double sum3 = 0.0d;
		for (int j = start; j < end+1; j++) {

			double mReduced = calcReducedSum(smaller, j);
			double nmReduced = calcReducedSum(N-smaller, j);
			double nReduced = calcReducedSum(N, s);
			
			double numerator = Math.log10(mReduced) + Math.log10(nmReduced) + Math.log10((larger*(larger+1))/2);
			double denominator = Math.log10((j*(j+1))/2) + Math.log10(((larger-k)*(larger-k+1))/2) + Math.log10(nReduced);
			double diff = numerator - denominator;
			
			
			double b1 = Math.pow(10, diff);
			sum3 += (b1);
			//System.out.println(sum2);
		}
		System.out.println(Math.log10(sum3));
		//System.out.println(System.currentTimeMillis()-startTime1);
	}
	
	public static double calcReducedSum(int m, int k){
		return (k*(2*m - k + 1))/2;
	} 
	public static double reducedFact(int num){
		return (num*(num+1))/2;
	}
	public static BigDecimal log(BigDecimal bd) {
		double d = bd.doubleValue();
		double result = 0.0;
		result = Math.log(d);
		if(Double.isNaN(result) || Double.isInfinite(result))
			return new BigDecimal("0");
		BigDecimal ret = new BigDecimal(Math.log(d));
		return ret.setScale(bd.scale(), BigDecimal.ROUND_HALF_EVEN);
	}

	public static BigDecimal log10(BigDecimal x) {
		BigDecimal ret = log(x);
		BigDecimal denom = log(new BigDecimal("10"));
		BigDecimal result = ret.divide(denom, x.scale(),
				BigDecimal.ROUND_HALF_EVEN);
		return result;
	}
	


}
