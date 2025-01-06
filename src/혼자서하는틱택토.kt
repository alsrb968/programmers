import kotlin.math.*

class 혼자서하는틱택토 {
    fun solution(board: Array<String>): Int {

        val oCount = board.sumOf { b -> b.count { it == 'O' } }
        val xCount = board.sumOf { b -> b.count { it == 'X' } }
        if (oCount < xCount || abs(oCount - xCount) > 1) return 0

        val winnable = listOf(
            listOf(0, 1, 2),
            listOf(3, 4, 5),
            listOf(6, 7, 8),
            listOf(0, 3, 6),
            listOf(1, 4, 7),
            listOf(2, 5, 8),
            listOf(0, 4, 8),
            listOf(2, 4, 6)
        )
        val linearBoard = board.flatMap { it.toList() }
        val oWin = winnable.any { it.all { idx -> linearBoard[idx] == 'O' } }
        val xWin = winnable.any { it.all { idx -> linearBoard[idx] == 'X' } }
        if (oWin && oCount == xCount) return 0
        if (xWin && oCount > xCount) return 0

        return 1
    }
}

fun main() {
    val sol = 혼자서하는틱택토()
    println(
        "test1: " + (sol.solution(
            arrayOf(
                "O.X",
                ".O.",
                "..X"
            )
        ) == 1)
    )
    println(
        "test2: " + (sol.solution(
            arrayOf(
                "OOO",
                "...",
                "XXX"
            )
        ) == 0)
    )
    println(
        "test3: " + (sol.solution(
            arrayOf(
                "...",
                ".X.",
                "..."
            )
        ) == 0)
    )
    println(
        "test4: " + (sol.solution(
            arrayOf(
                "...",
                "...",
                "..."
            )
        ) == 1)
    )
}