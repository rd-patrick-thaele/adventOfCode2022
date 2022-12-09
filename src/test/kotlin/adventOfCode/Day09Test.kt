package adventOfCode

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day09Test : FreeSpec({

    "method tests" - {
        "readMovements" {
            // given
            val movements = """
                R 4
                U 4
                L 13
                D 1
            """.trimIndent().lineSequence().toList()

            // when
            val movementList = Day09().readMovements(movements)

            // then
            movementList shouldBe listOf(
                Movement(Direction.R, 4),
                Movement(Direction.U, 4),
                Movement(Direction.L, 13),
                Movement(Direction.D, 1)
            )
        }

        "tail same position - head up" {
            // given
            val rope = Rope()
            val movement = Movement(Direction.U, 1)

            // when
            rope.moveHead(movement)

            // then
            rope.headPosition shouldBe Pair(0, 1)
            rope.tailPosition shouldBe Pair(0, 0)
        }

        "tail same position - head down" {
            // given
            val rope = Rope()
            val movement = Movement(Direction.D, 1)

            // when
            rope.moveHead(movement)

            // then
            rope.headPosition shouldBe Pair(0, -1)
            rope.tailPosition shouldBe Pair(0, 0)
        }

        "tail same position - head left" {
            // given
            val rope = Rope()
            val movement = Movement(Direction.L, 1)

            // when
            rope.moveHead(movement)

            // then
            rope.headPosition shouldBe Pair(-1, 0)
            rope.tailPosition shouldBe Pair(0, 0)
        }

        "tail same position - head right" {
            // given
            val rope = Rope()
            val movement = Movement(Direction.R, 1)

            // when
            rope.moveHead(movement)

            // then
            rope.headPosition shouldBe Pair(1, 0)
            rope.tailPosition shouldBe Pair(0, 0)
        }

        "tail same position - head up 2 steps" {
            // given
            val rope = Rope()
            val movement = Movement(Direction.U, 2)

            // when
            rope.moveHead(movement)

            // then
            rope.headPosition shouldBe Pair(0, 2)
            rope.tailPosition shouldBe Pair(0, 1)
        }

        "tail same position - head down 2 steps" {
            // given
            val rope = Rope()
            val movement = Movement(Direction.D, 2)

            // when
            rope.moveHead(movement)

            // then
            rope.headPosition shouldBe Pair(0, -2)
            rope.tailPosition shouldBe Pair(0, -1)
        }

        "tail same position - head left 2 steps" {
            // given
            val rope = Rope()
            val movement = Movement(Direction.L, 2)

            // when
            rope.moveHead(movement)

            // then
            rope.headPosition shouldBe Pair(-2, 0)
            rope.tailPosition shouldBe Pair(-1, 0)
        }

        "tail same position - head right 2 steps" {
            // given
            val rope = Rope()
            val movement = Movement(Direction.R, 2)

            // when
            rope.moveHead(movement)

            // then
            rope.headPosition shouldBe Pair(2, 0)
            rope.tailPosition shouldBe Pair(1, 0)
        }

        "tail diagonal position - head up" {
            // given
            val headPosition = Pair(1, 1)
            val tailPosition = Pair(0, 0)

            val rope = Rope(headPosition, tailPosition)
            val movement = Movement(Direction.U, 1)

            // when
            rope.moveHead(movement)

            // then
            rope.headPosition shouldBe Pair(1, 2)
            rope.tailPosition shouldBe Pair(1, 1)
        }

        "tail diagonal position - head down" {
            // given
            val headPosition = Pair(-1, -1)
            val tailPosition = Pair(0, 0)

            val rope = Rope(headPosition, tailPosition)
            val movement = Movement(Direction.D, 1)

            // when
            rope.moveHead(movement)

            // then
            rope.headPosition shouldBe Pair(-1, -2)
            rope.tailPosition shouldBe Pair(-1, -1)
        }
        "tail diagonal position - head left" {
            // given
            val headPosition = Pair(-1, 1)
            val tailPosition = Pair(0, 0)

            val rope = Rope(headPosition, tailPosition)
            val movement = Movement(Direction.L, 1)

            // when
            rope.moveHead(movement)

            // then
            rope.headPosition shouldBe Pair(-2, 1)
            rope.tailPosition shouldBe Pair(-1, 1)
        }

        "tail diagonal position - head right" {
            // given
            val headPosition = Pair(1, 1)
            val tailPosition = Pair(0, 0)

            val rope = Rope(headPosition, tailPosition)
            val movement = Movement(Direction.R, 1)

            // when
            rope.moveHead(movement)

            // then
            rope.headPosition shouldBe Pair(2, 1)
            rope.tailPosition shouldBe Pair(1, 1)
        }
    }

    "samples" - {
        // given
        val movements = """
            R 4
            U 4
            L 3
            D 1
            R 4
            D 1
            L 5
            R 2
        """.trimIndent().lineSequence().toList()

        "part 1" {
            // given
            val nbOfKnots = 2

            // when
            val nbOfTailPositions = Day09().getNbOfTailPositions(movements, nbOfKnots)

            // then
            nbOfTailPositions shouldBe 13
        }

        "part 2a" {
            // given
            val nbOfKnots = 10

            // when
            val nbOfTailPositions = Day09().getNbOfTailPositions(movements, nbOfKnots)

            // then
            nbOfTailPositions shouldBe 1
        }

        "part 2b" {
            // given
            val nbOfKnots = 10
            val movements = """
                R 5
                U 8
                L 8
                D 3
                R 17
                D 10
                L 25
                U 20
            """.trimIndent().lineSequence().toList()

            // when
            val nbOfTailPositions = Day09().getNbOfTailPositions(movements, nbOfKnots)

            // then
            nbOfTailPositions shouldBe 36
        }
    }

    "solutions" - {
        // given
        val movements = getResourceFileAsStringSequence("day09/input_part1.txt")

        "part 1" {
            // given
            val nbOfKnots = 2

            // when
            val nbOfTailPositions = Day09().getNbOfTailPositions(movements, nbOfKnots)

            // then
            nbOfTailPositions shouldBe 5907
        }

        "part 2" {
            // given
            val nbOfKnots = 10

            // when
            val nbOfTailPositions = Day09().getNbOfTailPositions(movements, nbOfKnots)

            // then
            nbOfTailPositions shouldBe 2303
        }
    }
}) {
}