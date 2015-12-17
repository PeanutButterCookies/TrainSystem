/*
 * Kevin Nash
 * 12/5/15
 */
package com.peanutbuttercookies.trainsystem.ctc;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.io.FileUtils;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
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
		try {
			FileUtils.deleteDirectory(new File("graph"));
		} catch (IOException e) {
			System.out.println("Could not delete old graph file");
			e.printStackTrace();
		}
		graph = new GraphDatabaseFactory().newEmbeddedDatabase(new File("graph"));

	}

	public synchronized boolean makeLine(String line) {
		try (Transaction tx = graph.beginTx()) {
			Schema schema = graph.schema();
			Label label =  DynamicLabel.label(line);
			schema.indexFor(label).on(ID).create();
			schema.indexFor(label).on("station").create();
			tx.success();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public synchronized CTCBlock getNextBlock(String line, int blockId) {
		try(Transaction tx = graph.beginTx()) {
			Node node = graph.findNode(DynamicLabel.label(line), ID, blockId);
			for(Relationship rel : node.getRelationships(Direction.OUTGOING)) {
				if((boolean)rel.getEndNode().getProperty("occupied")) {
					return new CTCBlock(rel.getEndNode());
				}
			}
			tx.success();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return null;
	}
	
	public synchronized CTCBlock getPrevBlock(String line, int blockId) {
		try(Transaction tx = graph.beginTx()) {
			Node node = graph.findNode(DynamicLabel.label(line), ID, blockId);
			for(Relationship rel : node.getRelationships(Direction.INCOMING)) {
				if((boolean)rel.getStartNode().getProperty("occupied")) {
					return new CTCBlock(rel.getStartNode());
				}
			}
			tx.success();
		} catch(Exception e) {
			return null;
		}
		
		return null;
	}


	public synchronized boolean addBlock(String line, Block block, int tc) {

		if (block == null) {
			return false;
		}

		try (Transaction tx = graph.beginTx()) {
			Label label = DynamicLabel.label(block.getLine());
			Node node = graph.findNode(label, ID, block.getBlockNumber());
			if (node == null) {
				node = graph.createNode(label);
			}

			setNodeProperties(node, block, tc);

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
				if(block.hasSwitch()) {
					rel.setProperty("switch", true);
					rel.setProperty("engaged", false);
				}
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
						rel.setProperty("switch", true);
						rel.setProperty("engaged", true);
						if(block.getTwoWay()) {
							Relationship rel2 = possibleNext.createRelationshipTo(node, RelTypes.CONNECTED_TO);
							rel2.setProperty("enabled", false);
						}
					}
				}
			}
			
			tx.success();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private void setNodeProperties(Node node, Block block, int tc) {
		node.setProperty(ID, block.getBlockNumber());
		node.setProperty("length", (int)block.getBlockLength());
		node.setProperty("aSwitch", block.hasSwitch());
		node.setProperty("occupied", block.isBlockOccupied());
		node.setProperty("section", block.getSection());
		node.setProperty("numOccupied", 0);
		node.setProperty("starttime", System.nanoTime());
		node.setProperty("tc", tc);
		node.setProperty("switchNum", block.getSwitchNum());
		if(!block.getIsYard() && block.getStationName() != null) {
			node.setProperty("station", block.getStationName());
		} else {
			node.setProperty("station", "Yard");
		}
		
		node.setProperty("hasRR", block.hasRRCrossing());
		if(block.hasRRCrossing()) {
			node.setProperty("rr", block.isRRCrossingEngaged());
		}
	}

	private Node makeTempNode(Integer id, Label label) {
		Node node = graph.createNode(label);
		node.setProperty(ID, id);
		return node;
	}

	public boolean setBlockOccupied(String line, Integer blockId, boolean occupied) {
		try(Transaction tx = graph.beginTx()) {
			Node node = graph.findNode(DynamicLabel.label(line), ID, blockId);
			if(node == null) {
				tx.success();
				return false;
			}
			node.setProperty("occupied", occupied);
			tx.success();
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
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
	
	public CTCBlock getBlock(String line, String station) {
		CTCBlock block = null;
		try(Transaction tx = graph.beginTx()) {
			Node node = graph.findNode(DynamicLabel.label(line), "station", station);
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
	
	public synchronized CTCBlock getAdjacentNode(String line, int blockId, Direction direction) {
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
			
			tx.success();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return block;
	}
	
	public synchronized int getAuthority(String line, List<Integer> nodes) {
		try(Transaction tx = graph.beginTx()) {
			int length = 0;
			Label label = DynamicLabel.label(line);
			for(Integer i : nodes) {
				Node node = graph.findNode(label, ID, i);
				length += (int)node.getProperty("length");
			}
			tx.success();
			return length;
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public synchronized List<Integer> getShortestPath(String line, int start, int end) {
		try(Transaction tx = graph.beginTx()) {
			Label label = DynamicLabel.label(line);
			Node startNode = graph.findNode(label, ID, start);
			Iterable<Path> paths = graph.traversalDescription()
					.breadthFirst()
					.relationships(RelTypes.CONNECTED_TO, Direction.OUTGOING)
					.traverse(startNode);
			for(Path path : paths) {
				if(((Integer)path.endNode().getProperty(ID)) != end) {
					continue;
				}
				List<Integer> list = new LinkedList<Integer>();
				for(Node n : path.nodes()) {
					list.add((int)n.getProperty(ID));
				}
				return list;
			}
			tx.success();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public synchronized Vector<Integer> getSwitchNext(String line, int blockId) {
		try(Transaction tx = graph.beginTx()) {
			Node node = graph.findNode(DynamicLabel.label(line), ID, blockId);
			Vector<Integer> next = new Vector<Integer>();
			for(Relationship r : node.getRelationships(Direction.OUTGOING)) {
				if((Boolean)r.getProperty("aSwitch")) {
					next.add((Integer)r.getEndNode().getProperty(ID));
				}
			}
			tx.success();
			return next;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public synchronized Command getSwitchCommand(String line, int switchId, int nextId) {
		try(Transaction tx = graph.beginTx()) {
			Label label = DynamicLabel.label(line);
			Node node = graph.findNode(label, ID, switchId);
			for( Relationship rel : node.getRelationships(Direction.OUTGOING)) {
				if(rel.getEndNode().getProperty(ID).equals(nextId)) {
					boolean engaged = (boolean)rel.getProperty("engaged");
					return new SwitchCommand(switchId, engaged);
				}
			}
			tx.success();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public synchronized boolean engageSwitch(String line, int switchId, boolean engaged) {
		try(Transaction tx = graph.beginTx()) {
			Node node = graph.findNode(DynamicLabel.label(line), ID, switchId);
			for(Relationship rel : node.getRelationships(Direction.OUTGOING)) {
				if((boolean)rel.getProperty("switch")) {
					if((boolean)rel.getProperty("enabled") == engaged) {
						rel.setProperty("enabled", true);
					} else {
						rel.setProperty("enabled", false);
					}
				}
			}
			tx.success();
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public synchronized CTCBlock getCurrentSwitch(String line, int switchId) {
		try(Transaction tx = graph.beginTx()) {
			Node node = graph.findNode(DynamicLabel.label(line), ID, switchId);
			for(Relationship rel : node.getRelationships(Direction.OUTGOING)) {
				if((boolean)rel.getProperty("switch") && (boolean)rel.getProperty("enabled")) {
					return new CTCBlock(node);
				}
			}
			tx.success();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return null;
	}

}
