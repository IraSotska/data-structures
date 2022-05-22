package com.ira.datastructures.list;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public abstract class AbstractListTest {

    private static final String FIRST_ELEMENT = "one";
    private static final String SECOND_ELEMENT = "two";
    private static final String THIRD_ELEMENT = "three";
    private static final String FOURTH_ELEMENT = "four";
    private static final String FIFTH_ELEMENT = "five";
    private static final String SIXTH_ELEMENT = "six";

    private final List<String> list = getList();

    abstract List<String> getList();

    @AfterEach
    void clearList() {
        list.clear();
    }

    @DisplayName("Should Add Typed Elements To Array List")
    @Test
    void shouldAddElementsToList() {
        list.add(FIRST_ELEMENT);
        list.add(SECOND_ELEMENT);
        list.add(THIRD_ELEMENT);
        list.add(FOURTH_ELEMENT);
        list.add(FIFTH_ELEMENT);
        list.add(SIXTH_ELEMENT);

        assertThat(list.get(0)).isEqualTo(FIRST_ELEMENT);
        assertThat(list.get(1)).isEqualTo(SECOND_ELEMENT);
        assertThat(list.get(2)).isEqualTo(THIRD_ELEMENT);
        assertThat(list.get(3)).isEqualTo(FOURTH_ELEMENT);
        assertThat(list.get(4)).isEqualTo(FIFTH_ELEMENT);
        assertThat(list.get(5)).isEqualTo(SIXTH_ELEMENT);
    }

    @DisplayName("Should Throw Index Out Of Bounds Exception If Add Element To Not Correct Index")
    @Test
    void shouldThrowIndexOutOfBoundsExceptionIfAddElementToNotCorrectIndex() {
        list.add(FIRST_ELEMENT);
        assertThatThrownBy(() -> list.add(FIRST_ELEMENT, 2)).isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> list.add(FIRST_ELEMENT, -1)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @DisplayName("Should Add Element By Index")
    @Test
    void shouldAddElementByIndex() {
        list.add(FIRST_ELEMENT);
        list.add(THIRD_ELEMENT);
        list.add(SECOND_ELEMENT, 1);

        assertThat(list.get(0)).isEqualTo(FIRST_ELEMENT);
        assertThat(list.get(1)).isEqualTo(SECOND_ELEMENT);
        assertThat(list.get(2)).isEqualTo(THIRD_ELEMENT);
        assertThat(list.size()).isEqualTo(3);
    }

    @DisplayName("Should Get Elements By Index")
    @Test
    void shouldGetElementsByIndex() {
        list.add(FIRST_ELEMENT);
        list.add(SECOND_ELEMENT);
        list.add(THIRD_ELEMENT);

        assertThat(list.get(0)).isEqualTo(FIRST_ELEMENT);
        assertThat(list.get(1)).isEqualTo(SECOND_ELEMENT);
        assertThat(list.get(2)).isEqualTo(THIRD_ELEMENT);
    }

    @DisplayName("Should Throw Index Out Of Bounds Exception If Get Index More Than Size")
    @Test
    void shouldThrowIndexOutOfBoundsExceptionIfGetIndexMoreThanSize() {
        list.add(FIRST_ELEMENT);
        assertThatThrownBy(() -> list.get(1)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @DisplayName("Should Throw Index Out Of Bounds Exception If Get Less Than Zero")
    @Test
    void shouldThrowIndexOutOfBoundsExceptionIfGetIndexLessThanZero() {
        list.add(FIRST_ELEMENT);
        assertThatThrownBy(() -> list.get(-1)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @DisplayName("Should Throw Index Out Of bounds Exception If Remove Element At Index Greater Than Size")
    @Test
    void shouldThrowIndexOutOfBoundsExceptionIfRemoveElementAtIndexGreaterThanSize() {
        list.add(FIRST_ELEMENT);

        assertThatThrownBy(() -> list.remove(1)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @DisplayName("Should Throw Index Out Of bounds Exception If Remove Element At Index Less Than Zero")
    @Test
    void shouldThrowIndexOutOfBoundsExceptionIfRemoveElementAtIndexLessThanZero() {
        list.add(FIRST_ELEMENT);

        assertThatThrownBy(() -> list.remove(-1)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @DisplayName("Should Remove Element")
    @Test
    void shouldRemoveElement() {
        list.add(FIRST_ELEMENT);
        list.add(THIRD_ELEMENT);
        list.add(FOURTH_ELEMENT);

        assertThat(list.remove(0)).isEqualTo(FIRST_ELEMENT);
        assertThat(list.get(1)).isEqualTo(FOURTH_ELEMENT);
        assertThat(list.size()).isEqualTo(2);
    }

    @DisplayName("Should Clear List")
    @Test
    void shouldClearList() {
        list.add(FIRST_ELEMENT);
        list.add(THIRD_ELEMENT);
        list.add(FOURTH_ELEMENT);
        list.clear();

        assertThat(list.size()).isEqualTo(0);
    }

    @DisplayName("Should Return Size Of List")
    @Test
    void shouldReturnSizeOfList() {
        list.add(FIRST_ELEMENT);
        list.add(THIRD_ELEMENT);
        list.add(FOURTH_ELEMENT);

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
        list.add(FIRST_ELEMENT);
        assertThatThrownBy(() -> list.get(1)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @DisplayName("Should Throw Index Out Of Bounds Exception If Set Index Less Than Zero")
    @Test
    void shouldThrowIndexOutOfBoundsExceptionIfSetIndexLessThanZero() {
        list.add(FIRST_ELEMENT);
        assertThatThrownBy(() -> list.get(-1)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @DisplayName("Should Set Element")
    @Test
    void shouldSetElement() {
        list.add(FIRST_ELEMENT);
        list.add(THIRD_ELEMENT);
        list.add(FOURTH_ELEMENT);
        list.set(FIFTH_ELEMENT, 1);

        assertThat(list.size()).isEqualTo(3);
        assertThat(list.get(0)).isEqualTo(FIRST_ELEMENT);
        assertThat(list.get(1)).isEqualTo(FIFTH_ELEMENT);
        assertThat(list.get(2)).isEqualTo(FOURTH_ELEMENT);
    }

    @DisplayName("Should Return True If Array Contain Element")
    @Test
    void shouldReturnTrueIfArrayContainElement() {
        list.add(FIRST_ELEMENT);

        assertThat(list.contains(FIRST_ELEMENT)).isEqualTo(true);
        assertThat(list.contains(SECOND_ELEMENT)).isEqualTo(false);
    }

    @DisplayName("Should Return Index Of Element")
    @Test
    void shouldReturnIndexOfElement() {
        list.add(FIRST_ELEMENT);
        list.add(SECOND_ELEMENT);
        list.add(THIRD_ELEMENT);
        list.add(SECOND_ELEMENT);

        assertThat(list.indexOf(FIRST_ELEMENT)).isEqualTo(0);
        assertThat(list.indexOf(SECOND_ELEMENT)).isEqualTo(1);
        assertThat(list.indexOf(THIRD_ELEMENT)).isEqualTo(2);
        assertThat(list.indexOf(FIFTH_ELEMENT)).isEqualTo(-1);
    }

    @DisplayName("Should Return Index Of Element Include Null Element")
    @Test
    void shouldReturnIndexOfElementIncludeNullElement() {
        list.add(FIRST_ELEMENT);
        list.add(null);
        list.add(THIRD_ELEMENT);
        list.add(null);

        assertThat(list.indexOf(FIRST_ELEMENT)).isEqualTo(0);
        assertThat(list.indexOf(null)).isEqualTo(1);
        assertThat(list.indexOf(THIRD_ELEMENT)).isEqualTo(2);
        assertThat(list.indexOf(FIFTH_ELEMENT)).isEqualTo(-1);
    }

    @DisplayName("Should Return Last Index Of Element Include Null Element")
    @Test
    void shouldReturnLastIndexOfElementIncludeNullElement() {
        list.add(FIRST_ELEMENT);
        list.add(null);
        list.add(THIRD_ELEMENT);
        list.add(null);

        assertThat(list.lastIndexOf(FIRST_ELEMENT)).isEqualTo(0);
        assertThat(list.lastIndexOf(null)).isEqualTo(3);
        assertThat(list.lastIndexOf(THIRD_ELEMENT)).isEqualTo(2);
        assertThat(list.lastIndexOf(FIFTH_ELEMENT)).isEqualTo(-1);
    }

    @DisplayName("Should Return Last Index Of Element")
    @Test
    void shouldReturnLastIndexOfElement() {
        list.add(FIRST_ELEMENT);
        list.add(SECOND_ELEMENT);
        list.add(THIRD_ELEMENT);
        list.add(SECOND_ELEMENT);
        list.add(FIRST_ELEMENT);

        assertThat(list.lastIndexOf(SECOND_ELEMENT)).isEqualTo(3);
        assertThat(list.lastIndexOf(FIRST_ELEMENT)).isEqualTo(4);
    }

    @DisplayName("Should Return String Of List Elements")
    @Test
    void shouldReturnStringOfListElements() {
        list.add(FIRST_ELEMENT);
        list.add(SECOND_ELEMENT);
        list.add(THIRD_ELEMENT);

        assertThat(list.toString()).isEqualTo("[one, two, three]");
        list.clear();
        assertThat(list.toString()).isEqualTo("[]");
    }

    @DisplayName("Should Return True If List Is Empty")
    @Test
    void shouldReturnTrueIfListIsEmpty() {
        assertThat(list.isEmpty()).isEqualTo(true);

        list.add(FIRST_ELEMENT);

        assertThat(list.isEmpty()).isEqualTo(false);
    }

    @DisplayName("Should Iterate Through List")
    @Test
    void shouldIterateThroughList() {
        list.add(FIRST_ELEMENT);
        list.add(SECOND_ELEMENT);
        list.add(THIRD_ELEMENT);

        var iterator = list.iterator();
        assertThat(iterator.next()).isEqualTo(FIRST_ELEMENT);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(SECOND_ELEMENT);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(THIRD_ELEMENT);
        assertThat(iterator.hasNext()).isFalse();
    }

    @DisplayName("Should Remove Element While Iterate Through List")
    @Test
    void shouldRemoveElementWhileIterateThroughList() {
        list.add(FIRST_ELEMENT);
        list.add(SECOND_ELEMENT);
        list.add(THIRD_ELEMENT);

        var iterator = list.iterator();
        assertThat(iterator.next()).isEqualTo(FIRST_ELEMENT);

        iterator.remove();

        assertThat(list.contains(SECOND_ELEMENT)).isFalse();
        assertThat(list.size()).isEqualTo(2);
    }


    @DisplayName("Should Throw Exception While Iterate If No Such Element")
    @Test
    void shouldThrowExceptionWhileIterateIfNoSuchElement() {
        list.add(FIRST_ELEMENT);

        var iterator = list.iterator();
        assertThat(iterator.next()).isEqualTo(FIRST_ELEMENT);
        assertThat(iterator.hasNext()).isFalse();
        assertThatThrownBy(iterator::next).isInstanceOf(NoSuchElementException.class);
    }
}
