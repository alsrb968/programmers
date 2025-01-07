class 자물쇠와열쇠 {
    class Key(_map: Array<IntArray>) {
        val size = _map.size
        private val mapRot0 = _map
        private val mapRot90 = Array(size) { IntArray(size) { 0 } }
        private val mapRot180 = Array(size) { IntArray(size) { 0 } }
        private val mapRot270 = Array(size) { IntArray(size) { 0 } }

        init {
            for (i in 0 until size) {
                for (j in 0 until size) {
                    mapRot90[j][size - 1 - i] = _map[i][j]
                    mapRot180[size - 1 - i][size - 1 - j] = _map[i][j]
                    mapRot270[size - 1 - j][i] = _map[i][j]
                }
            }
        }

        fun getRotMap(rotCount: Int): Array<IntArray> {
            return when (rotCount % 4) {
                0 -> mapRot0
                1 -> mapRot90
                2 -> mapRot180
                3 -> mapRot270
                else -> throw IllegalArgumentException("rotCount must be 0, 1, 2, 3")
            }
        }
    }

    class Lock(_map: Array<IntArray>, keySize: Int) {
        val size = _map.size
        private val map = Array(size + 2 * (keySize - 1)) { IntArray(size + 2 * (keySize - 1)) { 0 } }
        private val offset = keySize - 1

        init {
            for (i in 0 until size) {
                for (j in 0 until size) {
                    map[i + offset][j + offset] = _map[i][j]
                }
            }
        }

        fun isUnlock(key: Array<IntArray>, r: Int, c: Int): Boolean {
            val keyMap = Array(map.size) { IntArray(map.size) { 0 } }
            for (i in 0 until key.size) {
                for (j in 0 until key.size) {
                    keyMap[i + r][j + c] = key[i][j]
                }
            }

            for (i in 0 until size) {
                for (j in 0 until size) {
                    if (map[i + offset][j + offset] + keyMap[i + offset][j + offset] != 1) {
                        return false
                    }
                }
            }
            return true
        }
    }

    // 3 <= key(M) <= lock(N) <= 20
    fun solution(key: Array<IntArray>, lock: Array<IntArray>): Boolean {
        val keyObj = Key(key)
        val lockObj = Lock(lock, key.size)

        val endPoint = key.size - 1 + lock.size
        for (r in 0 until endPoint) {
            for (c in 0 until endPoint) {
                for (rotCount in 0..3) {
                    if (lockObj.isUnlock(keyObj.getRotMap(rotCount), r, c)) {
                        return true
                    }
                }
            }
        }

        return false
    }
}
// N20 M20 -> 58 * 58 * 4 = 13456
// N20 M3 -> 22 * 22 * 4 = 1936
// N3 M3 -> 5 * 5 * 4 = 100

fun main() {
    val solution = 자물쇠와열쇠()
    println(
        "test1: " + (solution.solution(
            arrayOf(
                intArrayOf(0, 0, 0),
                intArrayOf(1, 0, 0),
                intArrayOf(0, 1, 1),
            ),
            arrayOf(
                intArrayOf(1, 1, 1),
                intArrayOf(1, 1, 0),
                intArrayOf(1, 0, 1),
            )
        ))
    )
}