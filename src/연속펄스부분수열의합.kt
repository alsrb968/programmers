class 연속펄스부분수열의합 {
    fun solution(sequence: IntArray): Long {
        val pulsePlus = sequence.mapIndexed { index, value -> if (index % 2 == 0) value.toLong() else -value.toLong() }
        val pulseMinus = sequence.mapIndexed { index, value -> if (index % 2 == 0) -value.toLong() else value.toLong() }

        fun kadane(arr: List<Long>): Long {
            var max = arr[0]
            var sum = arr[0]

            for (i in 1 until arr.size) {
                sum = maxOf(arr[i], sum + arr[i])
                max = maxOf(max, sum)
            }

            return max
        }

        return maxOf(kadane(pulsePlus), kadane(pulseMinus))
    }
}

fun main() {
    val solution = 연속펄스부분수열의합()
    println(
        "test1: " +
                (solution.solution(intArrayOf(2, 3, -6, 1, 3, -1, 2, 4)) == 10L)
    )
}