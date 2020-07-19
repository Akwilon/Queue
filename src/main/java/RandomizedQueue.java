import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] array;
    private int position;
    private int size;
    private int lowestDeletedIndex;

    // construct an empty randomized queue
    public RandomizedQueue() {
        array = (Item[]) new Object[10];
        position = 0;
        lowestDeletedIndex = array.length - 1;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return position == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size > array.length * 0.75) {
            array = arrayRewriter(array, array.length * 2);
        }
        while (array[position] != null) {
            if (position == array.length - 1) {
                position = 0;
            }
            position++;
        }
        array[position] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        int deleteIndex = StdRandom.uniform(0, array.length);
        while (array[deleteIndex] == null) {
            deleteIndex = StdRandom.uniform(0, array.length);
        }
        Item res = array[deleteIndex];
        array[deleteIndex] = null;
        if (deleteIndex < lowestDeletedIndex) {
            position = deleteIndex;
            lowestDeletedIndex = deleteIndex;
        }

        size--;
        return res;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        int randomIndex = StdRandom.uniform(0, array.length);
        while (array[randomIndex] == null) {
            randomIndex = StdRandom.uniform(0, array.length);
        }
        return array[randomIndex];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }


    private boolean checkUsed(boolean[] used, int suspect) {
        if (array[suspect] == null) {
            return true;
        }
        return used[suspect];
    }


    private class RandomIterator implements Iterator<Item> {
        int currentPosition = 0;
        int countNext = 0;
        boolean[] used = new boolean[array.length];

        public boolean hasNext() {
            return countNext < size;
        }

        public Item next() {
            if (countNext >= size){
                throw new NoSuchElementException();
            }
            int randomInt = StdRandom.uniform(0, array.length);
            while (checkUsed(used, randomInt)) {
                randomInt = StdRandom.uniform(0, array.length);
            }
            currentPosition = randomInt;
            used[currentPosition] = true;
            Item res = array[currentPosition];
            countNext++;
            return res;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private Item[] arrayRewriter(Item[] arr, int size) {
        Item[] res = (Item[]) new Object[size];
        System.arraycopy(arr, 0, res, 0, arr.length);
        return res;
    }


    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue(11);
        randomizedQueue.dequeue();
        randomizedQueue.size();
        randomizedQueue.enqueue(4);
        randomizedQueue.sample();
        randomizedQueue.isEmpty();
        for (int i : randomizedQueue) {
            StdOut.println(i);
        }
    }
}
