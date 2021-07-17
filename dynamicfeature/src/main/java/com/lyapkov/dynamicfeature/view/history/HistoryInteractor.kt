package com.lyapkov.dynamicfeature.view.history

import com.lyapkov.core.viewmodel.Interactor
import com.lyapkov.model.data.AppState
import com.lyapkov.model.data.DataModel
import com.lyapkov.repository.Repository
import com.lyapkov.repository.RepositoryLocal

class HistoryInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
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
