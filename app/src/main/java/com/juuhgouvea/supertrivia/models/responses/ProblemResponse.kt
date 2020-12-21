package com.juuhgouvea.supertrivia.models.responses

import com.juuhgouvea.supertrivia.models.Problem

data class ProblemResponse(
    var status: String,
    var data: ResponseData
) {
    data class ResponseData(
        var problem: Problem
    )
}
