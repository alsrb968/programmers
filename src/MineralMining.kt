class MineralMining {
    companion object {
        private val ELEMENT_STONE = 0
        private val ELEMENT_IRON = 1
        private val ELEMENT_DIAMOND = 2

        fun Int.power(n: Int): Int {
            if (n == 0) return 1
            var value = this
            repeat(n - 1) {
                value *= this
            }
            return value
        }
    }

    data class Pick(
        val element: Int,
    ) {
        fun dig(mineral: Mineral): Int {
            val pickValue = element
            val mineralValue = mineral.element
            if (pickValue >= mineralValue) {
                return 1
            } else {
                val diff = mineralValue - pickValue
                return 5.power(diff)
            }
        }
    }

    data class Mineral(
        val element: Int,
    )

    fun solution(picks: IntArray, minerals: Array<String>): Int {
        val pickList = Array(3) { mutableListOf<Pick>() }
        val mineralList = mutableListOf<Mineral>()

        picks.forEachIndexed { index, count ->
            val element = when (index) {
                0 -> ELEMENT_DIAMOND
                1 -> ELEMENT_IRON
                2 -> ELEMENT_STONE
                else -> throw IllegalArgumentException("Invalid element")
            }
            val pick = Pick(element)
            pickList[index].addAll(List(count) { pick })
        }

        minerals.forEach { mineral ->
            when (mineral) {
                "diamond" -> mineralList.add(Mineral(ELEMENT_DIAMOND))
                "iron" -> mineralList.add(Mineral(ELEMENT_IRON))
                "stone" -> mineralList.add(Mineral(ELEMENT_STONE))
            }
        }

        var answer: Int = Int.MAX_VALUE

        fun dfs(pickList: List<MutableList<Pick>>, mineralList: List<Mineral>, totalDurability: Int = 0) {
            if (pickList.all { it.isEmpty() } || mineralList.isEmpty()) {
                answer = minOf(answer, totalDurability)
                return
            }

            for (i in pickList.indices) {
                if (pickList[i].isEmpty()) continue

                val newPickList = pickList.map { it.toMutableList() }
                val newMineralList = mineralList.toMutableList()

                val pick = newPickList[i].removeFirst()
                var durability = 0

                repeat(5) {
                    if (newMineralList.isNotEmpty()) {
                        val mineral = newMineralList.removeFirst()
                        durability += pick.dig(mineral)
                    } else {
                        return@repeat
                    }
                }

                dfs(newPickList, newMineralList, totalDurability + durability)
            }
        }

        val newPickList = pickList.map { it.toMutableList() }
        val newMineralList = mineralList.toMutableList()
        dfs(newPickList, newMineralList)

        return answer
    }
}

fun main() {
    val solution = MineralMining()

    println(
        solution.solution(
            intArrayOf(1, 3, 2),
            arrayOf("diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone")
        )
    ) // 12
    println(
        solution.solution(
            intArrayOf(0, 1, 1),
            arrayOf(
                "diamond",
                "diamond",
                "diamond",
                "diamond",
                "diamond",
                "iron",
                "iron",
                "iron",
                "iron",
                "iron",
                "diamond"
            )
        )
    ) // 50
}