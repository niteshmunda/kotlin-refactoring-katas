package com.gildedrose

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestForCheckAndUpdateQualityOfItems {
  @Test
  fun `decrease quality when name is not equal to aged_brie, bcks_passes and sulfurus_hand and quality greater than 0`() {
    // given
    val item = Item(name = "foo", sellIn = 0, quality = 1)
    val items = arrayOf(item)
    val app = GildedRose(items)


    // when
    app.checkAndUpdateQualityOfItems(item)

    // then
      Assertions.assertEquals(0, item.quality)

  }

  @Test
  fun `do not decrease quality when name is not equal to aged_brie, bcks_passes and sulfurus_hand and quality not greater than 0`() {
    // given
    val item = Item(name = "foo", sellIn = 0, quality = 0)
    val items = arrayOf(item)
    val app = GildedRose(items)


    // when
    app.checkAndUpdateQualityOfItems(item)

    // then
    assert(item.quality >= 0)

  }

  @Test
  fun `when name is in either AGED_BRIE, BCKS_PASSES, SULFURAS_HAND, and quanlity is greater that quality_bound then do nothing` () {
    // given
    val item = Item(name = Constants.SULFURAS_HAND, sellIn = 0, quality = Constants.QUALITY_BOUND)
    val items = arrayOf(item)
    val app = GildedRose(items)


    // when
    app.checkAndUpdateQualityOfItems(item)

    // then
      Assertions.assertEquals(Constants.QUALITY_BOUND, item.quality)
  }

  @Test
  fun `when name is in either AGED_BRIE, BCKS_PASSES, SULFURAS_HAND, and quanlity is less that quality_bound then update Quantity based on conditions on updateQuantityOnCertainCondition` () {
    // given
    val quality = Constants.QUALITY_BOUND -1
    val item = Item(name = Constants.SULFURAS_HAND, sellIn = 0, quality = quality)
    val items = arrayOf(item)
    val app = GildedRose(items)


    // when
    app.checkAndUpdateQualityOfItems(item)

    // then
      Assertions.assertEquals(quality + 1, item.quality)
  }
}
