package com.sotska.datastructures.list;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public abstract class AbstractListTest {

    private final List<String> list = getList();

    abstract List<String> getList();

    @DisplayName("Should Add Typed Elements To Array List")
    @Test
    void shouldAddElementsToList() {
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        list.add("five");
        list.add("six");

        assertThat(list.get(0)).isEqualTo("one");
        assertThat(list.get(1)).isEqualTo("two");
        assertThat(list.get(2)).isEqualTo("three");
        assertThat(list.get(3)).isEqualTo("four");
        assertThat(list.get(4)).isEqualTo("five");
        assertThat(list.get(5)).isEqualTo("six");
    }

    @DisplayName("Should Throw Index Out Of Bounds Exception If Add Element To Index More Than Size")
    @Test
    void shouldThrowIndexOutOfBoundsExceptionIfAddElementToIndexMoreThanSize() {
        list.add("one");

        assertThatThrownBy(() -> list.add("one", 2)).hasMessage("Index should be between 0 and 1 Current index is 2")
                .isExactlyInstanceOf(IndexOutOfBoundsException.class);
    }

    @DisplayName("Should Throw Index Out Of Bounds Exception If Add Element To Index Less Than Size")
    @Test
    void shouldThrowIndexOutOfBoundsExceptionIfAddElementToIndexLessThanSize() {
        list.add("one");

        assertThatThrownBy(() -> list.add("one", -1)).hasMessage("Index should be between 0 and 1 Current index is -1")
                .isExactlyInstanceOf(IndexOutOfBoundsException.class);
    }

    @DisplayName("Should Add Element By Index")
    @Test
    void shouldAddElementByIndex() {
        list.add("one");
        list.add("three");
        list.add("two", 1);

        assertThat(list.get(0)).isEqualTo("one");
        assertThat(list.get(1)).isEqualTo("two");
        assertThat(list.get(2)).isEqualTo("three");
        assertThat(list.size()).isEqualTo(3);
    }

    @DisplayName("Should Get Elements By Index")
    @Test
    void shouldGetElementsByIndex() {
        list.add("one");
        list.add("two");
        list.add("three");

        assertThat(list.get(0)).isEqualTo("one");
        assertThat(list.get(1)).isEqualTo("two");
        assertThat(list.get(2)).isEqualTo("three");
    }

    @DisplayName("Should Throw Index Out Of Bounds Exception If Get Index More Than Size")
    @Test
    void shouldThrowIndexOutOfBoundsExceptionIfGetIndexMoreThanSize() {
        list.add("one");
        assertThatThrownBy(() -> list.get(1)).hasMessage("Index should be between 0 and 1 Current index is 1")
                .isExactlyInstanceOf(IndexOutOfBoundsException.class);
    }

    @DisplayName("Should Throw Index Out Of Bounds Exception If Get Less Than Zero")
    @Test
    void shouldThrowIndexOutOfBoundsExceptionIfGetIndexLessThanZero() {
        list.add("one");
        assertThatThrownBy(() -> list.get(-1)).hasMessage("Index should be between 0 and 1 Current index is -1")
                .isExactlyInstanceOf(IndexOutOfBoundsException.class);
    }

    @DisplayName("Should Throw Index Out Of bounds Exception If Remove Element At Index Greater Than Size")
    @Test
    void shouldThrowIndexOutOfBoundsExceptionIfRemoveElementAtIndexGreaterThanSize() {
        list.add("one");

        assertThatThrownBy(() -> list.remove(1)).hasMessage("Index should be between 0 and 1 Current index is 1")
                .isExactlyInstanceOf(IndexOutOfBoundsException.class);
    }

    @DisplayName("Should Throw Index Out Of bounds Exception If Remove Element At Index Less Than Zero")
    @Test
    void shouldThrowIndexOutOfBoundsExceptionIfRemoveElementAtIndexLessThanZero() {
        list.add("one");

        assertThatThrownBy(() -> list.remove(-1)).hasMessage("Index should be between 0 and 1 Current index is -1")
                .isExactlyInstanceOf(IndexOutOfBoundsException.class);
    }

    @DisplayName("Should Remove Element")
    @Test
    void shouldRemoveElement() {
        list.add("one");
        list.add("three");
        list.add("four");

        assertThat(list.remove(0)).isEqualTo("one");
        assertThat(list.get(1)).isEqualTo("four");
        assertThat(list.size()).isEqualTo(2);
    }

    @DisplayName("Should Clear List")
    @Test
    void shouldClearList() {
        list.add("one");
        list.add("three");
        list.add("four");
        list.clear();

        assertThat(list.size()).isEqualTo(0);
    }

    @DisplayName("Should Return Size Of List")
    @Test
    void shouldReturnSizeOfList() {
        list.add("one");
        list.add("three");
        list.add("four");

        assertThat(list.size()).isEqualTo(3);
    }

    @DisplayName("Should Return Size Of Empty List")
    @Test
    void shouldReturnSizeOfEmptyList() {
        assertThat(list.size()).isEqualTo(0);
    }

    @DisplayName("Should Throw Index Out Of Bounds Exception If Set Index More Than Size")
    @Test
    void shouldThrowIndexOutOfBoundsExceptionIfSetIndexMoreThanSize() {
        list.add("one");
        assertThatThrownBy(() -> list.get(1)).hasMessage("Index should be between 0 and 1 Current index is 1")
                .isExactlyInstanceOf(IndexOutOfBoundsException.class);
    }

    @DisplayName("Should Throw Index Out Of Bounds Exception If Set Index Less Than Zero")
    @Test
    void shouldThrowIndexOutOfBoundsExceptionIfSetIndexLessThanZero() {
        list.add("one");
        assertThatThrownBy(() -> list.get(-1)).hasMessage("Index should be between 0 and 1 Current index is -1")
                .isExactlyInstanceOf(IndexOutOfBoundsException.class);
    }

    @DisplayName("Should Set Element")
    @Test
    void shouldSetElement() {
        list.add("one");
        list.add("three");
        list.add("four");
        list.set("five", 1);

        assertThat(list.size()).isEqualTo(3);
        assertThat(list.get(0)).isEqualTo("one");
        assertThat(list.get(1)).isEqualTo("five");
        assertThat(list.get(2)).isEqualTo("four");
    }

    @DisplayName("Should Return True If Array Contain Element")
    @Test
    void shouldReturnTrueIfArrayContainElement() {
        list.add("one");

        assertThat(list.contains("one")).isEqualTo(true);
        assertThat(list.contains("two")).isEqualTo(false);
    }

    @DisplayName("Should Return Index Of Element")
    @Test
    void shouldReturnIndexOfElement() {
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("two");

        assertThat(list.indexOf("one")).isEqualTo(0);
        assertThat(list.indexOf("two")).isEqualTo(1);
        assertThat(list.indexOf("three")).isEqualTo(2);
        assertThat(list.indexOf("five")).isEqualTo(-1);
    }

    @DisplayName("Should Return Index Of Element Include Null Element")
    @Test
    void shouldReturnIndexOfElementIncludeNullElement() {
        list.add("one");
        list.add(null);
        list.add("three");
        list.add(null);

        assertThat(list.indexOf("one")).isEqualTo(0);
        assertThat(list.indexOf(null)).isEqualTo(1);
        assertThat(list.indexOf("three")).isEqualTo(2);
        assertThat(list.indexOf("five")).isEqualTo(-1);
    }

    @DisplayName("Should Return Last Index Of Element Include Null Element")
    @Test
    void shouldReturnLastIndexOfElementIncludeNullElement() {
        list.add("one");
        list.add(null);
        list.add("three");
        list.add(null);

        assertThat(list.lastIndexOf("one")).isEqualTo(0);
        assertThat(list.lastIndexOf(null)).isEqualTo(3);
        assertThat(list.lastIndexOf("three")).isEqualTo(2);
        assertThat(list.lastIndexOf("five")).isEqualTo(-1);
    }

    @DisplayName("Should Return Last Index Of Element")
    @Test
    void shouldReturnLastIndexOfElement() {
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("two");
        list.add("one");

        assertThat(list.lastIndexOf("two")).isEqualTo(3);
        assertThat(list.lastIndexOf("one")).isEqualTo(4);
    }

    @DisplayName("Should Return String Of List Elements")
    @Test
    void shouldReturnStringOfListElements() {
        list.add("one");
        list.add("two");
        list.add("three");

        assertThat(list.toString()).isEqualTo("[one, two, three]");
    }

    @DisplayName("Should Return String Of Empty List Of Elements")
    @Test
    void shouldReturnStringOfEmptyListOfElements() {
        assertThat(list.toString()).isEqualTo("[]");
    }

    @DisplayName("Should Return True If List Is Empty")
    @Test
    void shouldReturnTrueIfListIsEmpty() {
        assertThat(list.isEmpty()).isEqualTo(true);

        list.add("one");

        assertThat(list.isEmpty()).isEqualTo(false);
    }

    @DisplayName("Should Iterate Through List")
    @Test
    void shouldIterateThroughList() {
        list.add("one");
        list.add("two");
        list.add("three");

        var iterator = list.iterator();
        assertThat(iterator.next()).isEqualTo("one");
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo("two");
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo("three");
        assertThat(iterator.hasNext()).isFalse();
    }

    @DisplayName("Should Remove Element While Iterate Through List")
    @Test
    void shouldRemoveElementWhileIterateThroughList() {
        list.add("one");
        list.add("two");
        list.add("three");

        var iterator = list.iterator();
        assertThat(iterator.next()).isEqualTo("one");

        iterator.remove();

        assertThat(list.contains("one")).isFalse();
        assertThat(list.size()).isEqualTo(2);
    }


    @DisplayName("Should Throw Exception While Iterate If No Such Element")
    @Test
    void shouldThrowExceptionWhileIterateIfNoSuchElement() {
        list.add("one");

        var iterator = list.iterator();
        assertThat(iterator.next()).isEqualTo("one");
        assertThat(iterator.hasNext()).isFalse();
        assertThatThrownBy(iterator::next).isExactlyInstanceOf(NoSuchElementException.class);
    }

    @DisplayName("Should Throw Exception While Iterate If No Such Element")
    @Test
    void shouldThrowExceptionWhileIterateIfRemoveWithoutCallNextMethod() {
        list.add("one");

        var iterator = list.iterator();
        assertThatThrownBy(iterator::remove).isExactlyInstanceOf(IllegalStateException.class);
    }

    @DisplayName("Should Throw Exception While Iterate If No Such Element")
    @Test
    void shouldThrowExceptionWhileIterateIfRemoveAlreadyCalledOnThisElement() {

        list.add("one");

        var iterator = list.iterator();
        iterator.next();
        iterator.remove();
        assertThatThrownBy(iterator::remove).isExactlyInstanceOf(IllegalStateException.class);
    }
}
