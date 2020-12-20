package com.juuhgouvea.supertrivia.models.responses

import com.juuhgouvea.supertrivia.models.User

data class UserResponse(
    var status: String,
    var data: ResponseData
) {
    data class ResponseData(
        var user: User
    )
}