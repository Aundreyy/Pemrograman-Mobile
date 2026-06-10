package com.example.modul5compose.core.network

import retrofit2.Response

suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): ApiResult<T> {
    return try {
        val response = apiCall()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                ApiResult.Success(body)
            } else {
                ApiResult.Error("Response body is null", response.code())
            }
        } else {
            ApiResult.Error(response.message() ?: "Unknown error", response.code())
        }
    } catch (e: Exception) {
        ApiResult.Exception(e)
    }
}