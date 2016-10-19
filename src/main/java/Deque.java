import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;

    private class Node {
        private Item item;
        private Node next;
    }

    public Deque() {

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
        element.item = item;
        last = element;
        oldLast.next = element;
    }

    public Item removeFirst() {
        if (!isEmpty()) {
            Item item = first.item;
            first = first.next;
            return item;
        }

        throw new NoSuchElementException();
    }


    public Item removeLast() {
        if (!isEmpty()) {
            Node current = first;
            Node newLast = null;
            while (current.next != null) {
                current = current.next;
                
            }
            return current.item;
        }

        throw new NoSuchElementException();
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