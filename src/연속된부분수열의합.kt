class 연속된부분수열의합 {
    fun solution(sequence: IntArray, k: Int): IntArray {
        val possibles = mutableListOf<Pair<Int, Int>>()

        var start = 0
        var end = 0
        var sum = sequence[0]

        while (start <= end && end < sequence.size) {

            if (sum < k) {
                end++
                if (end < sequence.size) {
                    sum += sequence[end]
                }
            } else if (sum > k) {
                start++
                if (start <= end) {
                    sum -= sequence[start - 1]
                }
            } else {
                possibles.add(start to end)
                sum -= sequence[start]
                start++
            }
        }

        return if (possibles.isNotEmpty()) {
            val p = possibles.minByOrNull { it.second - it.first }!!
            intArrayOf(p.first, p.second)
        } else {
            intArrayOf(-1, -1)
        }
    }
}

fun main() {
    val solution = 연속된부분수열의합()
    println(solution.solution(intArrayOf(1, 2, 3, 4, 5), 7).toList()) // [2, 3]
    println(solution.solution(intArrayOf(1, 1, 1, 2, 3, 4, 5), 5).toList()) // [6, 6]
    println(solution.solution(intArrayOf(2, 2, 2, 2, 2), 6).toList()) // [0, 2]
}