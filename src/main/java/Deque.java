import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first, last;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> previous;
    }

    public Deque() {

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

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException("cannot add nulls");
        }
        boolean isEmpty = isEmpty();
        Node<Item> oldFirst = first;
        Node<Item> element = new Node<>();
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
        if (item == null) {
            throw new NullPointerException("cannot add nulls");
        }
        boolean isEmpty = isEmpty();
        Node<Item> oldLast = last;
        Node<Item> element = new Node<>();
        element.item = item;
        last = element;
        if (!isEmpty) {
            oldLast.next = element;
            last.previous = oldLast;
        }
        if (last.previous == null) {
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
        int size = size();
        if (!isEmpty()) {
            Item item = last.item;
            if (size == 1) {
                first = null;
                last = null;
                return item;
            }
            Node<Item> newLast = last.previous;
            last = newLast;
            newLast.next = null;
            return item;

        }
        throw new NoSuchElementException();
    }


    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node<Item> current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

    }

    public static void main(String[] args) {

    }
}