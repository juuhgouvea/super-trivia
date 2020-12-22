package com.juuhgouvea.supertrivia.models.responses

import com.google.gson.annotations.SerializedName
import com.juuhgouvea.supertrivia.models.Answer

data class AnswerResponse(
    var status: String,
    var data: ResponseData

) {
    data class ResponseData(
        var answer: ResponseAnswer,
        var score: Long
    ) {
        data class ResponseAnswer(
                var status: String,
                @SerializedName("correct_answer") var correctAnswer: Answer
        )
    }
}
