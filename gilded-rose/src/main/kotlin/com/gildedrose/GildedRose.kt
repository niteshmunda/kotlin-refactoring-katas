package com.gildedrose

import com.gildedrose.Constants.AGED_BRIE
import com.gildedrose.Constants.BCKS_PASSES
import com.gildedrose.Constants.ELEVEN
import com.gildedrose.Constants.ONE
import com.gildedrose.Constants.QUALITY_BOUND
import com.gildedrose.Constants.SIX
import com.gildedrose.Constants.SULFURAS_HAND
import com.gildedrose.Constants.ZERO

class GildedRose(var items: Array<Item>) {

  fun updateQuality() {
    items.forEach { item ->
      checkAndUpdateQualityOfItems(item)

      updateSellin(item)

      updateQualityIfSellInIsLessThanZero(item)
    }
  }

  private fun updateQualityIfSellInIsLessThanZero(item: Item) {
    if (item.sellIn < ZERO) {
      if (item.name == AGED_BRIE) {
        if (item.quality < QUALITY_BOUND) {
          item.quality = increaseQualityByOne(item)
        }
      } else {
        if (item.name != BCKS_PASSES) {
          if (item.name != SULFURAS_HAND && item.quality > ZERO) {
            item.quality = decreaseQualityByOne(item)
          }
        } else {
          item.quality = decreaseQualityBySomeValue(item)
        }
      }
    }
  }

  fun updateSellin(item: Item) {
    if (item.name != SULFURAS_HAND) {
      item.sellIn = decreaseSellIn(item)
    }
  }

  fun checkAndUpdateQualityOfItems(item: Item) {
    if (item.name != AGED_BRIE && item.name != BCKS_PASSES) {
      if (item.name != SULFURAS_HAND) {
        if (item.quality > ZERO) {
          item.quality = decreaseQualityByOne(item)
        }
      }
    } else {
      if (item.quality < QUALITY_BOUND) {
        item.quality = increaseQualityByOne(item)

        if (item.name == BCKS_PASSES) {
          if (item.sellIn < ELEVEN && item.quality < QUALITY_BOUND) {
            item.quality = increaseQualityByOne(item)
          }

          if (item.sellIn < SIX && item.quality < QUALITY_BOUND) {
            item.quality = increaseQualityByOne(item)
          }
        }
      }
    }
  }

  fun increaseQualityByOne(item: Item) = item.quality + ONE

  fun decreaseQualityByOne(item: Item) = item.quality - ONE

  fun decreaseQualityBySomeValue(item: Item) = item.quality - item.quality

  fun decreaseSellIn(item: Item) = item.sellIn - ONE
}
