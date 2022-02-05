package com.jeibniz.cleanpokedex.data

/**
 * Wraps data and allows us to enrich it with error messages etc.
 * @param <T> Should be a model class. </T>
 **/
sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }

    fun <S> map(
        mapper: (value: R) -> S
    ): Result<S> {
        return when (this) {
            is Success<R> -> {
                Success(mapper(this.data as R))
            }
            is Error -> {
                Error(this.exception)
            }
            is Loading -> {
                Loading
            }
        }
    }
}

val Result<*>.succeeded
    get() = this is Result.Success && data != null
