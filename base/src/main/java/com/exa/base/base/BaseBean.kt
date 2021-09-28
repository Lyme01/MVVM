package com.exa.base.base

import java.io.Serializable

open class BaseBean<T>(
        open var data: T? = null,
        open var errorCode: Int? = null,
        open var errorMsg: String? = null,
        open var error: Throwable? = null
) : Serializable {
    val isSuccess: Boolean
        get() = errorCode == 0
}

data class ApiSuccessResponse<T>(var response: T) : BaseBean<T>(data = response)

class ApiLoadingResponse<T> : BaseBean<T>()

class ApiEmptyResponse<T> : BaseBean<T>()

data class ApiFailedResponse<T>(override var errorCode: Int?, override var errorMsg: String?) : BaseBean<T>(errorCode = errorCode, errorMsg = errorMsg)

data class ApiErrorResponse<T>(override var error: Throwable?) : BaseBean<T>(error = error)
