package com.gildedrose

import com.gildedrose.Constants.QUALITY_BOUND
import com.gildedrose.Constants.SULFURAS_HAND
import com.gildedrose.Constants.ZERO
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class GildedRoseTest {

  @Test
  fun foo() {
    val items = arrayOf(Item("foo", 0, 0))
    val app = GildedRose(items)
    app.updateQuality()
    assertEquals("foo", app.items[0].name)
  }

  internal class TestForCheckAndUpdateQualityOfItems {
    @Test
    internal fun `decrease quality when name is not equal to aged_brie, bcks_passes and sulfurus_hand and quality greater than 0`() {
      // given
      val item = Item(name = "foo", sellIn = 0, quality = 1)
      val items = arrayOf(item)
      val app = GildedRose(items)


      // when
      app.checkAndUpdateQualityOfItems(item)

      // then
      assertEquals(0, item.quality)

    }

    @Test
    internal fun `do not decrease quality when name is not equal to aged_brie, bcks_passes and sulfurus_hand and quality not greater than 0`() {
      // given
      val item = Item(name = "foo", sellIn = 0, quality = 0)
      val items = arrayOf(item)
      val app = GildedRose(items)


      // when
      app.checkAndUpdateQualityOfItems(item)

      // then
      assert(item.quality >= 0)

    }

    @Test
    internal fun `when name is in either AGED_BRIE, BCKS_PASSES, SULFURAS_HAND, and quanlity is greater that quality_bound then do nothing` () {
      // given
      val item = Item(name = Constants.SULFURAS_HAND, sellIn = 0, quality = Constants.QUALITY_BOUND)
      val items = arrayOf(item)
      val app = GildedRose(items)


      // when
      app.checkAndUpdateQualityOfItems(item)

      // then
      assertEquals(Constants.QUALITY_BOUND, item.quality)
    }

    @Test
    internal fun `when name is in either AGED_BRIE, BCKS_PASSES, SULFURAS_HAND, and quanlity is less that quality_bound then update Quantity based on conditions on updateQuantityOnCertainCondition` () {
      // given
      val quality = Constants.QUALITY_BOUND-1
      val item = Item(name = Constants.SULFURAS_HAND, sellIn = 0, quality = quality)
      val items = arrayOf(item)
      val app = GildedRose(items)


      // when
      app.checkAndUpdateQualityOfItems(item)

      // then
      assertEquals(quality+1, item.quality)
    }
  }

  internal class TestForUpdateQuantityOnCertainCondition {
    @Test
    internal fun `when item name is not BCKS_PASSES then only increment quality by one` () {
      // given
      val item = Item(name = Constants.SULFURAS_HAND, sellIn = 0, quality = Constants.QUALITY_BOUND)
      val items = arrayOf(item)
      val app = GildedRose(items)


      // when
      app.updateQuantityOnCertainCondition(item)

      // then
      assertEquals(Constants.QUALITY_BOUND+1, item.quality)
    }

    @Test
    internal fun `when item name is BCKS_PASSES then increment the quality when conditions for updateQualityIfSellinIsGreaterThan11Or6 are met`() {
      // given
      val quality = Constants.QUALITY_BOUND-2
      val item = Item(name = Constants.BCKS_PASSES, sellIn = 0, quality = quality)
      val items = arrayOf(item)
      val app = GildedRose(items)


      // when
      app.updateQuantityOnCertainCondition(item)

      // then
      assertEquals(quality+3, item.quality)
    }
  }

  internal class TestForUpdateQualityIfSellinIsGreaterThan11Or6 {
    @Test
    internal fun `when quality is greater than QUALITY_BOUND the do not update data`() {
      // given
      val item = Item(name = Constants.SULFURAS_HAND, sellIn = 0, quality = QUALITY_BOUND)
      val items = arrayOf(item)
      val app = GildedRose(items)


      // when
      app.updateQualityIfSellinIsGreaterThan11Or6(item)

      // then
      assertEquals(item, item)
    }

    @Test
    internal fun `when sell in is less than eleven and also less than six than increase the quality by 2`() {
      // given
      val quality = QUALITY_BOUND-1
      val item = Item(name = Constants.SULFURAS_HAND, sellIn = 0, quality = quality)
      val items = arrayOf(item)
      val app = GildedRose(items)


      // when
      app.updateQualityIfSellinIsGreaterThan11Or6(item)

      // then
      assertEquals(quality+2, item.quality)
    }

    @Test
    internal fun `when sell in is less than eleven and but greater than six than increase the quality by 1`() {
      // given
      val quality = QUALITY_BOUND - 1
      val item = Item(name = Constants.SULFURAS_HAND, sellIn = 7, quality = quality)
      val items = arrayOf(item)
      val app = GildedRose(items)


      // when
      app.updateQualityIfSellinIsGreaterThan11Or6(item)

      // then
      assertEquals(quality+1, item.quality)
    }

    @Test
    internal fun `when sell in is greater than eleven and also greater than six than no change`() {
      // given
      val quality = QUALITY_BOUND-1
      val item = Item(name = Constants.SULFURAS_HAND, sellIn = 14, quality = quality)
      val items = arrayOf(item)
      val app = GildedRose(items)


      // when
      app.updateQualityIfSellinIsGreaterThan11Or6(item)

      // then
      assertEquals(quality, item.quality)
    }
  }

  internal class TestForUpdateSellin {
    @Test
    fun `when item name is not SULFURAS_HAND, then decrease sellIn value`() {
      // given
      val sellIn = 3
      val item = Item(name = Constants.AGED_BRIE, sellIn = sellIn, quality = Constants.QUALITY_BOUND)
      val items = arrayOf(item)
      val app = GildedRose(items)


      // when
      app.updateSellin(item)

      // then
      assertEquals(sellIn-1, item.sellIn)
    }
  }

  internal class TestForUpdateQualityIfSellInIsLessThanZero {

    @Test
    internal fun `when sellIn value is greater than equal to zero than do nothing` () {
      // given
      val sellIn = 3
      val item = Item(name = Constants.AGED_BRIE, sellIn = sellIn, quality = Constants.QUALITY_BOUND)
      val items = arrayOf(item)
      val app = GildedRose(items)


      // when
      app.updateQualityIfSellInIsLessThanZero(item)

      // then
      assertEquals(sellIn, item.sellIn)
    }

    @Test
    internal fun `when sellIn value is less than 0, and name is AGED_BRIE, than when quality is less than QUALITY_BOUND, then do nothing`() {
      // given
      val sellIn = -1
      val quality = Constants.QUALITY_BOUND-1
      val item = Item(name = Constants.AGED_BRIE, sellIn = sellIn, quality = quality)
      val items = arrayOf(item)
      val app = GildedRose(items)


      // when
      app.updateQualityIfSellInIsLessThanZero(item)

      // then
      assertEquals(quality+1, item.quality)
    }

    @Test
    fun `when sellIn value is less than 0, and name is AGED_BRIE, than when quality is greater than and equal to QUALITY_BOUND`() {
      // given
      val sellIn = -1
      val quality = Constants.QUALITY_BOUND
      val item = Item(name = Constants.AGED_BRIE, sellIn = sellIn, quality = quality)
      val items = arrayOf(item)
      val app = GildedRose(items)


      // when
      app.updateQualityIfSellInIsLessThanZero(item)

      // then
      assertEquals(quality, item.quality)
    }

    @Test
    internal fun `when name is BCKS_PASSES than decrease quality to 0`() {
      // given
      val sellIn = 0
      val quality = Constants.QUALITY_BOUND
      val item = Item(name = Constants.BCKS_PASSES, sellIn = sellIn, quality = quality)
      val items = arrayOf(item)
      val app = GildedRose(items)


      // when
      app.updateQualityBasedOnBCKS_PASSES(item)

      // then
      assertEquals(0, item.quality)
    }

    @Test
    internal fun `when name is not BCKS_PASSES, than if name is not SULFURAS_HAND and quality is greater than 0, then decrease quality`() {
      // given
      val sellIn = 0
      val quality = Constants.QUALITY_BOUND
      val item = Item(name = Constants.AGED_BRIE, sellIn = sellIn, quality = quality)
      val items = arrayOf(item)
      val app = GildedRose(items)


      // when
      app.updateQualityBasedOnBCKS_PASSES(item)

      // then
      assertEquals(quality-1, item.quality)
    }

    @Test
    internal fun `when name is not BCKS_PASSES, than if name is SULFURAS_HAND and quality is greater than 0, then decrease quality`() {
      // given
      val sellIn = 0
      val quality = Constants.QUALITY_BOUND
      val item = Item(name = Constants.SULFURAS_HAND, sellIn = sellIn, quality = quality)
      val items = arrayOf(item)
      val app = GildedRose(items)


      // when
      app.updateQualityBasedOnBCKS_PASSES(item)

      // then
      assertEquals(quality, item.quality)
    }
    @Test
    internal fun `when name is not BCKS_PASSES, than if name is SULFURAS_HAND and quality is less than 0, then decrease quality`() {
      // given
      val sellIn = 0
      val quality = Constants.QUALITY_BOUND
      val item = Item(name = Constants.SULFURAS_HAND, sellIn = sellIn, quality = quality)
      val items = arrayOf(item)
      val app = GildedRose(items)


      // when
      app.updateQualityBasedOnBCKS_PASSES(item)

      // then
      assertEquals(quality, item.quality)
    }

    @Test
    internal fun `when name is not BCKS_PASSES, than if name is not SULFURAS_HAND and quality is less than 0, then decrease quality`() {
      // given
      val sellIn = 0
      val quality = Constants.QUALITY_BOUND
      val item = Item(name = Constants.SULFURAS_HAND, sellIn = sellIn, quality = quality)
      val items = arrayOf(item)
      val app = GildedRose(items)


      // when
      app.updateQualityBasedOnBCKS_PASSES(item)

      // then
      assertEquals(quality, item.quality)
    }
  }
}
