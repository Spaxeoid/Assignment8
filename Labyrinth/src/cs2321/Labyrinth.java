package cs2321;

import java.io.*;
import java.util.Scanner;
import java.util.Iterator;
import net.datastructures.*;

/* CS2321 Project: The Labyrinth
 * 
 * Do NOT change the setupLabyrinth function.
 *         
 * Implement the dfsPath, bfsPath, shortestPath, and totalPathDistance functions below.
 *
 */
public class Labyrinth {
	public static final int WALL = 1;
	public static final String PARSE_CHARACTER = ",";

	private Graph<RoomCoordinate, Walkway> mGraph;
	private int mWidth = -1;
	private int mHeight = -1;

	public Labyrinth(String aFileName) {
		mGraph = setupLabyrinth(aFileName);

		// TODO: Add other necessary code to constructor
	}

	/*
	 * Width of the labyrinth (# of squares, not pixels)
	 */
	public int getWidth() {
		return mWidth;
	}

	/*
	 * Height of the labyrinth (# of squares, not pixels)
	 */
	public int getHeight() {
		return mHeight;
	}

	/*
	 * Create the graph based on the maze specification in the input file !!!
	 * Don't Change this method !!!
	 */
	public Graph<RoomCoordinate, Walkway> setupLabyrinth(String aFileName) {
		Scanner lFile = null;
		try {
			lFile = new Scanner(new File(aFileName));
			lFile.useDelimiter(",\n");
		} catch (FileNotFoundException eException) {
			System.out.println(eException.getMessage());
			eException.printStackTrace();
			System.exit(-1);
		}

		//You need to copy your Adjacent Graph implementation to this project. 
		// Otherwise the following line has compiler error because AdjListGraph(...) does not exist.
		Graph<RoomCoordinate, Walkway> lGraph = new AdjListGraph<RoomCoordinate, Walkway>();

		try {
			int lXSize = 0;
			int lYSize = 0;
			if (lFile.hasNext()) {
				String[] lDimensions = lFile.nextLine().split(PARSE_CHARACTER);
				lXSize = Integer.parseInt(lDimensions[0]);
				lYSize = Integer.parseInt(lDimensions[1]);
			}

			mWidth = lXSize;
			mHeight = lYSize;

			/* Create all the room coordinates */
			Vertex<?>[][] lVertices = new Vertex<?>[lXSize][lYSize];
			for (int lYIndex = 0; lYIndex < lYSize; lYIndex++) {
				for (int lXIndex = 0; lXIndex < lXSize; lXIndex++) {
					RoomCoordinate lNextRoomCoordinate = new RoomCoordinate(lXIndex, lYIndex);
					Vertex<RoomCoordinate> lNextRoom = lGraph.insertVertex(lNextRoomCoordinate);
					lVertices[lXIndex][lYIndex] = lNextRoom;
				}
			}

			for (int lYIndex = 0; lYIndex < lYSize; lYIndex++) {
				String[] lWalls = lFile.nextLine().split(PARSE_CHARACTER);
				for (int lXIndex = 0; lXIndex < lXSize; lXIndex++) {
					if (Integer.parseInt(lWalls[lXIndex]) != WALL) {
						Vertex<RoomCoordinate> lVertex1 = (Vertex<RoomCoordinate>) lVertices[lXIndex][lYIndex];
						Vertex<RoomCoordinate> lVertex2 = (Vertex<RoomCoordinate>) lVertices[lXIndex][lYIndex - 1];

						Walkway lNewWalkway = new Walkway(
								lVertex1.getElement().toString() + lVertex2.getElement().toString(),
								Integer.parseInt(lWalls[lXIndex]));
						lGraph.insertEdge(lVertex1, lVertex2, lNewWalkway);
					}
				}
			}

			for (int lYIndex = 0; lYIndex < lYSize; lYIndex++) {
				String[] lWalls = lFile.nextLine().split(PARSE_CHARACTER);
				for (int lXIndex = 0; lXIndex < lXSize; lXIndex++) {
					if (Integer.parseInt(lWalls[lXIndex]) != WALL) {
						Vertex<RoomCoordinate> lVertex1 = (Vertex<RoomCoordinate>) lVertices[lXIndex][lYIndex];
						Vertex<RoomCoordinate> lVertex2 = (Vertex<RoomCoordinate>) lVertices[lXIndex - 1][lYIndex];

						Walkway lNewWalkway = new Walkway(
								lVertex1.getElement().toString() + lVertex2.getElement().toString(),
								Integer.parseInt(lWalls[lXIndex]));
						lGraph.insertEdge(lVertex1, lVertex2, lNewWalkway);
					}
				}
			}
		} catch (Exception eException) {
			System.out.println(eException.getMessage());
			eException.printStackTrace();
			System.exit(-1);
		}

		return lGraph;
	}

