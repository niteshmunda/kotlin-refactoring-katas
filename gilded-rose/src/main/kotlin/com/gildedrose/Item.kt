package com.gildedrose

import com.gildedrose.Constants.AGED_BRIE
import com.gildedrose.Constants.BACKSTAGE_PASSES
import com.gildedrose.Constants.QUALITY_AND_SELLIN_LOWER_BOUND
import com.gildedrose.Constants.QUALITY_CONSTANT_ELEVEN
import com.gildedrose.Constants.QUALITY_CONSTANT_SIX
import com.gildedrose.Constants.QUALITY_UPPER_BOUND
import com.gildedrose.Constants.SULFURAS_HAND_OF_RAGNAROS

data class Item(var name: String, var sellIn: Int, var quality: Int) {
  override fun toString(): String {
    return this.name + ", " + this.sellIn + ", " + this.quality
  }

  companion object {
    enum class SellInRanges {
      BelowLowerBound, BetweenLowerAndUpperBound
    }
    fun getSellinRange(sellIn: Int) : SellInRanges {
      return when {
        sellIn < QUALITY_AND_SELLIN_LOWER_BOUND -> SellInRanges.BelowLowerBound
        else -> SellInRanges.BetweenLowerAndUpperBound
      }
    }

    enum class QualityRange {
      AboveLowerBound, BelowUpperBound
    }

    fun getQualityRange(quality: Int) : QualityRange {
      return when {
        quality > QUALITY_AND_SELLIN_LOWER_BOUND -> QualityRange.AboveLowerBound
        else -> QualityRange.BelowUpperBound
      }
    }
  }

  fun qualityAboveLowerBound(quality: Int) : Boolean {
    return quality > QUALITY_AND_SELLIN_LOWER_BOUND
  }

  fun increaseQuality(): Int {
    return quality + if (sellIn < QUALITY_CONSTANT_SIX) {
      2
    } else if (sellIn in (QUALITY_CONSTANT_SIX + 1) until QUALITY_CONSTANT_ELEVEN) {
      1
    } else {
      0
    }
  }
}
