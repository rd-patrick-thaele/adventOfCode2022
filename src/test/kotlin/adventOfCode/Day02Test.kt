package adventOfCode

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day02Test : FreeSpec({
    "method tests" - {
        "create round" {
            // given
            val roundStrategy = "A Y"

            // when
            val round = Round(roundStrategy)

            // then
            round.opponent() shouldBe "A"
            round.me() shouldBe "Y"
        }
    }

    "samples" - {
        val strategyMap = """
                A Y
                B X
                C Z
            """.trimIndent().lineSequence().toList()

        "part1" {
            // when
            val totalScore = Day02().playRockPaperScissors(strategyMap)

            // then
            totalScore shouldBe 15
        }

        "part2" {
            // when
            val totalScore = Day02().playRockPaperScissors2(strategyMap)

            // then
            totalScore shouldBe 12
        }
    }

    "solution" - {
        "part1" {
            // given
            val strategyMap = getResourceFileAsStringSequence("day02/input_part1.txt")

            // when
            val totalScore = Day02().playRockPaperScissors(strategyMap)

            // then
            totalScore shouldBe 13675
        }

        "part1" {
            // given
            val strategyMap = getResourceFileAsStringSequence("day02/input_part1.txt")

            // when
            val totalScore = Day02().playRockPaperScissors2(strategyMap)

            // then
            totalScore shouldBe 14184
        }
    }
}) {
}