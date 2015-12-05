package com.shan.bioinfo.project;

import java.io.*;
import java.net.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class PubMedQuery {

	public int countPaperHits(String inputString) throws IOException, SAXException, ParserConfigurationException, XPathExpressionException {
		// TODO Auto-generated method stub
		// URL a GO Term in OBO xml format
				/*URL u1 = new URL(
						"http://www.ebi.ac.uk/QuickGO/GTerm?id=GO:0005232&format=oboxml");*/
				//System.out.println("Query1:"+inputString);
				URL u1 = new URL("http://www.ebi.ac.uk/europepmc/webservices/rest/search?query="+inputString);
				//URL u2 = new URL("http://www.ebi.ac.uk/europepmc/webservices/rest/search?query="+inputString+"%20PUB_YEAR:[2012%20TO%202015]");
				// Connect
				HttpURLConnection urlConnection1 = (HttpURLConnection) u1
						.openConnection();
				
				//HttpURLConnection urlConnection2 = (HttpURLConnection) u2
				//		.openConnection();

				// Parse an XML document from the connection
				InputStream inputStream1 = urlConnection1.getInputStream();
				Document xml1 = DocumentBuilderFactory.newInstance()
						.newDocumentBuilder().parse(inputStream1);
				inputStream1.close();
				
				/*InputStream inputStream2 = urlConnection2.getInputStream();
				Document xml2 = DocumentBuilderFactory.newInstance()
						.newDocumentBuilder().parse(inputStream2);
				inputStream2.close();*/

				// XPath is here used to locate parts of an XML document
				XPath xpath1 = XPathFactory.newInstance().newXPath();
				
				//XPath xpath2 = XPathFactory.newInstance().newXPath();
				
				int result1 = Integer.parseInt(xpath1.compile("/responseWrapper/hitCount").evaluate(xml1));
				//int result2 = Integer.parseInt(xpath2.compile("/responseWrapper/hitCount").evaluate(xml2));
				// Locate the term name and print it out
				/*System.out.println("Term name:"
						+ xpath.compile("/obo/term").evaluate(xml));*/
				return result1;
	}

}
