package com.lyapkov.repository

import com.lyapkov.model.data.AppState
import com.lyapkov.model.data.DataModel

class RoomDataBaseImplementation(private val historyDao: com.lyapkov.repository.room.HistoryDao) :
    DataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}

