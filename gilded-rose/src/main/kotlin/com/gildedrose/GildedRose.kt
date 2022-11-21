package com.gildedrose

import com.gildedrose.Constants.AGED_BRIE
import com.gildedrose.Constants.BACKSTAGE_PASSES
import com.gildedrose.Constants.QUALITY_CONSTANT_ONE
import com.gildedrose.Constants.SULFURAS_HAND_OF_RAGNAROS

class GildedRose {
  fun updateQuality(items: Array<Item>): List<Item> {
    val returnList = mutableListOf<Item>()
    items.forEach { item ->
      // name check
      val output = fwdUpdateInformation(item)
      returnList.add(item.copy(name = output.name, quality = output.quality, sellIn = output.sellIn))
    }
    return returnList
  }

  fun fwdUpdateInformation(item: Item) : Item {
    when (item.name) {
      AGED_BRIE -> {
        // sellin check
        sellInCheck(
          item = item,
          onBelowLowerBound = {
            // quality check
            qualityCheck(
              item = item,
              onAboveLowerBound = {},
              onBelowUpperBound = { item.quality = increaseQualityByOne(item) }
            )
          },
          onElse = {}
        )
      }

      BACKSTAGE_PASSES -> {
        // quality check
        qualityCheck(
          item = item,
          onAboveLowerBound = {},
          onBelowUpperBound = {
            item.quality = increaseQualityByOne(item)
            item.quality = item.increaseQuality()
          }
        )

        // sell in check
        sellInCheck(
          item = item,
          onBelowLowerBound = { item.quality = decreaseQualityBySomeValue() },
          onElse = {}
        )
      }

      SULFURAS_HAND_OF_RAGNAROS -> {
        // quality check
        qualityCheck(
          item = item,
          onAboveLowerBound = {},
          onBelowUpperBound = {
            item.quality = increaseQualityByOne(item)
            item.quality = item.increaseQuality()
          }
        )
      }

      else -> {
        // quality check
        qualityCheck(
          item = item,
          onAboveLowerBound = { item.quality = decreaseQualityByOne(item) },
          onBelowUpperBound = {}
        )

        // sellin check
        sellInCheck(
          item = item,
          onBelowLowerBound = {
            if (item.qualityAboveLowerBound(item.quality)) {
              item.quality = decreaseQualityByOne(item)
            }
          },
          onElse = {}
        )
      }
    }

    return item
  }

  private fun sellInCheck(item: Item, onBelowLowerBound: () -> Unit, onElse: () -> Unit) {
    when (Item.getSellinRange(item.sellIn)) {
      Item.Companion.SellInRanges.BelowLowerBound -> {
        onBelowLowerBound.invoke()
      }

      else -> {
        onElse.invoke()
      }
    }
  }

  private fun qualityCheck(item: Item, onAboveLowerBound: () -> Unit, onBelowUpperBound: () -> Unit) {
    when (Item.getQualityRange(item.quality)) {
      Item.Companion.QualityRange.AboveLowerBound -> {
        onAboveLowerBound.invoke()
      }

      Item.Companion.QualityRange.BelowUpperBound -> {
        onBelowUpperBound.invoke()
      }
    }
  }

  private fun increaseQualityByOne(item: Item): Int {
    return item.quality + QUALITY_CONSTANT_ONE
  }

  private fun decreaseQualityByOne(item: Item): Int {
    return item.quality - QUALITY_CONSTANT_ONE
  }

  private fun decreaseQualityBySomeValue(): Int {
    return 0
  }
}
