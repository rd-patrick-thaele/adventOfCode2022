package adventOfCode

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.beInstanceOf

class Day10Test: FreeSpec({

    "method tests" - {
        "readCommands" {
            // given
            val program = """
                noop
                addx 3
                addx -5
                addx 30
                noop
                addx -513
            """.trimIndent().lineSequence().toList()

            // when
            val instructions = Day10().readInstructions(program)

            // then
            instructions.size shouldBe 6

            instructions[0] should beInstanceOf<NoopInstruction>()
            instructions[1] shouldBe AddInstruction(3)
            instructions[2] shouldBe AddInstruction(-5)
            instructions[3] shouldBe AddInstruction(30)
            instructions[4] should beInstanceOf<NoopInstruction>()
            instructions[5] shouldBe AddInstruction(-513)
        }

        "getCycleNumbers" {
            // given
            val program = """
                noop
                addx 3
                addx -5
            """.trimIndent().lineSequence().toList()

            // when
            val cycleValues = Day10().runProgram(program)

            // then
            cycleValues shouldBe listOf(1, 1, 4, 4, -1)
        }
    }

    "samples" - {
        // given
        val program = getResourceFileAsStringSequence("day10/test_input.txt")

        "part 1" {
            // when
            val sumOfSixSignals = Day10().getSumOfSixSignals(program)

            // then
            sumOfSixSignals shouldBe 13140
        }

        "part 2" {
            // when
            val crtScreen = Day10().printOnCrtScreen(program)

            // then
            crtScreen shouldBe  """
                ##..##..##..##..##..##..##..##..##..##..
                ###...###...###...###...###...###...###.
                ####....####....####....####....####....
                #####.....#####.....#####.....#####.....
                ######......######......######......####
                #######.......#######.......#######.....
            """.trimIndent()
        }
    }

    "solutions" - {
        // given
        val program = getResourceFileAsStringSequence("day10/input_part1.txt")

        "part 1" {
            // when
            val sumOfSixSignals = Day10().getSumOfSixSignals(program)

            // then
            sumOfSixSignals shouldBe 15140
        }

        "part 2" {
            // when
            val crtScreen = Day10().printOnCrtScreen(program)

            // then
            crtScreen shouldBe  """
                ###..###....##..##..####..##...##..###..
                #..#.#..#....#.#..#....#.#..#.#..#.#..#.
                ###..#..#....#.#..#...#..#....#..#.#..#.
                #..#.###.....#.####..#...#.##.####.###..
                #..#.#....#..#.#..#.#....#..#.#..#.#....
                ###..#.....##..#..#.####..###.#..#.#....
            """.trimIndent()
        }
    }
}) {
}