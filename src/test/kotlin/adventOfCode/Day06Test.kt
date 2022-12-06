package adventOfCode

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day06Test : FreeSpec({
    "samples" - {
        "part 1" {
            // given
            val signal = "mjqjpqmgbljsphdztnvjfqwrcgsmlb"

            // when
            val startOfPacketMarkerIndex = Day06().getStartOfPacketMarkerIndex(signal)

            // then
            startOfPacketMarkerIndex shouldBe 7
        }

        "part 2" {
            // given
            val signal = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"

            // when
            val startOfPacketMarkerIndex = Day06().getStartOfMessageMarkerIndex(signal)

            // then
            startOfPacketMarkerIndex shouldBe 29
        }
    }

    "solutions" - {
        // given
        val signal = getResourceFileAsStringSequence("day06/input_part1.txt").first()

        "part 1" {
                        // when
            val startOfPacketMarkerIndex = Day06().getStartOfPacketMarkerIndex(signal)

            // then
            startOfPacketMarkerIndex shouldBe 1855
        }

        "part 2" {
            // when
            val startOfPacketMarkerIndex = Day06().getStartOfMessageMarkerIndex(signal)

            // then
            startOfPacketMarkerIndex shouldBe 3256
        }
    }
}) {
}