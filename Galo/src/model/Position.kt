package model


class Position private constructor(val index: Int) {
    val row get() = index / BOARD_SIZE
    val col get() = index % BOARD_SIZE
    val backSlash get() = row == col
    val slash get() = row+col == BOARD_SIZE-1
    companion object {
        val all = List(BOARD_DIM){ Position(it) }
        operator fun invoke(i: Int): Position {
            require(i in 0..<BOARD_DIM) { "Inválid position $i" }
            return all[i]
        }
    }
}
