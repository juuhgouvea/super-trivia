package com.juuhgouvea.supertrivia.models.responses.errors

import android.util.Log
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit

data class RegisterErrorResponse(
        var data: ResponseData
): ErrorResponse("", "") {
    data class ResponseData(
        var errors: ResponseErrors
    ) {
        data class ResponseErrors(
            var name: List<String>?,
            var email: List<String>?,
            var password: List<String>?,
        )
    }

    companion object {
        @Throws(Exception::class)
        fun parseJson(retrofitInstance: Retrofit, json: ResponseBody?): RegisterErrorResponse? {
            var converter: Converter<ResponseBody, RegisterErrorResponse> = retrofitInstance.responseBodyConverter(RegisterErrorResponse::class.java, Annotation::class.java.annotations)
            val errorResponse: RegisterErrorResponse? = converter.convert(json)
            return errorResponse
        }
    }
}
