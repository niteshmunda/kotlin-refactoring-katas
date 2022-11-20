package com.gildedrose

import com.gildedrose.Constants.SULFURAS_HAND_OF_RAGNAROS
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GildedRoseTest {

  @Test
  fun foo() {
    val items = arrayOf(Item("foo", 0, 0))
    val app = GildedRose(items)
    app.updateQuality(items)
    assertEquals("foo", app.items[0].name)
  }
}
