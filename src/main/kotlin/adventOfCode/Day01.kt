package adventOfCode

class Day01 {

    fun getMaxTotalCalories(input: List<String>): Int {
        return getBackpackTotals(input).maxOf { it }
    }

    private fun getBackpackTotals(input: List<String>): List<Int> {
        val backpackTotals = mutableListOf<Int>()
        var backpackTotal = 0

        for (calories in input) {

            if (calories == "") {
                backpackTotals.add(backpackTotal)
                backpackTotal = 0
            } else {
                backpackTotal += calories.toInt()
            }
        }
        backpackTotals.add(backpackTotal)

        return backpackTotals
    }

    fun getTop3CaloriesInTotal(input: List<String>): Int {
        val sortedBackpackTotals = getBackpackTotals(input).sortedDescending()

        return sortedBackpackTotals[0] + sortedBackpackTotals[1] + sortedBackpackTotals[2]
    }
}