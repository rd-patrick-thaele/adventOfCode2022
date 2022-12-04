package adventOfCode

class Day03 {
    fun getSumOfPriosForRucksacks(rucksacks: List<String>): Int {
        return rucksacks.map { getCommonType(it) }
            .map { getTypePrio(it) }
            .sum()
    }

    fun getCommonType(rucksack: String): Char {
        val length = rucksack.length
        val compartment1 = rucksack.substring(0, length/2)
        val compartment2 = rucksack.substring(length/2, length)

        return compartment1.toCharArray().intersect(compartment2.toCharArray().toSet()).first()
    }

    fun getTypePrio(type: Char): Int {
        if (type.isLowerCase()) {
            return type.toInt() - 96
        } else
        {
            return type.toInt() - 38
        }
    }

    fun getSumOfBadgePrios(rucksacks: List<String>): Int {
        var totalPrio = 0

        for (i in 0 until rucksacks.size step 3) {
            var commonTypes = rucksacks[i].toCharArray().intersect(rucksacks[i+1].toCharArray().toSet())
            commonTypes = commonTypes.intersect(rucksacks[i+2].toCharArray().toSet())

            totalPrio += getTypePrio(commonTypes.first())
        }

        return totalPrio
    }

}
