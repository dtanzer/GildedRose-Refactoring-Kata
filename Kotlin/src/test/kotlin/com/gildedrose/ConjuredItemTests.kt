package com.gildedrose

import com.gildedrose.automation.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ConjuredItemsTest {
	val spec = TestAutomation()

	@Test
	fun `conjured updates sell-in after one day`() {
		spec.`given` {
			`items on sale`(
				Conjured(2),
			)
		}.`when` {
			items `stay in stock for` 1.days()
		}.`then` {
			items[0] `has sell-in` 1
		}
	}

	@Test
	fun `conjured updates quality after time passes (before sell-in date)`() {
		spec.`given` {
			`items on sale`(
				Conjured(2, 20),
				Conjured(2, 55),
				Conjured(2, 0),
			)
		}.`when` {
			items `stay in stock for` 2.days()
		}.`then` {
			items[0] `has quality` (20 `updated by` Conjured.`quality degradation before sell-in`.after(2.days()))
			items[1] `has quality` (55 `updated by` Conjured.`quality degradation before sell-in`.after(2.days()))
			items[2] `has quality` 0
		}
	}
	
	@Test
	fun `conjured quality degrades twice as fast after sell-in date`() {
		spec.`given` {
			`items on sale`(
				Conjured(0, 20),
				Conjured(1, 55),
			)
		}.`when` {
			items `stay in stock for` 3.days()
		}.`then` {
			items[0] `has quality` (20 `updated by` Conjured.`quality degradation after sell-in`.after(3.days()))
			items[1] `has quality` ((55 `updated by` Conjured.`quality degradation before sell-in`.after(1.days()))
									`updated by` Conjured.`quality degradation after sell-in`.after(2.days()))
		}
	}

}
