package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

	@Test
	fun `quality of sulfuras does not degrade over time`() {
		val items = listOf(Item("Sulfuras, Hand of Ragnaros", 20, 80))
		val app = GildedRose(items)

		app.updateQuality()

		assertEquals(80, app.items[0].quality)

	}

}


