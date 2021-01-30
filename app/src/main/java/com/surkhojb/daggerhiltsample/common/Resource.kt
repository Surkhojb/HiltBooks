package com.surkhojb.daggerhiltsample.common

import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(exception: Exception, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, handleError(exception))
        }

        fun <T> error(msg: String, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}

private fun handleError(exception: Exception): String {
    return when(exception){
        is HttpException -> handleHttpError(exception.code())
        is IOException -> "Error: ${exception.localizedMessage}"
        else -> exception.localizedMessage
    }
}

private fun  handleHttpError(code: Int): String{
    return when(code){
        400 -> "Error: Bad request, check your params"
        401 -> "Error: You have not permission to perform this action."
        404 -> "Error: Resource not found at server"
        408 -> "Error: Timeout performing call."
        500 -> "Server error: Internal server error happened."
        501 -> "Server error: Action not implemented at server yet."
        502 -> "Server error: Bad gateway."
        503 -> "Server error: Service unavaliable, try it later"
        else -> "Coded error not handled."
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}