package com.gildedrose.automation

import com.gildedrose.GildedRose

class TestAutomation {
	fun given(setup: Givens.() -> Unit): When {
		val givens = Givens()
		setup(givens)
		return When(GildedRose(givens.items), givens.testItems)
	}
}

class When(val gildedRose: GildedRose, val testItems: List<TestItem>) {
	fun `when`(execute: Whens.() -> Unit): Then {
		val whens = Whens(gildedRose)
		execute(whens)
		return Then(gildedRose, testItems)
	}
}

class Then(val gildedRose: GildedRose, val testItems: List<TestItem>) {
	fun then(verify: Thens.() -> Unit) {
		val thens = Thens(testItems)
		verify(thens)
	}
}

