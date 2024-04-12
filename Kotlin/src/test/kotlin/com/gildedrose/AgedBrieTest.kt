package com.gildedrose

import com.gildedrose.automation.AgedBrie
import com.gildedrose.automation.TestAutomation
import com.gildedrose.automation.days
import org.junit.jupiter.api.Test

internal class AgedBrieTest {
	val spec = TestAutomation()

	@Test
	fun `aged brie updates sell-in after one day`() {
		spec.`given` {
			`items on sale`(
				AgedBrie(2)
			)
		}.`when` {
			items `stay in stock for` 1.days()
		}.`then` {
			items[0] `has sell-in` 1
		}
	}

	@Test
	fun `sell-in of aged brie even goes below zero`() {
		spec.`given` {
			`items on sale`(
				AgedBrie(2)
			)
		}.`when` {
			items `stay in stock for` 5.days()
		}.`then` {
			items[0] `has sell-in` -3
		}
	}

	@Test
	fun `quality of aged brie increases before sell-in`() {
		spec.`given` {
			`items on sale`(
				AgedBrie(2, 20)
			)
		}.`when` {
			items `stay in stock for` 1.days()
		}.`then` {
			items[0] `has quality` 21
		}
	}
	
	@Test
	fun `quality of aged brie increases twice as fast after sell-in`() {
		spec.`given` {
			`items on sale`(
				AgedBrie(0, 20)
			)
		}.`when` {
			items `stay in stock for` 3.days()
		}.`then` {
			items[0] `has quality` 26
		}
	}

	@Test
	fun `quality of aged brie does not increase beyond 50`() {
		spec.`given` {
			`items on sale`(
				AgedBrie(0, 49)
			)
		}.`when` {
			items `stay in stock for` 3.days()
		}.`then` {
			items[0] `has quality` 50
		}
	}

}
