package week2;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    Node first, last;

    private class Node {
        Item item;
        Node next;
    }

    public Deque() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return first == last;
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

    public void addFirst(Item item) {
        Node oldFirst = first;
        Node element = new Node();
        element.item = item;
        first = element;
        first.next = oldFirst;

    }

    public void addLast(Item item) {
        Node oldLast = last;
        Node element = new Node();


    }

    public Item removeFirst() {
        return null;
    }


    public Item removeLast() {
        return null;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator<Item>();
    }

    private class DequeIterator<Item> implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = (Item) current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {

    }
}