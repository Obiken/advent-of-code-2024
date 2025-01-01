fun main() {
    fun captureMulPairs(input: String): List<Pair<Int, Int>> {
        val pattern = "mul\\((\\d{1,3}),(\\d{1,3})\\)".toRegex()
        return pattern.findAll(input).map { matchResult ->
            Pair(matchResult.groupValues[1].toInt(), matchResult.groupValues[2].toInt())
        }.toList()
    }

    fun part1(input: String): Int {
        val pairs = captureMulPairs(input)
        return pairs.sumOf { pair -> pair.first * pair.second }
    }

    fun part2(input: String): Int {
        var enabled = true
        val pattern = "mul\\((\\d{1,3}),(\\d{1,3})\\)|do\\(\\)|don't\\(\\)".toRegex()
        return pattern.findAll(input).map { matchResult ->
            when (matchResult.groupValues.first().substringBefore('(')) {
                "mul" -> if (enabled)
                    matchResult.groupValues[1].toInt() * matchResult.groupValues[2].toInt()
                else 0

                "do" -> {
                    enabled = true
                    0
                }
                "don't" -> {
                    enabled = false
                    0
                }
                else -> 0
            }
        }.sumOf { it }
    }

    val testInput = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
    val testInput2 = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"

    // Test if implementation meets criteria from the description, like:
    check(part1(testInput) == 161) { "Wrong result, got ${part1(testInput)}" }
    check(part2(testInput2) == 48) { "Wrong result part 2, got ${part2(testInput2)}" }

    // Read the input from the `src/Day01.txt` file.
    val input = readInputAsString("Day03")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

