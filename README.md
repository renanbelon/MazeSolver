# MazeSolver
Artificial intelligence maze solver algorithm, this projects uses a depth first search algorithm to solve pairwise connection The mazes will be described by numbered nodes.


# Depth First Search
The algorithms used on this program was Depth First Search, which is an algorithms for navigating or searching tree or graph data structures. Depth First Search working methodology is very simple it starts on the root of a graph and travels in one direction until the end, and when it reaches the end of this path it starts backtracking until it finds the goal. A simple example of the Depth First Search working methodology is navigating through a maze and always turning right until reaching a dead end.

# How was the algorithms implemented
The implementation of the Depth First Search in this scenario works as the following: The system finds the connection between nodes and store it into individual arrays called neighbour for each node, every node has an array of neighbour which represents the nodes connected to then. The system starts at node zero and gets the first value of node zero’s array of neighbours, the system always gets the first index on array of neighbours before backtracking, if the first value of the array of neighbours is visited or the array is empty the system starts backtracking until if find another possible path. However in some cases such as maze 2 the system check if the first index in array of neighbour is visited and if it is it gets the second index if available. 
