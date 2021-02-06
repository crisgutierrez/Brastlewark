package com.example.brastlewark.util

import java.lang.Exception

sealed class DataState<out T> {

    data class Success<out T>(val data: T): DataState<T>()
    data class Failure(val exception: Exception): DataState<Nothing>()
    data class InProgress<out T>(val progressData: T? = null): DataState<T>()


}