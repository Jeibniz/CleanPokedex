package com.jeibniz.cleanpokedex.data

/**
 * Wraps data and allows us to enrich it with error messages etc.
 * @param <T> Should be a model class. </T>
 **/
sealed class Result<out R>

object LoadingResult : Result<Nothing>()

data class SuccessResult<R>(val data: R) : Result<R>()

data class ErrorResult<R>(val errorMessage: String? = null) : Result<R>() {
    constructor(throwable: Throwable) : this(throwable.message)
}

fun <R, S> Result<R>.map(
    mapper: (value: R) -> S
): Result<S> {
    return when (this) {
        is SuccessResult<R> -> {
            SuccessResult(mapper(this.data))
        }
        is ErrorResult -> {
            ErrorResult(errorMessage)
        }
        is LoadingResult -> {
            LoadingResult
        }
    }
}
