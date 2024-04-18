package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTest {
	private val spec = Spec()

	@Test
	fun `quality of sulfuras does not degrade over time`() {
		spec.`given` {
			`items on sale`(Sulfuras())
		}.`when` {
			items `have been in stock for` 2.days()
		}.`then` {
			items[0] `has quality` 80
		}
	}

	@Test
	fun `quality of backstage passes increases before sell-in`() {
		spec.`given` {
			`items on sale`(
				BackstagePasses(20, 20),
				BackstagePasses(10, 20),
				BackstagePasses(5, 20),
			)
		}.`when` {
			items `have been in stock for` 2.days()
		}.`then` {
			items[0] `has quality` (20 `updated by` BackstagePasses.`quality change with more than 10 days left`.after(2.days()))
			items[1] `has quality` (20 `updated by` BackstagePasses.`quality change with less than 10 days left`.after(2.days()))
			items[2] `has quality` (20 `updated by` BackstagePasses.`quality change with less than 5 days left`.after(2.days()))
		}
	}
}


