import kotlin.math.*

class 두원사이의정수쌍 {
    fun solution(r1: Int, r2: Int): Long {
        var count = 0L

        fun Pair<Int, Int>.pythagoras(): Double {
            return sqrt(first.toDouble() * first + second.toDouble() * second)
        }

        for (x in 0..r2) {

            var start = 0
            var end = r2
            var yMin = r2
            var yMax = 0

            var minStart = 0
            var minEnd = r2

            // y의 최소값 찾기
            while (minStart <= minEnd) {
                val mid = (minStart + minEnd) / 2
                if (Pair(mid, x).pythagoras() < r1) {
                    minStart = mid + 1
                } else {
                    minEnd = mid - 1
                }
            }
            yMin = max(minStart, minEnd)

            // y의 최대값 찾기
            while (start <= end) {
                val mid = (start + end) / 2
                if (Pair(mid, x).pythagoras() <= r2) {
                    start = mid + 1
                } else {
                    end = mid - 1
                }
            }
            yMax = min(start, end)

            count += yMax - yMin + 1
        }

        val overlap = r2 - r1 + 1

        return (count - overlap) * 4
    }
}

fun main() {
    val solution = 두원사이의정수쌍()
    println(solution.solution(2, 3)) // 20
    println(solution.solution(1, 3)) // 28
    println(solution.solution(2, 4)) // 44
    println(solution.solution(3, 4)) // 28
}