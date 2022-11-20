package com.gildedrose

import com.gildedrose.Constants.BACKSTAGE_PASSES
import com.gildedrose.Constants.SULFURAS_HAND_OF_RAGNAROS
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TestForCheckAndUpdateQualityOfItems {
  @Test
  fun `when name is not equal to AGED_BRIE, BCKS_PASSES and SULFURAS_HAND and quality greater than 0, than decrease quality by one`() {
    // given
    val quality = 1
    val sellIn = 0
    val item = Item(name = "foo", sellIn = sellIn, quality = quality)
    val items = arrayOf(item)
    val app = GildedRose(items)


    // when
    when {
      item.nameInConcertRegistry(item.name).not() -> {
        if (item.qualityAboveLowerBound(item.quality)) {
          item.quality = app.decreaseQualityByOne(item)
        }
      }

      item.name == BACKSTAGE_PASSES -> {
        if (item.qualityBelowUpperBound()) {
          item.quality = app.increaseQualityByOne(item)
          item.quality = item.increaseQuality()
        }
      }

      else -> {
        if (item.qualityBelowUpperBound()) {
          item.quality = app.increaseQualityByOne(item)
          item.quality = item.increaseQuality()
        }
        if (item.name != SULFURAS_HAND_OF_RAGNAROS) {
          item.sellIn = app.decreaseSellIn(item)
        }
      }
    }

    // then
    Assertions.assertEquals(quality - 1, item.quality)

  }

  @Test
  fun `when name is not equal to AGED_BRIE, BCKS_PASSES and SULFURAS_HAND and quality not greater than 0, than do nothing`() {
    // given
    val item = Item(name = "foo", sellIn = 0, quality = 0)
    val items = arrayOf(item)
    val app = GildedRose(items)


    // when
    when {
      item.nameInConcertRegistry(item.name).not() -> {
        if (item.qualityAboveLowerBound(item.quality)) {
          item.quality = app.decreaseQualityByOne(item)
        }
      }

      item.name == BACKSTAGE_PASSES -> {
        if (item.qualityBelowUpperBound()) {
          item.quality = app.increaseQualityByOne(item)
          item.quality = item.increaseQuality()
        }
      }

      else -> {
        if (item.qualityBelowUpperBound()) {
          item.quality = app.increaseQualityByOne(item)
          item.quality = item.increaseQuality()
        }
        if (item.name != SULFURAS_HAND_OF_RAGNAROS) {
          item.sellIn = app.decreaseSellIn(item)
        }
      }
    }

    // then
    assertEquals(item, item)

  }

  @Test
  fun `when name is in either AGED_BRIE, BCKS_PASSES, SULFURAS_HAND, and quality is greater and equal to quality_bound then do nothing`() {
    // given
    val item = Item(name = Constants.SULFURAS_HAND_OF_RAGNAROS, sellIn = 0, quality = Constants.QUALITY_UPPER_BOUND)
    val items = arrayOf(item)
    val app = GildedRose(items)


    // when
    when {
      item.nameInConcertRegistry(item.name).not() -> {
        if (item.qualityAboveLowerBound(item.quality)) {
          item.quality = app.decreaseQualityByOne(item)
        }
      }

      item.name == BACKSTAGE_PASSES -> {
        if (item.qualityBelowUpperBound()) {
          item.quality = app.increaseQualityByOne(item)
          item.quality = item.increaseQuality()
        }
      }

      else -> {
        if (item.qualityBelowUpperBound()) {
          item.quality = app.increaseQualityByOne(item)
          item.quality = item.increaseQuality()
        }
        if (item.name != SULFURAS_HAND_OF_RAGNAROS) {
          item.sellIn = app.decreaseSellIn(item)
        }
      }
    }

    // then
    Assertions.assertEquals(item, item)
  }

  @Test
  fun `when name is in either AGED_BRIE, BCKS_PASSES, and quality is less than quality_bound then update Quality by at least one when updateQuantityOnCertainCondition are met`() {
    // given
    val quality = Constants.QUALITY_UPPER_BOUND - 1
    val item = Item(name = Constants.BACKSTAGE_PASSES, sellIn = 0, quality = quality)
    val items = arrayOf(item)
    val app = GildedRose(items)


    // when
    when {
      item.nameInConcertRegistry(item.name).not() -> {
        if (item.qualityAboveLowerBound(item.quality)) {
          item.quality = app.decreaseQualityByOne(item)
        }
      }

      item.name == BACKSTAGE_PASSES -> {
        if (item.qualityBelowUpperBound()) {
          item.quality = app.increaseQualityByOne(item)
          item.quality = item.increaseQuality()
        }
      }

      else -> {
        if (item.qualityBelowUpperBound()) {
          item.quality = app.increaseQualityByOne(item)
          item.quality = item.increaseQuality()
        }
        if (item.name != SULFURAS_HAND_OF_RAGNAROS) {
          item.sellIn = app.decreaseSellIn(item)
        }
      }
    }

    // then
    assert(item.quality >= quality + 1)
  }
}
