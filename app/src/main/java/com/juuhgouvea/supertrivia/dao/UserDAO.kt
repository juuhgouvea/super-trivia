package com.juuhgouvea.supertrivia.dao

import android.content.res.Resources
import android.util.Log
import com.juuhgouvea.supertrivia.R
import com.juuhgouvea.supertrivia.models.User
import com.juuhgouvea.supertrivia.models.responses.UserResponse
import com.juuhgouvea.supertrivia.models.responses.errors.ErrorResponse
import com.juuhgouvea.supertrivia.models.responses.errors.RegisterErrorResponse
import com.juuhgouvea.supertrivia.network.services.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserDAO {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://super-trivia-server.herokuapp.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(UserService::class.java)

    fun login(user: User, finished: (response: UserResponse) -> Unit, fail: (response: ErrorResponse?) -> Unit) {
        service.login(user).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if(response.isSuccessful()) {
                    response?.body()?.let { response ->
                        finished(response)
                    }
                } else {
                    try {
                        val errorResponse = ErrorResponse.parseJson(retrofit, response.errorBody())
                        fail(errorResponse)
                    } catch(e: Exception) {
                        fail(ErrorResponse("fail", "Connection Error"))
                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                fail(ErrorResponse("fail", "Connection Error"))
                Log.e("login_error", t?.message)
            }

        })
    }

    fun register(user: User, finished: (response: UserResponse) -> Unit, fail: (response: ErrorResponse?) -> Unit) {
        service.register(user).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful()) {
                    response?.body()?.let { response ->
                        finished(response)
                    }
                } else {
                    try {
                        val errorResponse = RegisterErrorResponse.parseJson(retrofit, response.errorBody())
                        fail(errorResponse)
                    } catch(e: Exception) {
                        fail(ErrorResponse("fail", "Connection Error"))
                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                fail(ErrorResponse("fail", "Connection Error"))
                Log.e("register_error", t?.message)
            }

        })
    }
}