package com.lyapkov.translator.model.repository

import com.lyapkov.translator.model.data.AppState

interface RepositoryLocal<T> : Repository<T> {

    suspend fun saveToDB(appState: AppState)
}