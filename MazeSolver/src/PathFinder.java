/* Maze path finder algorithm,
 * This algorithm gets users input files which contains pairwise node connections maze,
 * the algorithm reads the input file, find all nodes and create nodes object to represent it,
 * each node object created contain as attributes an array of connections,
 * (meaning all other nodes connected to each particular node.)
 * and node ID representing each node unique identifier. 
 * Furthermore ,this algorithm performs a depth first search in order to find a solution for each maze,
 *  it store the solution in an ArrayList called stack and then print it.
 *  this algorithm was implemented by Renan Augusto Da Silva Gado.
 * */

import java.io.File;
import java.util.*;

public class PathFinder {
	private static int counter = 0;
	private static int numberOfNode = 0;
	static Nodes node;

	//Method that reads files
	public static int[] readFiles(String fileIn){
		File file = new File(fileIn);
		try{
			Scanner scanner = new Scanner(file);
			// count nodes in file in order determinate the size of array
			while (scanner.hasNextInt()){
				counter++;
				scanner.nextInt();
			}
			
			//create arrayOfNodes with a specific size and store values from file
		    int [] arrayOfNodes = new int[counter];
			Scanner nodeScanner = new Scanner(file);
			for(counter = 0; counter < arrayOfNodes.length; counter++){
				arrayOfNodes[counter] =nodeScanner.nextInt();
			}
			return arrayOfNodes;
		}
		
		catch(Exception e){
			System.out.println("Could not Read File");
			return null;
		}
	}// end readFiles method
	
	static public ArrayList<Integer> findConnection(int[] node, int nodeNumber){	
		ArrayList<Integer> NodeConnectionList = new ArrayList<Integer>();
		for(counter = 0; counter < node.length; counter = counter + 2){
			if(node[counter] == nodeNumber){
				NodeConnectionList.add(node[counter+1]);
				//System.out.println("node "+nodeNumber+" has Neighbour :"+node[counter+1]);
			}
		}

		for(counter = 1; counter < node.length; counter = counter + 2){
			if(node[counter] == nodeNumber){
				NodeConnectionList.add(node[counter-1]);
				//System.out.println("node "+nodeNumber+" has Neighbour :"+node[counter-1]);	
			}
		}
		return NodeConnectionList;
	}// end findConnection method
	
	// get the amount of nodes in file
	public static void getNodeAmount(int[] nodes){
		for (counter = 0; counter < nodes.length; counter++) {
		    if (nodes[counter] > numberOfNode) {
		      numberOfNode = nodes[counter]+1;
		    }
		}
		//System.out.println("the array has:"+numberOfNode+" nodes");
	}
	//method that gets the connection between nodes
	public static ArrayList<Nodes> getCon(int[]data){
		ArrayList<Nodes> nodesList = new ArrayList<Nodes>(numberOfNode);
		int counter = 0;
		while(counter < numberOfNode){
			node = new Nodes();
			node.setNodeID(counter);
			node.setConnections(findConnection(data,counter));
			nodesList.add(node);
			counter++;
		}
		return nodesList ;
	}//end of method getCon
	
	// method that print nodes connections
	public static void printNodeConnections(ArrayList<Nodes> nodeList){
		for(counter = 0; counter < nodeList.size(); counter++){
			node = nodeList.get(counter);
			System.out.println("node "+node.getNodeID()+" has neighbours: "+node.getConnections());
		}
	}//end method printNodeConnections
	// method that performs the DFS, this method take as input an arrayList of nodes object, 
	//the initial state and the number of nodes
	public static void dfs(ArrayList<Nodes> nList, int initialState, int numberOfNodes){
		ArrayList<Integer> stack = new ArrayList<Integer>();
		boolean [] visitedNodes = new boolean[numberOfNodes];
		node = nList.get(initialState);
		boolean pathNotFound = true;
		stack.add(node.getNodeID());
		visitedNodes[node.getNodeID()] = true;
		while(pathNotFound){
			try{
				if(visitedNodes[node.getConnections().get(0)] != true && node.getConnections().isEmpty() != true){
					node = nList.get(node.getConnections().remove(0));
					stack.add(node.getNodeID());
					visitedNodes[node.getNodeID()] = true;	
				}//end if
			
				if(visitedNodes[node.getConnections().get(0)] && node.getConnections().size() > 1){
					node = nList.get(node.getConnections().remove(1));
					stack.add(node.getNodeID());
					visitedNodes[node.getNodeID()] = true;
				}
	
				
				if(node.getNodeID() == 1){
					pathNotFound = false;
				}
	
				else if(visitedNodes[node.getConnections().get(0)] || node.getConnections().isEmpty()){
					stack.remove(stack.size()-1);
					node = nList.get(stack.get(stack.size()-1));
				}// end else if
			} //end try
			// this exception is catch if the any of the if statements in this method returns a array out of boundaries
			//exception which in this case indicates that the maze has no solution
			catch(Exception e){
				pathNotFound = false;
				System.out.println("Maze Has No Solution");
		}	
		}
		//print stack
		for(int counter : stack){
			System.out.println(counter);
		}		
	}//end method DFS
	
	//get user input
	public static String userInput(){
		System.out.println("Enter the name of the file which contain the maze that you want to solve!");
		Scanner mazeFileScanner = new Scanner(System.in);
		String mazeName = mazeFileScanner.nextLine();
		return mazeName;
	}
	
	public static void main(String[] args) {
			int initialState = 0;
			String maze = userInput();
			int[]data = readFiles(maze);
			//System.out.println(Arrays.toString(data));
			getNodeAmount(data);
			//printNodeConnections(getCon(data));	
			long startTime = System.nanoTime();
			dfs(getCon(data),initialState,numberOfNode);
			long endTime = System.nanoTime();
			System.out.println("timer: "+(endTime - startTime));
	}//end of main method
}

