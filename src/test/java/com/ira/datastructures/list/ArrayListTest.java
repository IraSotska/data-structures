package com.ira.datastructures.list;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ArrayListTest {

    private static final String FIRST_ELEMENT = "one";
    private static final String SECOND_ELEMENT = "two";
    private static final String THIRD_ELEMENT = "three";
    private static final String FOURTH_ELEMENT = "four";
    private static final String FIFTH_ELEMENT = "five";
    private static final String SIXTH_ELEMENT = "six";

    private ArrayList<String> arrayList = new ArrayList<>();

    @AfterEach
    void clearArrayList() {
        arrayList.clear();
    }

    @DisplayName("Should Add Typed Elements To Array List")
    @Test
    void shouldAddElementsToList() {
        arrayList.add(FIRST_ELEMENT);
        arrayList.add(SECOND_ELEMENT);
        arrayList.add(THIRD_ELEMENT);
        arrayList.add(FOURTH_ELEMENT);
        arrayList.add(FIFTH_ELEMENT);
        arrayList.add(SIXTH_ELEMENT);

        assertThat(arrayList.get(0)).isEqualTo(FIRST_ELEMENT);
        assertThat(arrayList.get(1)).isEqualTo(SECOND_ELEMENT);
        assertThat(arrayList.get(2)).isEqualTo(THIRD_ELEMENT);
        assertThat(arrayList.get(3)).isEqualTo(FOURTH_ELEMENT);
        assertThat(arrayList.get(4)).isEqualTo(FIFTH_ELEMENT);
        assertThat(arrayList.get(5)).isEqualTo(SIXTH_ELEMENT);
    }

    @DisplayName("Should Throw Index Out Of Bounds Exception If Add Element To Not Correct Index")
    @Test
    void shouldThrowIndexOutOfBoundsExceptionIfAddElementToNotCorrectIndex() {
        arrayList.add(FIRST_ELEMENT);
        assertThatThrownBy(() -> arrayList.add(FIRST_ELEMENT, 2)).isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> arrayList.add(FIRST_ELEMENT, -1)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @DisplayName("Should Add Element By Index")
    @Test
    void shouldAddElementByIndex() {
        arrayList.add(FIRST_ELEMENT);
        arrayList.add(THIRD_ELEMENT);
        arrayList.add(SECOND_ELEMENT, 1);

        assertThat(arrayList.get(0)).isEqualTo(FIRST_ELEMENT);
        assertThat(arrayList.get(1)).isEqualTo(SECOND_ELEMENT);
        assertThat(arrayList.get(2)).isEqualTo(THIRD_ELEMENT);
        assertThat(arrayList.size()).isEqualTo(3);
    }

    @DisplayName("Should Get Elements By Index")
    @Test
    void shouldGetElementsByIndex() {
        arrayList.add(FIRST_ELEMENT);
        arrayList.add(SECOND_ELEMENT);
        arrayList.add(THIRD_ELEMENT);

        assertThat(arrayList.get(0)).isEqualTo(FIRST_ELEMENT);
        assertThat(arrayList.get(1)).isEqualTo(SECOND_ELEMENT);
        assertThat(arrayList.get(2)).isEqualTo(THIRD_ELEMENT);
    }

    @DisplayName("Should Throw Index Out Of Bounds Exception If Get Not Correct Index")
    @Test
    void shouldThrowIndexOutOfBoundsExceptionIfGetNotCorrectIndex() {
        arrayList.add(FIRST_ELEMENT);
        assertThatThrownBy(() -> arrayList.get(-1)).isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> arrayList.get(1)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @DisplayName("Should Throw Index Out Of bounds Exception If Remove Element At Not Correct Index")
    @Test
    void shouldThrowIndexOutOfBoundsExceptionIfRemoveElementAtNotCorrectIndex() {
        arrayList.add(FIRST_ELEMENT);

        assertThatThrownBy(() -> arrayList.remove(1)).isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> arrayList.remove(-1)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @DisplayName("Should Remove Element")
    @Test
    void shouldRemoveElement() {
        arrayList.add(FIRST_ELEMENT);
        arrayList.add(THIRD_ELEMENT);
        arrayList.add(FOURTH_ELEMENT);

        assertThat(arrayList.remove(0)).isEqualTo(FIRST_ELEMENT);
        assertThat(arrayList.get(1)).isEqualTo(FOURTH_ELEMENT);
        assertThat(arrayList.size()).isEqualTo(2);
    }

    @DisplayName("Should Clear List")
    @Test
    void shouldClearList() {
        arrayList.add(FIRST_ELEMENT);
        arrayList.add(THIRD_ELEMENT);
        arrayList.add(FOURTH_ELEMENT);
        arrayList.clear();

        assertThat(arrayList.size()).isEqualTo(0);
    }

    @DisplayName("Should Return Size Of List")
    @Test
    void shouldReturnSizeOfList() {
        arrayList.add(FIRST_ELEMENT);
        arrayList.add(THIRD_ELEMENT);
        arrayList.add(FOURTH_ELEMENT);

        assertThat(arrayList.size()).isEqualTo(3);
    }

    @DisplayName("Should Throw Index Out Of Bounds Exception If Set Not Correct Index")
    @Test
    void shouldThrowIndexOutOfBoundsExceptionIfSetNotCorrectIndex() {
        arrayList.add(FIRST_ELEMENT);
        assertThatThrownBy(() -> arrayList.get(-1)).isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> arrayList.get(1)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @DisplayName("Should Set Element")
    @Test
    void shouldSetElement() {
        arrayList.add(FIRST_ELEMENT);
        arrayList.add(THIRD_ELEMENT);
        arrayList.add(FOURTH_ELEMENT);
        arrayList.set(FIFTH_ELEMENT, 1);

        assertThat(arrayList.size()).isEqualTo(3);
        assertThat(arrayList.get(0)).isEqualTo(FIRST_ELEMENT);
        assertThat(arrayList.get(1)).isEqualTo(FIFTH_ELEMENT);
        assertThat(arrayList.get(2)).isEqualTo(FOURTH_ELEMENT);
    }

    @DisplayName("Should Return True If Array Contain Element")
    @Test
    void shouldReturnTrueIfArrayContainElement() {
        arrayList.add(FIRST_ELEMENT);

        assertThat(arrayList.contains(FIRST_ELEMENT)).isEqualTo(true);
        assertThat(arrayList.contains(SECOND_ELEMENT)).isEqualTo(false);
    }

    @DisplayName("Should Return Index Of Element")
    @Test
    void shouldReturnIndexOfElement() {
        arrayList.add(FIRST_ELEMENT);
        arrayList.add(SECOND_ELEMENT);
        arrayList.add(THIRD_ELEMENT);
        arrayList.add(SECOND_ELEMENT);

        assertThat(arrayList.indexOf(FIRST_ELEMENT)).isEqualTo(0);
        assertThat(arrayList.indexOf(SECOND_ELEMENT)).isEqualTo(1);
        assertThat(arrayList.indexOf(THIRD_ELEMENT)).isEqualTo(2);
        assertThat(arrayList.indexOf(FIFTH_ELEMENT)).isEqualTo(-1);
    }

    @DisplayName("Should Return Index Of Element Include Null Element")
    @Test
    void shouldReturnIndexOfElementIncludeNullElement() {
        arrayList.add(FIRST_ELEMENT);
        arrayList.add(null);
        arrayList.add(THIRD_ELEMENT);
        arrayList.add(null);

        assertThat(arrayList.indexOf(FIRST_ELEMENT)).isEqualTo(0);
        assertThat(arrayList.indexOf(null)).isEqualTo(1);
        assertThat(arrayList.indexOf(THIRD_ELEMENT)).isEqualTo(2);
        assertThat(arrayList.indexOf(FIFTH_ELEMENT)).isEqualTo(-1);
    }

    @DisplayName("Should Return Last Index Of Element Include Null Element")
    @Test
    void shouldReturnLastIndexOfElementIncludeNullElement() {
        arrayList.add(FIRST_ELEMENT);
        arrayList.add(null);
        arrayList.add(THIRD_ELEMENT);
        arrayList.add(null);

        assertThat(arrayList.lastIndexOf(FIRST_ELEMENT)).isEqualTo(0);
        assertThat(arrayList.lastIndexOf(null)).isEqualTo(3);
        assertThat(arrayList.lastIndexOf(THIRD_ELEMENT)).isEqualTo(2);
        assertThat(arrayList.lastIndexOf(FIFTH_ELEMENT)).isEqualTo(-1);
    }

    @DisplayName("Should Return Last Index Of Element")
    @Test
    void shouldReturnLastIndexOfElement() {
        arrayList.add(FIRST_ELEMENT);
        arrayList.add(SECOND_ELEMENT);
        arrayList.add(THIRD_ELEMENT);
        arrayList.add(SECOND_ELEMENT);
        arrayList.add(FIRST_ELEMENT);

        assertThat(arrayList.lastIndexOf(SECOND_ELEMENT)).isEqualTo(3);
        assertThat(arrayList.lastIndexOf(FIRST_ELEMENT)).isEqualTo(4);
    }

    @DisplayName("Should Return String Of List Elements")
    @Test
    void shouldReturnStringOfListElements() {
        arrayList.add(FIRST_ELEMENT);
        arrayList.add(SECOND_ELEMENT);
        arrayList.add(THIRD_ELEMENT);

        assertThat(arrayList.toString()).isEqualTo("[one, two, three]");
        arrayList.clear();
        assertThat(arrayList.toString()).isEqualTo("[]");
    }

    @DisplayName("Should Return True If List Is Empty")
    @Test
    void shouldReturnTrueIfListIsEmpty() {
        assertThat(arrayList.isEmpty()).isEqualTo(true);

        arrayList.add(FIRST_ELEMENT);

        assertThat(arrayList.isEmpty()).isEqualTo(false);
    }

    @DisplayName("Should Iterate Throw List")
    @Test
    void shouldIterateThrowList() {
        arrayList.add(FIRST_ELEMENT);
        arrayList.add(SECOND_ELEMENT);
        arrayList.add(THIRD_ELEMENT);

        var iterator = arrayList.iterator();
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
        arrayList.add(FIRST_ELEMENT);
        arrayList.add(SECOND_ELEMENT);
        arrayList.add(THIRD_ELEMENT);

        var iterator = arrayList.iterator();
        assertThat(iterator.next()).isEqualTo(FIRST_ELEMENT);
        assertThat(iterator.hasNext()).isTrue();

        iterator.remove();

        assertThat(arrayList.size()).isEqualTo(2);
    }
}
