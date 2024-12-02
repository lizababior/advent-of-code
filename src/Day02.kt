import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        return parseNumberLists(input)
            .count { isValidSequence(it) }
    }

    fun part2(input: List<String>): Int {
        return parseNumberLists(input)
            .count { isValidWithOneDeletion(it) }
    }

    val testInput = readInput("Day02_test")
    check(part2(testInput) == 4)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}

fun List<Int>.isStrictlyIncreasing(): Boolean =
    zipWithNext().all { (a, b) -> a < b }

fun List<Int>.isStrictlyDecreasing(): Boolean =
    zipWithNext().all { (a, b) -> a > b }

fun List<Int>.isMonotonic(): Boolean =
    isStrictlyIncreasing() || isStrictlyDecreasing()

fun isValidSequence(input: List<Int>): Boolean =
    input.isMonotonic() && input.hasValidDifferences()

fun List<Int>.hasValidDifferences(diffRange: IntRange = 1..3): Boolean {
    return zipWithNext().all { (a, b) ->
        val diff = abs(a - b)
        diff in diffRange
    }
}

fun List<Int>.removeIndex(indexToRemove: Int): List<Int> =
    filterIndexed { index, _ -> index != indexToRemove }

fun isValidWithOneDeletion(input: List<Int>): Boolean {
    if (input.isMonotonic() && input.hasValidDifferences()) return true

    for (skipIndex in input.indices) {
        val skipList = input.removeIndex(skipIndex)
        if (skipList.isMonotonic() && skipList.hasValidDifferences()) return true
    }
    return false
}