package adventOfCode

class Day05 {
    fun processAndGetTopCrates9000(input: List<String>): String {
        val (cargo, commands) = splitInputIntoCargoAndCommands(input)

        commands.forEach { cargo.applyCommand9000(it) }

        return cargo.getIdsOfTopCrates()
    }

    private fun splitInputIntoCargoAndCommands(input: List<String>): Pair<Cargo, List<Command>> {
        val stackInput = mutableListOf<String>()
        val commandInput = mutableListOf<String>()
        var isStack = true

        for (line in input) {
            if (isStack) {
                if (line.isBlank()) {
                    isStack = false
                    continue
                }
                stackInput.add(line)
            } else {
                commandInput.add(line)
            }
        }

        val cargo = readStacks(stackInput)
        val commands = readCommands(commandInput)

        return Pair(cargo, commands)
    }

    fun readStacks(stacks: List<String>): Cargo {
        val nbOfStacks = getNumberOfStacks(stacks.last())
        val regex = createStackRegex(nbOfStacks)
        val cargo = Cargo(nbOfStacks)

        // Quickfix: something trims off trailing spaces during reading that breaks the regex **facepalm**
        val emptyStringBuffer = " ".repeat(nbOfStacks * 4)

        for (line in stacks.dropLast(1)) {
            val groupValues = regex.find(line + emptyStringBuffer)!!.groupValues
            for ((index, value) in groupValues.withIndex()) {
                if (index == 0 || value.isBlank()) continue

                cargo.addCrateToBottom(index, value)
            }
        }
        return cargo
    }

    fun getNumberOfStacks(stackIndexes: String): Int {
        return stackIndexes.toCharArray()
            .count { it != ' ' }
    }

    private fun createStackRegex(nbOfStacks: Int): Regex {
        val singleStackRegex = "[\\s\\[]([\\sA-Z])[\\s\\]]"
        var stackRegex = singleStackRegex

        repeat(nbOfStacks - 1) {
            stackRegex += "\\s"
            stackRegex += singleStackRegex
        }

        return stackRegex.toRegex()
    }

    fun readCommands(commandInput: List<String>): List<Command> {
        val regex = "move (\\d+) from (\\d+) to (\\d+)".toRegex()
        val commandList = mutableListOf<Command>()

        for (command in commandInput) {
            val (amount, from, to) = regex.find(command)!!.destructured
            commandList.add(Command(amount.toInt(), from.toInt(), to.toInt()))
        }

        return commandList
    }

    fun processAndGetTopCrates9001(input: List<String>): String {
        val (cargo, commands) = splitInputIntoCargoAndCommands(input)

        commands.forEach { cargo.applyCommand9001(it) }

        return cargo.getIdsOfTopCrates()
    }

}

class Cargo(nbOfStacks: Int) {

    private val stacks: List<MutableList<String>>

    init {
        val deserializedStacks = mutableListOf<MutableList<String>>()
        repeat(nbOfStacks) {
            deserializedStacks.add(mutableListOf())
        }

        stacks = deserializedStacks
    }

    /**
     * @param stackIndex starts at 1
     */
    fun addCrateToBottom(stackIndex: Int, crateId: String) {
        stacks[stackIndex - 1].add(0, crateId)
    }

    override fun toString(): String {
        return stacks.toString()
    }

    fun applyCommand9000(command: Command) {
        repeat(command.amount) {
            val crate = stacks[command.from - 1].removeLast()
            stacks[command.to - 1].add(crate)
        }
    }

    fun getIdsOfTopCrates(): String {
        return stacks.joinToString("") { it.last() }
    }

    fun applyCommand9001(command: Command) {
        val takenCrates = mutableListOf<String>()
        repeat(command.amount) {
            takenCrates.add(stacks[command.from - 1].removeLast())
        }

        repeat(command.amount) {
            stacks[command.to - 1].add(takenCrates.removeLast())
        }
    }
}

data class Command(val amount: Int, val from: Int, val to: Int)
