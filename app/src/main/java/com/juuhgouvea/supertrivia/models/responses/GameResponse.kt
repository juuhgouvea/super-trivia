package com.juuhgouvea.supertrivia.models.responses

import com.juuhgouvea.supertrivia.models.Game

data class GameResponse(
    var status: String,
    var data: ResponseData
) {
    data class ResponseData(
        var game: Game
    )
}
