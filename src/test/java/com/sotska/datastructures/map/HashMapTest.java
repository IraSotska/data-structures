package com.sotska.datastructures.map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {
    private final HashMap<String, String> hashMap = new HashMap<>();

    @DisplayName("Should Put Element To Map")
    @Test
    void shouldPutElementToMap() {
        assertNull(hashMap.put("key", "val"));
        assertEquals(hashMap.put("key", "val2"), "val");
        assertEquals(hashMap.size(), 1);
        assertEquals(hashMap.get("key"), "val2");
    }

    @DisplayName("Should Increase Number Of Buckets")
    @Test
    void shouldIncreaseNumberOfBuckets() {
        assertNull(hashMap.put("key", "val"));
        assertNull(hashMap.put("key2", "val2"));
        assertNull(hashMap.put("val", "val"));
        assertNull(hashMap.put("val2", "val2"));
        assertNull(hashMap.put(null, "val2"));
        assertEquals(hashMap.size(), 5);
    }

    @DisplayName("Should Return Element By Key")
    @Test
    void shouldReturnElementByKey() {
        hashMap.put("key", "val");
        assertEquals(hashMap.get("key"), "val");
    }

    @DisplayName("Should Return Null If Get Element That Not Exist")
    @Test
    void shouldReturnNullIfGetElementThatNotExist() {
        hashMap.put("key", "val");
        assertNull(hashMap.get("key2"));
    }

    @DisplayName("Should Return True If Contains Key")
    @Test
    void shouldReturnTrueIfContainsKey() {
        hashMap.put("key", "val");
        assertTrue(hashMap.containsKey("key"));
    }

    @DisplayName("Should Return False If Not Contains Key")
    @Test
    void shouldReturnFalseIfNotContainsKey() {
        hashMap.put("key", "val");
        assertFalse(hashMap.containsKey("key2"));
    }

    @DisplayName("Should Remove Element")
    @Test
    void shouldRemoveElement() {
        hashMap.put("key", "val");
        assertEquals(hashMap.remove("key"), "val");
        assertFalse(hashMap.containsKey("key"));
    }

    @DisplayName("Should Return Null If Remove Element That Not Exist")
    @Test
    void shouldReturnNullIfRemoveElementThatNotExist() {
        assertNull(hashMap.remove("key"));
    }

    @DisplayName("Should Return Size Of Map")
    @Test
    void shouldReturnSizeOfMap() {
        hashMap.put("key", "val");
        assertEquals(hashMap.size(), 1);
    }

    @DisplayName("Should Return Size Of Empty Map")
    @Test
    void shouldReturnSizeOfEmptyMap() {
        assertEquals(hashMap.size(), 0);
    }

    @DisplayName("Should Iterate Through Map")
    @Test
    void shouldIterateThroughMap() {
        var expectedKeys = Set.of("key", "key2");
        var expectedValues = Set.of("val", "val2");

        hashMap.put("key", "val");
        hashMap.put("key2", "val2");
        var iterator = hashMap.iterator();

        assertTrue(iterator.hasNext());
        var firstElement = iterator.next();
        assertTrue(iterator.hasNext());
        var secondElement = iterator.next();
        assertFalse(iterator.hasNext());

        assertEquals(Set.of(firstElement.getKey(), secondElement.getKey()), expectedKeys);
        assertEquals(Set.of(firstElement.getValue(), secondElement.getValue()), expectedValues);
    }

    @DisplayName("Should Remove Element While Iterate Through Map")
    @Test
    void shouldRemoveElementWhileIterateThroughMap() {
        hashMap.put("key2", "val2");
        var iterator = hashMap.iterator();
        assertTrue(iterator.hasNext());

        var nextElement = iterator.next();
        assertEquals(nextElement.getKey(), "key2");
        assertEquals(nextElement.getValue(), "val2");
        iterator.remove();
        assertFalse(hashMap.containsKey("key2"));
        assertEquals(hashMap.size(), 0);
    }

    @DisplayName("Should Throw Exception If No Such Elements")
    @Test
    void shouldThrowExceptionIfNoSuchElements() {
        var expectedKeys = Set.of("key", "key2");
        var expectedValues = Set.of("val", "val2");

        hashMap.put("key", "val");
        hashMap.put("key2", "val2");

        var iterator = hashMap.iterator();
        assertTrue(iterator.hasNext());
        var firstElement = iterator.next();
        assertTrue(iterator.hasNext());
        var secondElement = iterator.next();
        assertFalse(iterator.hasNext());
        assertEquals(Set.of(firstElement.getKey(), secondElement.getKey()), expectedKeys);
        assertEquals(Set.of(firstElement.getValue(), secondElement.getValue()), expectedValues);

        var exception = assertThrows(NoSuchElementException.class, iterator::next);
        assertEquals(exception.getMessage(), "No such element at map.");
    }

    @DisplayName("Should Throw Exception If Remove Element In Iterator Without Call Next")
    @Test
    void shouldThrowExceptionIfRemoveElementInIteratorWithoutCallNext() {
        var iterator = hashMap.iterator();

        var exception = assertThrows(IllegalStateException.class, iterator::remove);
        assertEquals(exception.getMessage(), "Have no element to remove.");
    }
}