	/**
	 * Complete the dfsPath function by implementing a Depth First Search
	 * algorithm to find a path from start to end. At each vertex, the adjacent
	 * edges shall be searched in the order of NORTH, EAST, SOURTH and WEST. 
	 * @param start an RoomCoordinate object represents the start location, 
	 * 				this object does not exist in the graph 
	 * @param end  an RoomCoordinate object represents the start location, 
	 * 				this object does not exist in the graph 
	 * @return 		Return the sequence of edges traversed in order to get from the
	 * 				start to the finish locations. If there is
	 * 				NO path, do NOT return null. Return an empty sequence. 
	 */
	public Iterable<Edge<Walkway>> dfsPath(RoomCoordinate start, RoomCoordinate end) {
		// #TODO: Complete and correct dfsPath()
		/* #TODO: TCJ */
		HashMap<Vertex<RoomCoordinate>, Boolean> visited = new HashMap<>((int) (mGraph.numVertices() * 1.2)); 
		HashMap<Vertex<RoomCoordinate>, Edge<Walkway>> forest = new HashMap<>((int) (mGraph.numVertices() * 1.2));
		Vertex<RoomCoordinate> v = null;
		Vertex<RoomCoordinate> z = null;
		for(Vertex<RoomCoordinate> e: mGraph.vertices()) {
			Vertex<RoomCoordinate> temp = e;
			if(temp.getElement().equals(start)) {
				v = temp;
			}
			if(temp.getElement().equals(end)) {
				z = temp;
			}
		}
		DFSPath(v, z, visited, forest);
		return constructPath(v, z, forest);
	}

	public boolean DFSPath(Vertex<RoomCoordinate> v, Vertex<RoomCoordinate> z, HashMap<Vertex<RoomCoordinate>, Boolean> visited, HashMap<Vertex<RoomCoordinate>, Edge<Walkway>> forest) {
		if( v == z) {
			return true;
		}else {
			visited.put(v, true);
			Edge<Walkway>[] edge = new Edge[4];
			for(Edge<Walkway> e: mGraph.outgoingEdges(v)) {
				if(mGraph.opposite(v, e).getElement().getY() < v.getElement().getY()) {
					edge[0] = e;
				}else if(mGraph.opposite(v, e).getElement().getX() > v.getElement().getX()) {
					edge[1] = e;
				}else if(mGraph.opposite(v, e).getElement().getY() > v.getElement().getY()) {
					edge[2] = e;
				}else if(mGraph.opposite(v, e).getElement().getX() < v.getElement().getX()) {
					edge[3] = e;
				}
			}
			for(int i = 0; i < 4; i++) {
				if(edge[i] != null) {
					Vertex<RoomCoordinate> w = mGraph.opposite(v, edge[i]);
					if(visited.get(w) == null) {
						forest.put(w, edge[i]);
						boolean found = DFSPath(w, z, visited, forest);
						if(found == true) {
							return true;
						}
					}
				}

			}
		}
		return false;
	}

	public Iterable<Edge<Walkway>> constructPath(Vertex<RoomCoordinate> u, Vertex<RoomCoordinate> v, HashMap<Vertex<RoomCoordinate>, Edge<Walkway>> forest){
		Vertex<RoomCoordinate> end = v;
		DoublyLinkedList<Edge<Walkway>> temp = new DoublyLinkedList<>();
		while(end != u) {
			Edge<Walkway> E = forest.get(end);
			temp.addFirst(E);
			end = mGraph.opposite(end, E);
		}
		return temp;
	}


