package model

import storage.Serializer

object GameStateSerializer: Serializer<GameState> {
    override fun serialize(data: GameState) = when (data) {
        is Run -> "Run:${data.turn}"
        is Win -> "Win:${data.winner}"
        Draw -> "Draw:"
    }
    override fun deserialize(text: String): GameState =
        text.split(':').let { [type, player] ->
            when (type) {
                "Run" -> Run(player.toPlayer())
                "Win" -> Win(player.toPlayer())
                "Draw" -> Draw
                else -> throw Exception("Unknown game state: $type")
            }
        }
}

object GameSerializer: Serializer<Game> {
    override fun serialize(data: Game): String = buildString {
        appendLine(data.first)
        appendLine(data.board.entries.joinToString(" ") { "${it.key.index}:${it.value}" })
        appendLine(GameStateSerializer.serialize(data.state))
        appendLine(data.score.entries.joinToString(" ") { "${it.key}=${it.value}" })
    }
    override fun deserialize(text: String): Game =
        text.split('\n').let { [l1, l2, l3, l4] -> Game(
            first = l1.toPlayer(),
            board = if (l2.isEmpty()) emptyMap()
                    else l2.split(' ')
                        .map{ it.split(':') }
                        .associate { [index, player] ->
                            Position(index.toInt()) to player.toPlayer()
                        },
            state = GameStateSerializer.deserialize(l3),
            score = l4.split(' ')
                .map{ it.split('=') }
                .associate { [player, score] ->
                    (if(player=="null") null else player.toPlayer()) to score.toInt()
                }
        ) }
}