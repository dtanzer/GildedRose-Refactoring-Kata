package com.gildedrose.automation

import com.gildedrose.GildedRose

class TestAutomation {
	fun given(setup: Givens.() -> Unit): When {
		val givens = Givens()
		setup(givens)
		return When(GildedRose(givens.items))
	}
}

class When(val gildedRose: GildedRose) {
	fun `when`(execute: Whens.() -> Unit): Then {
		val whens = Whens(gildedRose)
		execute(whens)
		return Then(gildedRose)
	}
}

class Then(val gildedRose: Any?) {
	fun then(verify: Thens.() -> Unit) {
		val thens = Thens()
		verify(thens)
	}
}

