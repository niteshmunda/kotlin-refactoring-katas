package com.gildedrose

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestForUpdateQuantityOnCertainCondition {
  @Test
  fun `when item name is not BCKS_PASSES then only increment quality by one` () {
    // given
    val item = Item(name = Constants.SULFURAS_HAND, sellIn = 0, quality = Constants.QUALITY_BOUND)
    val items = arrayOf(item)
    val app = GildedRose(items)


    // when
    app.updateQuantityOnCertainCondition(item)

    // then
      Assertions.assertEquals(Constants.QUALITY_BOUND + 1, item.quality)
  }

  @Test
  fun `when item name is BCKS_PASSES then increment the quality when conditions for updateQualityIfSellinIsGreaterThan11Or6 are met`() {
    // given
    val quality = Constants.QUALITY_BOUND - 3
    val item = Item(name = Constants.BCKS_PASSES, sellIn = 0, quality = quality)
    val items = arrayOf(item)
    val app = GildedRose(items)


    // when
    app.updateQuantityOnCertainCondition(item)

    // then
      Assertions.assertEquals(quality + 3, item.quality)
  }
}
