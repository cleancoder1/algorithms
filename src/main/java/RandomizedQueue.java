
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Node<Item> first;
    private Node<Item> last;

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

    }

    public Item dequeue() {
        boolean isEmpty = isEmpty();
        if (isEmpty) {
            throw new NoSuchElementException();
        }

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

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}