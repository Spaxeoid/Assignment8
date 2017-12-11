import java.util.LinkedList;

public class Util {

	public static <E> LinkedList<E> toList(Iterable<E> iterable) {
		LinkedList<E> list = new LinkedList<E>();
		if (iterable != null) {
			for (E e : iterable) {
				list.add(e);
			}
		}
		return list;
	}
}
