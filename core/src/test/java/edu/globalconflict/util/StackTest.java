package edu.globalconflict.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class StackTest {
    @Test
    public void testPeek_nullElement() {
        // given
        final Stack<String> stack = new Stack<>(5);

        // when
        final String element = stack.peek();

        // then
        assertNull(element);
    }

    @Test
    public void testPeek() {
        // given
        final Stack<String> stack = new Stack<>(5);
        stack.push("asdf");

        // when
        final String element = stack.peek();

        // then
        assertEquals("asdf", element);
        assertEquals(1, stack.size());
    }

    @Test
    public void testPop_nullElement() {
        // given
        final Stack<String> stack = new Stack<>(5);

        // when
        final String element = stack.pop();

        // then
        assertNull(element);
    }

    @Test
    public void testPop() {
        // given
        final Stack<String> stack = new Stack<>(5);
        stack.push("elem1");
        stack.push("elem2");

        // when
        final String element = stack.pop();

        // then
        assertEquals("elem2", element);
        assertEquals(1, stack.size());
    }

    @Test
    public void testPush() {
        // given
        final Stack<String> stack = new Stack<>(5);

        // when
        final boolean pushed = stack.push("qwerty");

        // then
        assertTrue(pushed);
        assertEquals(1, stack.size());
    }

    @Test
    public void testPush_fullCapacity() {
        // given
        final Stack<String> stack = new Stack<>(2);
        stack.push("elem1");
        stack.push("elem2");

        // when
        final boolean pushed = stack.push("qwerty");

        // then
        assertFalse(pushed);
        assertEquals(2, stack.size());
    }

    @Test
    public void testClear() {
        // given
        final Stack<String> stack = new Stack<>(2);
        stack.push("elem1");
        stack.push("elem2");

        // when
        stack.clear();

        // then
        assertTrue(stack.empty());
        assertEquals(0, stack.size());
    }

    @Test
    public void testRemove() {
        // given
        final Stack<String> stack = new Stack<>(5);
        stack.push("elem1");
        stack.push("elem2");
        stack.push("elem3");
        stack.push("elem4");

        // when
        stack.remove("elem2");

        // then
        assertEquals(3, stack.size());
        assertEquals("elem4", stack.pop());
        assertEquals("elem3", stack.pop());
        assertEquals("elem1", stack.pop());
    }
}