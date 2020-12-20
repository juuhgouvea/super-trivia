package com.juuhgouvea.supertrivia.network.services

import com.juuhgouvea.supertrivia.models.User
import com.juuhgouvea.supertrivia.models.responses.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserService {
    @POST("auth")
    @Headers("Content-Type: application/json")
    fun login(@Body user: User): Call<UserResponse>
}