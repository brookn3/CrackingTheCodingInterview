/**
 * 
 */
package chapter_7_9;

import java.lang.Iterable;
import java.util.Iterator; 
import java.util.List;

/**
 * A array like structure which can be rotated.
 * 
 * @author Brook Negussie
 * @version May 23, 2019
 */
public class CircularArray<T> implements Iterable<T> {
	
	private T[] circArray;
	public int length;
	private int currentStartingIndex;
	private int currentEndingIndex;
	
	/**
	 * Constructs a CircularArray object using the given size.  
	 * 
	 * @throws IllegalArgumentException if the given length is less 
	 * 									than one.
	 * @param theLength The desired length of the CircularArray object.
	 */
	public CircularArray(final int theLength) {
		
		if (theLength < 1) {
			throw new IllegalArgumentException("You need to provide \"theSize\" value which is greater than 0.");
		}
		
		circArray = (T[]) new Object[theLength];
		length = theLength;
		currentStartingIndex = 0;
		currentEndingIndex = theLength - 1;
	}
	
	/**
	 * Returns the value stored at the given index.
	 * 
	 * @throws IllegalArgumentException if the given index is not valid.
	 * @param theIndex The index of the value to return. 
	 * @return the value stored at the given index.
	 */
	public T get(final int theIndex) {
		validateIndex(theIndex);
		
		T result;
		
		/* Making sure that if the Circular Array has already been
		 * rotated that this get method still works properly.
		 */
		if (currentStartingIndex + theIndex > length - 1) {
			result = circArray[currentStartingIndex + theIndex - length];
		} else {
			result = circArray[currentStartingIndex + theIndex];
		}
		
		return result;
	}
	
	/**
	 * Inserts the given value at the given index location.
	 * 
	 * @param theIndex The index of where the given value will be placed.
	 * @param theValue The value to be placed into the CircularArray.
	 * @return true if the given value was placed into the Circular 
	 * 				Array properly or else false. 
	 */
	public boolean add(final int theIndex, final T theValue) {
		boolean result = false;
		
		try {
			validateIndex(theIndex);
		} catch (IllegalArgumentException e) {
			return false;
		} 
		
		circArray[theIndex] = theValue;
		result = true;
		
		return result;
	}
	
	
	/**
	 * Given a specific index, the circular array will basically
	 * start from that location.
	 * 
	 * @throws IllegalArgumentException if the given index is not valid.
	 * @param theIndexOfTheNewStartingLocation The index to be considered the start of the array.
	 */
	public void rotate(final int theIndexOfTheNewStartingLocation) {
		validateIndex(theIndexOfTheNewStartingLocation);
		
		currentStartingIndex = theIndexOfTheNewStartingLocation;
		if (theIndexOfTheNewStartingLocation == 0) {
			currentEndingIndex = length - 1;
		} else {
			currentEndingIndex = theIndexOfTheNewStartingLocation - 1;
		}
	}
	
	/**
	 * The String representation of the object in the array.
	 * 
	 * @return The String representation of the object in the array. 
	 */
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		for (int i = currentStartingIndex; i < length; i++) {
			
			if (circArray[i] != null) {
				result.append(circArray[i].toString());
			}
			
		}
		if (currentEndingIndex < currentStartingIndex) {
			for (int i = 0; i <= currentEndingIndex; i++) {
				
				if (circArray[i] != null) {
					result.append(circArray[i].toString());
				}
				
			}
		}
		
		return result.toString();
	}
	
	private void validateIndex(final int theIndex) {
		if (theIndex < 0 || 
				theIndex > length - 1) {
			
			throw new IllegalArgumentException("You need to " +
					"provide a valid index value which is greater " +
					"than or equal to zero and less than the size " +
					"of the CircularArray.");
		}
	}
	
	
	/**
	 * Allowing users to iterate over the CircularArray object 
	 * using such things as a for-each loop.
	 * 
	 * @return An Iterator over the CircularArray object. 
	 */
	@Override
	public Iterator<T> iterator() {
		Iterator<T> it = new Iterator<T>() {
			private int currentIndex = currentStartingIndex;
			private boolean wentAroundOnce = false;
			
			@Override
			public boolean hasNext() {
				boolean result = false;
				
				if (currentEndingIndex == currentStartingIndex) { // There is only one element in the CircularArray
					result = currentIndex == 0;
				} else if (currentEndingIndex < currentStartingIndex) { // The CircularArray has been shifted & the "currentStartingIndex" is not at the zero starting index anymore. 
					result = !(wentAroundOnce && currentIndex != currentStartingIndex);
				} else { // The CircularArray has NOT been shifted & || the "currentStartingIndex" remains at the zero starting index.
					result = currentIndex < length && circArray[currentIndex] != null;
				}
				
				
				return result;
			}
			
			@Override
			public T next() {
				T result = circArray[currentIndex++];
				
				if (currentIndex > length - 1 && currentEndingIndex < currentStartingIndex) {
					currentIndex = 0;
					wentAroundOnce = true;
				}
				
				return result;
			}
		};
		
		return it;
	}
}