class 순위 {
    fun solution(n: Int, results: Array<IntArray>): Int {
        val playerMap = Array(n + 1) { BooleanArray(n + 1) { false } }

        results.forEach { (winner, loser) ->
            playerMap[winner][loser] = true
        }

        for (k in 1..n) {
            for (w in 1..n) {
                for (l in 1..n) {
                    if (playerMap[w][k] && playerMap[k][l]) {
                        playerMap[w][l] = true
                    }
                }
            }
        }

        var answer = 0

        for (i in 1..n) {
            var count = 0
            for (j in 1..n) {
                if (playerMap[i][j] || playerMap[j][i]) {
                    count++
                }
            }
            if (count == n - 1) {
                answer++
            }
        }

        return answer
    }
}

fun main() {
    val solution = 순위()
    println(
        "test1: " + (solution.solution(
            5,
            arrayOf(
                intArrayOf(4, 3),
                intArrayOf(4, 2),
                intArrayOf(3, 2),
                intArrayOf(1, 2),
                intArrayOf(2, 5),
            )
        ) == 2)
    )
}