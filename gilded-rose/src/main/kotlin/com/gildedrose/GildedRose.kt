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
          item.quality = item.quality + ONE
        }
      } else {
        if (item.name != BCKS_PASSES) {
          if (item.quality > ZERO && item.name != SULFURAS_HAND) {
            item.quality = item.quality - ONE
          }
        } else {
          item.quality = item.quality - item.quality
        }
      }
    }
  }

  fun updateSellin(item: Item) {
    if (item.name != SULFURAS_HAND) {
      item.sellIn = item.sellIn - ONE
    }
  }

  fun checkAndUpdateQualityOfItems(item: Item) {
    if (item.name != AGED_BRIE && item.name != BCKS_PASSES) {
      if (item.quality > ZERO && item.name != SULFURAS_HAND) {
        item.quality = item.quality - ONE
      }
    } else {
      if (item.quality < QUALITY_BOUND) {
        item.quality = item.quality + ONE

        if (item.name == BCKS_PASSES) {
          if (item.sellIn < ELEVEN && item.quality < QUALITY_BOUND) {
            item.quality = item.quality + ONE
          }

          if (item.sellIn < SIX && item.quality < QUALITY_BOUND) {
            item.quality = item.quality + ONE
          }
        }
      }
    }
  }
}
