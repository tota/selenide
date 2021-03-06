package com.codeborne.selenide.collections;

import com.codeborne.selenide.ex.ListSizeMismatch;
import com.codeborne.selenide.impl.WebElementsCollection;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Mocks.mockCollection;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.mockito.Mockito.mock;

class SizeLessThanOrEqualTest implements WithAssertions {
  @Test
  void applyWithWrongSizeList() {
    assertThat(new SizeLessThanOrEqual(1).apply(asList(mock(WebElement.class), mock(WebElement.class))))
      .isFalse();
  }

  @Test
  void applyWithSameSize() {
    assertThat(new SizeLessThanOrEqual(1).apply(singletonList(mock(WebElement.class))))
      .isTrue();
  }

  @Test
  void applyWithLessSize() {
    assertThat(new SizeLessThanOrEqual(2).apply(singletonList(mock(WebElement.class))))
      .isTrue();
  }

  @Test
  void failMethod() {
    WebElementsCollection collection = mockCollection("Collection description");

    assertThatThrownBy(() ->
      new SizeLessThanOrEqual(10).fail(collection,
        emptyList(),
        new Exception("Exception message"),
        10000))
      .isInstanceOf(ListSizeMismatch.class)
      .hasMessageStartingWith("List size mismatch: expected: <= 10, actual: 0, collection: Collection description\nElements: []");
  }

  @Test
  void testToString() {
    assertThat(new SizeLessThanOrEqual(10))
      .hasToString("size <= 10");
  }
}
