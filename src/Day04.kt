fun main() {
    fun validDirection(y: Int, x: Int, maxY: Int, maxX: Int, direction: Int): Boolean {
        return when (direction) {
            1 -> y - 2 > 0 && x + 3 < maxX
            2 -> x + 3 < maxX
            3 -> y + 3 < maxY && x + 3 < maxX
            4 -> y + 3 < maxY
            5 -> y + 3 < maxY && x - 2 > 0
            6 -> x - 2 > 0
            7 -> y - 2 > 0 && x - 2 > 0
            8 -> y - 2 > 0
            else -> return false
        }
    }

    fun pickWord(array: Array<Array<Char>>, y: Int, x: Int, direction: Int): String {
        return when (direction) {
            1 -> array[y][x].toString() + array[y - 1][x + 1] + array[y - 2][x + 2] + array[y - 3][x + 3]
            2 -> array[y][x].toString() + array[y][x + 1] + array[y][x + 2] + array[y][x + 3]
            3 -> array[y][x].toString() + array[y + 1][x + 1] + array[y + 2][x + 2] + array[y + 3][x + 3]
            4 -> array[y][x].toString() + array[y + 1][x] + array[y + 2][x] + array[y + 3][x]
            5 -> array[y][x].toString() + array[y + 1][x - 1] + array[y + 2][x - 2] + array[y + 3][x - 3]
            6 -> array[y][x].toString() + array[y][x - 1] + array[y][x - 2] + array[y][x - 3]
            7 -> array[y][x].toString() + array[y - 1][x - 1]+ array[y - 2][x - 2] + array[y - 3][x - 3]
            8 -> array[y][x].toString() + array[y - 1][x] + array[y - 2][x] + array[y - 3][x]
            else -> ""
        }
    }

    fun numberOfMatchDirection(array: Array<Array<Char>>, y: Int, x: Int): Int {
        var sum = 0
        for (i in 1..8) {
            if (validDirection(y, x, array.size, array[0].size, i) && pickWord(array, y, x, i) == "XMAS") {
                sum++
            }
        }
        return sum
    }

    fun countXmas(array: Array<Array<Char>>, y: Int, x: Int): Int {
        val wordOne = array[y - 1][x - 1].toString() + array[y][x] + array[y + 1][x + 1]
        val wordTwo = array[y + 1][x - 1].toString() + array[y][x] + array[y -1][x + 1]

        if ((wordOne == "MAS" || wordOne == "SAM") && (wordTwo == "MAS" || wordTwo == "SAM")) return 1
        return  0
    }

    fun sumAllCoordinates(twoDArray: Array<Array<Char>>): Int {
        var sum = 0
        for (y in twoDArray.indices) {
            for (x in twoDArray[y].indices) {
                sum += numberOfMatchDirection(twoDArray, y, x)
            }
        }
        return sum
    }

    fun sumXMAS(twoDArray: Array<Array<Char>>): Int {
        var sum = 0
        for (y in twoDArray.indices) {
            if (y > 0 && y < twoDArray.size - 1) {
                for (x in twoDArray[y].indices) {
                    if (x > 0 && x < twoDArray.size - 1) {
                        if (twoDArray[y][x] == 'A') {
                            sum += countXmas(twoDArray, y, x)
                        }
                    }
                }
            }
        }
        return sum
    }

    fun part1(input: String): Int {
        val twoDArray = input
            .split("\n")
            .map { line -> line.trim().toCharArray().toTypedArray() }
            .toTypedArray()
        return sumAllCoordinates(twoDArray)
    }

    fun part2(input: String): Int {
        val twoDArray = input
            .split("\n")
            .map { line -> line.trim().toCharArray().toTypedArray() }
            .toTypedArray()
        return sumXMAS(twoDArray)
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
    check(part1(testInput) == 18) { "Wrong result, got ${part1(testInput)}" }
    check(part2(testInput) == 9) { "Wrong result, got ${part2(testInput)}" }

    // Read the input from the `src/Day04.txt` file.
    val input = readInputAsString("Day04")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
