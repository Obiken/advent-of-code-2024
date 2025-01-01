import kotlin.math.abs

fun main() {
    fun splitInput(input: String): List<String> {
        return input.split("\n")
    }

    fun part1(input: String): Int {
        return splitInput(input).count()
    }

    fun part2(input: String): Int {
        return splitInput(input).count()
    }

    val testInput = "test_input"

    // Test if implementation meets criteria from the description, like:
    check(part1(testInput) == 18) {"Wrong result, got ${part1(testInput)}"}
    check(part2(testInput) == 4) {"Wrong result, got ${part2(testInput)}"}

    // Read the input from the `src/Day01.txt` file.
    val input = readInputAsString("Day01")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
