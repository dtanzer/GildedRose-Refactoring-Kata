package com.gildedrose

import com.gildedrose.automation.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class OtherItemsTest {
    val spec = TestAutomation()

    @Test
    fun `elixir updates sell-in after one day`() {
        spec.`given` {
            `items on sale`(
                Elixir(2),
            )
        }.`when` {
            items `stay in stock for` 1.days()
        }.`then` {
            items[0] `has sell-in` 1
        }
    }

    @Test
    fun `elixir updates quality after time passes (before sell-in date)`() {
        spec.`given` {
            `items on sale`(
                Elixir(2, 20),
                Elixir(2, 55),
                Elixir(2, 0),
            )
        }.`when` {
            items `stay in stock for` 2.days()
        }.`then` {
            items[0] `has quality` (20 `updated by` Elixir.`quality degradation before sell-in`.after(2.days()))
            items[1] `has quality` (55 `updated by` Elixir.`quality degradation before sell-in`.after(2.days()))
            items[2] `has quality` 0
        }
    }
    
    @Test
    fun `elixir quality degrades twice as fast after sell-in date`() {
        spec.`given` {
            `items on sale`(
                Elixir(0, 20),
                Elixir(1, 55),
            )
        }.`when` {
            items `stay in stock for` 3.days()
        }.`then` {
            items[0] `has quality` (20 `updated by` Elixir.`quality degradation after sell-in`.after(3.days()))
            items[1] `has quality` ((55 `updated by` Elixir.`quality degradation before sell-in`.after(1.days()))
                                    `updated by` Elixir.`quality degradation after sell-in`.after(2.days()))
        }
    }

}


