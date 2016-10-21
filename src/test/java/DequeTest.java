import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DequeTest {

    private Deque<String> cut;

    @Before
    public void setUp() {
        cut = new Deque<>();
    }


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
        deque.addLast(567);
        deque.addFirst(345);

        assertTrue(deque.removeFirst().equals(Integer.valueOf(345)));
        assertThat(deque.removeLast(), is(Integer.valueOf(567)));
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

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullpointerByAddingNullAsFistElement() {
        cut.addFirst(null);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullpointerByAddingNullAsLastElement() {
        cut.addLast(null);
    }

    @Test
    public void shouldReturnFirstItem() {
        // when
        cut.addFirst("foo");

        assertFalse(cut.isEmpty());
        assertEquals(1, cut.size());
        assertEquals("foo", cut.removeFirst());
    }

    @Test
    public void shouldReturnLastItem() {
        // when
        cut.addLast("foo");

        assertFalse(cut.isEmpty());
        assertEquals(1, cut.size());
        assertEquals("foo", cut.removeLast());
    }

    @Test
    public void shouldReturnFirstItemAsLastItemOfQueueIsSizeOne() {
        // when
        cut.addFirst("foo");

        // then
        assertFalse(cut.isEmpty());
        assertEquals(1, cut.size());
        assertEquals("foo", cut.removeLast());
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowNoSuchElementExceptionOnRemovingFirstItemFromEmptyDequq() {
        cut.removeFirst();
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowNoSuchElementExceptionOnRemovingLastItemFromEmptyDequq() {
        cut.removeLast();
    }

    @Test
    public void testIterationAfterAddingFirst() {
        // given
        cut.addFirst("foo");
        cut.addFirst("bar");

        // when
        Iterator<String> iterator = cut.iterator();

        // then
        assertEquals("bar", iterator.next());
        assertEquals("foo", iterator.next());
    }

    @Test
    public void testIterationAfterAddingLast() {
        // given
        cut.addLast("foo");
        cut.addLast("bar");

        // when
        Iterator<String> iterator = cut.iterator();

        // then
        assertEquals("foo", iterator.next());
        assertEquals("bar", iterator.next());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void iteratorShouldThrowUnsupportedOperationExceptionWhenCallingRemove() {
        cut.iterator().remove();
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorShouldThrowNoSuchElementExceptionIfLastElementIsReached() {
        assertFalse(cut.iterator().hasNext());
        cut.iterator().next();
    }
}