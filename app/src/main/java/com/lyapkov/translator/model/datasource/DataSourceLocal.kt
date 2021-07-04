package com.lyapkov.translator.model.datasource

import com.lyapkov.translator.model.data.AppState

interface DataSourceLocal<T> : DataSource<T> {

    suspend fun saveToDB(appState: AppState)
}