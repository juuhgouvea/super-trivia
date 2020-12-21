package com.juuhgouvea.supertrivia.dao

import com.juuhgouvea.supertrivia.models.Difficulty
import com.juuhgouvea.supertrivia.models.responses.GameResponse
import com.juuhgouvea.supertrivia.network.services.GameService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GameDAO {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://super-trivia-server.herokuapp.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(GameService::class.java)

    fun start(
        token: String,
        difficulty: String,
        category: String?,
        finished: (response: GameResponse) -> Unit
    ) {
        service.start(
            token,
            difficulty,
            category
        ).enqueue(object: Callback<GameResponse> {
            override fun onResponse(call: Call<GameResponse>, response: Response<GameResponse>) {
                response?.body()?.let { response ->
                    finished(response)
                }
            }

            override fun onFailure(call: Call<GameResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun finish(
        token: String,
        finished: (response: GameResponse) -> Unit
    ) {
        service.finish(token)
            .enqueue(object: Callback<GameResponse> {
                override fun onResponse(
                    call: Call<GameResponse>,
                    response: Response<GameResponse>
                ) {
                    response?.body()?.let { response ->
                        finished(response)
                    }
                }

                override fun onFailure(call: Call<GameResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }
}