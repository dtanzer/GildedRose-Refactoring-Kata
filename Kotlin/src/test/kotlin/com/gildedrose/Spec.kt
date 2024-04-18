package com.gildedrose

import org.junit.jupiter.api.Assertions

class Spec {
	fun `given` (b: Arranger.() -> Unit): When {
		val arranger = Arranger()
		b(arranger)
		return When(arranger.gildedRose, arranger.items)
	}
}

class Arranger {
	lateinit var items: List<TestItem>
	lateinit var gildedRose: GildedRose

	fun `items on sale`(vararg items: TestItem) {
		this.items = listOf(*items)
		this.gildedRose = GildedRose(this.items.map { it -> it.item })
	}
}
class When(val gildedRose: GildedRose, val items: List<TestItem>) {
	fun `when` (b: Acter.() -> Unit): Then {
		val a = Acter(gildedRose)
		b(a)
		return Then(gildedRose, items)
	}
}

class Acter(val gildedRose: GildedRose) {
	val items = this

	infix fun `have been in stock for`(time: TimeUnit) {
		for(i in 1 .. time.days) {
			gildedRose.updateQuality()
		}
	}
}

class Then(val gildedRose: GildedRose, val items: List<TestItem>) {
	fun `then` (b: Asserter.() -> Unit) {
		b(Asserter(gildedRose, items))
	}
}

class Asserter(val gildedRose: GildedRose, val items: List<TestItem>) {
}

open class TestItem(val item: Item) {
	infix fun `has quality`(expected: Int) {
		Assertions.assertEquals(expected, item.quality)
	}
}
class Sulfuras : TestItem(Item("Sulfuras, Hand of Ragnaros", 20, 80))
class BackstagePasses(sellIn: Int, quality: Int) : TestItem(Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality)) {
	companion object {
		val `quality change with more than 10 days left` = 1
		val `quality change with less than 10 days left` = 2
		val `quality change with less than 5 days left` = 3
	}
}

fun Int.days(): TimeUnit {
	return TimeUnit(this)
}

fun Int.after(time: TimeUnit): Int {
	return this*time.days
}

infix fun Int.`updated by`(quantity: Int): Int {
	return this+quantity
}

data class TimeUnit(val days: Int)