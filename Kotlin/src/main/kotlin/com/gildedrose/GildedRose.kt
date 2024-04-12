package com.gildedrose

class GildedRose(var items: List<Item>) {

    fun updateQuality() {
        for (i in items.indices) {
			val item = items[i]

            when(item.name) {
                "Sulfuras, Hand of Ragnaros" -> updateSulfuras(item)
                "Aged Brie" -> updateAgedBrie(item)
                "Backstage passes to a TAFKAL80ETC concert" -> updateBackstagePasses(item)
                else -> updateOtherItem(item)
            }
        }
    }

	private fun updateOtherItem(item: Item) {
        if (item.sellIn <= 0) {
            item.quality = (item.quality - 2).coerceAtLeast(0)
        } else {
            item.quality = (item.quality - 1).coerceAtLeast(0)
        }
        
        item.sellIn = item.sellIn - 1
    }

	private fun updateBackstagePasses(backstagePasses: Item) {
        if (backstagePasses.sellIn <= 0) {
            backstagePasses.quality = 0
        } else if (backstagePasses.sellIn < 6) {
            backstagePasses.quality = (backstagePasses.quality + 3).coerceAtMost(50)
        } else if (backstagePasses.sellIn < 11) {
            backstagePasses.quality = (backstagePasses.quality + 2).coerceAtMost(50)
        } else {
            backstagePasses.quality = (backstagePasses.quality + 1).coerceAtMost(50)
        }

        backstagePasses.sellIn = backstagePasses.sellIn - 1
    }

	private fun updateAgedBrie(agedBrie: Item) {
        agedBrie.sellIn = agedBrie.sellIn - 1
    
        if (agedBrie.sellIn < 0) {
            agedBrie.quality = (agedBrie.quality + 2).coerceAtMost(50)
        } else {
            agedBrie.quality = (agedBrie.quality + 1).coerceAtMost(50)
        }
    }

	private fun updateSulfuras(sulfuras: Item) {
        //Nothing to be done here
	}

}

