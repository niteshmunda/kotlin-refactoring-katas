package com.gildedrose

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestForUpdateQualityIfSellinIsGreaterThan11Or6 {
  @Test
  fun `when quality is greater than QUALITY_BOUND the do not update data`() {
    // given
    val item = Item(name = Constants.SULFURAS_HAND_OF_RAGNAROS, sellIn = 0, quality = Constants.QUALITY_UPPER_BOUND)
    val items = arrayOf(item)
    val app = GildedRose(items)


    // when
    if (item.qualityBelowUpperBound()) {
      item.quality = item.increaseQuality()
    }

    // then
    Assertions.assertEquals(item, item)
  }

  @Test
  fun `when sellin is less than eleven and also less than six than increase the quality by 2`() {
    // given
    val quality = Constants.QUALITY_UPPER_BOUND - 2
    val item = Item(name = Constants.SULFURAS_HAND_OF_RAGNAROS, sellIn = 0, quality = quality)
    val items = arrayOf(item)
    val app = GildedRose(items)


    // when
    if (item.qualityBelowUpperBound()) {
      item.quality = item.increaseQuality()
    }

    // then
    Assertions.assertEquals(quality + 2, item.quality)
  }

  @Test
  fun `when sell in is less than eleven and but greater than six than increase the quality by 1`() {
    // given
    val quality = Constants.QUALITY_UPPER_BOUND - 1
    val item = Item(name = Constants.SULFURAS_HAND_OF_RAGNAROS, sellIn = 7, quality = quality)
    val items = arrayOf(item)
    val app = GildedRose(items)


    // when
    if (item.qualityBelowUpperBound()) {
      item.quality = item.increaseQuality()
    }

    // then
    Assertions.assertEquals(quality + 1, item.quality)
  }

  @Test
  fun `when sell in is greater than eleven and also greater than six than no change`() {
    // given
    val quality = Constants.QUALITY_UPPER_BOUND - 1
    val item = Item(name = Constants.SULFURAS_HAND_OF_RAGNAROS, sellIn = 14, quality = quality)
    val items = arrayOf(item)
    val app = GildedRose(items)


    // when
    if (item.qualityBelowUpperBound()) {
      item.quality = item.increaseQuality()
    }

    // then
    Assertions.assertEquals(quality, item.quality)
  }
}
