package cp213;

import java.util.HashSet;
import java.util.ArrayList;

/**
 * COMPLETE THE CODE AT // your code here
 *
 * A single linked list structure of <code>Node T</code> objects. These value
 * objects must be Comparable - i.e. they must provide the compareTo method.
 * Only the <code>T</code> value contained in the priority queue is visible
 * through the standard priority queue methods. Extends the
 * <code>SingleLink</code> class.
 *
 * @author Anastasia Malinovski, 200694700, mali4700@mylaurier.ca
 * @version 2021-06-16
 * @param <T> this SingleList value type.
 */
public class SingleList<T extends Comparable<T>> extends SingleLink<T> {

	/**
	 * Searches for the first occurrence of key in this SingleList. Private helper
	 * methods - used only by other ADT methods.
	 *
	 * @param key The value to look for.
	 * @return A pointer to the node previous to the node containing key.
	 */
	private SingleNode<T> linearSearch(final T key) {
		SingleNode<T> current = this.front;
		SingleNode<T> result = null;
		while (current != null) {
			if (key == current.getNext().getValue()) {
				result = current;
				break;
			}
			current = current.getNext();
		}

		return result;
	}

	/**
	 * Appends value to the end of this SingleList.
	 *
	 * @param value The value to append.
	 */
	public void append(final T value) {
		SingleNode<T> reer = new SingleNode<T>(value, null);
		if (this.length == 0) {
			this.rear = reer;
			this.front = reer;
		} else {
			this.rear.setNext(reer);
			this.rear = reer;
		}
		this.length += 1;
		return;
	}

	/**
	 * Removes duplicates from this SingleList. The list contains one and only one
	 * of each value formerly present in this SingleList. The first occurrence of
	 * each value is preserved.
	 */
	public void clean() {
		SingleNode<T> current = this.front;
		HashSet<T> record = new HashSet<T>();
		SingleNode<T> previous = null;
		while (current != null) {
			if (!record.add(current.getValue())) {
				previous.setNext(current.getNext());
				this.length -= 1;
			}
			previous = current;
			current = current.getNext();
		}

		return;
	}

	/**
	 * Combines contents of two lists into a third. Values are alternated from the
	 * origin lists into this SingleList. The origin lists are empty when finished.
	 * NOTE: value must not be moved, only nodes.
	 *
	 * @param left  The first list to combine with this SingleList.
	 * @param right The second list to combine with this SingleList.
	 */
	public void combine(final SingleList<T> left, final SingleList<T> right) {
		boolean side = true;
		SingleNode<T> current_l = left.front;
		SingleNode<T> current_r = right.front;
		while (current_l != null || current_r != null) {
			if (current_l != null) {
				this.append(current_l.getValue());
				this.length += 1;
				current_l = current_l.getNext();
			}

			if (current_r != null) {
				this.append(current_r.getValue());
				this.length += 1;
				current_r = current_r.getNext();

			}
		}
		left.length = 0;
		right.length = 0;
		left.front = null;
		left.rear = null;
		right.front = null;
		right.rear = null;

		return;
	}

	/**
	 * Determines if this SingleList contains key.
	 *
	 * @param key The key value to look for.
	 * @return true if key is in this SingleList, false otherwise.
	 */
	public boolean contains(final T key) {
		SingleNode<T> current = this.front;
		boolean result = false;
		while (current != null) {
			if (current.getValue().equals(key)) {
				result = true;
				break;
			}
			current = current.getNext();
		}

		return result;
	}

	/**
	 * Finds the number of times key appears in list.
	 *
	 * @param key The value to look for.
	 * @return The number of times key appears in this SingleList.
	 */
	public int count(final T key) {
		SingleNode<T> current = this.front;
		int count = 0;
		while (current != null) {
			if (current.getValue().equals(key)) {
				count += 1;
			}
			current = current.getNext();
		}

		return count;
	}

	/**
	 * Finds and returns the value in list that matches key.
	 *
	 * @param key The value to search for.
	 * @return The value that matches key, null otherwise.
	 */
	public T find(final T key) {
		SingleNode<T> current = this.front;
		T result = null;
		while (current != null) {
			if (current.getValue().equals(key)) {
				result = current.getValue();
				break;
			}
			current = current.getNext();
		}

		return result;
	}

