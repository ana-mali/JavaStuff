package cp213;

/**
 * COMPLETE THE CODE AT // your code here
 *
 * A single linked stack structure of <code>Node T</code> objects. Only the
 * <code>T</code> value contained in the stack is visible through the standard
 * stack methods. Extends the <code>SingleLink</code> class. Note that the rear
 * attribute should be ignored as the rear is not used in a stack.
 *
 * @author // your name, id, email here
 * @version 2021-06-16
 * @param <T> the SingleStack value type.
 */
public class SingleStack<T> extends SingleLink<T> {

	/**
	 * Combines the contents of the left and right SingleStacks into the current
	 * SingleStack. Moves nodes only - does not refer to values in any way, or call
	 * the high-level methods pop or push. left and right SingleStacks are empty
	 * when done. Nodes are moved alternately from left and right to this
	 * SingleStack.
	 *
	 * You have two source stacks named left and right. Move all nodes from these
	 * two stacks to the current stack. It does not make a difference if the current
	 * stack is empty or not, just get nodes from the right and left stacks and add
	 * them to the current stack. You may use any appropriate SingleLink helper
	 * methods available.
	 *
	 * Do not assume that both right and left stacks are of the same length.
	 *
	 * @param left  The first SingleStack to extract nodes from.
	 * @param right The second SingleStack to extract nodes from.
	 */
	public void combine(final SingleStack<T> left, final SingleStack<T> right) {
		int total_length = left.length + right.length;
		boolean side = true;
		for (int x = 0; x < total_length; x++) {
			if (side) {
				if (left.length > 0) {
					this.push(left.front.getValue());
					left.front = left.front.getNext();
					left.length -= 1;
				} else {
					this.push(right.front.getValue());
					right.front = right.front.getNext();
					right.length -= 1;
				}
			} else {
				if (right.length > 0) {
					this.push(right.front.getValue());
					right.front = right.front.getNext();
					right.length -= 1;
				} else {
					this.push(left.front.getValue());
					left.front = left.front.getNext();
					left.length -= 1;
				}
			}
		}
		left.length = 0;
		right.length = 0;
		left.front = null;
		right.front = null;
		left.rear = null;
		right.rear = null;

		return;
	}

	/**
	 * Returns the top value of the stack and removes that value from the stack. The
	 * next node in the stack becomes the new top node. Decrements the stack length.
	 *
	 * @return The value at the top of the stack.
	 */
	public T pop() {

		T result = this.rear.getValue();
		SingleNode<T> current = this.front;
		while (current != null) {
			if (current.getNext() == this.rear) {
				current.setNext(null);
				this.rear = current;
			}
			current=current.getNext();
		}
		this.length -= 1;
		if (this.length==0) {
			this.front=null;
			this.rear=null;
		}
		return result;
	}

	/**
	 * Adds value to the top of the stack. Increments the stack length.
	 *
	 * @param value The value to add to the top of the stack.
	 */
	public void push(final T value) {
		if (this.length == 0) {
			this.rear = new SingleNode<T>(value, null);
			this.front = this.rear;
		} else {
			SingleNode<T> reer = new SingleNode<T>(value, null);
			this.rear.setNext(reer);
			this.rear = reer;
		}
		this.length+=1;
		return;
	}

	/**
	 * Splits the contents of the current SingleStack into the left and right
	 * SingleStacks. Moves nodes only - does not move value or call the high-level
	 * methods insert or remove. this SingleStack is empty when done. Nodes are
	 * moved alternately from this SingleStack to left and right. left and right may
	 * already contain values.
	 *
	 * This is the opposite of the combine method.
	 *
	 * @param left  The first SingleStack to move nodes to.
	 * @param right The second SingleStack to move nodes to.
	 */
	public void splitAlternate(final SingleStack<T> left, final SingleStack<T> right) {
		SingleNode<T> current = this.front;
		boolean side = true;
		while (current != null) {
			if (side) {
				left.push(current.getValue());
				side = false;
			} else {
				right.push(current.getValue());
				side = true;
			}
			current = current.getNext();

		}
		this.front = null;
		this.rear = null;
		this.length = 0;

		return;
	}
	public T peek() {
		T result=null;
		if(this.length!=0) {
			result=this.rear.getValue();
		}
		
		return result;
	}
}