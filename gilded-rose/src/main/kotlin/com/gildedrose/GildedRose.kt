package com.gildedrose

class GildedRose(var items: Array<Item>) {
  companion object {
    const val AGED_BRIE = "Aged Brie"
    const val BCKS_PASSES = "Backstage passes to a TAFKAL80ETC concert"
    const val SULFURAS_HAND = "Sulfuras, Hand of Ragnaros"
  }
  fun updateQuality() {
    for (i in items.indices) {
      if (items[i].name != AGED_BRIE && items[i].name != BCKS_PASSES) {
        if (items[i].quality > 0) {
          if (items[i].name != SULFURAS_HAND) {
            items[i].quality = items[i].quality - 1
          }
        }
      } else {
        if (items[i].quality < 50) {
          items[i].quality = items[i].quality + 1

          if (items[i].name == BCKS_PASSES) {
            if (items[i].sellIn < 11) {
              if (items[i].quality < 50) {
                items[i].quality = items[i].quality + 1
              }
            }

            if (items[i].sellIn < 6) {
              if (items[i].quality < 50) {
                items[i].quality = items[i].quality + 1
              }
            }
          }
        }
      }

      if (items[i].name != SULFURAS_HAND) {
        items[i].sellIn = items[i].sellIn - 1
      }

      if (items[i].sellIn < 0) {
        if (items[i].name != AGED_BRIE) {
          if (items[i].name != BCKS_PASSES) {
            if (items[i].quality > 0) {
              if (items[i].name != SULFURAS_HAND) {
                items[i].quality = items[i].quality - 1
              }
            }
          } else {
            items[i].quality = items[i].quality - items[i].quality
          }
        } else {
          if (items[i].quality < 50) {
            items[i].quality = items[i].quality + 1
          }
        }
      }
    }
  }
}
