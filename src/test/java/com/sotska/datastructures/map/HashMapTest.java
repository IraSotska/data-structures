package com.sotska.datastructures.map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HashMapTest {
    private final HashMap<String, String> hashMap = new HashMap<>();

    @DisplayName("Should Put Element To Map")
    @Test
    void shouldPutElementToMap() {
        assertThat(hashMap.put("key", "val")).isEqualTo(null);
        assertThat(hashMap.put("key", "val2")).isEqualTo("val");
        assertThat(hashMap.size()).isEqualTo(1);
        assertThat(hashMap.get("key")).isEqualTo("val2");
    }

    @DisplayName("Should Increase Number Of Buckets")
    @Test
    void shouldIncreaseNumberOfBuckets() {
        assertThat(hashMap.put("key", "val")).isEqualTo(null);
        assertThat(hashMap.put("key2", "val2")).isEqualTo(null);
        assertThat(hashMap.put("val", "val")).isEqualTo(null);
        assertThat(hashMap.put("val2", "val2")).isEqualTo(null);
        assertThat(hashMap.put(null, "val2")).isEqualTo(null);
        assertThat(hashMap.size()).isEqualTo(5);
    }

    @DisplayName("Should Return Element By Key")
    @Test
    void shouldReturnElementByKey() {
        hashMap.put("key", "val");
        assertThat(hashMap.get("key")).isEqualTo("val");
    }

    @DisplayName("Should Return Null If Get Element That Not Exist")
    @Test
    void shouldReturnNullIfGetElementThatNotExist() {
        hashMap.put("key", "val");
        assertThat(hashMap.get("key2")).isEqualTo(null);
    }

    @DisplayName("Should Return True If Contains Key")
    @Test
    void shouldReturnTrueIfContainsKey() {
        hashMap.put("key", "val");
        assertThat(hashMap.containsKey("key")).isTrue();
    }

    @DisplayName("Should Return False If Not Contains Key")
    @Test
    void shouldReturnFalseIfNotContainsKey() {
        hashMap.put("key", "val");
        assertThat(hashMap.containsKey("key2")).isFalse();
    }

    @DisplayName("Should Remove Element")
    @Test
    void shouldRemoveElement() {
        hashMap.put("key", "val");
        assertThat(hashMap.remove("key")).isEqualTo("val");
    }

    @DisplayName("Should Return Null If Remove Element That Not Exist")
    @Test
    void shouldReturnNullIfRemoveElementThatNotExist() {
        assertThat(hashMap.remove("key")).isEqualTo(null);
    }

    @DisplayName("Should Return Size Of Map")
    @Test
    void shouldReturnSizeOfMap() {
        hashMap.put("key", "val");
        assertThat(hashMap.size()).isEqualTo(1);
    }

    @DisplayName("Should Return Size Of Empty Map")
    @Test
    void shouldReturnSizeOfEmptyMap() {
        assertThat(hashMap.size()).isEqualTo(0);
    }

    @DisplayName("Should Iterate Through Map")
    @Test
    void shouldIterateThroughMap() {
        var expectedKeys = Set.of("key", "key2");
        var expectedValues = Set.of("val", "val2");

        hashMap.put("key", "val");
        hashMap.put("key2", "val2");
        var iterator = hashMap.iterator();

        assertThat(iterator.hasNext()).isTrue();
        var firstElement = iterator.next();
        assertThat(iterator.hasNext()).isTrue();
        var secondElement = iterator.next();
        assertThat(iterator.hasNext()).isFalse();

        assertThat(Set.of(firstElement.getKey(), secondElement.getKey())).isEqualTo(expectedKeys);
        assertThat(Set.of(firstElement.getValue(), secondElement.getValue())).isEqualTo(expectedValues);
    }

    @DisplayName("Should Remove Element While Iterate Through Map")
    @Test
    void shouldRemoveElementWhileIterateThroughMap() {
        hashMap.put("key2", "val2");
        var iterator = hashMap.iterator();
        assertThat(iterator.hasNext()).isTrue();

        var nextElement = iterator.next();
        assertThat(nextElement.getKey()).isEqualTo("key2");
        assertThat(nextElement.getValue()).isEqualTo("val2");
        iterator.remove();
        assertThat(hashMap.containsKey("key2")).isFalse();
        assertThat(hashMap.size()).isEqualTo(0);
    }

    @DisplayName("Should Throw Exception If No Such Elements")
    @Test
    void shouldThrowExceptionIfNoSuchElements() {
        var expectedKeys = Set.of("key", "key2");
        var expectedValues = Set.of("val", "val2");

        hashMap.put("key", "val");
        hashMap.put("key2", "val2");

        var iterator = hashMap.iterator();
        assertThat(iterator.hasNext()).isTrue();
        var firstElement = iterator.next();
        assertThat(iterator.hasNext()).isTrue();
        var secondElement = iterator.next();
        assertThat(iterator.hasNext()).isFalse();
        assertThat(Set.of(firstElement.getKey(), secondElement.getKey())).isEqualTo(expectedKeys);
        assertThat(Set.of(firstElement.getValue(), secondElement.getValue())).isEqualTo(expectedValues);
        assertThatThrownBy(iterator::next).isExactlyInstanceOf(NoSuchElementException.class).hasMessage("No such element at map.");
    }
}