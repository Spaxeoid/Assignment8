import org.junit.*;
import jug.*;
import cs2321.*;
import net.datastructures.*;
import java.util.LinkedList;

@jug.SuiteName("DistanceTest")
public class DistanceTest {

	private Labyrinth TARGET = init();
	private Labyrinth T = init();

	public Labyrinth init() {

		return new Labyrinth("WeightedTinyLabyrinth.txt");

	}

	@org.junit.Test(timeout = 15000)
	@jug.TestName("WeightedTiny: totalPathDistance( dfsPath( (0,0), (1,0) ) ) equals 5.0")
	public void Test1() throws Throwable {
		TARGET = new Labyrinth("WeightedTinyLabyrinth.txt");

		org.junit.Assert.assertEquals("WeightedTiny: totalPathDistance( dfsPath( (0,0), (1,0) ) ) equals 5.0",
				(Object) (5.0), (Object) (TARGET
						.totalPathDistance(TARGET.dfsPath(new RoomCoordinate(0, 0), new RoomCoordinate(1, 0)))));
	}

	@org.junit.Test(timeout = 15000)
	@jug.TestName("WeightedTiny: totalPathDistance( bfsPath( (0,0), (1,0) ) ) equals 5.0")
	public void Test2() throws Throwable {
		TARGET = new Labyrinth("WeightedTinyLabyrinth.txt");

		org.junit.Assert.assertEquals("WeightedTiny: totalPathDistance( bfsPath( (0,0), (1,0) ) ) equals 5.0",
				(Object) (5.0), (Object) (TARGET
						.totalPathDistance(TARGET.bfsPath(new RoomCoordinate(0, 0), new RoomCoordinate(1, 0)))));
	}

	@org.junit.Test(timeout = 15000)
	@jug.TestName("WeightedTiny: totalPathDistance( shortestPath( (0,0), (1,0) ) ) equals 5.0")
	public void Test3() throws Throwable {
		TARGET = new Labyrinth("WeightedTinyLabyrinth.txt");

		org.junit.Assert.assertEquals("WeightedTiny: totalPathDistance( shortestPath( (0,0), (1,0) ) ) equals 5.0",
				(Object) (5.0), (Object) (TARGET
						.totalPathDistance(TARGET.shortestPath(new RoomCoordinate(0, 0), new RoomCoordinate(1, 0)))));
	}

	@org.junit.Test(timeout = 15000)
	@jug.TestName("WeightedSmall: totalPathDistance( dfsPath( (3,2), (3,3) ) ) equals 32.0")
	public void Test4() throws Throwable {
		TARGET = new Labyrinth("WeightedSmallLabyrinth.txt");

		org.junit.Assert.assertEquals("WeightedSmall: totalPathDistance( dfsPath( (3,2), (3,3) ) ) equals 32.0",
				(Object) (32.0), (Object) (TARGET
						.totalPathDistance(TARGET.dfsPath(new RoomCoordinate(3, 2), new RoomCoordinate(3, 3)))));
	}

	@org.junit.Test(timeout = 15000)
	@jug.TestName("WeightedSmall: totalPathDistance( bfsPath( (3,2), (3,3) ) ) equals 24.0")
	public void Test5() throws Throwable {
		TARGET = new Labyrinth("WeightedSmallLabyrinth.txt");

		org.junit.Assert.assertEquals("WeightedSmall: totalPathDistance( bfsPath( (3,2), (3,3) ) ) equals 24.0",
				(Object) (24.0), (Object) (TARGET
						.totalPathDistance(TARGET.bfsPath(new RoomCoordinate(3, 2), new RoomCoordinate(3, 3)))));
	}

	@org.junit.Test(timeout = 15000)
	@jug.TestName("WeightedSmall: totalPathDistance( shortestPath( (3,2), (3,3) ) ) equals 21.0")
	public void Test6() throws Throwable {
		TARGET = new Labyrinth("WeightedSmallLabyrinth.txt");

		org.junit.Assert.assertEquals("WeightedSmall: totalPathDistance( shortestPath( (3,2), (3,3) ) ) equals 21.0",
				(Object) (21.0), (Object) (TARGET
						.totalPathDistance(TARGET.shortestPath(new RoomCoordinate(3, 2), new RoomCoordinate(3, 3)))));
	}

