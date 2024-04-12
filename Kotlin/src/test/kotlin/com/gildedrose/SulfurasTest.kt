package com.gildedrose

import com.gildedrose.automation.Sulfuras
import com.gildedrose.automation.TestAutomation
import com.gildedrose.automation.days
import org.junit.jupiter.api.Test

internal class SulfurasTest {
	val spec = TestAutomation()

	@Test
	fun `sulfuras does not degrade in quality`() {
		spec.`given` {
			`items on sale`(
				Sulfuras(10), Sulfuras(0), Sulfuras(-1)
			)
		}.`when` {
			items `stay in stock for` 1.days()
		}.`then` {
			items[0] `has quality` Sulfuras.quality
			items[1] `has quality` Sulfuras.quality
			items[2] `has quality` Sulfuras.quality
		}
	}

	@Test
	fun `sulfuras does not have to be sold`() {
		spec.`given` {
			`items on sale`(
				Sulfuras(10), Sulfuras(0), Sulfuras(-1)
			)
		}.`when` {
			items `stay in stock for` 1.days()
		}.`then` {
			items[0] `has sell-in` 10
			items[1] `has sell-in` 0
			items[2] `has sell-in` -1
		}
	}
}
