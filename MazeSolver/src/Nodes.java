// class nodes is responsible for creating nodes objects and its attributes
import java.util.*;
public class Nodes {
	
	private int nodeID;
	private ArrayList<Integer>connections = new ArrayList<Integer>();
	
	public Nodes(){}
	
	public Nodes(int nodeID, ArrayList<Integer> connections){
		this.nodeID = nodeID;
		this.connections = connections;
	}
	
	public void setNodeID(int nodeID){
	        this.nodeID = nodeID;
	    }
	    
	public int getNodeID(){
	        return nodeID;
	    }
	
	public void setConnections(ArrayList<Integer> connections){
		this.connections = connections;
	}
	
	public ArrayList<Integer> getConnections(){
		return connections;
	}
	
}
