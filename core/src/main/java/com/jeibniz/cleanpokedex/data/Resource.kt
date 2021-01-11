package com.jeibniz.cleanpokedex.data

/**
 * Wraps data and allows us to enrich it with error messages etc.
 * @param <T> Should be a model class. </T>
 **/
class Resource<T>(val status: Status, val data: T?, val throwable: Throwable?) {
    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    fun <R> map(
        mapper: (value: T) -> R ): Resource<R> {
        if (this.status == Status.SUCCESS) {
            return success(mapper(this.data!!))
        } else if (this.status == Status.ERROR) {
            return error(this.throwable!!)
        } else if (this.status == Status.LOADING) {
            return loading()
        } else {
            throw UnknownError("Resource has an invalid status!")
        }
    }

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(throwable: Throwable): Resource<T> {
            return Resource(Status.ERROR, null, throwable)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null)
        }
    }
}
