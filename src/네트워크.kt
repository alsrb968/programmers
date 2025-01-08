class 네트워크 {
    data class Network(
        val id: Int,
        val connects: MutableList<Int> = mutableListOf()
    )
    fun solution(n: Int, computers: Array<IntArray>): Int {
        val networks = Array(n) { Network(it) }

        for (r in 0 until n - 1) {
            for (c in r + 1 until n) {
                if (computers[r][c] == 1) {
                    networks[r].connects.add(c)
                    networks[c].connects.add(r)
                }
            }
        }

        var answer = 0

        val visited = BooleanArray(n)
        for (net in networks) {
            if (visited[net.id]) {
                continue
            }

            val queue = mutableListOf<Network>()
            queue.add(net)

            while (queue.isNotEmpty()) {
                val network = queue.removeAt(0)
                visited[network.id] = true

                for (nextId in network.connects) {
                    if (!visited[nextId]) {
                        queue.add(networks[nextId])
                    }
                }
            }

            answer++
        }

        return answer
    }
}

fun main() {
    val solution = 네트워크()
    println(
        "test1: " +
                (solution.solution(
                    3, arrayOf(
                        intArrayOf(1, 1, 0),
                        intArrayOf(1, 1, 0),
                        intArrayOf(0, 0, 1)
                    )
                ) == 2)
    )
    println(
        "test2: " +
                (solution.solution(
                    3, arrayOf(
                        intArrayOf(1, 1, 0),
                        intArrayOf(1, 1, 1),
                        intArrayOf(0, 1, 1)
                    )
                ) == 1)
    )
    println(
        "test3: " +
                (solution.solution(
                    3, arrayOf(
                        intArrayOf(1, 0, 0),
                        intArrayOf(0, 1, 0),
                        intArrayOf(0, 0, 1)
                    )
                ) == 3)
    )
    println(
        "test4: " +
                (solution.solution(
                    3, arrayOf(
                        intArrayOf(1, 1, 1),
                        intArrayOf(1, 1, 1),
                        intArrayOf(1, 1, 1)
                    )
                ) == 1)
    )
    println(
        "test5: " +
                (solution.solution(
                    4, arrayOf(
                        intArrayOf(1, 1, 1, 1),
                        intArrayOf(0, 1, 0, 0),
                        intArrayOf(0, 0, 1, 0),
                        intArrayOf(0, 0, 0, 1)
                    )
                ) == 1)
    )
    println(
        "test6: " +
                (solution.solution(
                    4, arrayOf(
                        intArrayOf(1, 0, 0, 1),
                        intArrayOf(0, 1, 1, 0),
                        intArrayOf(0, 1, 1, 0),
                        intArrayOf(1, 0, 0, 1)
                    )
                ) == 2)
    )
    println(
        "test7: " +
                (solution.solution(
                    5, arrayOf(
                        intArrayOf(1, 1, 0, 1, 0),
                        intArrayOf(1, 1, 0, 0, 0),
                        intArrayOf(0, 0, 1, 0, 1),
                        intArrayOf(1, 0, 0, 1, 1),
                        intArrayOf(0, 0, 1, 1, 1)
                    )
                ) == 1)
    )
}