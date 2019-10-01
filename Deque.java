import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

// A double-ended queue or deque is a generalization of a stack and a queue that supports adding and removing items
// from either the front or the back of the data structure

//  Performance Requirements
//  The deque implementation must support each deque operation (including construction) in constant worst-case time.
//  A deque containing n items must use at most 48n + 192 bytes of memory.
//  Additionally, your iterator implementation must support each operation (including construction) in constant worst-case time.


public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int N;

    private class Node {
        Item item;
        Node previous;
        Node next;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        N = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the deque
    public int size() {
        return N;
    }

    // add the item to the front
    public void addFirst(Item item) throws IllegalAccessException {
        if (item == null) {
            throw new IllegalAccessException();
        }
        if (first == null) {
            first = new Node();
            first.item = item;
            last = first;
        } else {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            oldFirst.previous = first;
        }
        N++;
    }

    // add the item to the back
    public void addLast(Item item) throws IllegalArgumentException {
        if (item == null) {
            throw new IllegalArgumentException("Item is null");
        }
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.previous = oldLast;
        if (oldLast != null) {
            oldLast.next = last;
        } else {
            first = last;
        }
        N++;
    }

    // remove and return the item from the front
    public Item removeFirst() throws NoSuchElementException {
        Item item = first.item;
        if (N == 0) {
            throw new NoSuchElementException();
        } else if (N == 1) {
            first = null;
            last = null;
        } else {
            first = first.next;
        }
        N--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() throws NoSuchElementException {
        Item item = last.item;
        if (N == 0) {
            throw new NoSuchElementException();
        } else if (N == 1) {
            last = null;
            first = null;
        } else {
            last = last.previous;
            last.next = null;
        }
        N--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return first != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("Method remove cannot be invoke");
        }

        public Item next() {
            return first.next.item;
        }

    }

    // unit testing (required)
    public static void main(String[] args) throws IllegalAccessException {
        Deque<Integer> dq = new Deque<>();
        dq.addFirst(5);
        dq.removeLast();
        dq.addLast(5);
        StdOut.println("the first item is: " + dq.first.item);
        StdOut.println("the last item is: " + dq.last.item);
        StdOut.println("the size is: " + dq.N);

        for (int i = 0; i < 50; i++) {
            dq.addFirst(i);
        }
        StdOut.println(dq.size());

        for (int i = 0; i < 50; i++) {
            StdOut.print(dq.removeFirst() + " ");
        }
        StdOut.println();
        StdOut.println(dq.size());


    }

}
