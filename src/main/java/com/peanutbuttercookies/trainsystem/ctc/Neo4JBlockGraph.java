package com.peanutbuttercookies.trainsystem.ctc;

import java.io.File;
import java.util.List;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.schema.Schema;
import org.neo4j.helpers.collection.IteratorUtil;

import com.peanutbuttercookies.trainsystem.commonresources.Block;

public class Neo4JBlockGraph {
	private GraphDatabaseService graph;
	private static final String ID = "blockNumber";

	private static enum RelTypes implements RelationshipType {
		CONNECTED_TO
	}

	public Neo4JBlockGraph() {
		graph = new GraphDatabaseFactory().newEmbeddedDatabase(new File("graph.db"));

	}

	public synchronized boolean makeLine(String line) {
		try (Transaction tx = graph.beginTx()) {
			Schema schema = graph.schema();
			Label label =  DynamicLabel.label(line);
			schema.indexFor(label).on(ID).create();
			Node yard = graph.createNode(label);
			yard.setProperty(ID, 0);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public synchronized boolean addBlock(String line, Block block) {

		if (block == null) {
			return false;
		}

		try (Transaction tx = graph.beginTx()) {
			Label label = DynamicLabel.label(block.getLine());
			Node node = graph.findNode(label, ID, block.getBlockNumber());
			if (node == null) {
				node = graph.createNode(label);
			}

			setNodeProperties(node, block);

			// Creates next nodes/relationships if they do not already
			// exist
			// differs differs depending on whether or not the block is two way
			if (block.getNext() != null) {
				Node next = graph.findNode(label, ID, block.getNext().getBlockNumber());
				if (next == null) {
					next = makeTempNode(block.getNext().getBlockNumber(), label);
				}

				Relationship rel = node.createRelationshipTo(next, RelTypes.CONNECTED_TO);
				rel.setProperty("enabled", true);
				if(block.getTwoWay()) {
					Relationship rel2 = next.createRelationshipTo(node, RelTypes.CONNECTED_TO);
					rel2.setProperty("enabled", true);
				}
			}

			// Creates disabled relationships to possible blocks
			if (block.hasSwitch()) {
				for (Block b : block.getSwitchList()) {
					if (b != block.getNext()) {
						Node possibleNext = graph.findNode(label, ID, b.getBlockNumber());
						// Creates uncreated block nodes and sets block number
						if (possibleNext == null) {
							possibleNext = makeTempNode(b.getBlockNumber(), label);
						}

						Relationship rel = node.createRelationshipTo(possibleNext, RelTypes.CONNECTED_TO);
						rel.setProperty("enabled", false);
						if(block.getTwoWay()) {
							Relationship rel2 = possibleNext.createRelationshipTo(node, RelTypes.CONNECTED_TO);
							rel2.setProperty("enabled", false);
						}
					}
				}
			}
			
			//Create rel to yard if the block number is 1
			if(block.getBlockNumber() == 1) {
				Node yard = graph.findNode(label, ID, 0);
				Relationship rel = yard.createRelationshipTo(node, RelTypes.CONNECTED_TO);
				rel.setProperty("enabled", true);
				rel = node.createRelationshipTo(yard, RelTypes.CONNECTED_TO);
				rel.setProperty("enabled", true);
			}

			tx.success();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private void setNodeProperties(Node node, Block block) {
		node.setProperty(ID, block.getBlockNumber());
		node.setProperty("length", block.getBlockLength());
		node.setProperty("aSwitch", block.hasSwitch());
		node.setProperty("occupied", block.isBlockOccupied());
		node.setProperty("section", block.getSection());
		node.setProperty("numOccupied", 0);
		node.setProperty("startTime", System.nanoTime());
	}

	private Node makeTempNode(Integer id, Label label) {
		Node node = graph.createNode(label);
		node.setProperty(ID, id);
		return node;
	}

	public synchronized List<Command> getPath(String line, Integer start, Integer end) {

		// TODO

		return null;
	}
	
	public boolean setBlockOccupied(String line, Integer blockId) {
		//TODO
		return false;
	}
	
	public CTCBlock getBlock(String line, Integer blockId) {
		CTCBlock block = null;
		try(Transaction tx = graph.beginTx()) {
			Node node = graph.findNode(DynamicLabel.label(line), ID, blockId);
			block = new CTCBlock(node);
			tx.success();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return block;
	}
	
	public int getBlockCount(String line) {
		int count = 0;
		try(Transaction tx = graph.beginTx()) {
			ResourceIterator<Node> iterator = graph.findNodes(DynamicLabel.label(line));
			count = IteratorUtil.count(iterator);
			tx.success();
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
		
		return count;
	}
	
	public CTCBlock getAdjacentNode(String line, int blockId, Direction direction) {
		CTCBlock block = null;
		try(Transaction tx = graph.beginTx()) {
			Node node = graph.findNode(DynamicLabel.label(line), ID, blockId);
			Iterable<Relationship> rels = node.getRelationships(direction);
			Node incoming = null;
			for(Relationship rel : rels) {
				if(((boolean)rel.getProperty("enabled"))) {
					incoming = rel.getStartNode();
					break;
				}
			}
			if(incoming != null) {
				block = new CTCBlock(incoming);
			}
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return block;
	}
	
	public double getAuthority(String line, int start, int end) {
		//TODO
		try(Transaction tx = graph.beginTx()) {
			
		} catch(Exception e) {
			return -1;
		}
		return 0;
	}

}