	@org.junit.Test(timeout = 15000)
	@jug.TestName("WeightedMedium: totalPathDistance( dfsPath( (4,2), (3,3) ) ) equals 38.0")
	public void Test7() throws Throwable {
		TARGET = new Labyrinth("WeightedMediumLabyrinth.txt");

		org.junit.Assert.assertEquals("WeightedMedium: totalPathDistance( dfsPath( (4,2), (3,3) ) ) equals 38.0",
				(Object) (38.0), (Object) (TARGET
						.totalPathDistance(TARGET.dfsPath(new RoomCoordinate(4, 2), new RoomCoordinate(3, 3)))));
	}

	@org.junit.Test(timeout = 15000)
	@jug.TestName("WeightedMedium: totalPathDistance( bfsPath( (4,2), (3,3) ) ) equals 40.0")
	public void Test8() throws Throwable {
		TARGET = new Labyrinth("WeightedMediumLabyrinth.txt");

		org.junit.Assert.assertEquals("WeightedMedium: totalPathDistance( bfsPath( (4,2), (3,3) ) ) equals 40.0",
				(Object) (40.0), (Object) (TARGET
						.totalPathDistance(TARGET.bfsPath(new RoomCoordinate(4, 2), new RoomCoordinate(3, 3)))));
	}

	@org.junit.Test(timeout = 15000)
	@jug.TestName("WeightedMedium: totalPathDistance( shortestPath( (4,2), (3,3) ) ) equals 16.0")
	public void Test9() throws Throwable {
		TARGET = new Labyrinth("WeightedMediumLabyrinth.txt");

		org.junit.Assert.assertEquals("WeightedMedium: totalPathDistance( shortestPath( (4,2), (3,3) ) ) equals 16.0",
				(Object) (16.0), (Object) (TARGET
						.totalPathDistance(TARGET.shortestPath(new RoomCoordinate(4, 2), new RoomCoordinate(3, 3)))));
	}

	@org.junit.Test(timeout = 15000)
	@jug.TestName("WeightedLarge: totalPathDistance( dfsPath( (1,6), (1,7) ) ) equals 904.0")
	public void Test10() throws Throwable {
		TARGET = new Labyrinth("WeightedLargeLabyrinth.txt");

		org.junit.Assert.assertEquals("WeightedLarge: totalPathDistance( dfsPath( (1,6), (1,7) ) ) equals 904.0",
				(Object) (904.0), (Object) (TARGET
						.totalPathDistance(TARGET.dfsPath(new RoomCoordinate(1, 6), new RoomCoordinate(1, 7)))));
	}

	@org.junit.Test(timeout = 15000)
	@jug.TestName("WeightedLarge: totalPathDistance( bfsPath( (1,6), (1,7) ) ) equals 200.0")
	public void Test11() throws Throwable {
		TARGET = new Labyrinth("WeightedLargeLabyrinth.txt");

		org.junit.Assert.assertEquals("WeightedLarge: totalPathDistance( bfsPath( (1,6), (1,7) ) ) equals 200.0",
				(Object) (200.0), (Object) (TARGET
						.totalPathDistance(TARGET.bfsPath(new RoomCoordinate(1, 6), new RoomCoordinate(1, 7)))));
	}

	@org.junit.Test(timeout = 15000)
	@jug.TestName("WeightedLarge: totalPathDistance( shortestPath( (1,6), (1,7) ) ) equals 30.0")
	public void Test12() throws Throwable {
		TARGET = new Labyrinth("WeightedLargeLabyrinth.txt");

		org.junit.Assert.assertEquals("WeightedLarge: totalPathDistance( shortestPath( (1,6), (1,7) ) ) equals 30.0",
				(Object) (30.0), (Object) (TARGET
						.totalPathDistance(TARGET.shortestPath(new RoomCoordinate(1, 6), new RoomCoordinate(1, 7)))));
	}

}
