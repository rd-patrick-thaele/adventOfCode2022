package adventOfCode

import kotlin.math.absoluteValue
import kotlin.math.sign

class Day09 {
    fun getNbOfTailPositions(input: List<String>, nbOfTotalKnots: Int): Int {
        val movements = readMovements(input)
        val rope = Rope()
        rope.generateIntermediateKnots(nbOfTotalKnots - 2)

        movements.forEach { rope.moveHead(it) }

        return rope.getNbOfTailPositions()
    }

    fun readMovements(movements: List<String>): List<Movement> {
        val regex = "([RULD]) ([\\d]+)".toRegex()
        val movementList = mutableListOf<Movement>()

        for (line in movements) {
            val (direction, steps) = regex.find(line)!!.destructured
            movementList.add(Movement(Direction.valueOf(direction), steps.toInt()))
        }

        return movementList
    }

}

data class Movement(val direction: Direction, val steps: Int)

enum class Direction {
    R, U, L, D, RU, RD, LU, LD
}

class Rope(var headPosition: Pair<Int, Int> = Pair(0, 0), var tailPosition: Pair<Int, Int> = Pair(0, 0)) {

    private var knots = listOf(headPosition, tailPosition)
    private val tailPositionHistory = mutableSetOf(tailPosition)

    fun getNbOfTailPositions(): Int {
        return tailPositionHistory.size
    }

    fun generateIntermediateKnots(nbOfIntermediateKnots: Int) {
        val generatedKnots = mutableListOf(headPosition)

        repeat(nbOfIntermediateKnots) {
            generatedKnots.add(headPosition.copy())
        }

        generatedKnots.add(tailPosition)
        knots = generatedKnots
    }

    fun moveHead(movement: Movement) {
        for (i in 0 until movement.steps) {
            var head = knots.first()

            head = when (movement.direction) {
                Direction.R -> Pair(head.first + 1, head.second)
                Direction.U -> Pair(head.first, head.second + 1)
                Direction.L -> Pair(head.first - 1, head.second)
                Direction.D -> Pair(head.first, head.second - 1)
                else -> TODO("Not Defined")
            }

            val updatedKnots = mutableListOf(head)

            for (j in 1 until knots.size) {
                var nextKnot = knots[j]

                val diffX = head.first - nextKnot.first
                val diffY = head.second - nextKnot.second

                if (diffX.absoluteValue == 2 || diffY.absoluteValue == 2) {
                    val headDirection = getDirectionOfHead(diffX, diffY)

                    nextKnot = when (headDirection) {
                        Direction.R -> Pair(head.first - 1, head.second)
                        Direction.U -> Pair(head.first, head.second - 1)
                        Direction.L -> Pair(head.first + 1, head.second)
                        Direction.D -> Pair(head.first, head.second + 1)
                        Direction.RU -> Pair(nextKnot.first + 1, nextKnot.second + 1)
                        Direction.RD -> Pair(nextKnot.first + 1, nextKnot.second - 1)
                        Direction.LU -> Pair(nextKnot.first - 1, nextKnot.second + 1)
                        Direction.LD -> Pair(nextKnot.first - 1, nextKnot.second - 1)
                    }
                }

                updatedKnots.add(nextKnot)
                head = nextKnot
            }

            tailPositionHistory.add(updatedKnots.last())
            knots = updatedKnots

        }

        headPosition = knots.first()
        tailPosition = knots.last()
    }

    private fun getDirectionOfHead(diffX: Int, diffY: Int): Direction {
        val horizontalDirection = if (diffX.sign == 1) Direction.R else Direction.L
        val verticalDirection = if (diffY.sign == 1) Direction.U else Direction.D

        val isDiagonal = diffX.absoluteValue - diffY.absoluteValue == 0
        if (isDiagonal) {
            return Direction.valueOf(horizontalDirection.toString() + verticalDirection.toString())
        }

        val isHorizontalDirection = diffX.absoluteValue - diffY.absoluteValue > 0
        return if (isHorizontalDirection) horizontalDirection else verticalDirection
    }
}