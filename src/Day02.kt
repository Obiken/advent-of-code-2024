import kotlin.math.abs

fun main() {
    fun increaseOrDecrease(intList: List<Int>): Boolean {
        val decrease = intList.zipWithNext { a, b -> a > b && abs(a - b) < 4 }
            .distinct().size == 1 && intList.zipWithNext { a, b -> a > b && abs(a -b) < 4}.first()
        val increase = intList.zipWithNext { a, b -> a < b && abs(a - b) < 4 }
            .distinct().size == 1 && intList.zipWithNext { a, b -> a < b && abs(a -b) < 4 }.first()
        return increase || decrease
    }

    fun part1(input: List<String>): Int {
        return input.count { report ->
            val intList = report.split(" ").map { it.toInt() }
            increaseOrDecrease(intList)
        }
    }

    fun part2(input: List<String>): Int {
        return input.count { report ->
            val intList = report.split(" ").map { it.toInt() }
            if (increaseOrDecrease(intList)) true
            else {
//                println("Not an initial safe report: $report")
                List(intList.size) { index ->
            //                    print("Dropping $element at $index: ")
                    val mutableList = intList.toMutableList()
                    mutableList.removeAt(index)
            //                    println("$mutableList : ${increaseOrDecrease(mutableList)}")
                    increaseOrDecrease(mutableList)
                }.any { it }
            }
        }
    }

    val testInput = "7 6 4 2 1\n" +
            "1 2 7 8 9\n" +
            "9 7 6 2 1\n" +
            "1 3 2 4 5\n" +
            "8 6 4 4 1\n" +
            "1 3 6 7 9\n" +
            "62 69 70 70 77\n" +
            "77 72 65 63 59"
    // Test if implementation meets criteria from the description, like:
    check(part1(testInput.split("\n")) == 2)

    check(part2(testInput.split("\n")) == 4)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
