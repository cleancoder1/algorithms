import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Object[] elements;
    private int head;
    private int tail;

    public Deque() {
        elements = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return (tail - head) & (elements.length - 1);
    }               // return the number of items on the deque

    public void addFirst(Item e) {
        if (e == null) {
            throw new NullPointerException();
        }
        elements[head = (head - 1) & (elements.length - 1)] = e;
        if (head == tail)
            doubleCapacity();

    }          // add the item to the front

    public void addLast(Item e) {
        if (e == null)
            throw new NullPointerException();
        elements[tail] = e;
        if ((tail = (tail + 1) & (elements.length - 1)) == head)
            doubleCapacity();
    }           // add the item to the end

    public Item removeFirst() {
        Item x = pollFirst();
        if (x == null)
            throw new NoSuchElementException();
        return x;
    }                // remove and return the item from the front

    public Item removeLast() {
        Item x = pollLast();
        if (x == null)
            throw new NoSuchElementException();
        return x;
    }                 // remove and return the item from the end

    public Iterator<Item> iterator() {
        return new DeqIterator();
    }         // return an iterator over items in order from front to end

    public static void main(String[] args) {

    }   // unit testing

    private void doubleCapacity() {
        assert head == tail;
        int p = head;
        int n = elements.length;
        int r = n - p; // number of elements to the right of p
        int newCapacity = n << 1;
        if (newCapacity < 0)
            throw new IllegalStateException("Sorry, deque too big");
        Object[] a = new Object[newCapacity];
        System.arraycopy(elements, p, a, 0, r);
        System.arraycopy(elements, 0, a, r, p);
        elements = a;
        head = 0;
        tail = n;
    }

    private Item pollFirst() {
        int h = head;
        @SuppressWarnings("unchecked")
        Item result = (Item) elements[h];
        // Element is null if deque empty
        if (result == null)
            return null;
        elements[h] = null;     // Must null out slot
        head = (h + 1) & (elements.length - 1);
        return result;
    }

    private Item pollLast() {
        int t = (tail - 1) & (elements.length - 1);
        @SuppressWarnings("unchecked")
        Item result = (Item) elements[t];
        if (result == null)
            return null;
        elements[t] = null;
        tail = t;
        return result;
    }


    private class DeqIterator implements Iterator<Item> {
        /**
         * Index of element to be returned by subsequent call to next.
         */
        private int cursor = head;

        /**
         * Tail recorded at construction (also in remove), to stop
         * iterator and also to check for comodification.
         */
        private int fence = tail;



        public boolean hasNext() {
            return cursor != fence;
        }

        public Item next() {
            if (cursor == fence)
                throw new NoSuchElementException();
            @SuppressWarnings("unchecked")
            Item result = (Item) elements[cursor];
            // This check doesn't catch all possible comodifications,
            // but does catch the ones that corrupt traversal
            if (tail != fence || result == null)
                throw new ConcurrentModificationException();
            cursor = (cursor + 1) & (elements.length - 1);
            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}