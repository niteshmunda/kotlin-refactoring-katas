package com.gildedrose

import com.gildedrose.Constants.AGED_BRIE
import com.gildedrose.Constants.BACKSTAGE_PASSES
import com.gildedrose.Constants.QUALITY_UPPER_BOUND
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestForUpdateQualityIfSellInIsLessThanZero {

  @Test
  fun `when sellIn value is greater than equal to zero than do nothing`() {
    // given
    val sellIn = 3
    val item = Item(name = Constants.AGED_BRIE, sellIn = sellIn, quality = Constants.QUALITY_UPPER_BOUND)
    val items = arrayOf(item)
    val app = GildedRose(items)


    // when
    if (item.sellInBelowLowerBound()) {
      when {
        item.name == AGED_BRIE -> {
          if (item.quality < QUALITY_UPPER_BOUND) {
            item.quality = app.increaseQualityByOne(item)
          }
        }

        item.name == BACKSTAGE_PASSES -> {
          item.quality = app.decreaseQualityBySomeValue(item)
        }

        item.nameInConcertRegistry(item.name) -> {
          if (item.qualityAboveLowerBound(item.quality)) {
            item.quality = app.decreaseQualityByOne(item)
          }
        }
      }
    }

    // then
    Assertions.assertEquals(sellIn, item.sellIn)
  }

  @Test
  fun `when sellIn value is less than 0, and name is AGED_BRIE, than when quality is less than QUALITY_BOUND, then do nothing`() {
    // given
    val sellIn = -1
    val quality = Constants.QUALITY_UPPER_BOUND - 1
    val item = Item(name = Constants.AGED_BRIE, sellIn = sellIn, quality = quality)
    val items = arrayOf(item)
    val app = GildedRose(items)


    // when
    if (item.sellInBelowLowerBound()) {
      when {
        item.name == AGED_BRIE -> {
          if (item.quality < QUALITY_UPPER_BOUND) {
            item.quality = app.increaseQualityByOne(item)
          }
        }

        item.name == BACKSTAGE_PASSES -> {
          item.quality = app.decreaseQualityBySomeValue(item)
        }

        item.nameInConcertRegistry(item.name) -> {
          if (item.qualityAboveLowerBound(item.quality)) {
            item.quality = app.decreaseQualityByOne(item)
          }
        }
      }
    }

    // then
    Assertions.assertEquals(quality + 1, item.quality)
  }

  @Test
  fun `when sellIn value is less than 0, and name is AGED_BRIE, than when quality is greater than and equal to QUALITY_BOUND`() {
    // given
    val sellIn = -1
    val quality = Constants.QUALITY_UPPER_BOUND
    val item = Item(name = Constants.AGED_BRIE, sellIn = sellIn, quality = quality)
    val items = arrayOf(item)
    val app = GildedRose(items)


    // when
    if (item.sellInBelowLowerBound()) {
      when {
        item.name == AGED_BRIE -> {
          if (item.quality < QUALITY_UPPER_BOUND) {
            item.quality = app.increaseQualityByOne(item)
          }
        }

        item.name == BACKSTAGE_PASSES -> {
          item.quality = app.decreaseQualityBySomeValue(item)
        }

        item.nameInConcertRegistry(item.name) -> {
          if (item.qualityAboveLowerBound(item.quality)) {
            item.quality = app.decreaseQualityByOne(item)
          }
        }
      }
    }

    // then
    Assertions.assertEquals(quality, item.quality)
  }
}