	/**
	 * Get the nth item in this SingleList.
	 *
	 * @param n The index of the item to return.
	 * @return The nth item in this SingleList.
	 * @throws ArrayIndexOutOfBoundsException if n is not a valid index.
	 */
	public T get(final int n) throws ArrayIndexOutOfBoundsException {
		SingleNode<T> current = this.front;
		int count = 0;
		T result = null;
		while (current != null) {
			if (count == n) {
				result = current.getValue();
				break;
			}
			count += 1;
			current = current.getNext();
		}

		return result;
	}

	/**
	 * Determines whether two lists are identical.
	 *
	 * @param source The list to compare against this SingleList.
	 * @return true if this SingleList contains the same values in the same order as
	 *         source, false otherwise.
	 */
	public boolean identical(final SingleList<T> source) {
		boolean result = true;
		if (this.length == source.length) {
			SingleNode<T> current_s = source.front;
			SingleNode<T> current = this.front;
			while (current != null) {
				if (!current.getValue().equals(current_s.getValue())) {
					result = false;
					break;
				} else {
					result = true;
				}
				current = current.getNext();
				current_s = current_s.getNext();
			}
		} else {
			result = false;
		}

		return result;
	}

	/**
	 * Finds the first location of a value by key in this SingleList.
	 *
	 * @param key The value to search for.
	 * @return The index of key in this SingleList, -1 otherwise.
	 */
	public int index(final T key) {
		int index = -1;
		int count = 0;
		SingleNode<T> current = this.front;
		while (current != null) {
			if (current.getValue() == key) {
				index = count;
				break;
			}
			count += 1;
			current = current.getNext();
		}
		return index;
	}

	/**
	 * Inserts value into this SingleList at index i. If i greater than the length
	 * of this SingleList, append value to the end of this SingleList.
	 *
	 * @param i     The index to insert the new value at.
	 * @param value The new value to insert into this SingleList.
	 */
	public void insert(int i, final T value) {
		SingleNode<T> current = this.front;
		SingleNode<T> new_node = new SingleNode<T>(value, null);

		if (i == 0) {
			new_node.setNext(current);
			this.front = new_node;
			if (this.rear == null) {
				this.rear = new_node;
			}
		} else if (i > this.length - 1) {
			this.rear.setNext(new_node);
			this.rear = new_node;
		} else {
			int count = 1;
			SingleNode<T> previous = current;
			current = current.getNext();
			while (current != null) {
				if (count == i) {
					new_node.setNext(current);
					previous.setNext(new_node);
					break;
				}
				previous = current;
				current = current.getNext();
				count++;
			}

		}
		this.length += 1;

		return;
	}

	/**
	 * Creates an intersection of two other SingleLists into this SingleList. Copies
	 * value to this SingleList. left and right SingleLists are unchanged. Values
	 * from left are copied in order first, then values from right are copied in
	 * order.
	 *
	 * @param left  The first SingleList to create an intersection from.
	 * @param right The second SingleList to create an intersection from.
	 */
	public void intersection(final SingleList<T> left, final SingleList<T> right) {
		SingleNode<T> current_l = left.front;
		SingleNode<T> current_r = right.front;
		HashSet<T> count = new HashSet<T>();
		while (current_l != null) {
			if (count.add(current_l.getValue())) {
				this.append(current_l.getValue());
			}
			current_l = current_l.getNext();

		}
		while (current_r != null) {
			if (count.add(current_r.getValue())) {
				this.append(current_r.getValue());
			}
			current_r = current_r.getNext();
		}
		return;
	}

	/**
	 * Finds the maximum value in this SingleList.
	 *
	 * @return The maximum value.
	 */
	public T max() {
		SingleNode<T> current = this.front;
		T maximum = current.getValue();
		while (current != null) {
			if (maximum.compareTo(current.getValue()) < 0) {
				maximum = current.getValue();
			}
			current = current.getNext();
		}

		return maximum;
	}

	/**
	 * Finds the minimum value in this SingleList.
	 *
	 * @return The minimum value.
	 */
	public T min() {

		SingleNode<T> current = this.front;
		T minimum = current.getValue();
		while (current != null) {
			if (minimum.compareTo(current.getValue()) > 0) {
				minimum = current.getValue();
			}
			current = current.getNext();
		}

		return minimum;
	}

	/**
	 * Inserts value into the front of this SingleList.
	 *
	 * @param value The value to insert into the front of this SingleList.
	 */
	public void prepend(final T value) {
		SingleNode<T> new_node = new SingleNode<T>(value, this.front);
		this.front = new_node;
		this.length += 1;
		return;
	}

