import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val pairList =
            input.map { line -> Pair(line.substringBefore("  ").toInt(), line.substringAfter("  ").trim().toInt()) }
        val firstList:MutableList<Int> = pairList.map { it.first }.toMutableList()
        val secondList: MutableList<Int> = pairList.map { it.second }.toMutableList()
        firstList.sort()
        secondList.sort()
        return firstList.zip(secondList).sumOf { (first, second) -> abs(first - second) }
    }

    fun part2(input: List<String>): Int {
        val pairList =
            input.map { line -> Pair(line.substringBefore("  ").toInt(), line.substringAfter("  ").trim().toInt()) }
        val firstList:MutableList<Int> = pairList.map { it.first }.toMutableList()
        val secondList: MutableList<Int> = pairList.map { it.second }.toMutableList()
        return firstList.sumOf{ element -> element * secondList.count { it == element }}
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(("3   4\n" +
            "4   3\n" +
            "2   5\n" +
            "1   3\n" +
            "3   9\n" +
            "3   3").split("\n")) == 11)

    check(part2(("3   4\n" +
            "4   3\n" +
            "2   5\n" +
            "1   3\n" +
            "3   9\n" +
            "3   3").split("\n")) == 31)

    // Or read a large test input from the `src/Day01_test.txt` file:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
