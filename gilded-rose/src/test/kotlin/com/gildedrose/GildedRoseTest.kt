package com.gildedrose

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
  fun `test updateQuality function when name is not equal to aged_brie, bcks_passes and quality greater than 0 and name is not equal to sulfurus_hand` () {
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
  fun `test updateQuality function when name is not equal to aged_brie, bcks_passes and quality not greater than 0 and name is not equal to sulfurus_hand` () {
    // given
    val item = Item(name = "foo", sellIn = 0, quality = 0)
    val items = arrayOf(item)
    val app = GildedRose(items)


    // when
    app.checkAndUpdateQualityOfItems(item)

    // then
    assertEquals(0, item.quality)

  }

}
