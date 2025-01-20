class 최댓값과최솟값 {
    fun solution(s: String): String {
        val list = mutableListOf<String>().apply {
            addAll(s.split(" "))
        }.map { it.toInt() }
        val max = list.maxOrNull()
        val min = list.minOrNull()

        return "$min $max"
    }
}