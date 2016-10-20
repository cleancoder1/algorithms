import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;

    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }

    public Deque() {

    }

    public boolean isEmpty() {
        return size() == 0;
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
        boolean isEmpty = isEmpty();
        Node oldFirst = first;
        Node element = new Node();
        element.item = item;
        first = element;
        if (!isEmpty) {
            first.next = oldFirst;
            oldFirst.previous = element;
        }
        if (first.next == null) {
            last = first;
        }

    }

    public void addLast(Item item) {
        boolean isEmpty = isEmpty();
        Node oldLast = last;
        Node element = new Node();
        element.item = item;
        last = element;
        if (!isEmpty) {
            oldLast.next = element;
            last.previous = oldLast;
        }
        if (first.next == null) {
            first = last;
        }

    }

    public Item removeFirst() {
        if (!isEmpty()) {
            Item item = first.item;
            first = first.next;
            if (size() == 1) {
                last = null;
            }
            return item;
        }


        throw new NoSuchElementException();
    }


    public Item removeLast() {
        if (!isEmpty()) {
            Item item = last.item;
            Node newLast = last.previous;
            last = newLast;
            newLast.next = null;
            if (size() == 1) {
                first = null;
            }
            return item;

        }
        throw new NoSuchElementException();
    }


    public Iterator<Item> iterator() {
        return new DequeIterator<>();
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