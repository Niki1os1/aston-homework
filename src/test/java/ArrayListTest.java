import collections.lists.array_list.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class ArrayListTest {

    @Test
    public void testAdd() {
        ArrayList<Integer> list = new ArrayList<>();
        assertTrue(list.add(10));
        assertTrue(list.add(20));
        assertTrue(list.add(30));
        assertTrue(list.add(null)); // Уязвимая ситуация: добавление null
    }

    @Test
    public void testGet() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
        assertNull(list.get(3));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(11)); // Уязвимая ситуация: индекс вне диапазона
    }

    @Test
    public void testRemove() {
        ArrayList<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("cherry");

        assertTrue(list.remove("banana"));
        assertEquals("apple", list.get(0));
        assertEquals("cherry", list.get(1));
        assertFalse(list.remove("grape")); // Уязвимая ситуация: удаление несуществующего элемента
    }

    @Test
    public void testRemoveElementAtIndex() {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("apple", "banana", "cherry"));

        String removedElement = list.remove(1);

        assertEquals("banana", removedElement);
        assertEquals(2, list.size());
        assertEquals("apple", list.get(0));
        assertEquals("cherry", list.get(1));
    }

    @Test
    public void testRemoveElementAtInvalidIndex() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(10, 20, 30));

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(3);
        });
    }

    @Test
    public void testRemoveElementFromEmptyList() {
        ArrayList<Double> list = new ArrayList<>();

        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(0);
        });
    }

    @Test
    public void testAddAllWithElements() {
        ArrayList<Integer> list = new ArrayList<>();
        List<Integer> elementsToAdd = Arrays.asList(10, 20, 30);

        assertTrue(list.addAll(elementsToAdd));
        assertEquals(3, list.size());
        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
    }

    @Test
    public void testAddAllWithEmptyCollection() {
        ArrayList<String> list = new ArrayList<>();
        List<String> emptyList = new LinkedList<>();

        assertFalse(list.addAll(emptyList));
        assertTrue(list.size()==0);
    }

    @Test
    public void testAddAllWithInsufficientCapacity() {
        ArrayList<Integer> list = new ArrayList<>(2);
        List<Integer> elementsToAdd = Arrays.asList(10, 20, 30);

        assertTrue(list.addAll(elementsToAdd));
        assertEquals(3, list.size());
        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
    }

    @Test
    public void testSort() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(30);
        list.add(10);
        list.add(20);

        list.sort(Comparator.comparingInt(a -> a));

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
    }

    @Test
    public void testSortWithIntegers() {
        List<Integer> list = new LinkedList<>(Arrays.asList(30, 10, 20));
        ArrayList.sort(list);

        List<Integer> expected = Arrays.asList(10, 20, 30);
        assertIterableEquals(expected, list);
    }

    @Test
    public void testSortWithStrings() {
        List<String> list = new LinkedList<>(Arrays.asList("banana", "apple", "cherry"));
        ArrayList.sort(list);

        List<String> expected = Arrays.asList("apple", "banana", "cherry");
        assertIterableEquals(expected, list);
    }

    @Test
    public void testSortWithEmptyList() {
        List<Integer> list = new LinkedList<>();
        ArrayList.sort(list);

        assertTrue(list.isEmpty());
    }

}
