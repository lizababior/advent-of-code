fun main() {
    fun part1(input: List<String>): Int {
        val validLines = parseNumberLists(input)

        val lists = validLines.map { it[0] to it[1] }.unzip()

        return lists.first.sorted()
            .zip(lists.second.sorted())
            .sumOf { (a, b) -> kotlin.math.abs(a - b) }
    }

    fun part2(input: List<String>): Int {
        val validLines = parseNumberLists(input)

        val (leftList, rightList) = validLines.map { it[0] to it[1] }.unzip()

        val rightFrequencies = rightList.groupingBy { it }.eachCount()

        return leftList.sumOf { leftNum ->
            leftNum * (rightFrequencies[leftNum] ?: 0)
        }
    }

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
