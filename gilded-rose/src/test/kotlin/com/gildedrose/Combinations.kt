package com.gildedrose

object Combinations {
  fun <T, U, O> dotProduct(
    items1: Array<T>,
    items2: Array<U>,
    combine: (T, U) -> O,
  ): List<O> {
    val result = mutableListOf<O>()
    items1.forEach { item1 ->
      items2.forEach { item2 ->
        result.add(combine.invoke(item1, item2))
      }
    }
    return result.toList()
  }
}
