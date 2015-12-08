package com.peanutbuttercookies.trainsystem.ctc;

import java.io.File;
import java.util.List;

import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.schema.Schema;

import com.peanutbuttercookies.trainsystem.commonresources.Block;

public class Neo4JBlockGraph {
	private GraphDatabaseService graph;

	public Neo4JBlockGraph() {
		graph = new GraphDatabaseFactory().newEmbeddedDatabase(new File("graph.db"));
		

	}
	
	public synchronized boolean makeLine(String line) {
		try(Transaction tx = graph.beginTx()) {
			Schema schema = graph.schema();
			schema.indexFor(DynamicLabel.label(line))
				.on("blockNumber")
				.create();
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public synchronized boolean addBlock(String line, Block block) {
		
		try(Transaction tx = graph.beginTx()) {
			//TODO
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public synchronized List<Integer> getPath(String line, Integer start, Integer end) {

		//TODO
		
		return null;
	}
	
}
