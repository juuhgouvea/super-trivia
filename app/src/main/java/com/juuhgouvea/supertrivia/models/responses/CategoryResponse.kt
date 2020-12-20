package com.juuhgouvea.supertrivia.models.responses

import com.juuhgouvea.supertrivia.models.Category

data class CategoryResponse(
    var status: String,
    var data: ResponseData
) {
    data class ResponseData(
        var categories: List<Category>
    )
}