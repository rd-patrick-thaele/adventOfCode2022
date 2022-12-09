package adventOfCode

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day08Test : FreeSpec({

    "method tests" - {
        "getIndexesOfHiddenTreesFromRow" {
            // given
            val treeRow = listOf(3, 0, 3, 7, 3, 2, 5, 3)

            // when
            val indexes = Day08().getIndexesOfHiddenTreesFromRow(treeRow)

            // then
            indexes shouldBe setOf(1, 2, 4, 5)
        }

        "readTreeMap" {
            // given
            val treeMap = """
                30373
                25512
                65332
                33549
                35390
            """.trimIndent().lineSequence().toList()

            // when
            val map = Day08().readTreeMap(treeMap)

            // then
            map.horizontalTreeLines.first() shouldBe listOf(3,0,3,7,3)
            map.horizontalTreeLines.last() shouldBe listOf(3,5,3,9,0)

            map.verticalTreeLines.first() shouldBe listOf(3,2,6,3,3)
            map.verticalTreeLines.last() shouldBe listOf(3,2,2,9,0)
        }

        "getScenicScoresForRow" {
            // given
            val treeRow = listOf(3, 0, 3, 7, 3, 2, 5, 3)

            // when
            val rowScores = Day08().getScenicScoresForRow(treeRow)

            // then
            rowScores shouldBe listOf(0, 1, 2, 12, 2, 1, 3, 0)
        }
    }

    "samples" - {
        // given
        val treeMap = """
            30373
            25512
            65332
            33549
            35390
        """.trimIndent().lineSequence().toList()

        "part 1" {
            // where
            val nbOfVisibleTrees = Day08().getNbOfVisibleTrees(treeMap)

            // then
            nbOfVisibleTrees shouldBe 21
        }

        "part 2" {
            // where
            val scenicHighScore = Day08().getHighestScenicScore(treeMap)

            // then
            scenicHighScore shouldBe 8
        }
    }

    "solutions" - {
        // given
        val treeMap = getResourceFileAsStringSequence("day08/input_part1.txt")

        "part 1" {
            // where
            val nbOfVisibleTrees = Day08().getNbOfVisibleTrees(treeMap)

            // then
            nbOfVisibleTrees shouldBe 1787
        }

        "part 2" {
            // where
            val scenicHighScore = Day08().getHighestScenicScore(treeMap)

            // then
            scenicHighScore shouldBe 440640
        }
    }
}) {
}