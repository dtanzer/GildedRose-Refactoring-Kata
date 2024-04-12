package com.gildedrose.automation

import com.gildedrose.Item
import org.junit.jupiter.api.Assertions

class Givens {
	var testItems: List<TestItem> = listOf()
	val items: List<Item> get() { return testItems.map { it.item } }

	fun `items on sale`(vararg items: TestItem) {
		this.testItems = listOf(*items)
	}

}

open class TestItem(val item: Item) {
	inline infix fun `has sell-in`(expected: Int) {
		Assertions.assertEquals(expected, item.sellIn)
	}
	inline infix fun `has quality`(expected: Int) {
		Assertions.assertEquals(expected, item.quality)
	}
}

class Elixir(sellIn: Int = Elixir.sellIn) : TestItem(Item("Elixir of the Mongoose", sellIn, quality)) {
	companion object {
		val sellIn = 2
		val quality = 20
	}
}

class Sulfuras(sellIn: Int) : TestItem(Item("Sulfuras, Hand of Ragnaros", sellIn, quality)) {
	companion object {
		val quality = 80
	}
}
