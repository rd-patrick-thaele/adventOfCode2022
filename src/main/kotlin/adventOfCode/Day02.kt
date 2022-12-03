package adventOfCode

class Day02 {
    fun playRockPaperScissors(strategyMap: List<String>): Int {

        return strategyMap.map { Round(it) }
            .map { it.calculateScore() }
            .sum()
    }

    fun playRockPaperScissors2(strategyMap: List<String>): Int {

        return strategyMap.map { Round(it) }
            .map { it.calculateScore2() }
            .sum()
    }
}

val regex = "([ABC]) ([XYZ])".toRegex()

class Round(roundStrategy: String) {
    private val opponent : String
    private val me : String

    private val WIN = 6
    private val DRAW = 3
    private val LOSE = 0

    private val ROCK = 1
    private val PAPER = 2
    private val SCISSOR = 3

    init {
        val matchResult = regex.find(roundStrategy)
        val (a, b) = matchResult!!.destructured

        opponent = a
        me = b
    }

    fun opponent(): String {
        return opponent
    }

    fun me(): String {
        return me
    }

    fun calculateScore2(): Int {
        return when (opponent) {
            "A" -> when (me) {
                "X" -> SCISSOR + LOSE
                "Y" -> ROCK + DRAW
                "Z" -> PAPER + WIN
                else -> 0
            }
            "B" -> when (me) {
                "X" -> ROCK + LOSE
                "Y" -> PAPER + DRAW
                "Z" -> SCISSOR + WIN
                else -> 0
            }
            "C" -> when (me) {
                "X" -> PAPER + LOSE
                "Y" -> SCISSOR + DRAW
                "Z" -> ROCK + WIN
                else -> 0
            }
            else -> 0
        }
    }

    fun calculateScore(): Int {
        return when (opponent) {
            "A" -> when (me) {
                "X" -> ROCK + DRAW
                "Y" -> PAPER + WIN
                "Z" -> SCISSOR + LOSE
                else -> 0
            }
            "B" -> when (me) {
                "X" -> ROCK + LOSE
                "Y" -> PAPER + DRAW
                "Z" -> SCISSOR + WIN
                else -> 0
            }
            "C" -> when (me) {
                "X" -> ROCK + WIN
                "Y" -> PAPER + LOSE
                "Z" -> SCISSOR + DRAW
                else -> 0
            }
            else -> 0
        }
    }
}
