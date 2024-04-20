package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTest {
	val spec = Spec()

	@Test
	fun `quality of sulfuras does not degrade over time (2 days)`() {
		spec.`given` {
			`items on sale`(Sulfuras())
		}.`when` {
			`items on sale` `have been in stock for` 2.days()
		}.`then` {
			items[0] `has quality` Sulfuras.`initial quality`
		}
	}

}


