import java.util.*

class 과제진행하기 {
    data class Plan(
        val name: String,
        val start: Int,
        val time: Int
    ) {
        val end = start + time

        constructor(plan: Array<String>) : this(
            plan[0],
            plan[1].split(":").let { it[0].toInt() * 60 + it[1].toInt() },
            plan[2].toInt()
        )
    }

    fun solution(plans: Array<Array<String>>): Array<String> {
        val planList = plans.map { Plan(it) }.sortedBy { it.start }.toMutableList()
        val stopStack = Stack<Plan>()
        val doneList = mutableListOf<Plan>()

        while (planList.isNotEmpty()) {
            val currPlan = planList.removeFirst()
            val nextPlan = planList.firstOrNull()
            if (nextPlan == null) {
                doneList.add(currPlan)
                while (stopStack.isNotEmpty()) {
                    doneList.add(stopStack.pop())
                }
                break
            }

            if (currPlan.end > nextPlan.start) {
                val remainTime = currPlan.end - nextPlan.start
                val stopPlan = currPlan.copy(time = remainTime)
                stopStack.push(stopPlan)
            } else {
                doneList.add(currPlan)

                var time = currPlan.end
                while (stopStack.isNotEmpty() && nextPlan.start > time) {
                    val lastStop = stopStack.pop()
                    if (time + lastStop.time <= nextPlan.start) {
                        doneList.add(lastStop.copy(start = time))
                        time += lastStop.time
                    } else {
                        val remainTime = lastStop.time - (nextPlan.start - time)
                        stopStack.push(lastStop.copy(time = remainTime))
                        break
                    }
                }
            }
        }


        var answer: Array<String> = doneList.map { it.name }.toTypedArray()
        return answer
    }
}

fun main() {
    val solution = 과제진행하기()
    println(
        "test1: " +
                solution.solution(
                    arrayOf(
                        arrayOf("korean", "11:40", "30"),
                        arrayOf("english", "12:10", "20"),
                        arrayOf("math", "12:30", "40")
                    )
                ).contentEquals(arrayOf("korean", "english", "math"))
    )
    println(
        "test2: " +
                solution.solution(
                    arrayOf(
                        arrayOf("science", "12:40", "50"),
                        arrayOf("music", "12:20", "40"),
                        arrayOf("history", "14:00", "30"),
                        arrayOf("computer", "12:30", "100")
                    )
                ).contentEquals(arrayOf("science", "history", "computer", "music"))
    )
    println(
        "test3: " +
                solution.solution(
                    arrayOf(
                        arrayOf("aaa", "12:00", "20"),
                        arrayOf("bbb", "12:10", "30"),
                        arrayOf("ccc", "12:40", "10")
                    )
                ).contentEquals(arrayOf("bbb", "ccc", "aaa"))
    )
    println(
        "test4: " +
                solution.solution(
                    arrayOf(
                        arrayOf("aaa", "12:00", "30"), // remain 20
                        arrayOf("bbb", "12:10", "30"), // remain 10
                        arrayOf("ccc", "12:30", "10"),
                        arrayOf("ddd", "13:00", "20")
                    )
                ).contentEquals(arrayOf("ccc", "bbb", "ddd", "aaa"))
    )
    println(
        "test5: " +
                solution.solution(
                    arrayOf(
                        arrayOf("a", "09:00", "120"),
                        arrayOf("b", "10:00", "30"),
                        arrayOf("c", "10:30", "60")
                    )
                ).contentEquals(arrayOf("b", "c", "a"))
    )
    println(
        "test6: " +
                solution.solution(
                    arrayOf(
                        arrayOf("a", "09:00", "60"),
                        arrayOf("b", "10:00", "120"),
                        arrayOf("c", "12:00", "30")
                    )
                ).contentEquals(arrayOf("a", "b", "c"))
    )
    println(
        "test7: " +
                solution.solution(
                    arrayOf(
                        arrayOf("a", "09:00", "90"),
                        arrayOf("b", "09:30", "30"),
                        arrayOf("c", "10:00", "30"),
                        arrayOf("d", "10:30", "30")
                    )
                ).contentEquals(arrayOf("b", "c", "d", "a"))
    )
}