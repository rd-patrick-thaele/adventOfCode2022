package adventOfCode

class Day04 {
    fun getNbOfTotalRangeOverlaps(pairAssignments: List<String>): Int {
        return pairAssignments
            .map { readLine(it) }
            .map { doRangesFullyConainEachOther(it) }
            .count { it }
    }

    fun readLine(pairAssignment: String): Pair<IntRange, IntRange> {
        val regex = "([\\d]+)-([\\d]+),([\\d]+)-([\\d]+)".toRegex()
        val (rangeAFrom, rangeATo, rangeBFrom, rangeBTo) = regex.find(pairAssignment)!!.destructured

        return Pair(
            rangeAFrom.toInt()..rangeATo.toInt(),
            rangeBFrom.toInt()..rangeBTo.toInt()
        )
    }

    fun doRangesFullyConainEachOther(ranges: Pair<IntRange, IntRange>): Boolean {
        val sectionSet1 = ranges.first.toSet()
        val sectionSet2 = ranges.second.toSet()
        val combined = sectionSet1 + sectionSet2

        return combined == sectionSet1 || combined == sectionSet2
    }

    fun getNbOfRangeOverlaps(pairAssignments: List<String>): Int {
        return pairAssignments.map { readLine(it) }
            .map { doRangesIntersect(it) }
            .count { it }
    }

    fun doRangesIntersect(ranges: Pair<IntRange, IntRange>): Boolean {
        val sectionSet1 = ranges.first.toSet()
        val sectionSet2 = ranges.second.toSet()

        return sectionSet1.intersect(sectionSet2).isNotEmpty()
    }
}
