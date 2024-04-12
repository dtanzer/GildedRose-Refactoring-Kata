package com.gildedrose

class GildedRose(var items: List<Item>) {

    fun updateQuality() {
        for (i in items.indices) {
			val item = items[i]

            if (item.name == "Sulfuras, Hand of Ragnaros") {
                updateSulfuras(item)
                continue
            }
            if (item.name == "Aged Brie") {
                updateAgedBrie(item)
                continue
            }
            if(item.name == "Backstage passes to a TAFKAL80ETC concert") {
                updateBackstagePasses(item)
                continue
            }

            if (item.name != "Aged Brie" && item.name != "Backstage passes to a TAFKAL80ETC concert") {
                if (item.quality > 0) {
                    if (item.name != "Sulfuras, Hand of Ragnaros") {
                        item.quality = item.quality - 1
                    }
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1

                    if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
                        if (item.sellIn < 11) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1
                            }
                        }

                        if (item.sellIn < 6) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1
                            }
                        }
                    }
                }
            }

            if (item.name != "Sulfuras, Hand of Ragnaros") {
                item.sellIn = item.sellIn - 1
            }

            if (item.sellIn < 0) {
                if (item.name != "Aged Brie") {
                    if (item.name != "Backstage passes to a TAFKAL80ETC concert") {
                        if (item.quality > 0) {
                            if (item.name != "Sulfuras, Hand of Ragnaros") {
                                item.quality = item.quality - 1
                            }
                        }
                    } else {
                        item.quality = item.quality - item.quality
                    }
                } else {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1
                    }
                }
            }
        }
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

