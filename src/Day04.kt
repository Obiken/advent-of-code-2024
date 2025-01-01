import kotlin.math.abs

fun main() {
    fun part1(input: String): Int {
        return input.count()
    }

    fun part2(input: String): Int {
        return input.count()
    }

    val testInput = "MMMSXXMASM\n" +
            "MSAMXMSMSA\n" +
            "AMXSXMAAMM\n" +
            "MSAMASMSMX\n" +
            "XMASAMXAMM\n" +
            "XXAMMXXAMA\n" +
            "SMSMSASXSS\n" +
            "SAXAMASAAA\n" +
            "MAMMMXMMMM\n" +
            "MXMXAXMASX"

    // Test if implementation meets criteria from the description, like:
    check(part1(testInput) == 18) {"Wrong result, got ${part1(testInput)}"}
//    check(part2(testInput.split("\n")) == 4)

    // Read the input from the `src/Day01.txt` file.
    val input = readInputAsString("Day02")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
