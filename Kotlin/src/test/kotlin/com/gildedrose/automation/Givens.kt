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

class Elixir(sellIn: Int = Elixir.sellIn, quality: Int = Elixir.quality) : TestItem(Item("Elixir of the Mongoose", sellIn, quality)) {
	companion object {
		val `quality degradation before sell-in` = -1
		val `quality degradation after sell-in` = -2
val sellIn = 2
		val quality = 20
	}
}

class Sulfuras(sellIn: Int) : TestItem(Item("Sulfuras, Hand of Ragnaros", sellIn, quality)) {
	companion object {
		val quality = 80
	}
}

class AgedBrie(sellIn: Int = AgedBrie.sellIn, quality: Int = AgedBrie.quality) : TestItem(Item("Aged Brie", sellIn, quality)) {
	companion object {
		val sellIn = 2
		val quality = 20
	}
}

class BackstagePasses(sellIn: Int = BackstagePasses.sellIn, quality: Int = BackstagePasses.quality) : TestItem(Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality)) {
	companion object {
		val `quality change with more than 10 days left` = 1
		val `quality change with less than 10 days left` = 2
		val `quality change with less than 5 days left` = 3
		val sellIn = 2
		val quality = 20
	}
}

infix fun Int.`updated by`(value: Int): Int {
	return this+value
}

infix fun Int.after(waitTime: TimeUnit): Int {
	return this*waitTime.days
}
