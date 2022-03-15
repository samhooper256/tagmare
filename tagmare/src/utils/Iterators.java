package utils;

import java.util.*;

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
	
	public static <T> ListIterator<T> unmodifiable(ListIterator<T> itr) {
		return new ListIterator<T>() {
			
			@Override
			public boolean hasNext() {
				return itr.hasNext();
			}
			
			@Override
			public T next() {
				return itr.next();
			}
			
			@Override
			public T previous() {
				return itr.previous();
			}

			@Override
			public boolean hasPrevious() {
				return itr.hasPrevious();
			}

			@Override
			public int nextIndex() {
				return itr.nextIndex();
			}

			@Override
			public int previousIndex() {
				return itr.previousIndex();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException("remove");
			}

			@Override
			public void set(T e) {
				throw new UnsupportedOperationException("set");
			}

			@Override
			public void add(T e) {
				throw new UnsupportedOperationException("add");
			}
			
		};
	}
	
	public static <T> Iterator<T> descendingUnmodifiable(List<T> list) {
		return new Iterator<T>() {
			
			int nextIndex = list.size() - 1;

			@Override
			public boolean hasNext() {
				return nextIndex >= 0;
			}

			@Override
			public T next() {
				if(!hasNext())
					throw new IllegalStateException("No more elements");
				T item = list.get(nextIndex);
				nextIndex--;
				return item;
			}
			
		};
	}
	
}