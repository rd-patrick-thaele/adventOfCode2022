package adventOfCode

const val NOOP_INSTRUCTION = "noop"

class Day10 {

    fun getSumOfSixSignals(program: List<String>): Int {
        val cycleValues = runProgram(program)
        val signals = listOf(20, 60, 100, 140, 180, 220)

        return signals.sumOf { it * cycleValues[it - 2] }
    }

    fun runProgram(program: List<String>): List<Int> {
        val instructions = readInstructions(program)
        val register = Register()

        instructions.forEach { it.runInstruction(register) }

        return register.getCycleNumberHistory()
    }

    fun readInstructions(program: List<String>): List<Instruction> {
        val instructions = mutableListOf<Instruction>()
        val regex = "addx (-?[\\d]+)".toRegex()

        for (line in program) {
            if (line == NOOP_INSTRUCTION) {
                instructions.add(NoopInstruction())
                continue
            }

            val (addValue) = regex.find(line)!!.destructured
            instructions.add(AddInstruction(addValue.toInt()))
        }

        return instructions
    }

    fun printOnCrtScreen(program: List<String>): String {
        val cycleValues = runProgram(program)
        val crtRowWidth = 40
        var outPutString = ""
        var sprite = 0..2

        for ((index, cycleValue) in cycleValues.withIndex()) {

            outPutString += if (index % crtRowWidth in sprite) "#" else "."

            if ((index + 1) % crtRowWidth == 0) {
                outPutString += "\n"
            }

            sprite = cycleValue-1 .. cycleValue+1
        }

        println(outPutString)

        // line break correction
        return outPutString.removeSuffix("\n")
    }

}

interface Instruction {
    fun runInstruction(register: Register)
}

class NoopInstruction : Instruction {

    override fun runInstruction(register: Register) {
        register.runNoopCycle()
    }
}

data class AddInstruction(val value: Int) : Instruction {
    override fun runInstruction(register: Register) {
        register.runNoopCycle()
        register.runAddCycle(value)
    }

}

class Register {

    private var registerValue = 1
    private val cycleValueHistory = mutableListOf<Int>()

    fun getCycleNumberHistory(): List<Int> {
        return cycleValueHistory
    }

    fun runNoopCycle() {
        cycleValueHistory.add(registerValue)
    }

    fun runAddCycle(value: Int) {
        registerValue += value
        cycleValueHistory.add(registerValue)
    }
}
