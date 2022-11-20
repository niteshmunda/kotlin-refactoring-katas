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

  class TestForUpdateSellin {
    @Test
    fun `when item name is not SULFURAS_HAND, then decrease sellIn value`() {
      // given
      val sellIn = 3
      val item = Item(name = Constants.AGED_BRIE, sellIn = sellIn, quality = Constants.QUALITY_UPPER_BOUND)
      val items = arrayOf(item)
      val app = GildedRose(items)


      // when
      app.updateSellin(item)

      // then
      assertEquals(sellIn-1, item.sellIn)
    }
  }

}
