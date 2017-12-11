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
		return null;
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
		return null;
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
		return null;
	}

	/*
	 * Complete the totalPathDistance function, which calculates how far the
	 * given path traverses.
	 */
	public static double totalPathDistance(Iterable<Edge<Walkway>> path) {
		// # TODO: Complete totalPathDistance function
		return 0;
	}

	public static void main(String[] aArguments) {
		Labyrinth lLabyrinth = new Labyrinth("SmallLabyrinth.txt");

		// TODO: Testing
	}

}
