package com.juuhgouvea.supertrivia.dao

import com.juuhgouvea.supertrivia.models.responses.ProblemResponse
import com.juuhgouvea.supertrivia.network.services.ProblemService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProblemDAO {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://super-trivia-server.herokuapp.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(ProblemService::class.java)

    fun next(token: String, finished: (response: ProblemResponse) -> Unit) {
        service.next(token)
            .enqueue(object: Callback<ProblemResponse> {
                override fun onResponse(
                    call: Call<ProblemResponse>,
                    response: Response<ProblemResponse>
                ) {
                    response?.body()?.let { response ->
                        finished(response)
                    }
                }

                override fun onFailure(call: Call<ProblemResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

    fun view(token: String, finished: (response: ProblemResponse) -> Unit) {
        service.view(token)
            .enqueue(object: Callback<ProblemResponse> {
                override fun onResponse(
                    call: Call<ProblemResponse>,
                    response: Response<ProblemResponse>
                ) {
                    response?.body()?.let { response ->
                        finished(response)
                    }
                }

                override fun onFailure(call: Call<ProblemResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }
}