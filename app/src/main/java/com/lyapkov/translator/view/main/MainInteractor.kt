package com.lyapkov.translator.view.main

import com.lyapkov.core.viewmodel.Interactor
import com.lyapkov.model.data.AppState
import com.lyapkov.model.data.dto.SearchResultDto
import com.lyapkov.repository.Repository
import com.lyapkov.repository.RepositoryLocal
import com.lyapkov.translator.utils.mapSearchResultToResult

class MainInteractor(
    private val repositoryRemote: Repository<List<SearchResultDto>>,
    private val repositoryLocal: RepositoryLocal<List<SearchResultDto>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState
        if (fromRemoteSource) {
            appState = AppState.Success(mapSearchResultToResult(repositoryRemote.getData(word)))
            repositoryLocal.saveToDB(appState)
        } else {
            appState = AppState.Success(mapSearchResultToResult(repositoryLocal.getData(word)))
        }
        return appState
    }
}
