package com.gildedrose

import com.gildedrose.automation.Elixir
import com.gildedrose.automation.TestAutomation
import com.gildedrose.automation.days
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTest {
    val spec = TestAutomation()

    @Test
    fun `elixir degrades in quality before sell-in`() {
        spec.`given` {
            `items on sale`(
                Elixir(2)
            )
        }.`when` {
            items `stay in stock for` 1.days()
        }.`then` {
            items[0] `has sell-in` 1
        }
    }
}


