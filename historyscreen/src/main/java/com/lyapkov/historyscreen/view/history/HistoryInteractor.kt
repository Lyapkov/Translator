package geekbrains.ru.history.view.history

import com.lyapkov.core.viewmodel.Interactor
import com.lyapkov.model.data.AppState
import com.lyapkov.model.data.dto.SearchResultDto
import com.lyapkov.repository.Repository
import com.lyapkov.repository.RepositoryLocal
import com.lyapkov.translator.utils.mapSearchResultToResult

class HistoryInteractor(
    private val repositoryRemote: Repository<List<SearchResultDto>>,
    private val repositoryLocal: RepositoryLocal<List<SearchResultDto>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            mapSearchResultToResult(
                if (fromRemoteSource) {
                    repositoryRemote
                } else {
                    repositoryLocal
                }.getData(word)
            )
        )
    }
}
