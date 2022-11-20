package com.gildedrose

import com.gildedrose.Constants.AGED_BRIE
import com.gildedrose.Constants.BACKSTAGE_PASSES
import com.gildedrose.Constants.QUALITY_CONSTANT_ONE
import com.gildedrose.Constants.QUALITY_AND_SELLIN_LOWER_BOUND
import com.gildedrose.Constants.QUALITY_UPPER_BOUND
import com.gildedrose.Constants.SULFURAS_HAND_OF_RAGNAROS

class GildedRose(var items: Array<Item>) {

  private val gildedRoseLogic = GildedRoseLogic

  fun updateQuality() {
    items.forEach { item ->
      checkAndUpdateQualityOfItems(item)
      updateSellin(item)
      updateQualityIfSellInIsLessThanZero(item)
    }
  }

  fun checkAndUpdateQualityOfItems(item: Item) {
    if (item.nameInConcertRegistry(item.name).not() && item.qualityAboveLowerBound(item.quality)) {
      item.quality = decreaseQualityByOne(item)
    } else {
      if (item.qualityBelowUpperBound()) {
        item.quality = increaseQualityByOne(item)
        if (item.name == BACKSTAGE_PASSES) {
          if (item.qualityBelowUpperBound()) {
            item.quality = item.increaseQuality()
          }
        }
      }
    }
  }

  fun updateQualityIfSellInIsLessThanZero(item: Item) {
    if (item.sellIn >= QUALITY_AND_SELLIN_LOWER_BOUND) return
    if (item.name != AGED_BRIE) {
      updateQualityBasedOnBCKSPASSES(item)
    } else {
      if (item.quality < QUALITY_UPPER_BOUND) {
        item.quality = increaseQualityByOne(item)
      }
    }
  }

  fun updateQualityBasedOnBCKSPASSES(item: Item) {
    if (item.name != BACKSTAGE_PASSES) {
      if (item.name != SULFURAS_HAND_OF_RAGNAROS && item.quality > QUALITY_AND_SELLIN_LOWER_BOUND) {
        item.quality = decreaseQualityByOne(item)
      }
    } else {
      item.quality = decreaseQualityBySomeValue(item)
    }
  }

  fun updateSellin(item: Item) {
    if (item.name != SULFURAS_HAND_OF_RAGNAROS) {
      item.sellIn = decreaseSellIn(item)
    }
  }

  private fun increaseQualityByOne(item: Item): Int {
    return item.quality + QUALITY_CONSTANT_ONE
  }

  private fun decreaseQualityByOne(item: Item): Int {
    return item.quality - QUALITY_CONSTANT_ONE
  }

  private fun decreaseQualityBySomeValue(item: Item): Int {
    return 0
  }

  private fun decreaseSellIn(item: Item): Int {
    return item.sellIn - QUALITY_CONSTANT_ONE
  }
}
