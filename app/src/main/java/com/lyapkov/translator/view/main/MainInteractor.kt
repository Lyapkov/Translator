package com.lyapkov.translator.view.main

import com.lyapkov.translator.model.data.AppState
import com.lyapkov.translator.model.data.DataModel
import com.lyapkov.translator.model.repository.Repository
import com.lyapkov.translator.viewmodel.Interactor

class MainInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: Repository<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}
