package com.lyapkov.translator.di

import androidx.room.Room
import com.lyapkov.translator.model.data.DataModel
import com.lyapkov.translator.model.datasource.RetrofitImplementation
import com.lyapkov.translator.model.datasource.RoomDataBaseImplementation
import com.lyapkov.translator.model.repository.Repository
import com.lyapkov.translator.model.repository.RepositoryImplementation
import com.lyapkov.translator.model.repository.RepositoryImplementationLocal
import com.lyapkov.translator.model.repository.RepositoryLocal
import com.lyapkov.translator.room.HistoryDataBase
import com.lyapkov.translator.view.history.HistoryInteractor
import com.lyapkov.translator.view.history.HistoryViewModel
import com.lyapkov.translator.view.main.MainInteractor
import com.lyapkov.translator.view.main.MainViewModel
import org.koin.dsl.module

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>> { RepositoryImplementation(RetrofitImplementation()) }
    single<RepositoryLocal<List<DataModel>>> {
        RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
    }
}

val mainScreen = module {
    factory { MainViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}