package com.juuhgouvea.supertrivia.network.services

import com.juuhgouvea.supertrivia.models.responses.AnswerResponse
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface AnswerService {
    @POST("problems/answer")
    @Headers("Content-Type: application/json")
    fun answer(
        @Header("Authorization") token: String,
        @Query("answer") answer: Long
    ): Call<AnswerResponse>
}