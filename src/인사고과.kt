class 인사고과 {
    data class Employee(
        val index: Int,
        val attitude: Int,
        val peerEval: Int
    ) {
        val total = attitude + peerEval
    }

    fun solution(scores: Array<IntArray>): Int {
        val employees = scores.mapIndexed { index, score -> Employee(index, score[0], score[1]) }
        val he = employees[0]
        val sortedEmployees = employees.sortedWith(compareByDescending<Employee> { it.attitude }.thenBy { it.peerEval })

        var rank = 1
        var maxPeerEval = 0

        for (employee in sortedEmployees) {
            if (maxPeerEval <= employee.peerEval) {
                maxPeerEval = employee.peerEval
                if (employee.total > he.total) {
                    rank++
                }
            } else {
                if (employee == he) {
                    return -1
                }
            }
        }

        return rank
    }
}

fun main() {
    val solution = 인사고과()
    println(
        "test1: " +
                (solution.solution(
                    arrayOf(
                        intArrayOf(2, 2),
                        intArrayOf(1, 4),
                        intArrayOf(3, 2),
                        intArrayOf(3, 2),
                        intArrayOf(2, 1),
                    )
                ) == 4)
    )
}