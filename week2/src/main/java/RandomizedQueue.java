
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Node<Item> first;
    private Node<Item> last;
    int size;

    private static class Node<Item> {
        Item item;
        Node<Item> next;
    }

    public RandomizedQueue() {

    }

    public static void main(String[] args) {

    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        Iterator<Item> iterator = iterator();
        int count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        return count;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        boolean isEmpty = isEmpty();
        Node<Item> oldLast = last;
        Node<Item> newElement = new Node<>();
        newElement.item = item;
        if (isEmpty) {
            first = newElement;
        }
        last = newElement;
        if (oldLast != null) {
            oldLast.next = newElement;
        }
        size++;

    }

    public Item dequeue() {
        boolean isEmpty = isEmpty();
        if (isEmpty) {
            throw new NoSuchElementException();
        }
        size--;
        int index = StdRandom.uniform(0, size());
        Node<Item> node = getNode(index);
        if (index > 0) {
            Node<Item> previousNode = getNode(index - 1);
            previousNode.next = node.next;
        }
        if (index == 0) {
            first = node.next;

            return node.item;

        }
        return node.item;

    }


    private Node<Item> getNode(int index) {
        Node<Item> x = first;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }

    public Item sample() {
        boolean isEmpty = isEmpty();
        if (isEmpty) {
            throw new NoSuchElementException();
        }

        int index = StdRandom.uniform(0, size());
        return getNode(index).item;
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }


    private class RandomizedQueueIterator implements Iterator<Item> {
        private Node<Item> current = first;
        private int[] lookuptable;
        int count = 0;

        public RandomizedQueueIterator() {
            lookuptable = new int[size];
            for (int i = 0; i < size; i++) {
                lookuptable[i] = i;
            }
            StdRandom.shuffle(lookuptable);

        }

        @Override
        public boolean hasNext() {
            return count == size;
//            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int index = lookuptable[count];
            Node<Item> node = getNode(index);
            //remove this element at index
            count++;
            return node.item;
//            Item item = current.item;
//            current = current.next;
//            return item;
        }
    }
}