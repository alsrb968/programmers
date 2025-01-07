import java.util.*

class 디스크컨트롤러 {
    data class Job(
        val index: Int,
        val start: Int,
        val duration: Int
    )
    fun solution(jobs: Array<IntArray>): Int {
        val jobList = jobs.mapIndexed { index, job -> Job(index, job[0], job[1]) }
            .sortedBy { it.start }.toMutableList()
        val heap = PriorityQueue<Job>(compareBy({ it.duration }, { it.start }, { it.index }))

        var currTime = 0
        var doneTime = 0
        while (jobList.isNotEmpty() || heap.isNotEmpty()) {
            while (jobList.isNotEmpty() && jobList.first().start <= currTime) {
                heap.add(jobList.removeFirst())
            }
            if (heap.isNotEmpty()) {
                val job = heap.poll()
                currTime += job.duration
                doneTime += currTime - job.start
            } else if (jobList.isNotEmpty()) {
                currTime = jobList.first().start
            }
        }

        return doneTime / jobs.size
    }
}

fun main() {
    val solution = 디스크컨트롤러()
    println(
        "test1: " + (solution.solution(
            arrayOf(
                intArrayOf(0, 3), // 1: 3
                intArrayOf(1, 9), // 3: 16
                intArrayOf(3, 5), // 2: 5
            )
        ) == 8)
    )
    println(
        "test2: " + (solution.solution(
            arrayOf(
                intArrayOf(0, 3), // 1: 3
                intArrayOf(2, 6), // 2: 7
                intArrayOf(1, 9), // 3: 17
            )
        ) == 9)
    )
    println(
        "test3: " + (solution.solution(
            arrayOf(
                intArrayOf(0, 10), // 1: 10
                intArrayOf(6, 3), // 3: 9
                intArrayOf(8, 2), // 2: 4
            )
        ) == 7)
    )
}