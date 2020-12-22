package com.juuhgouvea.supertrivia.dao

import com.juuhgouvea.supertrivia.models.responses.RankingResponse
import com.juuhgouvea.supertrivia.network.services.RankingService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RankingDAO {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://super-trivia-server.herokuapp.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(RankingService::class.java)

    fun getAll(finished: (response: RankingResponse) -> Unit) {
        service.getAll().enqueue(object: Callback<RankingResponse> {
            override fun onResponse(call: Call<RankingResponse>, response: Response<RankingResponse>) {
                response?.body()?.let { response ->
                    finished(response)
                }
            }

            override fun onFailure(call: Call<RankingResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}