package adventOfCode

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day04Test : FreeSpec({

    "method tests" - {
        "readLine" {
            // given
            val pairAssignment = "2-4,6-8"

            // when
            val ranges = Day04().readLine(pairAssignment)

            // then
            ranges.first shouldBe 2..4
            ranges.second shouldBe 6..8
        }

        "doRangesFullyContainEachOther" - {
            "separate" {
                // given
                val ranges = Pair(2..4, 6..8)

                // when
                val fullyContained = Day04().doRangesFullyConainEachOther(ranges)

                // then
                fullyContained shouldBe false
            }

            "separate at boundary" {
                // given
                val ranges = Pair(2..3, 4..5)

                // when
                val fullyContained = Day04().doRangesFullyConainEachOther(ranges)

                // then
                fullyContained shouldBe false
            }

            "partly overlap" {
                // given
                val ranges = Pair(5..7, 7..9)

                // when
                val fullyContained = Day04().doRangesFullyConainEachOther(ranges)

                // then
                fullyContained shouldBe false
            }

            "fully contained" {
                // given
                val ranges = Pair(2..8, 3..7)

                // when
                val fullyContained = Day04().doRangesFullyConainEachOther(ranges)

                // then
                fullyContained shouldBe true
            }

            "fully contained, single sector" {
                // given
                val ranges = Pair(6..6, 4..6)

                // when
                val fullyContained = Day04().doRangesFullyConainEachOther(ranges)

                // then
                fullyContained shouldBe true
            }
        }

        "doRangesIntersect" - {
            "separate" {
                // given
                val ranges = Pair(2..4, 6..8)

                // when
                val fullyContained = Day04().doRangesIntersect(ranges)

                // then
                fullyContained shouldBe false
            }

            "separate at boundary" {
                // given
                val ranges = Pair(2..3, 4..5)

                // when
                val fullyContained = Day04().doRangesIntersect(ranges)

                // then
                fullyContained shouldBe false
            }

            "partly overlap" {
                // given
                val ranges = Pair(5..7, 7..9)

                // when
                val fullyContained = Day04().doRangesIntersect(ranges)

                // then
                fullyContained shouldBe true
            }

            "fully contained" {
                // given
                val ranges = Pair(2..8, 3..7)

                // when
                val fullyContained = Day04().doRangesIntersect(ranges)

                // then
                fullyContained shouldBe true
            }

            "fully contained, single sector" {
                // given
                val ranges = Pair(6..6, 4..6)

                // when
                val fullyContained = Day04().doRangesIntersect(ranges)

                // then
                fullyContained shouldBe true
            }
        }
    }

    "samples" - {
        // given
        val pairAssignments = """
                2-4,6-8
                2-3,4-5
                5-7,7-9
                2-8,3-7
                6-6,4-6
                2-6,4-8
            """.trimIndent().lineSequence().toList()

        "part 1" {
            // when
            val totalRangeOverlaps = Day04().getNbOfTotalRangeOverlaps(pairAssignments)

            // then
            totalRangeOverlaps shouldBe 2
        }

        "part 2" {
            // when
            val rangeOverlaps = Day04().getNbOfRangeOverlaps(pairAssignments)

            // then
            rangeOverlaps shouldBe 4
        }
    }

    "solutions" - {
        // given
        val pairAssignments = getResourceFileAsStringSequence("day04/input_part1.txt")

        "part 1" {
            // when
            val totalRangeOverlaps = Day04().getNbOfTotalRangeOverlaps(pairAssignments)

            // then
            totalRangeOverlaps shouldBe 573
        }

        "part 2" {
            // when
            val rangeOverlaps = Day04().getNbOfRangeOverlaps(pairAssignments)

            // then
            rangeOverlaps shouldBe 867
        }
    }
}) {
}