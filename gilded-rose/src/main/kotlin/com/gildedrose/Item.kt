package com.gildedrose

import com.gildedrose.Constants.AGED_BRIE
import com.gildedrose.Constants.BACKSTAGE_PASSES
import com.gildedrose.Constants.QUALITY_AND_SELLIN_LOWER_BOUND
import com.gildedrose.Constants.SULFURAS_HAND_OF_RAGNAROS

open class Item(var name: String, var sellIn: Int, var quality: Int) {
  override fun toString(): String {
    return this.name + ", " + this.sellIn + ", " + this.quality
  }

  companion object {
    val concertRegistry = listOf(AGED_BRIE, BACKSTAGE_PASSES, SULFURAS_HAND_OF_RAGNAROS)
  }

  fun nameInConcertRegistry(name: String) : Boolean = concertRegistry.contains(name)

  fun qualityAboveLowerBound(quality: Int) : Boolean {
    return quality > QUALITY_AND_SELLIN_LOWER_BOUND
  }
}