	/**
	 * Complete the dfsPath function by implementing a Breadth First Search
	 * algorithm to find a path from start to end. At each vertex, the adjacent
	 * edges shall be searched in the order of NORTH, EAST, SOURTH and WEST. 
	 * @param start an RoomCoordinate object represents the start location, 
	 * 				this object does not exist in the graph 
	 * @param end  an RoomCoordinate object represents the start location, 
	 * 				this object does not exist in the graph 
	 * @return 		Return the sequence of edges traversed in order to get from the
	 * 				start to the finish locations. If there is
	 * 				NO path, do NOT return null. Return an empty sequence. 
	 */
	public Iterable<Edge<Walkway>> bfsPath(RoomCoordinate start, RoomCoordinate end) {
		// #TODO: Complete and correct bfsPath()
		/* #TODO: TCJ */
		HashMap<Vertex<RoomCoordinate>, Boolean> visited = new HashMap<>((int) (mGraph.numVertices() * 1.2)); 
		HashMap<Vertex<RoomCoordinate>, Edge<Walkway>> forest = new HashMap<>((int) (mGraph.numVertices() * 1.2));
		Vertex<RoomCoordinate> v = null;
		Vertex<RoomCoordinate> z = null;
		for(Vertex<RoomCoordinate> e: mGraph.vertices()) {
			Vertex<RoomCoordinate> temp = e;
			if(temp.getElement().equals(start)) {
				v = temp;
			}
			if(temp.getElement().equals(end)) {
				z = temp;
			}
		}
		
		return BFSPath(v, z, visited, forest);		
	}

	public Iterable<Edge<Walkway>> BFSPath(Vertex<RoomCoordinate> v, Vertex<RoomCoordinate> z, HashMap<Vertex<RoomCoordinate>, Boolean> visited, HashMap<Vertex<RoomCoordinate>, Edge<Walkway>> forest) {
		if(v == z) {
			return null;
		} else {
			DoublyLinkedList<Vertex<RoomCoordinate>> queue = new DoublyLinkedList<>();
			visited.put(v, true);
			queue.addFirst(v);
			Boolean found = false;
			while(!queue.isEmpty()) {
				Vertex<RoomCoordinate> u  = queue.removeLast();
				Edge<Walkway>[] edge = new Edge[4];
				for(Edge<Walkway> e: mGraph.outgoingEdges(u)) {
					if(mGraph.opposite(u, e).getElement().getY() < u.getElement().getY()) {
						edge[0] = e;
					}else if(mGraph.opposite(u, e).getElement().getX() > u.getElement().getX()) {
						edge[1] = e;
					}else if(mGraph.opposite(u, e).getElement().getY() > u.getElement().getY()) {
						edge[2] = e;
					}else if(mGraph.opposite(u, e).getElement().getX() < u.getElement().getX()) {
						edge[3] = e;
					}
				}
				for(int i = 0; i < 4; i++) {
					if(edge[i] != null) {
						Vertex<RoomCoordinate> w = mGraph.opposite(u, edge[i]);
						if(visited.get(w) == null) {
							visited.put(w, true);
							forest.put(w, edge[i]);
							queue.addFirst(w);
							if(w == z) {
								found = true;
								break;
							}
						}
					}
				}
				if(found == true) {
					break;
				}
			}
		}
		return constructPath(v, z, forest);
	}

	/**
	 * Complete the shortestPath function by implementing Dijkstra's
	 * algorithm to find a path from start to end. At each vertex, the adjacent
	 * edges shall be searched in the order of NORTH, EAST, SOURTH and WEST. 
	 * @param start an RoomCoordinate object represents the start location, 
	 * 				this object does not exist in the graph 
	 * @param end  an RoomCoordinate object represents the start location, 
	 * 				this object does not exist in the graph 
	 * @return 		Return the sequence of edges traversed in order to get from the
	 * 				start to the finish locations. If there is
	 * 				NO path, do NOT return null. Return an empty sequence. 
	 */
	public Iterable<Edge<Walkway>> shortestPath(RoomCoordinate start, RoomCoordinate end) {
		// #TODO: Complete and correct shortestPath()
		/* #TODO: TCJ */
		HashMap<Vertex<RoomCoordinate>, Integer> d = new HashMap<>();
		HeapPQ<Integer, Vertex<RoomCoordinate>> pq = new HeapPQ<>();
		
		return null;
	}

	/*
	 * Complete the totalPathDistance function, which calculates how far the
	 * given path traverses.
	 */
	public static double totalPathDistance(Iterable<Edge<Walkway>> path) {
		// # TODO: Complete totalPathDistance function
		int size = 0;
		for(Edge<Walkway> e: path) {
			size += e.getElement().getDistance();
		}
		
		return size;
	}

	public static void main(String[] aArguments) {
		Labyrinth lLabyrinth = new Labyrinth("SmallLabyrinth.txt");

		// TODO: Testing
	}

}
