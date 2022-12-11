package adventOfCode

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import java.math.BigInteger

class Day11Test : FreeSpec({

    "method tests" - {
        "read tests" - {
            "readMonkeyNotes - single" {
                // given
                val notes = """
                Monkey 0:
                  Starting items: 79, 98
                  Operation: new = old * 19
                  Test: divisible by 23
                    If true: throw to monkey 2
                    If false: throw to monkey 3
            """.trimIndent().lineSequence().toList()

                // when
                val monkeys = Day11().readMonkeyNotes(notes)

                // then
                monkeys.first().items shouldBe listOf(BigInteger.valueOf(79), BigInteger.valueOf(98))
                monkeys.first().operation shouldBe ProductOperation(19)
                monkeys.first().testDivisor shouldBe 23
                monkeys.first().testPositiveMonkey shouldBe 2
                monkeys.first().testNegativeMonkey shouldBe 3
            }

            "readMonkeyNotes - multiple" {
                // given
                val notes = getResourceFileAsStringSequence("day11/test_input.txt")

                // when
                val monkeys = Day11().readMonkeyNotes(notes)

                // then
                monkeys.size shouldBe 4
                monkeys[0].operation shouldBe ProductOperation(19)
                monkeys[1].operation shouldBe SumOperation(6)
                monkeys[2].operation shouldBe SquareOperation()
                monkeys[3].items shouldBe listOf(BigInteger.valueOf(74))
            }

            "parseStartingItems" {
                // given
                val startingItems = "  Starting items: 79, 98, 12, 34"

                // when
                val parsedItems = Day11().parseStartingItems(startingItems)

                // then
                parsedItems shouldBe listOf(
                    BigInteger.valueOf(79),
                    BigInteger.valueOf(98),
                    BigInteger.valueOf(12),
                    BigInteger.valueOf(34)
                )
            }

            "parseOperation - product" {
                // given
                val operation = "    Operation: new = old * 19"

                // when
                val parsed = Day11().parseOperation(operation)

                // then
                parsed shouldBe ProductOperation(19)
            }

            "parseOperation - sum" {
                // given
                val operation = "    Operation: new = old + 35"

                // when
                val parsed = Day11().parseOperation(operation)

                // then
                parsed shouldBe SumOperation(35)
            }

            "parseOperation - square" {
                // given
                val operation = "    Operation: new = old * old"

                // when
                val parsed = Day11().parseOperation(operation)

                // then
                parsed shouldBe SquareOperation()
            }

            "parseDivisor" {
                // given
                val divisor = "   Test: divisible by 23"

                // when
                val parsed = Day11().parseDivisor(divisor)

                // then
                parsed shouldBe 23
            }

            "parseTestPositiveMonkey" {
                // given
                val testPositiveMonkey = "    If true: throw to monkey 2"

                // when
                val parsed = Day11().parseTestPositiveMonkey(testPositiveMonkey)

                // then
                parsed shouldBe 2
            }

            "parseTestNegativeMonkey" {
                // given
                val testPositiveMonkey = "    If false: throw to monkey 3"

                // when
                val parsed = Day11().parseTestNegativeMonkey(testPositiveMonkey)

                // then
                parsed shouldBe 3
            }
        }

        "monkey tests" - {

            "throwItem" {
                // given
                val monkey = Monkey(
                    mutableListOf(BigInteger.valueOf(79), BigInteger.valueOf(98)),
                    ProductOperation(19),
                    23,
                    2,
                    3
                )

                // when
                val thrownItem = monkey.throwItem()

                // then
                thrownItem.targetMonkey shouldBe 3
                thrownItem.item shouldBe BigInteger.valueOf(500)
                monkey.inspectedItems shouldBe 1
                monkey.items shouldBe listOf(BigInteger.valueOf(98))
            }

            "catchItem" {
                // given
                val monkey = Monkey(
                    mutableListOf(BigInteger.valueOf(79), BigInteger.valueOf(98)),
                    ProductOperation(19),
                    23,
                    2,
                    3
                )

                // when
                monkey.catchItem(BigInteger.valueOf(12))

                // then
                monkey.items shouldBe listOf(BigInteger.valueOf(79), BigInteger.valueOf(98), BigInteger.valueOf(12))
            }
        }
    }

    "samples" - {
        // given
        val notes = getResourceFileAsStringSequence("day11/test_input.txt")

        "part 1" {
            // given
            val nbOfRounds = 20
            val reliefDivisor = 3L

            // when
            val monkeyBusinessLevel = Day11().getMonkeyBusinessLevel(notes, nbOfRounds, reliefDivisor)

            // then
            monkeyBusinessLevel shouldBe 10605
        }

        "part 2" {
            // given
            val nbOfRounds = 10_000
            val reliefDivisor = 1L

            // when
            val monkeyBusinessLevel = Day11().getMonkeyBusinessLevel(notes, nbOfRounds, reliefDivisor)

            // then
            monkeyBusinessLevel shouldBe 2_713_310_158
        }
    }

    "solutions" - {
        // given
        val notes = getResourceFileAsStringSequence("day11/input_part1.txt")

        "part 1" {
            // given
            val nbOfRounds = 20
            val reliefDivisor = 3L

            // when
            val monkeyBusinessLevel = Day11().getMonkeyBusinessLevel(notes, nbOfRounds, reliefDivisor)

            // then
            monkeyBusinessLevel shouldBe 78960
        }
    }
}) {
}