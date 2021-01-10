package com.jeibniz.cleanpokedex.data.pokemon

/**
 * Wraps data and allows us to enrich it with error messages etc.
 * @param <T> Should be a model class. </T>
 **/
class Resource<T>(val status: Status, val data: T?, val throwable: Throwable?) {
    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    companion object {
        fun <T> success(data: T?): Resource<T?> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(throwable: Throwable): Resource<T?> {
            return Resource(Status.ERROR, null, throwable)
        }

        fun <T> loading(): Resource<T?> {
            return Resource(Status.LOADING, null, null)
        }
    }
}
