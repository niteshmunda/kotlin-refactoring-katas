package com.gildedrose

import com.gildedrose.Constants.AGED_BRIE
import com.gildedrose.Constants.BACKSTAGE_PASSES
import com.gildedrose.Constants.QUALITY_CONSTANT_ONE
import com.gildedrose.Constants.QUALITY_UPPER_BOUND
import com.gildedrose.Constants.SULFURAS_HAND_OF_RAGNAROS

class GildedRose(var items: Array<Item>) {

  private val gildedRoseLogic = GildedRoseLogic

  fun updateQuality() {
    items.forEach { item ->
      when {
        item.nameInConcertRegistry(item.name).not() -> {
          if (item.qualityAboveLowerBound(item.quality)) {
            item.quality = decreaseQualityByOne(item)
          }
        }

        item.name == BACKSTAGE_PASSES -> {
          if (item.qualityBelowUpperBound()) {
            item.quality = increaseQualityByOne(item)
            item.quality = item.increaseQuality()
          }
        }

        else -> {
          if (item.qualityBelowUpperBound()) {
            item.quality = increaseQualityByOne(item)
            item.quality = item.increaseQuality()
          }
          if (item.name != SULFURAS_HAND_OF_RAGNAROS) {
            item.sellIn = decreaseSellIn(item)
          }
        }
      }

      if (item.sellInBelowLowerBound()) {
        when {
          item.name == AGED_BRIE -> {
            if (item.quality < QUALITY_UPPER_BOUND) {
              item.quality = increaseQualityByOne(item)
            }
          }

          item.name == BACKSTAGE_PASSES -> {
            item.quality = decreaseQualityBySomeValue(item)
          }

          item.nameInConcertRegistry(item.name) -> {
            if (item.qualityAboveLowerBound(item.quality)) {
              item.quality = decreaseQualityByOne(item)
            }
          }
        }
      }
    }
  }

  fun increaseQualityByOne(item: Item): Int {
    return item.quality + QUALITY_CONSTANT_ONE
  }

  fun decreaseQualityByOne(item: Item): Int {
    return item.quality - QUALITY_CONSTANT_ONE
  }

  fun decreaseQualityBySomeValue(item: Item): Int {
    return 0
  }

  fun decreaseSellIn(item: Item): Int {
    return item.sellIn - QUALITY_CONSTANT_ONE
  }
}
