package com.lyapkov.translator.presenter

import com.lyapkov.translator.model.data.AppState
import com.lyapkov.translator.view.base.View

interface Presenter<T : AppState, V : View> {

    fun attachView(view: V)

    fun detachView(view: V)

    fun getData(word: String, isOnline: Boolean)
}
