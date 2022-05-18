package com.ira.datastructures.list;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LinkedListTest {
    private static final String FIRST_ELEMENT = "one";
    private static final String SECOND_ELEMENT = "two";
    private static final String THIRD_ELEMENT = "three";
    private static final String FOURTH_ELEMENT = "four";
    private static final String FIFTH_ELEMENT = "five";
    private static final String SIXTH_ELEMENT = "six";

    private LinkedList<String> linkedList = new LinkedList<>();

    @AfterEach
    void clearList() {
        linkedList.clear();
    }

    @DisplayName("Should Add Typed Elements To Array List")
    @Test
    void shouldAddElementsToList() {
        linkedList.add(FIRST_ELEMENT);
        linkedList.add(SECOND_ELEMENT);
        linkedList.add(THIRD_ELEMENT);
        linkedList.add(FOURTH_ELEMENT);
        linkedList.add(FIFTH_ELEMENT);
        linkedList.add(SIXTH_ELEMENT);

        assertThat(linkedList.get(0)).isEqualTo(FIRST_ELEMENT);
        assertThat(linkedList.get(1)).isEqualTo(SECOND_ELEMENT);
        assertThat(linkedList.get(2)).isEqualTo(THIRD_ELEMENT);
        assertThat(linkedList.get(3)).isEqualTo(FOURTH_ELEMENT);
        assertThat(linkedList.get(4)).isEqualTo(FIFTH_ELEMENT);
        assertThat(linkedList.get(5)).isEqualTo(SIXTH_ELEMENT);
    }

    @DisplayName("Should Throw Index Out Of Bounds Exception If Add Element To Not Correct Index")
    @Test
    void shouldThrowIndexOutOfBoundsExceptionIfAddElementToNotCorrectIndex() {
        linkedList.add(FIRST_ELEMENT);
        assertThatThrownBy(() -> linkedList.add(FIRST_ELEMENT, 2)).isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> linkedList.add(FIRST_ELEMENT, -1)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @DisplayName("Should Add Element By Index")
    @Test
    void shouldAddElementByIndex() {
        linkedList.add(FIRST_ELEMENT);
        linkedList.add(THIRD_ELEMENT);
        linkedList.add(SECOND_ELEMENT, 1);

        assertThat(linkedList.get(0)).isEqualTo(FIRST_ELEMENT);
        assertThat(linkedList.get(1)).isEqualTo(SECOND_ELEMENT);
        assertThat(linkedList.get(2)).isEqualTo(THIRD_ELEMENT);
        assertThat(linkedList.size()).isEqualTo(3);
    }

    @DisplayName("Should Get Elements By Index")
    @Test
    void shouldGetElementsByIndex() {
        linkedList.add(FIRST_ELEMENT);
        linkedList.add(SECOND_ELEMENT);
        linkedList.add(THIRD_ELEMENT);

        assertThat(linkedList.get(0)).isEqualTo(FIRST_ELEMENT);
        assertThat(linkedList.get(1)).isEqualTo(SECOND_ELEMENT);
        assertThat(linkedList.get(2)).isEqualTo(THIRD_ELEMENT);
    }

    @DisplayName("Should Throw Index Out Of Bounds Exception If Get Not Correct Index")
    @Test
    void shouldThrowIndexOutOfBoundsExceptionIfGetNotCorrectIndex() {
        linkedList.add(FIRST_ELEMENT);
        assertThatThrownBy(() -> linkedList.get(-1)).isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> linkedList.get(1)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @DisplayName("Should Throw Index Out Of bounds Exception If Remove Element At Not Correct Index")
    @Test
    void shouldThrowIndexOutOfBoundsExceptionIfRemoveElementAtNotCorrectIndex() {
        linkedList.add(FIRST_ELEMENT);

        assertThatThrownBy(() -> linkedList.remove(1)).isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> linkedList.remove(-1)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @DisplayName("Should Remove Element")
    @Test
    void shouldRemoveElement() {
        linkedList.add(FIRST_ELEMENT);
        linkedList.add(THIRD_ELEMENT);
        linkedList.add(FOURTH_ELEMENT);

        assertThat(linkedList.remove(0)).isEqualTo(FIRST_ELEMENT);
        assertThat(linkedList.get(1)).isEqualTo(FOURTH_ELEMENT);
        assertThat(linkedList.size()).isEqualTo(2);
    }

    @DisplayName("Should Clear List")
    @Test
    void shouldClearList() {
        linkedList.add(FIRST_ELEMENT);
        linkedList.add(THIRD_ELEMENT);
        linkedList.add(FOURTH_ELEMENT);
        linkedList.clear();

        assertThat(linkedList.size()).isEqualTo(0);
    }

    @DisplayName("Should Return Size Of List")
    @Test
    void shouldReturnSizeOfList() {
        linkedList.add(FIRST_ELEMENT);
        linkedList.add(THIRD_ELEMENT);
        linkedList.add(FOURTH_ELEMENT);

        assertThat(linkedList.size()).isEqualTo(3);
    }

    @DisplayName("Should Throw Index Out Of Bounds Exception If Set Not Correct Index")
    @Test
    void shouldThrowIndexOutOfBoundsExceptionIfSetNotCorrectIndex() {
        linkedList.add(FIRST_ELEMENT);
        assertThatThrownBy(() -> linkedList.get(-1)).isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> linkedList.get(1)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @DisplayName("Should Set Element")
    @Test
    void shouldSetElement() {
        linkedList.add(FIRST_ELEMENT);
        linkedList.add(THIRD_ELEMENT);
        linkedList.add(FOURTH_ELEMENT);
        linkedList.set(FIFTH_ELEMENT, 1);

        assertThat(linkedList.size()).isEqualTo(3);
        assertThat(linkedList.get(0)).isEqualTo(FIRST_ELEMENT);
        assertThat(linkedList.get(1)).isEqualTo(FIFTH_ELEMENT);
        assertThat(linkedList.get(2)).isEqualTo(FOURTH_ELEMENT);
    }

    @DisplayName("Should Return True If Array Contain Element")
    @Test
    void shouldReturnTrueIfArrayContainElement() {
        linkedList.add(FIRST_ELEMENT);

        assertThat(linkedList.contains(FIRST_ELEMENT)).isEqualTo(true);
        assertThat(linkedList.contains(SECOND_ELEMENT)).isEqualTo(false);
    }

    @DisplayName("Should Return Index Of Element")
    @Test
    void shouldReturnIndexOfElement() {
        linkedList.add(FIRST_ELEMENT);
        linkedList.add(SECOND_ELEMENT);
        linkedList.add(THIRD_ELEMENT);
        linkedList.add(SECOND_ELEMENT);

        assertThat(linkedList.indexOf(FIRST_ELEMENT)).isEqualTo(0);
        assertThat(linkedList.indexOf(SECOND_ELEMENT)).isEqualTo(1);
        assertThat(linkedList.indexOf(THIRD_ELEMENT)).isEqualTo(2);
        assertThat(linkedList.indexOf(FIFTH_ELEMENT)).isEqualTo(-1);
    }

    @DisplayName("Should Return Index Of Element Include Null Element")
    @Test
    void shouldReturnIndexOfElementIncludeNullElement() {
        linkedList.add(FIRST_ELEMENT);
        linkedList.add(null);
        linkedList.add(THIRD_ELEMENT);
        linkedList.add(null);

        assertThat(linkedList.indexOf(FIRST_ELEMENT)).isEqualTo(0);
        assertThat(linkedList.indexOf(null)).isEqualTo(1);
        assertThat(linkedList.indexOf(THIRD_ELEMENT)).isEqualTo(2);
        assertThat(linkedList.indexOf(FIFTH_ELEMENT)).isEqualTo(-1);
    }

    @DisplayName("Should Return Last Index Of Element Include Null Element")
    @Test
    void shouldReturnLastIndexOfElementIncludeNullElement() {
        linkedList.add(FIRST_ELEMENT);
        linkedList.add(null);
        linkedList.add(THIRD_ELEMENT);
        linkedList.add(null);

        assertThat(linkedList.lastIndexOf(FIRST_ELEMENT)).isEqualTo(0);
        assertThat(linkedList.lastIndexOf(null)).isEqualTo(3);
        assertThat(linkedList.lastIndexOf(THIRD_ELEMENT)).isEqualTo(2);
        assertThat(linkedList.lastIndexOf(FIFTH_ELEMENT)).isEqualTo(-1);
    }

    @DisplayName("Should Return Last Index Of Element")
    @Test
    void shouldReturnLastIndexOfElement() {
        linkedList.add(FIRST_ELEMENT);
        linkedList.add(SECOND_ELEMENT);
        linkedList.add(THIRD_ELEMENT);
        linkedList.add(SECOND_ELEMENT);
        linkedList.add(FIRST_ELEMENT);

        assertThat(linkedList.lastIndexOf(SECOND_ELEMENT)).isEqualTo(3);
        assertThat(linkedList.lastIndexOf(FIRST_ELEMENT)).isEqualTo(4);
    }

    @DisplayName("Should Return String Of List Elements")
    @Test
    void shouldReturnStringOfListElements() {
        linkedList.add(FIRST_ELEMENT);
        linkedList.add(SECOND_ELEMENT);
        linkedList.add(THIRD_ELEMENT);

        assertThat(linkedList.toString()).isEqualTo("[one, two, three]");
        linkedList.clear();
        assertThat(linkedList.toString()).isEqualTo("[]");
    }

    @DisplayName("Should Return True If List Is Empty")
    @Test
    void shouldReturnTrueIfListIsEmpty() {
        assertThat(linkedList.isEmpty()).isEqualTo(true);

        linkedList.add(FIRST_ELEMENT);

        assertThat(linkedList.isEmpty()).isEqualTo(false);
    }

    @DisplayName("Should Iterate Throw List")
    @Test
    void shouldIterateThrowList() {
        linkedList.add(FIRST_ELEMENT);
        linkedList.add(SECOND_ELEMENT);
        linkedList.add(THIRD_ELEMENT);

        var iterator = linkedList.iterator();
        assertThat(iterator.next()).isEqualTo(FIRST_ELEMENT);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(SECOND_ELEMENT);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(THIRD_ELEMENT);
        assertThat(iterator.hasNext()).isFalse();
    }

    @DisplayName("Should Remove Element While Iterate Throw List")
    @Test
    void shouldRemoveElementWhileIterateThrowList() {
        linkedList.add(FIRST_ELEMENT);
        linkedList.add(SECOND_ELEMENT);
        linkedList.add(THIRD_ELEMENT);

        var iterator = linkedList.iterator();
        assertThat(iterator.next()).isEqualTo(FIRST_ELEMENT);
        assertThat(iterator.hasNext()).isTrue();

        iterator.remove();

        assertThat(linkedList.contains(SECOND_ELEMENT)).isFalse();
        assertThat(linkedList.size()).isEqualTo(2);
    }
}