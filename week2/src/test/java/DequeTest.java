import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DequeTest {

//    private Deque<String> cut;
//    private Deque<Integer> d;
//
//    @Before
//    public void setUp() {
//        cut = new Deque<>();
//        d = new Deque<>();
//    }
//
//
//    @Test
//    public void addsElementsToFront() throws Exception {
//        Deque<Integer> deque = new Deque();
//        deque.addFirst(123);
//        assertEquals(1, deque.size());
//    }
//
//
//    @Test(expected = NoSuchElementException.class)
//    public void removeFromEmptyDeque() throws Exception {
//        Deque<Integer> deque = new Deque();
//        deque.removeLast();
//    }
//
//    @Test(expected = NoSuchElementException.class)
//    public void removeFirstFromEmptyDeque() throws Exception {
//        Deque<Integer> deque = new Deque();
//        deque.removeFirst();
//    }
//
//
//    @Test
//    public void addAndRemoveElements() throws Exception {
//        Deque<Integer> deque = new Deque();
//        deque.addFirst(123);
//        deque.addLast(234);
//        deque.addLast(567);
//        deque.addFirst(345);
//
//        assertTrue(deque.removeFirst().equals(Integer.valueOf(345)));
//        assertThat(deque.removeLast(), is(Integer.valueOf(567)));
//    }
//
//    @Test
//    public void sizeTest() {
//        Deque<Integer> deque = new Deque();
//        deque.addFirst(123);
//        deque.addFirst(123);
//        deque.addFirst(123);
//        deque.addFirst(123);
//        deque.addFirst(123);
//        deque.addFirst(123);
//        deque.addFirst(123);
//        deque.addLast(234);
//        assertThat(deque.size(), is(8));
//    }
//
//    @Test
//    //Test 5: Calls to addLast(), removeFirst(), and isEmpty()
//    public void addLastRemoveFirst(){
//        Deque<Integer> deque = new Deque();
//        deque.addLast(123);
//        deque.addLast(123);
//        deque.removeFirst();
//        deque.removeFirst();
//
//
//        assertTrue(deque.isEmpty());
//    }
//
//    @Test(expected = NullPointerException.class)
//    public void shouldThrowNullpointerByAddingNullAsFistElement() {
//        cut.addFirst(null);
//    }
//
//    @Test(expected = NullPointerException.class)
//    public void shouldThrowNullpointerByAddingNullAsLastElement() {
//        cut.addLast(null);
//    }
//
//    @Test
//    public void shouldReturnFirstItem() {
//        // when
//        cut.addFirst("foo");
//
//        assertFalse(cut.isEmpty());
//        assertEquals(1, cut.size());
//        assertEquals("foo", cut.removeFirst());
//    }
//
//    @Test
//    public void shouldReturnLastItem() {
//        // when
//        cut.addLast("foo");
//
//        assertFalse(cut.isEmpty());
//        assertEquals(1, cut.size());
//        assertEquals("foo", cut.removeLast());
//    }
//
//    @Test
//    public void shouldReturnFirstItemAsLastItemOfQueueIsSizeOne() {
//        // when
//        cut.addFirst("foo");
//
//        // then
//        assertFalse(cut.isEmpty());
//        assertEquals(1, cut.size());
//        assertEquals("foo", cut.removeLast());
//    }
//
//    @Test(expected = NoSuchElementException.class)
//    public void shouldThrowNoSuchElementExceptionOnRemovingFirstItemFromEmptyDequq() {
//        cut.removeFirst();
//    }
//
//    @Test(expected = NoSuchElementException.class)
//    public void shouldThrowNoSuchElementExceptionOnRemovingLastItemFromEmptyDequq() {
//        cut.removeLast();
//    }
//
//    @Test
//    public void testIterationAfterAddingFirst() {
//        // given
//        cut.addFirst("foo");
//        cut.addFirst("bar");
//
//        // when
//        Iterator<String> iterator = cut.iterator();
//
//        // then
//        assertEquals("bar", iterator.next());
//        assertEquals("foo", iterator.next());
//    }
//
//    @Test
//    public void testIterationAfterAddingLast() {
//        // given
//        cut.addLast("foo");
//        cut.addLast("bar");
//
//        // when
//        Iterator<String> iterator = cut.iterator();
//
//        // then
//        assertEquals("foo", iterator.next());
//        assertEquals("bar", iterator.next());
//    }
//
//    @Test(expected = UnsupportedOperationException.class)
//    public void iteratorShouldThrowUnsupportedOperationExceptionWhenCallingRemove() {
//        cut.iterator().remove();
//    }
//
//    @Test(expected = NoSuchElementException.class)
//    public void iteratorShouldThrowNoSuchElementExceptionIfLastElementIsReached() {
//        assertFalse(cut.iterator().hasNext());
//        cut.iterator().next();
//    }
//
//    @Test(expected= NullPointerException.class)
//    public void test_add_first_null() {
//        d.addFirst(null);
//    }
//
//    @Test(expected= NullPointerException.class)
//    public void test_add_last_null() {
//        d.addLast(null);
//    }
//
//    @Test(expected= NoSuchElementException.class)
//    public void test_remove_first_empty() {
//        d.removeFirst();
//    }
//
//    @Test(expected= NoSuchElementException.class)
//    public void test_remove_last_empty() {
//        d.removeLast();
//    }
//
//    @Test(expected= NoSuchElementException.class)
//    public void test_empty_iterator_next() {
//        Iterator<Integer> iter = d.iterator();
//        iter.next();
//    }
//
//    @Test(expected= UnsupportedOperationException.class)
//    public void test_iterator_remove() {
//        Iterator<Integer> iter = d.iterator();
//        iter.remove();
//    }
//
//    @Test
//    public void test_empty() {
//        assertTrue(d.isEmpty());
//        assertSame(0, d.size());
//    }
//
//    @Test
//    public void test_add_first() {
//        d.addFirst(1);
//        assertFalse(d.isEmpty());
//        assertSame(1, d.size());
//    }
//
//    @Test
//    public void test_add_last() {
//        d.addLast(1);
//        assertFalse(d.isEmpty());
//        assertSame(1, d.size());
//    }
//
//    @Test
//    public void test_add_multiple() {
//        d.addFirst(3);
//        d.addFirst(2);
//        d.addLast(4);
//        d.addFirst(1);
//
//        assertFalse(d.isEmpty());
//        assertSame(4, d.size());
//    }
//
//    @Test
//    public void test_remove_first() {
//        d.addFirst(2);
//        d.addFirst(1);
//        d.addLast(3);
//
//        int i = d.removeFirst();
//        assertSame(1, i);
//        assertSame(2, d.size());
//
//        i = d.removeFirst();
//        assertSame(2, i);
//        assertSame(1, d.size());
//
//        i = d.removeFirst();
//        assertSame(3, i);
//        assertTrue(d.isEmpty());
//    }
//
//    @Test
//    public void test_remove_last() {
//        d.addLast(2);
//        d.addLast(3);
//        d.addFirst(1);
//
//        int i = d.removeLast();
//        assertSame(3, i);
//        assertSame(2, d.size());
//
//        i = d.removeLast();
//        assertSame(2, i);
//        assertSame(1, d.size());
//
//        i = d.removeLast();
//        assertSame(1, i);
//        assertTrue(d.isEmpty());
//    }
//
//    @Test
//    public void test_empty_full_empty_first() {
//        d.addFirst(1);
//        d.removeFirst();
//        assertTrue(d.isEmpty());
//        assertSame(0, d.size());
//
//        d.addFirst(1);
//        assertFalse(d.isEmpty());
//        assertSame(1, d.size());
//    }
//
//    @Test
//    public void test_empty_full_empty_last() {
//        d.addLast(1);
//        d.removeLast();
//        assertTrue(d.isEmpty());
//        assertSame(0, d.size());
//
//        d.addLast(1);
//        assertFalse(d.isEmpty());
//        assertSame(1, d.size());
//    }
//
//    @Test
//    public void test_empty_full_empty_mixed() {
//        int i;
//
//        d.addFirst(1);
//        i = d.removeLast();
//        assertSame(1, i);
//        assertTrue(d.isEmpty());
//        assertSame(0, d.size());
//
//        d.addLast(1);
//        assertFalse(d.isEmpty());
//        assertSame(1, d.size());
//
//        i = d.removeFirst();
//        assertSame(1, i);
//        assertTrue(d.isEmpty());
//    }
//
//    @Test
//    public void test_iterator_empty() {
//        Iterator<Integer> iter = d.iterator();
//        assertFalse(iter.hasNext());
//    }
//
//    @Test
//    public void test_iterator_add_firsts() {
//        d.addFirst(3);
//        d.addFirst(2);
//        d.addFirst(1);
//
//        Iterator<Integer> iter = d.iterator();
//
//        assertTrue(iter.hasNext());
//        assertSame(1, iter.next());
//        assertTrue(iter.hasNext());
//        assertSame(2, iter.next());
//        assertTrue(iter.hasNext());
//        assertSame(3, iter.next());
//        assertFalse(iter.hasNext());
//    }
//
//    @Test
//    public void test_iterator_add_lasts() {
//        d.addLast(1);
//        d.addLast(2);
//        d.addLast(3);
//
//        Iterator<Integer> iter = d.iterator();
//
//        assertTrue(iter.hasNext());
//        assertSame(1, iter.next());
//        assertTrue(iter.hasNext());
//        assertSame(2, iter.next());
//        assertTrue(iter.hasNext());
//        assertSame(3, iter.next());
//        assertFalse(iter.hasNext());
//    }
//
//    @Test
//    public void test_iterator_mixed() {
//        d.addFirst(2);
//        d.addLast(3);
//        d.addLast(5);
//        d.removeLast();
//        d.addLast(4);
//        d.addFirst(0);
//        d.removeFirst();
//        d.addFirst(1);
//
//        Iterator<Integer> iter = d.iterator();
//        for (int i = 1; i < 5; i++) {
//            assertTrue(iter.hasNext());
//            assertSame(i, iter.next());
//        }
//    }
//
//    @Test
//    public void test_multiple_iterators() {
//        for (int i = 1; i <= 5; i++) d.addLast(i);
//
//        int x = 0, y = 0;
//        for (Integer i : d) {
//            x++;
//            y = 0;
//            for (Integer j : d) {
//                y++;
//                assertSame(x * y, i *j);
//            }
//        }
//    }
//
//    @Test
//    public void test_intermixed_with_iterator() {
//        d.addFirst(2);
//        d.addLast(3);
//        d.addLast(5);
//        d.removeLast();
//        d.addLast(4);
//        d.addFirst(0);
//        d.removeFirst();
//        d.addFirst(1);
//        d.removeFirst();
//        d.removeFirst();
//        d.removeLast();
//        d.removeLast();
//
//        for (Integer i : d) fail();
//    }

}