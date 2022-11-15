package com.gildedrose

import com.gildedrose.Constants.SULFURAS_HAND
import com.gildedrose.Constants.ZERO
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GildedRoseTest {

  @Test
  fun foo() {
    val items = arrayOf(Item("foo", 0, 0))
    val app = GildedRose(items)
    app.updateQuality()
    assertEquals("foo", app.items[0].name)
  }

  @Test
  fun `decrease quality when name is not equal to aged_brie, bcks_passes and quality greater than 0 and name is not equal to sulfurus_hand`() {
    // given
    val item = Item(name = "foo", sellIn = 0, quality = 1)
    val items = arrayOf(item)
    val app = GildedRose(items)


    // when
    app.checkAndUpdateQualityOfItems(item)

    // then
    assertEquals(0, item.quality)

  }

  @Test
  fun `do not decrease quality when name is not equal to aged_brie, bcks_passes and quality not greater than 0 and name is not equal to sulfurus_hand`() {
    // given
    val item = Item(name = "foo", sellIn = 0, quality = 0)
    val items = arrayOf(item)
    val app = GildedRose(items)


    // when
    app.checkAndUpdateQualityOfItems(item)

    // then
    assertEquals(0, item.quality)

  }

  @Test
  fun `do not decrease quality only when quality is greater than zero and Name is not sulfuras`() {
    // given
    val item = Item(name = "foo", sellIn = 0, quality = 0)
    val items = arrayOf(item)
    val app = GildedRose(items)


    // when
    if (item.name != SULFURAS_HAND && item.quality > ZERO) {
      item.quality = app.decreaseQualityByOne(item)
    }

    // then
    assertEquals(0, item.quality)
  }

  @Test
  fun `do not decrease quality only when quality is less than zero and Name is not sulfuras`() {
    // given
    val item = Item(name = "foo", sellIn = 0, quality = 0)
    val items = arrayOf(item)
    val app = GildedRose(items)


    // when
    if (item.name != SULFURAS_HAND && item.quality > ZERO) {
      item.quality = app.decreaseQualityByOne(item)
    }

    // then
    assertEquals(0, item.quality)
  }

  @Test
  fun `do not decrease quality only when quality is less than zero and Name is sulfuras`() {
    // given
    val item = Item(name = Constants.SULFURAS_HAND, sellIn = 0, quality = -1)
    val items = arrayOf(item)
    val app = GildedRose(items)


    // when
    if (item.name != SULFURAS_HAND && item.quality > ZERO) {
      item.quality = app.decreaseQualityByOne(item)
    }

    // then
    assertEquals(-1, item.quality)
  }

  @Test
  fun `decrease quality only when quality is greater than zero and Name is not sulfuras`() {
    // given
    val item = Item(name = "foo", sellIn = 0, quality = 1)
    val items = arrayOf(item)
    val app = GildedRose(items)


    // when
    if (item.name != SULFURAS_HAND && item.quality > ZERO) {
      item.quality = app.decreaseQualityByOne(item)
    }

    // then
    assertEquals(0, item.quality)
  }


}
