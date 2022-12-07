package adventOfCode

import java.util.UUID

const val ROOT_DIR = "/"
const val MOVE_TO_ROOT_DIR = "$ cd $ROOT_DIR"
const val MOVE_TO_PARENT_DIR = "$ cd .."
const val LIST_DIR_CONTENT = "$ ls"

class Day07 {
    fun getTotalSizeOfMax100kDirs(terminalOutput: List<String>): Long {
        val rootDir = readTerminalOutput(terminalOutput)

        return rootDir.getDirectorySizes()
            .values
            .filter { it <= 100000 }
            .sum()
    }

    fun readTerminalOutput(terminalOutput: List<String>): Directory {
        val rootDir = Directory()
        var currentDir = rootDir

        val cdRegex = "\\$ cd ([a-z]+)".toRegex()
        val dirRegex = "dir ([a-z]+)".toRegex()
        val fileRegex = "([\\d]+) ([\\.a-z]+)".toRegex()

        for (outputLine in terminalOutput) {
            if (outputLine == MOVE_TO_ROOT_DIR) {
                currentDir = rootDir
                continue
            }

            if (outputLine == MOVE_TO_PARENT_DIR) {
                currentDir = currentDir.parent()
                continue
            }

            if (outputLine == LIST_DIR_CONTENT) continue

            val moveToDir = cdRegex.find(outputLine)
            if (moveToDir != null) {
                val (dirName) = moveToDir.destructured
                currentDir = currentDir.cd(dirName)
                continue
            }

            val newDirectory = dirRegex.find(outputLine)
            if (newDirectory != null) {
                val (dirName) = newDirectory.destructured
                currentDir.mkdir(dirName)
                continue
            }

            val newFile = fileRegex.find(outputLine)
            if (newFile != null) {
                val (fileSize, fileName) = newFile.destructured
                currentDir.touch(fileName, fileSize.toLong())
                continue
            }
        }

        return rootDir
    }

    fun getSizeOfDeletedDir(terminalOutput: List<String>): Long {
        val rootDir = readTerminalOutput(terminalOutput)

        val directorySizes = rootDir.getDirectorySizes()
        val sizeOfRootDir = directorySizes.getValue(rootDir.getId())
        val leftSpace = 70000000 - sizeOfRootDir
        val missingSpace = 30000000 - leftSpace

        return directorySizes.values
            .filter { it > missingSpace }
            .min()
    }

}

class Directory(private val directoryName: String = ROOT_DIR, private val parent: Directory? = null) {
    private val directories = mutableMapOf<String, Directory>()
    private val idNameMapping = mutableMapOf<UUID, String>()
    private val files = mutableListOf<AoCFile>()
    private val id = UUID.randomUUID()

    fun ls(): String {
        val directoryOutput = directories.keys
            .joinToString("\ndir ", "dir ")

        val fileOutput = if (files.isEmpty()) ""
        else
            files.joinToString("\n", "\n") { it.second.toString() + " " + it.first }

        return directoryOutput + fileOutput
    }

    fun mkdir(directoryName: String) {
        val newDir = Directory(directoryName, this)
        directories[directoryName] = newDir
        idNameMapping[newDir.id] = newDir.directoryName
    }

    fun touch(fileName: String, size: Long) {
        files.add(AoCFile(fileName, size))
    }

    fun cd(directoryName: String): Directory {
        return directories.getValue(directoryName)
    }

    fun parent(): Directory {
        return parent ?: this
    }

    fun getDirectorySizes(): Map<UUID, Long> {
        val fileSum = files.sumOf { it.second }

        if (directories.isEmpty()) {
            return mapOf(Pair(id, fileSum))
        }

        val directorySizes = directories.values.map { it.getDirectorySizes() }
            .reduce { sum, it -> sum + it }
            .toMutableMap()

        val totalDirSum = fileSum + directories.values.sumOf { directorySizes.getValue(it.id) }
        directorySizes[id] = totalDirSum

        return directorySizes
    }

    fun getId(): UUID {
        return id
    }

}

typealias AoCFile = Pair<String, Long>
