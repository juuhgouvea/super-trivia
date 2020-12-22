package com.juuhgouvea.supertrivia.network.services

import com.juuhgouvea.supertrivia.models.responses.RankingResponse
import retrofit2.Call
import retrofit2.http.GET

interface RankingService {
    @GET("ranking")
    fun getAll(): Call<RankingResponse>
}