package cs2321;

public class RoomCoordinate implements Comparable<RoomCoordinate> {
	private int mX;
	private int mY;

	public RoomCoordinate(int aX, int aY) {
		mX = aX;
		mY = aY;
	}

	public int getX() {
		return mX;
	}

	public int getY() {
		return mY;
	}

	public String toString() {
		return "(" + mX + "," + mY + ")";
	}

	@Override
	public int compareTo(RoomCoordinate aCoordinate) {
		if (getX() < aCoordinate.getX()) {
			return -1;
		} else if (getX() == aCoordinate.getX()) {
			if (getY() < aCoordinate.getY()) {
				return -1;
			} else if (getY() == aCoordinate.getY()) {
				return 0;
			} else {
				return 1;
			}
		} else {
			return 1;
		}
	}
	
	public boolean equals(Object aCoordinate) {
		return compareTo( (RoomCoordinate)aCoordinate) == 0 ;
	}
	
	public int hashCode(){
		return toString().hashCode();
	}
}
