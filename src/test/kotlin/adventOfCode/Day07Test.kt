package adventOfCode

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day07Test : FreeSpec({

    "method tests" - {
        "print directory" {
            // given
            val dir = Directory()

            dir.mkdir("abc")
            dir.mkdir("xyz")

            dir.touch("a.txt", 14848514)
            dir.touch("page.swp", 98561543423)

            // when
            val ls = dir.ls()

            // then
            ls shouldBe """
                dir abc
                dir xyz
                14848514 a.txt
                98561543423 page.swp
            """.trimIndent()
        }

        "readTerminalOutput - first level" {
            // given
            val terminalOutput = """
                $ cd /
                $ ls
                dir a
                14848514 b.txt
                8504156 c.dat
                dir d
            """.trimIndent().lineSequence().toList()

            // when
            val rootDirectory = Day07().readTerminalOutput(terminalOutput)

            // then
            rootDirectory.ls() shouldBe """
                dir a
                dir d
                14848514 b.txt
                8504156 c.dat
            """.trimIndent()
        }

        "readTerminalOutput - traverse down" {
            // given
            val terminalOutput = """
                $ cd /
                $ ls
                dir a
                $ cd a
                $ ls
                dir e
            """.trimIndent().lineSequence().toList()

            // when
            val rootDirectory = Day07().readTerminalOutput(terminalOutput)

            // then
            rootDirectory.ls() shouldBe "dir a"
            rootDirectory.cd("a").ls() shouldBe "dir e"
        }

        "readTerminalOutput - traverse up" {
            // given
            val terminalOutput = """
                $ cd /
                $ ls
                dir a
                dir b
                $ cd a
                $ ls
                dir e
                $ cd ..
                $ cd b
                $ ls
                dir foo
            """.trimIndent().lineSequence().toList()

            // when
            val rootDirectory = Day07().readTerminalOutput(terminalOutput)

            // then
            rootDirectory.ls() shouldBe """
                   dir a
                   dir b
                """.trimIndent()
            rootDirectory.cd("b").ls() shouldBe "dir foo"
        }
    }

    "samples" - {
        // given
        val terminalOutput = """
                $ cd /
                $ ls
                dir a
                14848514 b.txt
                8504156 c.dat
                dir d
                $ cd a
                $ ls
                dir e
                29116 f
                2557 g
                62596 h.lst
                $ cd e
                $ ls
                584 i
                $ cd ..
                $ cd ..
                $ cd d
                $ ls
                4060174 j
                8033020 d.log
                5626152 d.ext
                7214296 k
            """.trimIndent().lineSequence().toList()

        "part 1" {
            // when
            val totalSize = Day07().getTotalSizeOfMax100kDirs(terminalOutput)

            // then
            totalSize shouldBe 95437
        }

        "part 1 - duplicated dir names" {
            // given
            val terminalOutput = """
                $ cd /
                $ ls
                dir a
                14848514 b.txt
                8504156 c.dat
                dir d
                $ cd a
                $ ls
                dir a
                29116 f
                2557 g
                62596 h.lst
                $ cd a
                $ ls
                584 i
                $ cd ..
                $ cd ..
                $ cd d
                $ ls
                4060174 j
                8033020 d.log
                5626152 d.ext
                7214296 k
            """.trimIndent().lineSequence().toList()

            // when
            val totalSize = Day07().getTotalSizeOfMax100kDirs(terminalOutput)

            // then
            totalSize shouldBe 95437
        }

        "part 2" {
            // when
            val sizeOfDeletedDir = Day07().getSizeOfDeletedDir(terminalOutput)

            // then
            sizeOfDeletedDir shouldBe 24933642
        }
    }

    "solutions" - {
        // given
        val terminalOutput = getResourceFileAsStringSequence("day07/input_part1.txt")

        "part 1" {
            // when
            val totalSize = Day07().getTotalSizeOfMax100kDirs(terminalOutput)

            // then
            totalSize shouldBe 1243729
        }

        "part 2" {
            // when
            val totalSize = Day07().getSizeOfDeletedDir(terminalOutput)

            // then
            totalSize shouldBe 4443914
        }
    }
}) {
}