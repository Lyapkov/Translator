package com.lyapkov.translator.view.base

import com.lyapkov.translator.model.data.AppState

interface View {

    fun renderData(appState: AppState)

}
