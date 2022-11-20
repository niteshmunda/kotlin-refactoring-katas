package com.gildedrose

import org.approvaltests.Approvals
import org.approvaltests.core.Options
import org.approvaltests.writers.ApprovalTextWriter

class Interactions {
  private val buffer = StringBuffer()

  private val all: String get() = buffer.toString().ifBlank { "- No interactions -" }

  internal fun append(tag: String, interaction: String): Interactions {
    buffer
      .append("$tag: ")
      .append(interaction)
      .append(System.lineSeparator())
    return this
  }

  override fun toString(): String {
    return all
  }
}

fun textWriter(content: String): ApprovalTextWriter {
  val extensionOptions = Options().forFile().withExtension("txt")
  return ApprovalTextWriter(content, extensionOptions)
}
