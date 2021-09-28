package com.exa.base.http
import androidx.lifecycle.Observer
import com.exa.base.base.*


abstract class IStateObserver<T>(private val uiView: IUiView? = null) : Observer<BaseBean<T>> {

    override fun onChanged(t: BaseBean<T>) {
        if (t is ApiLoadingResponse) {
            onShowLoading()
            return
        }
        when (t) {
            is ApiSuccessResponse -> onSuccess(t.response)
            is ApiEmptyResponse -> onDataEmpty()
            is ApiFailedResponse -> onFailed(t.errorCode!!)
            is ApiErrorResponse -> onError(t.error!!)
        }
        uiView?.dismissLoading()
        onComplete()
    }

    abstract fun onSuccess(data: T)

    abstract fun onDataEmpty()

    open fun onShowLoading() = uiView?.showLoading()

    abstract fun onError(e: Throwable)

    abstract fun onComplete()

    abstract fun onFailed(httpCode: Int)

}