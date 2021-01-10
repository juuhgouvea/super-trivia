package com.juuhgouvea.supertrivia.models.responses.errors

import android.util.Log
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit

data class ErrorResponse(
    var status: String,
    var message: String
) {
    companion object {
        @Throws(Exception::class)
        fun parseJson(retrofitInstance: Retrofit, json: ResponseBody?): ErrorResponse? {
            var converter: Converter<ResponseBody, ErrorResponse> = retrofitInstance.responseBodyConverter(ErrorResponse::class.java, Annotation::class.java.annotations)
            val errorResponse: ErrorResponse? = converter.convert(json)
            return errorResponse
        }
    }
}