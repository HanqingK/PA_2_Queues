package Program_Assignment_2_Queues;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

// A randomized queue is similar to a stack or queue,
// except that the item removed is chosen uniformly at random among items in the data structure.

// Performance requirements.
// Your randomized queue implementation must support each randomized queue operation(besides creating an iterator) in constant amortized time.
// That is,any intermixed sequence of m randomized queue operations(starting from an empty queue)must take at most cm steps in the worst case,
// for some constant c.A randomized queue containing n items must use at most 48n+192bytes of memory.
// Additionally,your iterator implementation must support operations next()and hasNext()in constant worst-case time;
// and construction in linear time;you may(and will need to)use a linear amount of extra memory per iterator.

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] randomQueue;
    private int N;

    // construct an empty randomized queue
    public RandomizedQueue() {
        randomQueue = (Item[]) new Object[1];
        N = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return N;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (N == randomQueue.length) {
            resize(2 * N);
        }
        randomQueue[N++] = item;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = randomQueue[i];
        }
        randomQueue = copy;
    }

    // remove and return a random item
    public Item dequeue() {
        if (N == 0) {
            throw new NoSuchElementException();
        }
        int rm = StdRandom.uniform(N);
        Item item = randomQueue[rm];
        randomQueue[rm] = randomQueue[--N];
        if (N > 0 && N < randomQueue.length / 4) {
            resize(randomQueue.length / 2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (N == 0) {
            throw new NoSuchElementException();
        }
        int rm = StdRandom.uniform(N);
        return randomQueue[rm];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private int[] arrayOfIndex = StdRandom.permutation(N);
        private int i = 0;

        public boolean hasNext() {
            return i < N;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if (i == N) {
                throw new NoSuchElementException();
            }
            int index = arrayOfIndex[i];
            i++;
            Item item = randomQueue[index];
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        for (int i = 0; i < 50; i++) {
            rq.enqueue(i);
        }
        StdOut.println(rq.size());
        // rq.enqueue(null);

        Iterator irt = rq.iterator();
        while (irt.hasNext()) {
            StdOut.print(irt.next() + " ");
        }
        StdOut.println();

        Iterator irt2 = rq.iterator();
        while (irt2.hasNext()) {
            StdOut.print(irt2.next() + " ");
        }
        StdOut.println();
        for (int j = 0; j < 50; j++) {
            StdOut.print(rq.sample() + " ");
        }
        StdOut.println();
        StdOut.println(rq.size());

    }

}
