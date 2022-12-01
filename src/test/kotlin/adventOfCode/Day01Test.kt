package adventOfCode

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day01Test : FreeSpec({

    "samples" - {

        // given
        val input = """
                1000
                2000
                3000

                4000

                5000
                6000

                7000
                8000
                9000

                10000
            """.trimIndent().lineSequence().toList()

        "part 1" {
            // when
            val maxTotalCalories = Day01().getMaxTotalCalories(input)

            // then
            maxTotalCalories shouldBe 24000
        }

        "part 2" {
            // when
            val top3InTotal = Day01().getTop3CaloriesInTotal(input)

            // then
            top3InTotal shouldBe 45000
        }
    }

    "solution" - {
        "part 1" {
            // given
            val input = getResourceFileAsStringSequence("day01/input_part1.txt")

            // when
            val maxTotalCalories = Day01().getMaxTotalCalories(input)

            // then
            maxTotalCalories shouldBe 69528
        }

        "part 2" {
            // given
            val input = getResourceFileAsStringSequence("day01/input_part1.txt")

            // when
            val top3InTotal = Day01().getTop3CaloriesInTotal(input)

            // then
            top3InTotal shouldBe 206152
        }
    }
}) {
}