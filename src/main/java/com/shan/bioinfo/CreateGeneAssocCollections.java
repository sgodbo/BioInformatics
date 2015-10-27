package com.shan.bioinfo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class CreateGeneAssocCollections {
	

	private static boolean containsAGeneEntry(List<BasicDBObject> listOfGeneMaps, String geneId) {
		ListIterator<BasicDBObject> gItr = listOfGeneMaps.listIterator();
		while(gItr.hasNext()){
			BasicDBObject dbo = gItr.next();
			if(dbo.containsKey(geneId)){
				return true;
			}
		}
	// TODO Auto-generated method stub
	return false;
}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

        // Create seed data

		
       
        //MongoClientURI uri  = new MongoClientURI("mongodb://shandemetz:abcd1234@ds037814.mongolab.com:37814/geneassoc");
		MongoClientURI uri  = new MongoClientURI("mongodb://localhost:27017/geneassoc");
        MongoClient client = new MongoClient(uri);
        DB db = client.getDB(uri.getDatabase());
        
        /*
         * First we'll add a few songs. Nothing is required to create the
         * songs collection; it is created automatically when we insert.
         */
        
        DBObject dbo = BasicDBObjectBuilder.start().get();
        DBCollection genes = db.createCollection("genes",dbo);
        DBCollection diseases = db.createCollection("diseases",dbo);
        DBCollection genediseaseassoc = db.createCollection("genediseaseassoc",dbo);

        		
        		FileReader fr = new FileReader("C:\\Users\\SHANDEMETZ\\Downloads\\Compressed\\all_gene_disease_associations.txt\\all_gene_disease_associations.txt");
        		BufferedReader br = new BufferedReader(fr);
        		String line = "";
        		String[] lineSplits = null;
        		String geneId = "";
        		String geneName = "";
        		String diseaseId = "";
        		String diseaseName = "";
        		BasicDBObject findQueryGene;
        		BasicDBObject findQueryDisease;
        		
                DBCursor docsGene;
                DBCursor docsDisease;
                BasicDBObject mapOfGenes;
        		BasicDBObject mapOfDiseases;
        		BasicDBObject mapOfGeneDiseaseAssoc;
        		int i = 0;
        		int j = 0;
        		br.readLine();
        		while((line = br.readLine()) != null){
        			mapOfGenes = new BasicDBObject();
        			mapOfDiseases = new BasicDBObject();
        			mapOfGeneDiseaseAssoc = new BasicDBObject();
        			lineSplits = line.split("\t");
        			geneId = lineSplits[0];
        			geneName = lineSplits[1];
        			diseaseId = Arrays.asList(lineSplits[3].split(":")).get(1);
        			diseaseName = lineSplits[4];
        			findQueryGene = new BasicDBObject("_id",geneId);
        			findQueryDisease = new BasicDBObject("_id",diseaseId);
        			docsGene = genes.find(findQueryGene);
        			docsDisease = diseases.find(findQueryDisease);
        			if(!docsGene.hasNext()){
        				mapOfGenes.put("_id",geneId);
        				mapOfGenes.put("geneName",geneName);
        				genes.insert(mapOfGenes);
        			}
        			if(!docsDisease.hasNext()){
        				mapOfDiseases.put("_id",diseaseId);
        				mapOfDiseases.put("diseaseName", diseaseName);
        				diseases.insert(mapOfDiseases);
        			}
        				mapOfGeneDiseaseAssoc.put("_id", i);
        				mapOfGeneDiseaseAssoc.put("diseaseId",diseaseId);
            			mapOfGeneDiseaseAssoc.put("geneId", geneId);
            			genediseaseassoc.insert(mapOfGeneDiseaseAssoc);
            			i++;
        			
        			j++;
        			System.out.println(j);
        		}
        	    
        // Note that the insert method can take either an array or a document.
        //songs.insert(seedData);
       
        /*
         * Then we need to give Boyz II Men credit for their contribution to
         * the hit "One Sweet Day".
         */

        /*BasicDBObject updateQuery = new BasicDBObject("song", "One Sweet Day");
        songs.update(updateQuery, new BasicDBObject("$set", new BasicDBObject("artist", "Mariah Carey ft. Boyz II Men")));
        
        /*
         * Finally we run a query which returns all the hits that spent 10 
         * or more weeks at number 1.
         */
      
        //BasicDBObject findQuery = new BasicDBObject("weeksAtOne", new BasicDBObject("$gte",10));
        

        /*while(docs.hasNext()){
            DBObject doc = docs.next();
            System.out.println(
                doc.get("geneId") + ":" + doc.get("geneName")
            );
        }*/
        
        // Since this is an example, we'll clean up after ourselves.

        
        // Only close the connection when your app is terminating

        client.close();
    }
	}
