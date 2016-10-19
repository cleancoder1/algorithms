package week2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DequeTest {


    @Test
    public void addsElementsToFront() throws Exception {
        Deque<Integer> deque = new Deque();
        deque.addFirst(123);
        assertEquals(1, deque.size());
    }


    @Test
    public void addsElementToLast() throws Exception {


    }
}