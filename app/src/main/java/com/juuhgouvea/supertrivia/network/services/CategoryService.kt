package com.juuhgouvea.supertrivia.network.services

import com.juuhgouvea.supertrivia.models.responses.CategoryResponse
import retrofit2.Call
import retrofit2.http.GET

interface CategoryService {
    @GET("categories")
    fun getCategories(): Call<CategoryResponse>
}