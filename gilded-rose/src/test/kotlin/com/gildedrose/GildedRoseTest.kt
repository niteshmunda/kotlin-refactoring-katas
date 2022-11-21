package com.gildedrose

import com.gildedrose.Combinations.dotProduct
import com.gildedrose.Constants.AGED_BRIE
import com.gildedrose.Constants.BACKSTAGE_PASSES
import com.gildedrose.Constants.SULFURAS_HAND_OF_RAGNAROS
import org.approvaltests.Approvals
import org.approvaltests.combinations.CombinationApprovals
import org.approvaltests.namer.NamerFactory
import org.approvaltests.reporters.QuietReporter
import org.approvaltests.reporters.UseReporter
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.lambda.functions.Function1

class GildedRoseTest {

  private val interactions = Interactions()
  companion object {
    @JvmStatic
    fun combinationApprovalParameters(): List<Triple<String, Int, Int>> {
      val allNames = allNames()
      val allSellInRange = allSellInRange()
      val allQualityRange = allQualityRange()

      val allNamesDotAllSellInRange = dotProduct(
        items1 = allNames.toTypedArray(),
        items2 = allSellInRange.toTypedArray(),
        combine = { name, sellIn -> name to sellIn }
      )

      return dotProduct(
        items1 = allNamesDotAllSellInRange.toTypedArray(),
        items2 = allQualityRange.toTypedArray(),
        combine = { (name, sellIn), quality -> Triple(name, sellIn, quality) }
      ).distinct()
    }

    private fun allQualityRange(): List<Int> {
      return listOf(-1, 0, 2, 6, 10, 11, 49, 50, 65)
    }

    private fun allSellInRange(): List<Int> {
      return listOf(0, 12, 50)
    }

    private fun allNames(): List<String> {
      return listOf(AGED_BRIE, SULFURAS_HAND_OF_RAGNAROS, BACKSTAGE_PASSES, "foo")
    }
  }

  @Test
  fun foo() {
    val items = arrayOf(Item("foo", 0, 0))
    val app = GildedRose(items)
    app.updateQuality(items)
    assertEquals("foo", app.items[0].name)
  }

  @ParameterizedTest
  @MethodSource(value = ["combinationApprovalParameters"])
  @UseReporter(QuietReporter::class)
  internal fun `combination approval test for regression protection`(
    input: Triple<String, Int, Int>
  ) {
    // given
    val (name, sellin, quality) = input
    val testName = fileName(input)

    // when
    val app = GildedRose(arrayOf(Item("foo", 0, 0)))// temporary for just object creation
    val output = app.fwdUpdateInformation(Item(name,  sellin, quality))

    interactions.append("result(out)", output.toString())
    // then

//    CombinationApprovals.verifyAllCombinations(
//      Function1 {
//        app.fwdUpdateInformation(Item(name,  sellin, quality))
//      }
//    )

    NamerFactory.withParameters(testName).use {
      Approvals.verify(textWriter(interactions.toString()), NamerWithoutBuildInfo())
    }
  }

  private fun fileName(input: Triple<String, Int, Int>): String {
    val (name, sellin, quality) = input
    return "-$name-$sellin-${quality}"
  }
}
