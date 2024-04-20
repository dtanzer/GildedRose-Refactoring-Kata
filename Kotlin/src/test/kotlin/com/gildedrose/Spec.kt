package com.gildedrose

import org.junit.jupiter.api.Assertions

class Spec {
	fun given(b: Arranger.() -> Unit): When {
		val arranger = Arranger()
		b(arranger)
		
		return When(arranger.gildedRose, arranger.items)
	}
}

class Arranger {
	lateinit var gildedRose: GildedRose
	lateinit var items: List<TestItem>
	
	fun `items on sale`(vararg item: TestItem) {
		items = listOf(*item)
		gildedRose = GildedRose(items.map {it.item})
	}
}

open class TestItem(val item: Item) {
	infix fun `has quality`(expected: Int) {
		Assertions.assertEquals(expected, item.quality)
	}
}
class Sulfuras : TestItem(Item("Sulfuras, Hand of Ragnaros", 20, `initial quality`)) {
	companion object {
		val `initial quality` = 80
	}
}

class When(val gildedRose: GildedRose, val items: List<TestItem>) {
	fun `when`(b: Acter.() -> Unit): Then {
		b(Acter(gildedRose, items))
		return Then(gildedRose, items)
	}
}

class Acter(val gildedRose: GildedRose, val items: List<TestItem>) {
	val `items on sale` = this
	infix fun `have been in stock for`(time: TimeUnit) {
		for(i in 1..time.days) {
			gildedRose.updateQuality()
		}
	}
}

fun Int.days(): TimeUnit {
	return TimeUnit(this)
}

data class TimeUnit(val days: Int)

class Then(val gildedRose: GildedRose, val items: List<TestItem>) {
	fun then(b: Asserter.() -> Unit) {
		b(Asserter(gildedRose, items))
	}
}

class Asserter(val gildedRose: GildedRose, val items: List<TestItem>) {}