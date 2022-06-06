package com.sotska.datastructures.map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HashMapTest {
    private final HashMap<String, String> hashMap = new HashMap<>();
    private static final String FIRST_VALUE = "val";
    private static final String SECOND_VALUE = "val2";
    private static final String FIRST_KEY = "key";
    private static final String SECOND_KEY = "key2";

    @DisplayName("Should Put Element To Map")
    @Test
    void shouldPutElementToMap() {
        assertThat(hashMap.put(FIRST_KEY, FIRST_VALUE)).isEqualTo(null);
        assertThat(hashMap.put(FIRST_KEY, SECOND_VALUE)).isEqualTo(FIRST_VALUE);
        assertThat(hashMap.size()).isEqualTo(1);
        assertThat(hashMap.get(FIRST_KEY)).isEqualTo(SECOND_VALUE);
    }

    @DisplayName("Should Increase Number Of Buckets")
    @Test
    void shouldIncreaseNumberOfBuckets() {
        assertThat(hashMap.put(FIRST_KEY, FIRST_VALUE)).isEqualTo(null);
        assertThat(hashMap.put(SECOND_KEY, SECOND_VALUE)).isEqualTo(null);
        assertThat(hashMap.put(FIRST_VALUE, FIRST_VALUE)).isEqualTo(null);
        assertThat(hashMap.put(SECOND_VALUE, SECOND_VALUE)).isEqualTo(null);
        assertThat(hashMap.put(null, SECOND_VALUE)).isEqualTo(null);
        assertThat(hashMap.size()).isEqualTo(5);
    }

    @DisplayName("Should Return Element By Key")
    @Test
    void shouldReturnElementByKey() {
        hashMap.put(FIRST_KEY, FIRST_VALUE);
        assertThat(hashMap.get(FIRST_KEY)).isEqualTo(FIRST_VALUE);
    }

    @DisplayName("Should Return Null If Get Element That Not Exist")
    @Test
    void shouldReturnNullIfGetElementThatNotExist() {
        hashMap.put(FIRST_KEY, FIRST_VALUE);
        assertThat(hashMap.get(SECOND_KEY)).isEqualTo(null);
    }

    @DisplayName("Should Return True If Contains Key")
    @Test
    void shouldReturnTrueIfContainsKey() {
        hashMap.put(FIRST_KEY, FIRST_VALUE);
        assertThat(hashMap.containsKey(FIRST_KEY)).isTrue();
    }

    @DisplayName("Should Return False If Not Contains Key")
    @Test
    void shouldReturnFalseIfNotContainsKey() {
        hashMap.put(FIRST_KEY, FIRST_VALUE);
        assertThat(hashMap.containsKey(SECOND_KEY)).isFalse();
    }

    @DisplayName("Should Remove Element")
    @Test
    void shouldRemoveElement() {
        hashMap.put(FIRST_KEY, FIRST_VALUE);
        assertThat(hashMap.remove(FIRST_KEY)).isEqualTo(FIRST_VALUE);
    }

    @DisplayName("Should Return Null If Remove Element That Not Exist")
    @Test
    void shouldReturnNullIfRemoveElementThatNotExist() {
        assertThat(hashMap.remove(FIRST_KEY)).isEqualTo(null);
    }

    @DisplayName("Should Return Size Of Map")
    @Test
    void shouldReturnSizeOfMap() {
        hashMap.put(FIRST_KEY, FIRST_VALUE);
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
        hashMap.put(FIRST_KEY, FIRST_VALUE);
        hashMap.put(SECOND_KEY, SECOND_VALUE);
        var iterator = hashMap.iterator();
        assertThat(iterator.hasNext()).isTrue();
        var nextElement = iterator.next();
        assertThat(nextElement.getKey()).isEqualTo(FIRST_KEY);
        assertThat(nextElement.getValue()).isEqualTo(FIRST_VALUE);
        assertThat(iterator.hasNext()).isTrue();
        nextElement = iterator.next();
        assertThat(nextElement.getKey()).isEqualTo(SECOND_KEY);
        assertThat(nextElement.getValue()).isEqualTo(SECOND_VALUE);
        assertThat(iterator.hasNext()).isFalse();
    }

    @DisplayName("Should Remove Element While Iterate Through Map")
    @Test
    void shouldRemoveElementWhileIterateThroughMap() {
        hashMap.put(FIRST_KEY, FIRST_VALUE);
        hashMap.put(SECOND_KEY, SECOND_VALUE);
        var iterator = hashMap.iterator();
        assertThat(iterator.hasNext()).isTrue();
        var nextElement = iterator.next();
        assertThat(nextElement.getKey()).isEqualTo(FIRST_KEY);
        assertThat(nextElement.getValue()).isEqualTo(FIRST_VALUE);
        iterator.remove();
        assertThat(hashMap.containsKey(FIRST_KEY)).isFalse();
        assertThat(hashMap.containsKey(SECOND_KEY)).isTrue();
        assertThat(hashMap.size()).isEqualTo(1);
    }

    @DisplayName("Should Throw Exception If No Such Elements")
    @Test
    void shouldThrowExceptionIfNoSuchElements() {
        hashMap.put(FIRST_KEY, FIRST_VALUE);
        hashMap.put(SECOND_KEY, SECOND_VALUE);
        var iterator = hashMap.iterator();
        assertThat(iterator.hasNext()).isTrue();
        var nextElement = iterator.next();
        assertThat(nextElement.getKey()).isEqualTo(FIRST_KEY);
        assertThat(nextElement.getValue()).isEqualTo(FIRST_VALUE);
        assertThat(iterator.hasNext()).isTrue();
        nextElement = iterator.next();
        assertThat(nextElement.getKey()).isEqualTo(SECOND_KEY);
        assertThat(nextElement.getValue()).isEqualTo(SECOND_VALUE);
        assertThat(iterator.hasNext()).isFalse();
        assertThatThrownBy(iterator::next).isInstanceOf(NoSuchElementException.class);
    }
}