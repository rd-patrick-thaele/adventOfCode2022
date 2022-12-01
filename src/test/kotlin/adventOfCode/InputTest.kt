package adventOfCode

import java.io.File

val RESOURCE_FOLDER_PREFIX = "./src/test/resources/"

fun getResourceFileAsStringSequence(resourceFile: String): List<String> {
    return File(RESOURCE_FOLDER_PREFIX + resourceFile).readLines()
}

fun getResouceFileAsIntSequence(resourceFile: String): List<Int> {
    return getResourceFileAsStringSequence(resourceFile)
        .map(String::toInt)
}

fun getResouceFileAsLongSequence(resourceFile: String): List<Long> {
    return getResourceFileAsStringSequence(resourceFile)
        .map(String::toLong)
}