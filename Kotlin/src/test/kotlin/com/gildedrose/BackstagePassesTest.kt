package com.gildedrose

import com.gildedrose.automation.*
import org.junit.jupiter.api.Test

internal class BackstagePassesTest {
	val spec = TestAutomation()

	@Test
	fun `backstage passes updates sell-in after one day`() {
		spec.`given` {
			`items on sale`(
				BackstagePasses(2),
				BackstagePasses(0),
		)
		}.`when` {
			items `stay in stock for` 1.days()
		}.`then` {
			items[0] `has sell-in` 1
			items[1] `has sell-in` -1
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
			items `stay in stock for` 2.days()
		}.`then` {
			items[0] `has quality` (20 `updated by` BackstagePasses.`quality change with more than 10 days left`.after(2.days()))
			items[1] `has quality` (20 `updated by` BackstagePasses.`quality change with less than 10 days left`.after(2.days()))
			items[2] `has quality` (20 `updated by` BackstagePasses.`quality change with less than 5 days left`.after(2.days()))
		}
	}
	
	@Test
	fun `quality of backstage passes increases at different rates when crossing a rate border`() {
		spec.`given` {
			`items on sale`(
				BackstagePasses(11, 20),
				BackstagePasses(6, 20),
			)
		}.`when` {
			items `stay in stock for` 2.days()
		}.`then` {
			items[0] `has quality` ((20 `updated by` BackstagePasses.`quality change with more than 10 days left`.after(1.days()))
										   `updated by` BackstagePasses.`quality change with less than 10 days left`.after(1.days()))
			items[1] `has quality` ((20 `updated by` BackstagePasses.`quality change with less than 10 days left`.after(1.days()))
										`updated by` BackstagePasses.`quality change with less than 5 days left`.after(1.days()))
		}
	}

	@Test
	fun `quality of backstage passes goes to zero after sell-in`() {
		spec.`given` {
			`items on sale`(
				BackstagePasses(1, 20)
			)
		}.`when` {
			items `stay in stock for` 3.days()
		}.`then` {
			items[0] `has quality` 0
		}
	}

	@Test
	fun `quality of backstage passes does not increase beyond 50`() {
		spec.`given` {
			`items on sale`(
				BackstagePasses(5, 49)
			)
		}.`when` {
			items `stay in stock for` 3.days()
		}.`then` {
			items[0] `has quality` 50
		}
	}
}
