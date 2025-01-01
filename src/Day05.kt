fun main() {
    fun splitInput(input: String): List<String> {
        return input.split("\n")
    }

    fun middleValue(input: List<Int>): Int {
        val middleIndex = input.size / 2
        return input[middleIndex]
    }

    fun breaksRule(rule: Pair<Int, Int>, update: List<Int>): Boolean {
        update.indexOf(rule.first).let {
            if (it == -1) return false
            if (update.indexOf(rule.second) != -1 && update.indexOf(rule.second) < it) {
                return true
            }
        }
        return false
    }

    fun breakingRules(update: List<Int>, rules: List<Pair<Int, Int>>): Boolean {
        var ruleBroken = false
        rules.forEach { rule ->
            if (breaksRule(rule, update)) {
                ruleBroken = true
            }
        }
        return ruleBroken
    }

    fun applyRules(rules: List<Pair<Int, Int>>, update: List<Int>): List<Int> {
        val brokenRules = mutableListOf<Pair<Int, Int>>()
        rules.forEach { rule ->
            if (breaksRule(rule, update)) {
                brokenRules.add(rule)
            }
        }
        val tempList = update.toMutableList()
        while (breakingRules(tempList, rules)) {
            brokenRules.forEach { brokenRule ->
                if (breaksRule(brokenRule, tempList)) {
                    val secondIndex = tempList.indexOf(brokenRule.second)
                    tempList.remove(brokenRule.first)
                    tempList.add(secondIndex, brokenRule.first)
                }
            }
        }
        return tempList
    }

    fun part1(input: String): Int {
        val inputList = splitInput(input)
        val separator = inputList.indexOf(inputList.find { !it.contains("|") })
        val rules = inputList.subList(0, separator).map {
            Pair(
                it.split("|").first().trimEnd('\r').toInt(),
                it.split("|").last().trimEnd('\r').toInt()
            )
        }
        val updates = inputList.subList(separator + 1, inputList.size)

        return updates.asSequence()
            .map { update -> update.split(",").map { it.trimEnd('\r').toInt() } }
            .filter { !breakingRules(it, rules) }
            .map { middleValue(it) }
            .sumOf { it }
    }

    fun part2(input: String): Int {
        val inputList = splitInput(input)
        val separator = inputList.indexOf(inputList.find { !it.contains("|") })
        val rules = inputList.subList(0, separator).map {
            Pair(
                it.split("|").first().trimEnd('\r').toInt(),
                it.split("|").last().trimEnd('\r').toInt()
            )
        }
        val updates = inputList.subList(separator + 1, inputList.size)

        println(
            updates.asSequence()
                .map { update -> update.split(",").map { it.trimEnd('\r').toInt() } }
                .filter { breakingRules(it, rules) }
                .map { applyRules(rules, it) }
                .toList())
        return updates.asSequence()
            .map { update -> update.split(",").map { it.trimEnd('\r').toInt() } }
            .filter { breakingRules(it, rules) }
            .map { applyRules(rules, it) }
            .map { middleValue(it) }
            .also { println(it.toList()) }
            .sumOf { it }
    }

    val testInput = "47|53\n" +
            "97|13\n" +
            "97|61\n" +
            "97|47\n" +
            "75|29\n" +
            "61|13\n" +
            "75|53\n" +
            "29|13\n" +
            "97|29\n" +
            "53|29\n" +
            "61|53\n" +
            "97|53\n" +
            "61|29\n" +
            "47|13\n" +
            "75|47\n" +
            "97|75\n" +
            "47|61\n" +
            "75|61\n" +
            "47|29\n" +
            "75|13\n" +
            "53|13\n" +
            "\r\n" +
            "75,47,61,53,29\n" +
            "97,61,53,29,13\n" +
            "75,29,13\n" +
            "75,97,47,61,53\n" +
            "61,13,29\n" +
            "97,13,75,29,47\r"

    // Test if implementation meets criteria from the description, like:
    check(part1(testInput) == 143) { "Wrong result part 1, got ${part1(testInput)}" }
    check(part2(testInput) == 123) { "Wrong result part 2, got ${part2(testInput)}" }

    // Read the input from the `src/Day01.txt` file.
    val input = readInputAsString("Day05")
    println("Part 1: ${part1(input)}")
//    println("Part 2: ${part2(input)}")
}
