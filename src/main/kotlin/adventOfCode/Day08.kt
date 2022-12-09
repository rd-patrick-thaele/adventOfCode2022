package adventOfCode

class Day08 {
    fun getNbOfVisibleTrees(treeMap: List<String>): Int {
        val trees = readTreeMap(treeMap)

        val hiddenTrees = getHiddenTreePositions(trees)
        val totalNbOfTrees = treeMap.size * treeMap.first().length

        return totalNbOfTrees - hiddenTrees.size
    }

    fun readTreeMap(treeMap: List<String>): TreeLandscape {
        val horizontalTreeLines = mutableListOf<List<Int>>()
        val verticalTreeLines = List<MutableList<Int>>(treeMap.first().length) { mutableListOf() }

        for (treeLine in treeMap) {

            val horizontalTreeLine = mutableListOf<Int>()
            horizontalTreeLines.add(horizontalTreeLine)

            for ((j, treeSize) in treeLine.toCharArray().withIndex()) {
                val numericTreeSize = treeSize.digitToInt()
                horizontalTreeLine.add(numericTreeSize)
                verticalTreeLines[j].add(numericTreeSize)
            }
        }

        return TreeLandscape(horizontalTreeLines, verticalTreeLines)
    }

    private fun getHiddenTreePositions(trees: TreeLandscape): Set<Pair<Int, Int>> {
        val hiddenHorizontalTrees = trees.horizontalTreeLines.map { getIndexesOfHiddenTreesFromRow(it) }
            .mapIndexed { index, hiddenPositions -> hiddenPositions.map { Pair(it, index) } }
            .flatten()
            .toSet()

        val hiddenVerticalTrees = trees.verticalTreeLines.map { getIndexesOfHiddenTreesFromRow(it) }
            .mapIndexed { index, hiddenPositions -> hiddenPositions.map { Pair(index, it) } }
            .flatten()
            .toSet()

        return hiddenHorizontalTrees.intersect(hiddenVerticalTrees)
    }

    fun getIndexesOfHiddenTreesFromRow(treeRow: List<Int>): Set<Int> {
        val hiddenTreesFromLeft = getHiddenTreesFromLeftToRight(treeRow)
        val hiddenTreesFromRight = getHiddenTreesFromLeftToRight(treeRow.asReversed())
            .map { treeRow.size - it - 1 }
            .toSet()

        return hiddenTreesFromLeft.intersect(hiddenTreesFromRight)
    }

    private fun getHiddenTreesFromLeftToRight(treeRow: List<Int>): Set<Int> {
        val hiddenTreesFromLeft = mutableSetOf<Int>()
        var biggestTree = treeRow.first()
        for (i: Int in 1 until treeRow.size - 1) {
            val currentTree = treeRow[i]
            if (currentTree <= biggestTree) hiddenTreesFromLeft.add(i)
            else biggestTree = currentTree
        }
        return hiddenTreesFromLeft
    }

    fun getHighestScenicScore(treeMap: List<String>): Int {
        val trees = readTreeMap(treeMap)

        val horizontalScores = trees.horizontalTreeLines.map { getScenicScoresForRow(it) }
        val verticalScores = trees.verticalTreeLines.map { getScenicScoresForRow(it) }

        val scenicScores = List<MutableList<Int>>(treeMap.size) { mutableListOf() }

        for (y in treeMap.indices) {
            for (x in treeMap.first().indices) {
                val scenicScore = horizontalScores[y][x] * verticalScores[x][y]
                scenicScores[y].add(scenicScore)
            }
        }

        return scenicScores.maxOf { it.max() }
    }

    fun getScenicScoresForRow(treeRow: List<Int>): List<Int> {
        val scores = mutableListOf<Int>()

        for ((index, tree) in treeRow.withIndex()) {
            if (index == 0 || index == treeRow.size - 1) {
                scores.add(0)
                continue
            }

            var leftCount = 1
            var leftIndex = index - 1
            while (leftIndex > 0 && tree > treeRow[leftIndex]) {
                leftCount ++
                leftIndex --
            }

            var rightCount = 1
            var rightIndex = index + 1
            while (rightIndex < treeRow.size - 1 && tree > treeRow[rightIndex]) {
                rightCount ++
                rightIndex ++
            }

            scores.add(leftCount * rightCount)
        }

        return scores
    }

}

data class TreeLandscape(val horizontalTreeLines: List<List<Int>>, val verticalTreeLines: List<List<Int>>)
