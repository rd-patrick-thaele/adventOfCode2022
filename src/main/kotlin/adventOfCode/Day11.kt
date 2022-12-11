package adventOfCode

import java.math.BigInteger

class Day11 {
    fun getMonkeyBusinessLevel(notes: List<String>, nbOfRounds: Int, reliefDivisor: Long): Long {
        val monkeys = readMonkeyNotes(notes)

        repeat(nbOfRounds) {
            monkeys.forEach { monkey ->
                while (monkey.items.isNotEmpty()) {
                    val thrownItem = monkey.throwItem(reliefDivisor)
                    monkeys[thrownItem.targetMonkey].catchItem(thrownItem.item)
                }
            }

            if ((it + 1) % 100 == 0) {
                println("\n== After round ${it + 1} ==")
                monkeys.forEachIndexed { index, monkey -> println("Monkey $index inspected items ${monkey.inspectedItems} times. ${monkey.positiveDecisions} of that positive.") }
            }
        }

        val activity = monkeys.map { it.inspectedItems }
            .sortedDescending()

        return activity[0].toLong() * activity[1].toLong()
    }

    fun readMonkeyNotes(notes: List<String>): List<Monkey> {
        val monkeyList = mutableListOf<Monkey>()

        val notesIterator = notes.iterator()

        while (notesIterator.hasNext()) {

            var monkeyNumber = notesIterator.next()
            if (monkeyNumber.isBlank()) monkeyNumber = notesIterator.next()
            println("Reading $monkeyNumber")

            val startingItems = parseStartingItems(notesIterator.next())
            val operation = parseOperation(notesIterator.next())
            val divisor = parseDivisor(notesIterator.next())
            val testPositiveMonkey = parseTestPositiveMonkey(notesIterator.next())
            val testNegativeMonkey = parseTestNegativeMonkey(notesIterator.next())

            monkeyList.add(
                Monkey(
                    startingItems.toMutableList(),
                    operation,
                    divisor,
                    testPositiveMonkey,
                    testNegativeMonkey
                )
            )
        }

        return monkeyList
    }

    fun parseStartingItems(startingItems: String): List<BigInteger> {
        val prefix = "Starting items: "
        return startingItems.trim()
            .removePrefix(prefix)
            .split(", ")
            .map { BigInteger.valueOf(it.toLong()) }
    }

    fun parseOperation(operation: String): Operation {
        val productRegex = "Operation: new = old \\* ([\\d]+)".toRegex()
        val sumRegex = "Operation: new = old \\+ ([\\d]+)".toRegex()


        val productMatch = productRegex.find(operation)
        if (productMatch != null) {
            val (factor) = productMatch.destructured
            return ProductOperation(factor.toLong())
        }

        val sumMatch = sumRegex.find(operation)
        if (sumMatch != null) {
            val (summand) = sumMatch.destructured
            return SumOperation(summand.toLong())
        }

        return SquareOperation()
    }

    fun parseDivisor(divisor: String): Long {
        val divisorRegex = "Test: divisible by ([\\d]+)".toRegex()

        val (parsed) = divisorRegex.find(divisor)!!.destructured

        return parsed.toLong()
    }

    fun parseTestPositiveMonkey(testPositiveMonkey: String): Int {
        val regex = "If true: throw to monkey ([\\d]+)".toRegex()

        val (monkeyIndex) = regex.find(testPositiveMonkey)!!.destructured

        return monkeyIndex.toInt()
    }

    fun parseTestNegativeMonkey(testNegativeMonkey: String): Int {
        val regex = "If false: throw to monkey ([\\d]+)".toRegex()

        val (monkeyIndex) = regex.find(testNegativeMonkey)!!.destructured

        return monkeyIndex.toInt()
    }

}

data class Monkey(
    val items: MutableList<BigInteger>,
    val operation: Operation,
    val testDivisor: Long,
    val testPositiveMonkey: Int,
    val testNegativeMonkey: Int
) {
    var inspectedItems = 0
    var positiveDecisions = 0

    fun throwItem(reliefDivisor: Long = 3): Throw {
        inspectedItems++

        val item = items.removeFirst()
        val increasedWorryLevel = operation.apply(item)

        val boredWorryLevel =
            if (reliefDivisor > 1) increasedWorryLevel / BigInteger.valueOf(reliefDivisor) else increasedWorryLevel

        //return if (boredWorryLevel % BigInteger.valueOf(testDivisor) == BigInteger.ZERO) {
        return if (true) {
            positiveDecisions++
            Throw(testPositiveMonkey, boredWorryLevel)
        }
        else
            Throw(testNegativeMonkey, boredWorryLevel)
    }

    fun catchItem(item: BigInteger) {
        items.add(item)
    }
}

interface Operation {
    fun apply(value: BigInteger): BigInteger
}

data class ProductOperation(val factor: Long) : Operation {
    override fun apply(value: BigInteger): BigInteger {
        return value.times(BigInteger.valueOf(factor))
    }
}

data class SumOperation(val summand: Long) : Operation {
    override fun apply(value: BigInteger): BigInteger {
        return value.plus(BigInteger.valueOf(summand))
    }
}

class SquareOperation : Operation {
    override fun apply(value: BigInteger): BigInteger {
        return value * value
    }

    override fun equals(other: Any?): Boolean {
        return true
    }

    override fun hashCode(): Int {
        return 42
    }

}

data class Throw(val targetMonkey: Int, val item: BigInteger)
