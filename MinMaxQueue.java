import java.util.*;
import java.math.*;

class MinMaxQueue {
    private Deque<Integer> queue;
    private Deque<Integer> minQueue;
    private Deque<Integer> maxQueue;
    // Time O(1) Space O(n)
    public MinMaxQueue() {
        queue = new ArrayDeque<>();
        minQueue = new ArrayDeque<>();
        maxQueue = new ArrayDeque<>();
    }

    public void enqueue(int element) {
        if (queue.size() == 0) {
            queue.addLast(element);
            minQueue.addLast(element);
            maxQueue.addLast(element);
        } else {
            queue.addLast(element);
            
            // For minimum deque: remove all elements from the back of minQueue that are greater than val.
            // ensures that minQueue remains sorted in increasing order
            while (!minQueue.isEmpty() && minQueue.peekLast() > element) {
                minQueue.removeLast();
            }
            minQueue.addLast(element);
            
            // For maximum deque: remove all elements from the back of maxQueue that are less than val
            // keeps maxQueue sorted in decreasing order
            while (!maxQueue.isEmpty() && maxQueue.peekLast() < element) {
                maxQueue.removeLast();
            }
            maxQueue.addLast(element);
        }
    }

    public int dequeue() {
        int removed = queue.peekFirst();
        queue.removeFirst();
        // val also in front, remove corresponding min/max
        if (!minQueue.isEmpty() && removed == minQueue.peekFirst()) {
            minQueue.removeFirst();
        }
        if (!maxQueue.isEmpty() && removed == maxQueue.peekFirst()) {
            maxQueue.removeFirst();
        }
        return removed;
    }

    public int getMin() {
        return minQueue.peekFirst();
    }

    public int getMax() {
        return maxQueue.peekFirst();
    }
}
