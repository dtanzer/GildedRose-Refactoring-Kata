package com.gildedrose

class Spec {
	fun `given` (b: () -> Unit): When {
		b()
		return When()
	}
}

class When() {
	fun `when` (b: () -> Unit): Then {
		b()
		return Then()
	}
}

class Then {
	fun `then` (b: () -> Unit) {
		b()
	}
}