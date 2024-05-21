package cp213;

import java.util.HashSet;

/**
 * COMPLETE THE CODE AT // your code here
 *
 * A single linked priority queue structure of <code>Node T</code> objects.
 * These value objects must be Comparable - i.e. they must provide the compareTo
 * method. Only the <code>T</code> value contained in the priority queue is
 * visible through the standard priority queue methods. Extends the
 * <code>SingleLink</code> class.
 *
 * @author Anastasia Malinovski, 200694700, mali4700@mylaurier.ca
 * @version 2021-07-02
 * @param <T> value type for base value structure.
 */
public class SinglePriorityQueue<T extends Comparable<T>> extends SingleLink<T> {

	/**
	 * Combines the contents of the left and right SinglePriorityQueues into the
	 * current SinglePriorityQueue. Moves nodes only - does not move value or call
	 * the high-level methods insert or remove. left and right SinglePriorityQueues
	 * are empty when done. Nodes are moved alternately from left and right to this
	 * SinglePriorityQueue. When finished all nodes must be in priority order from
	 * front to rear.
	 *
	 * Do not use the SinglePriorityQueue insert and remove methods.
	 *
	 * Do not assume that both right and left priority queues are of the same
	 * length.
	 *
	 * @param left  The first SinglePriorityQueue to extract nodes from.
	 * @param right The second SinglePriorityQueue to extract nodes from.
	 */
	public void combine(final SinglePriorityQueue<T> left, final SinglePriorityQueue<T> right) {
		assert this.front == null : "May combine into an empty Priority Queue only";

		SingleNode<T> current_l = left.front;
		SingleNode<T> current_r = right.front;
		while (current_l != null || current_r != null) {
			if (current_l != null) {
				this.insert(current_l.getValue());
				current_l = current_l.getNext();
			}
			if (current_r != null) {
				this.insert(current_r.getValue());
				current_r = current_r.getNext();
			}
		}
		return;
	}

	/**
	 * Adds value to the SinglePriorityQueue. Data is stored in priority order, with
	 * highest priority value at the front of the SinglePriorityQueue, and lowest at
	 * the rear. Priority is determined by simple comparison - lower values have
	 * higher priority. For example, 1 has a higher priority than 2 because 1 is a
	 * lower value than 2. (Think of the phrase, "We're number one!" as an
	 * indication of priority.)
	 *
	 * When inserting value to the priority queue, the queue must remain sorted.
	 * Hence you need to find the proper location of inserting value. use the head
	 * pointer to go through the queue. e.g., use SingleNode&lt;T&gt; current =
	 * this.head;
	 *
	 * use current = current.getNext(); to traverse the queue.
	 *
	 * To get access to the value inside a node of queue use current.getValue().
	 *
	 * @param value value to insert in sorted order in priority queue.
	 */
	public void insert(final T value) {
		SingleNode<T> in = new SingleNode<T>(value, null);
		SingleNode<T> current = this.front;
		if (current == null) {
			this.front = in;
			this.rear = in;
		} else if (current.getValue().compareTo(value) >= 0) {
			in.setNext(current);
			this.front = in;
		} else if (this.rear.getValue().compareTo(value) <= 0) {
			this.rear.setNext(in);
			this.rear = in;
		} else {
			SingleNode<T> previous = current;
			current = current.getNext();
			while (current != null) {
				if (current.getValue().compareTo(value) >= 0) {
					in.setNext(current);
					previous.setNext(in);
					break;
				}
				previous = current;
				current = current.getNext();
			}
		}
		this.length += 1;

		return;
	}

	/**
	 * Returns the highest priority value in the SinglePriorityQueue. Decrements the
	 * count.
	 *
	 * @return the highest priority value currently in the SinglePriorityQueue.
	 */
	public T remove() {
		T result=this.front.getValue();
		this.front = this.front.getNext();
		this.length -= 1;

		return result;
	}

	/**
	 * Splits the contents of this SinglePriorityQueue into the higher and lower
	 * SinglePriorityQueue. Moves nodes only - does not move value or call the
	 * high-level methods insert or remove. this SinglePriorityQueue is empty when
	 * done. Nodes with priority value higher than key are moved to the
	 * SinglePriorityQueue higher. Nodes with a priority value lower than or equal
	 * to key are moved to the SinglePriorityQueue lower.
	 *
	 * Do not use the SinglePriorityQueue insert and remove methods.
	 *
	 * @param key    value to compare against node values in SinglePriorityQueue
	 * @param higher an initially empty SinglePriorityQueue queue that ends up with
	 *               all values with priority higher than key.
	 * @param lower  an initially empty SinglePriorityQueue queue that ends up with
	 *               all values with priority lower than or equal to key.
	 */
	public void splitByKey(final T key, final SinglePriorityQueue<T> higher, final SinglePriorityQueue<T> lower) {
		SingleNode<T> current = this.front;
		if (current.getValue() != null) {
			for (int x = 0; x < this.length; x++) {
				if (current.getValue() == key) {
					higher.insert(key);
				} else if (current.getValue().compareTo(key) < 0) {
					lower.insert(current.getValue());
				} else {
					higher.insert(current.getValue());
				}
				current = current.getNext();
			}
		}
		this.length = 0;
		this.front = null;
		this.rear = null;

		return;
	}
}
