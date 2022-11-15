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
    for (i in items.indices) {

      if (items[i].name != AGED_BRIE && items[i].name != BCKS_PASSES) {
        if (items[i].quality > ZERO && items[i].name != SULFURAS_HAND) {
          items[i].quality = items[i].quality - ONE
        }
      } else {
        if (items[i].quality < QUALITY_BOUND) {
          items[i].quality = items[i].quality + ONE

          if (items[i].name == BCKS_PASSES) {
            if (items[i].sellIn < ELEVEN && items[i].quality < QUALITY_BOUND) {
              items[i].quality = items[i].quality + ONE
            }

            if (items[i].sellIn < SIX && items[i].quality < QUALITY_BOUND) {
              items[i].quality = items[i].quality + ONE
            }
          }
        }
      }

      if (items[i].name != SULFURAS_HAND) {
        items[i].sellIn = items[i].sellIn - ONE
      }

      if (items[i].sellIn < ZERO) {
        if (items[i].name != AGED_BRIE) {
          if (items[i].name != BCKS_PASSES) {
            if (items[i].quality > ZERO) {
              if (items[i].name != SULFURAS_HAND) {
                items[i].quality = items[i].quality - ONE
              }
            }
          } else {
            items[i].quality = items[i].quality - items[i].quality
          }
        } else {
          if (items[i].quality < QUALITY_BOUND) {
            items[i].quality = items[i].quality + ONE
          }
        }
      }
    }
  }
}
