package adventOfCode

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day05Test : FreeSpec({

    "method tests" - {
        "readStacks" {
            // given
            val serializedStacks = """
                    [D]    
                [N] [C]    
                [Z] [M] [P]
                 1   2   3 
            """.trimIndent().lineSequence().toList()

            // when
            val cargo = Day05().readStacks(serializedStacks)

            // then
            cargo.toString() shouldBe listOf(listOf("Z", "N"), listOf("M", "C", "D"), listOf("P")).toString()
        }

        "getNumberOfStacks" {
            // given
            val stackIndexes = " 1   2   3 "

            // when
            val nbOfStacks = Day05().getNumberOfStacks(stackIndexes)

            // then
            nbOfStacks shouldBe 3
        }

        "readCommands" {
            // given
            val commandInput = """
                move 1 from 2 to 1
                move 3 from 1 to 3
                move 2 from 2 to 1
                move 1 from 1 to 2
            """.trimIndent().lineSequence().toList()

            // when
            val commands = Day05().readCommands(commandInput)

            // then
            commands shouldBe listOf(
                Command(1, 2, 1), Command(3, 1, 3),
                Command(2, 2, 1), Command(1, 1, 2)
            )
        }

        "apply command" {
            // given
            val serializedStacks = """
                    [D]    
                [N] [C]    
                [Z] [M] [P]
                 1   2   3 
            """.trimIndent().lineSequence().toList()
            val cargo = Day05().readStacks(serializedStacks)
            val command =  Command(1, 2, 1)

            // when
            cargo.applyCommand9000(command)

            // then
            cargo.toString() shouldBe listOf(listOf("Z", "N", "D"), listOf("M", "C"), listOf("P")).toString()
        }

        "apply command - amount > 1" {
            // given
            val serializedStacks = """
                 [D]        
                 [N] [C]    
                 [Z] [M] [P]
                  1   2   3 
            """.trimIndent().lineSequence().toList()
            val cargo = Day05().readStacks(serializedStacks)
            val command =  Command(3, 1, 3)

            // when
            cargo.applyCommand9000(command)

            // then
            cargo.toString() shouldBe listOf(listOf(), listOf("M", "C"), listOf("P", "D", "N", "Z")).toString()
        }
    }

    "samples" - {
        // given
        val input = """
                    [D]    
                [N] [C]    
                [Z] [M] [P]
                 1   2   3 
                
                move 1 from 2 to 1
                move 3 from 1 to 3
                move 2 from 2 to 1
                move 1 from 1 to 2
            """.trimIndent().lineSequence().toList()

        "part 1" {
            // when
            val topCrates = Day05().processAndGetTopCrates9000(input)

            // then
            topCrates shouldBe "CMZ"
        }

        "part 2" {
            // when
            val topCrates = Day05().processAndGetTopCrates9001(input)

            // then
            topCrates shouldBe "MCD"
        }
    }

    "solutions" - {
        // given
        val input = getResourceFileAsStringSequence("day05/input_part1.txt")

        "part 1" {
            // when
            val topCrates = Day05().processAndGetTopCrates9000(input)

            // then
            topCrates shouldBe "TLNGFGMFN"
        }

        "part 2" {
            // when
            val topCrates = Day05().processAndGetTopCrates9001(input)

            // then
            topCrates shouldBe "FGLQJCMBD"
        }
    }
}) {
}