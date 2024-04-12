package com.gildedrose.automation

import com.gildedrose.GildedRose

class Whens(private val gildedRose: GildedRose) {
	val items = this

	infix fun `stay in stock for`(waitTime: TimeUnit) {
		for(i in 1..waitTime.days) {
			gildedRose.updateQuality()
		}
	}
}

data class TimeUnit(
	val days: Int
)

fun Int.days(): TimeUnit {
	return TimeUnit(this)
}
