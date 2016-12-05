
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Object[] elements;

    private int head;
    private int tail;


    public RandomizedQueue() {
        elements = (Item[]) new Object[1];
    }

    public static void main(String[] args) {

    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return tail - head;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (tail == elements.length) {
            resize();
        }
        elements[tail++] = item;

        int loc = StdRandom.uniform(0, size());
        Item temp = (Item) elements[loc];
        elements[loc] = elements[size() - 1];
        elements[size() - 1] = temp; // last one


    }

    public Item dequeue() {
        if (tail == 0) {
            throw new NoSuchElementException();
        }
        Item element = (Item) elements[head];
        elements[head] = null; //avoid loitering
        head = head + 1;
        return element;
    }


    private void resize() {
        int length = elements.length;
        Object[] copy = new Object[2 * length];
        for (int i = 0; i < length; i++)
            copy[i] = elements[i];
        elements = copy;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int index = StdRandom.uniform(head, tail);
        return (Item) elements[index];
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }


    private class RandomizedQueueIterator implements Iterator<Item> {
        private int current = head;
        private int[] lookuptable;

        public RandomizedQueueIterator() {
            lookuptable = new int[size()];
            for (int i = 0; i < size(); i++) {
                lookuptable[i] = i;
            }
            StdRandom.shuffle(lookuptable);

        }

        @Override
        public boolean hasNext() {
            if (size() == 0) {
                return false;
            }
            return current < tail;
        }

        @Override
        public Item next() {
            if (current == tail) {
                throw new NoSuchElementException();
            }
            int i = lookuptable[current];
            current++;

            return (Item) elements[i];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}