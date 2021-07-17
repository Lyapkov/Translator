package com.lyapkov.translator.view.main

import com.lyapkov.model.data.AppState
import com.lyapkov.model.data.DataModel
import com.lyapkov.repository.Repository
import com.lyapkov.repository.RepositoryLocal
import com.lyapkov.core.viewmodel.Interactor

class MainInteractor(
    private val repositoryRemote: com.lyapkov.repository.Repository<List<com.lyapkov.model.data.DataModel>>,
    private val repositoryLocal: com.lyapkov.repository.RepositoryLocal<List<com.lyapkov.model.data.DataModel>>
) : Interactor<com.lyapkov.model.data.AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): com.lyapkov.model.data.AppState {
        val appState: com.lyapkov.model.data.AppState
        if (fromRemoteSource) {
            appState = com.lyapkov.model.data.AppState.Success(repositoryRemote.getData(word))
            repositoryLocal.saveToDB(appState)
        } else {
            appState = com.lyapkov.model.data.AppState.Success(repositoryLocal.getData(word))
        }
        return appState
    }
}
