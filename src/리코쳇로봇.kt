import java.util.ArrayDeque

class 리코쳇로봇 {
    data class Coord(
        var r: Int,
        var c: Int,
    ) {
        fun isInBound(rows: Int, cols: Int) = r in 0 until rows && c in 0 until cols

        fun next(direction: Coord) = Coord(r + direction.r, c + direction.c)

        fun isWall(map: Array<IntArray>) = map[r][c] == -1

        fun check(checkMap: Array<BooleanArray>) {
            checkMap[r][c] = true
        }

        fun uncheck(checkMap: Array<BooleanArray>) {
            checkMap[r][c] = false
        }

        fun isChecked(checkMap: Array<BooleanArray>) = checkMap[r][c]
    }

    private val directions = arrayOf(
        Coord(0, 1), // right
        Coord(1, 0), // down
        Coord(0, -1), // left
        Coord(-1, 0), // up
    )

    fun solution(board: Array<String>): Int {
        val rows = board.size
        val cols = board[0].length
        val map = Array(rows) { IntArray(cols) { 0 } }

        var robot = Coord(0, 0)
        var goal = Coord(0, 0)

        for (i in 0 until rows) {
            for (j in 0 until cols) {
                when (board[i][j]) {
                    'D' -> map[i][j] = -1
                    'G' -> {
                        goal = Coord(i, j)
                        map[i][j] = 2
                    }
                    'R' -> {
                        robot = Coord(i, j)
                        map[i][j] = 3
                    }
                }
            }
        }

        val checkMap = Array(rows) { BooleanArray(cols) { false } }
        val queue = ArrayDeque <Pair<Coord, Int>>()

        queue.add(Pair(robot, 0))
        while (queue.isNotEmpty()) {
            val (coord, count) = queue.removeFirst()
            if (coord == goal) {
                return count
            }

            coord.check(checkMap)

            for (direction in directions) {
                var current = coord
                while (current.next(direction).isInBound(rows, cols) && !current.next(direction).isWall(map)) {
                    current = current.next(direction)
                }

                if (!current.isChecked(checkMap)) {
                    current.check(checkMap)
                    queue.add(Pair(current, count + 1))
                }
            }
        }

        return -1
    }
}

fun main() {
    val solution = 리코쳇로봇()
    println(
        solution.solution(
            arrayOf(
                "...D..R",
                ".D.G...",
                "....D.D",
                "D....D.",
                "..D....",
            )
        )
    ) // 7

    println(
        solution.solution(
            arrayOf(
                ".D.R",
                "....",
                ".G..",
                "...D",
            )
        )
    ) // -1
}