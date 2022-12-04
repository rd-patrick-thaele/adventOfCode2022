package adventOfCode

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day03Test : FreeSpec({

    "method tests" - {
        "getCommonType" {
            // given
            val rucksack = "vJrwpWtwJgWrhcsFMMfFFhFp"

            //when
            val commonType = Day03().getCommonType(rucksack)

            //then
            commonType shouldBe 'p'
        }

        "getTypePrio" {
            // given
            val type = 'p'

            // when
            val prio = Day03().getTypePrio(type)

            // then
            prio shouldBe 16
        }

        "getTypePrio upper case" {
            // given
            val type = 'B'

            // when
            val prio = Day03().getTypePrio(type)

            // then
            prio shouldBe 28
        }
    }

    "samples" - {
        //given
        val rucksacks = """
                vJrwpWtwJgWrhcsFMMfFFhFp
                jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
                PmmdzqPrVvPwwTWBwg
                wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
                ttgJtRGJQctTZtZT
                CrZsJsPPZsGzwwsLwLmpwMDw
            """.trimIndent().lineSequence().toList()

        "part 1" {
            //when
            val sumOfPriorities = Day03().getSumOfPriosForRucksacks(rucksacks)

            //then
            sumOfPriorities shouldBe 157
        }

        "part 2" {
            //when
            val sumOfPriorities = Day03().getSumOfBadgePrios(rucksacks)

            //then
            sumOfPriorities shouldBe 70
        }
    }

    "solutions" - {
        "part 1" {
            //given
            val rucksacks = getResourceFileAsStringSequence("day03/input_part1.txt")

            //when
            val sumOfPriorities = Day03().getSumOfPriosForRucksacks(rucksacks)

            //then
            sumOfPriorities shouldBe 8018
        }

        "part 2" {
            //given
            val rucksacks = getResourceFileAsStringSequence("day03/input_part1.txt")

            //when
            val sumOfPriorities = Day03().getSumOfBadgePrios(rucksacks)

            //then
            sumOfPriorities shouldBe 2518
        }
    }
}) {
}