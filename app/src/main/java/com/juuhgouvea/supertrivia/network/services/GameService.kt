package com.juuhgouvea.supertrivia.network.services

import com.juuhgouvea.supertrivia.models.responses.GameResponse
import retrofit2.Call
import retrofit2.http.*

interface GameService {
    @GET("games")
    fun start(
        @Header("Authorization") token: String,
        @Query("difficulty") difficulty: String,
        @Query("category") category: String?
    ): Call<GameResponse>

    @DELETE("games")
    fun finish(
        @Header("Authorization") token: String,
    ): Call<GameResponse>
}