package utils;

import java.util.Iterator;

public final class Iterators {

	private Iterators() {
		
	}
	
	public static <T> Iterator<T> unmodifiable(Iterator<T> itr) {
		return new Iterator<T>() {

			@Override
			public boolean hasNext() {
				return itr.hasNext();
			}

			@Override
			public T next() {
				return itr.next();
			}
			
		};
	}
	
}