	/**
	 * Finds, removes, and returns the value in this SingleList that matches key.
	 *
	 * @param key The value to search for.
	 * @return The value matching key, null otherwise.
	 */
	public T remove(final T key) {
		SingleNode<T> current = this.front;
		SingleNode<T> previous = null;
		T result = null;
		while (current != null) {
			if (current.getValue().compareTo(key) == 0) {
				if (previous == null) {
					result = current.getValue();
					this.front = this.front.getNext();
					this.length -= 1;
					break;
				} else {
					previous.setNext(current.getNext());
					result = current.getValue();
					this.length -= 1;
					break;
				}
			}
			previous = current;
			current = current.getNext();
		}
		return result;
	}

	/**
	 * Removes the value at the front of this SingleList.
	 *
	 * @return The value at the front of this SingleList.
	 */
	public T removeFront() {
		T result = this.front.getValue();
		this.front = this.front.getNext();
		this.length -= 1;

		return result;
	}

	/**
	 * Finds and removes all values in this SingleList that match key.
	 *
	 * @param key The value to search for.
	 */
	public void removeMany(final T key) {
		SingleNode<T> current = this.front;
		SingleNode<T> previous = null;
		T result = null;
		while (current != null) {
			if (current.getValue().compareTo(key) == 0) {
				if (previous == null) {
					result = current.getValue();
					this.front = this.front.getNext();
					this.length -= 1;

				} else {
					previous.setNext(current.getNext());
					result = current.getValue();
					this.length -= 1;

				}
			}
			previous = current;
			current = current.getNext();
		}
		return;
	}

	/**
	 * Reverses the order of the values in this SingleList.
	 */
	public void reverse() {
		ArrayList<T> a_list = new ArrayList<T>();
		SingleNode<T> current = this.front;
		while (current != null) {
			a_list.add(current.getValue());
			current = current.getNext();
		}
		this.length = 0;
		this.front = null;
		this.rear = null;
		for (int x = 0; x < a_list.size(); x++) {
			int i = a_list.size() - x - 1;
			this.append(a_list.get(i));
		}

		return;
	}

	/**
	 * Splits the contents of this SingleList into the left and right SingleLists.
	 * Moves nodes only - does not move value or call the high-level methods insert
	 * or remove. this SingleList is empty when done. The first half of this
	 * SingleList is moved to left, and the last half of this SingleList is moved to
	 * right. If the resulting lengths are not the same, left should have one more
	 * item than right. Order is preserved.
	 *
	 * @param left  The first SingleList to move nodes to.
	 * @param right The second SingleList to move nodes to.
	 */
	public void split(final SingleList<T> left, final SingleList<T> right) {
		SingleNode<T> current = this.front;
		int half = this.length / 2;
		while (current != null) {
			if (this.length > half) {
				left.append(current.getValue());
				this.length -= 1;
			} else {
				right.append(current.getValue());
				this.length -= 1;
			}
			current = current.getNext();
		}
		this.front = null;
		this.rear = null;
		this.length = 0;
		return;
	}

	/**
	 * Splits the contents of this SingleList into the left and right SingleLists.
	 * Moves nodes only - does not move value or call the high-level methods insert
	 * or remove. this SingleList is empty when done. Nodes are moved alternately
	 * from this SingleList to left and right. Order is preserved.
	 *
	 * @param left  The first SingleList to move nodes to.
	 * @param right The second SingleList to move nodes to.
	 */
	public void splitAlternate(final SingleList<T> left, final SingleList<T> right) {
		SingleNode<T> current = this.front;
		boolean side = true;
		while (current != null) {
			if (side) {
				left.append(current.getValue());
				side = false;
			} else {
				right.append(current.getValue());
				side = true;
			}
			current = current.getNext();
		}
		this.front = null;
		this.rear = null;
		this.length = 0;

		return;
	}

	/**
	 * Creates a union of two other SingleLists into this SingleList. Copies value
	 * to this list. left and right SingleLists are unchanged. Values from left are
	 * copied in order first, then values from right are copied in order.
	 *
	 * @param left  The first SingleList to create a union from.
	 * @param right The second SingleList to create a union from.
	 */
	public void union(final SingleList<T> left, final SingleList<T> right) {
		SingleNode<T> current_l = left.front;
		SingleNode<T> current_r = right.front;
		HashSet<T> count = new HashSet<T>();
		while (current_l != null) {
			if (count.add(current_l.getValue())) {
				this.append(current_l.getValue());
			}
			current_l = current_l.getNext();

		}
		while (current_r != null) {
			if (count.add(current_r.getValue())) {
				this.append(current_r.getValue());
			}
			current_r = current_r.getNext();
		}
		return;
	}
}
