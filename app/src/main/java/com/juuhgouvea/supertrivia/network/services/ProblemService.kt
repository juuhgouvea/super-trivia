package com.juuhgouvea.supertrivia.network.services

import com.juuhgouvea.supertrivia.models.responses.ProblemResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ProblemService {
    @GET("problems/next")
    fun next(@Header("Authorization") token: String): Call<ProblemResponse>

    @GET("problems/view")
    fun view(@Header("Authorization") token: String): Call<ProblemResponse>
}