package com.gildedrose

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestForUpdateQualityIfSellInIsLessThanZero {

  @Test
  fun `when sellIn value is greater than equal to zero than do nothing` () {
    // given
    val sellIn = 3
    val item = Item(name = Constants.AGED_BRIE, sellIn = sellIn, quality = Constants.QUALITY_BOUND)
    val items = arrayOf(item)
    val app = GildedRose(items)


    // when
    app.updateQualityIfSellInIsLessThanZero(item)

    // then
      Assertions.assertEquals(sellIn, item.sellIn)
  }

  @Test
  fun `when sellIn value is less than 0, and name is AGED_BRIE, than when quality is less than QUALITY_BOUND, then do nothing`() {
    // given
    val sellIn = -1
    val quality = Constants.QUALITY_BOUND -1
    val item = Item(name = Constants.AGED_BRIE, sellIn = sellIn, quality = quality)
    val items = arrayOf(item)
    val app = GildedRose(items)


    // when
    app.updateQualityIfSellInIsLessThanZero(item)

    // then
      Assertions.assertEquals(quality + 1, item.quality)
  }

  @Test
  fun `when sellIn value is less than 0, and name is AGED_BRIE, than when quality is greater than and equal to QUALITY_BOUND`() {
    // given
    val sellIn = -1
    val quality = Constants.QUALITY_BOUND
    val item = Item(name = Constants.AGED_BRIE, sellIn = sellIn, quality = quality)
    val items = arrayOf(item)
    val app = GildedRose(items)


    // when
    app.updateQualityIfSellInIsLessThanZero(item)

    // then
      Assertions.assertEquals(quality, item.quality)
  }

  @Test
  fun `when name is BCKS_PASSES than decrease quality to 0`() {
    // given
    val sellIn = 0
    val quality = Constants.QUALITY_BOUND
    val item = Item(name = Constants.BCKS_PASSES, sellIn = sellIn, quality = quality)
    val items = arrayOf(item)
    val app = GildedRose(items)


    // when
    app.updateQualityBasedOnBCKS_PASSES(item)

    // then
      Assertions.assertEquals(0, item.quality)
  }

  @Test
  fun `when name is not BCKS_PASSES, than if name is not SULFURAS_HAND and quality is greater than 0, then decrease quality`() {
    // given
    val sellIn = 0
    val quality = Constants.QUALITY_BOUND
    val item = Item(name = Constants.AGED_BRIE, sellIn = sellIn, quality = quality)
    val items = arrayOf(item)
    val app = GildedRose(items)


    // when
    app.updateQualityBasedOnBCKS_PASSES(item)

    // then
      Assertions.assertEquals(quality - 1, item.quality)
  }

  @Test
  fun `when name is not BCKS_PASSES, than if name is SULFURAS_HAND and quality is greater than 0, then decrease quality`() {
    // given
    val sellIn = 0
    val quality = Constants.QUALITY_BOUND
    val item = Item(name = Constants.SULFURAS_HAND, sellIn = sellIn, quality = quality)
    val items = arrayOf(item)
    val app = GildedRose(items)


    // when
    app.updateQualityBasedOnBCKS_PASSES(item)

    // then
      Assertions.assertEquals(quality, item.quality)
  }
  @Test
  fun `when name is not BCKS_PASSES, than if name is SULFURAS_HAND and quality is less than 0, then decrease quality`() {
    // given
    val sellIn = 0
    val quality = Constants.QUALITY_BOUND
    val item = Item(name = Constants.SULFURAS_HAND, sellIn = sellIn, quality = quality)
    val items = arrayOf(item)
    val app = GildedRose(items)


    // when
    app.updateQualityBasedOnBCKS_PASSES(item)

    // then
      Assertions.assertEquals(quality, item.quality)
  }

  @Test
  fun `when name is not BCKS_PASSES, than if name is not SULFURAS_HAND and quality is less than 0, then decrease quality`() {
    // given
    val sellIn = 0
    val quality = Constants.QUALITY_BOUND
    val item = Item(name = Constants.SULFURAS_HAND, sellIn = sellIn, quality = quality)
    val items = arrayOf(item)
    val app = GildedRose(items)


    // when
    app.updateQualityBasedOnBCKS_PASSES(item)

    // then
      Assertions.assertEquals(quality, item.quality)
  }
}
