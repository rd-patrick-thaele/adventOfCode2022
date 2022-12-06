package adventOfCode

class   Day06 {
    fun getStartOfPacketMarkerIndex(signal: String): Int {
        return getMarkerIndex(4, signal)
    }

    private fun getMarkerIndex(nbOfDistinctChars: Int, signal: String) : Int{
        val indexShift = nbOfDistinctChars - 1

        for(index in indexShift until signal.length ) {

            val marker = signal.substring(index - indexShift..index)
                .toCharArray()
                .toSet()

            if (marker.size == nbOfDistinctChars)
                return index + 1
        }
        return -1
    }

    fun getStartOfMessageMarkerIndex(signal: String): Int {
        return getMarkerIndex(14, signal)
    }

}
