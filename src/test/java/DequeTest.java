import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DequeTest {


    @Test
    public void addsElementsToFront() throws Exception {
        Deque<Integer> deque = new Deque();
        deque.addFirst(123);
        assertEquals(1, deque.size());
    }


    @Test(expected = NoSuchElementException.class)
    public void removeFromEmptyDeque() throws Exception {
        Deque<Integer> deque = new Deque();
        deque.removeLast();
    }

    @Test(expected = NoSuchElementException.class)
    public void removeFirstFromEmptyDeque() throws Exception {
        Deque<Integer> deque = new Deque();
        deque.removeFirst();
    }


    @Test
    public void addAndRemoveElements() throws Exception {
        Deque<Integer> deque = new Deque();
        deque.addFirst(123);
        deque.addLast(234);
        deque.addFirst(345);

        Integer result = deque.removeLast();
        assertTrue(result.equals(Integer.valueOf(234)));
        assertTrue(deque.removeFirst().equals(Integer.valueOf(345)));
        assertThat(deque.removeLast(), is(Integer.valueOf(234)));
    }

    @Test
    public void sizeTest() {
        Deque<Integer> deque = new Deque();
        deque.addFirst(123);
        deque.addFirst(123);
        deque.addFirst(123);
        deque.addFirst(123);
        deque.addFirst(123);
        deque.addFirst(123);
        deque.addFirst(123);
        deque.addLast(234);
        assertThat(deque.size(), is(8));
    }
}