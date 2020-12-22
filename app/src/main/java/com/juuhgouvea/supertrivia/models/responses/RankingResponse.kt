package com.juuhgouvea.supertrivia.models.responses

import com.juuhgouvea.supertrivia.models.RankingPosition

data class RankingResponse(
    var status: String,
    var data: ResponseData
) {
    data class ResponseData(
        var ranking: List<RankingPosition>
    )
}
