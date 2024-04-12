package com.gildedrose

class GildedRose(var items: List<Item>) {

    fun updateQuality() {
        for (i in items.indices) {
			val item = items[i]

            when(item.name) {
                "Sulfuras, Hand of Ragnaros" -> updateSulfuras(item)
                "Aged Brie" -> updateAgedBrie(item)
                "Backstage passes to a TAFKAL80ETC concert" -> updateBackstagePasses(item)
                "Conjured Mana Cake" -> updateConjured(item)
                else -> updateOtherItem(item)
            }
        }
    }

	private fun updateConjured(item: Item) {
        degradeQuality(item, 2, 4)
        updateSellIn(item)
    }

	private fun updateOtherItem(item: Item) {
        degradeQuality(item, 1, 2)
        updateSellIn(item)
    }

	private fun updateBackstagePasses(backstagePasses: Item) {
        if (backstagePasses.sellIn <= 0) {
            backstagePasses.quality = 0
        } else if (backstagePasses.sellIn < 6) {
            increaseQuality(backstagePasses, 3)
        } else if (backstagePasses.sellIn < 11) {
            increaseQuality(backstagePasses, 2)
        } else {
            increaseQuality(backstagePasses, 1)
        }

        updateSellIn(backstagePasses)
    }

	private fun updateAgedBrie(agedBrie: Item) {
        if (agedBrie.sellIn <= 0) {
            increaseQuality(agedBrie, 2)
        } else {
            increaseQuality(agedBrie, 1)
        }

        updateSellIn(agedBrie)
    }

	private fun increaseQuality(item: Item, qualityIncrease: Int) {
		item.quality = (item.quality + qualityIncrease).coerceAtMost(50)
	}

	private fun updateSulfuras(sulfuras: Item) {
        //Nothing to be done here
	}

    private fun updateSellIn(item: Item) {
        item.sellIn = item.sellIn - 1
    }
    
    private fun degradeQuality(item: Item, beforeSellIn: Int, afterSellIn: Int) {
        if (item.sellIn <= 0) {
            item.quality = (item.quality - afterSellIn).coerceAtLeast(0)
        } else {
            item.quality = (item.quality - beforeSellIn).coerceAtLeast(0)
        }
    }

}

