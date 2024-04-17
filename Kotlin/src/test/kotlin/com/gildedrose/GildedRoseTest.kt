package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTest {
	private val spec = Spec()

	@Test
	fun `quality of sulfuras does not degrade over time`() {
		var app: GildedRose? = null

		spec.`given` {
			val items = listOf(Item("Sulfuras, Hand of Ragnaros", 20, 80))
			app = GildedRose(items)
		}.`when` {
			app!!.updateQuality()
		}.`then` {
			assertEquals(80, app!!.items[0].quality)
		}
	}

}


