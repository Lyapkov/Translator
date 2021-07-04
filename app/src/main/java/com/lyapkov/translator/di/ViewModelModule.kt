package com.lyapkov.translator.di

import com.lyapkov.translator.model.data.DataModel
import com.lyapkov.translator.model.datasource.RetrofitImplementation
import com.lyapkov.translator.model.datasource.RoomDataBaseImplementation
import com.lyapkov.translator.model.repository.Repository
import com.lyapkov.translator.model.repository.RepositoryImplementation
import com.lyapkov.translator.view.main.MainInteractor
import com.lyapkov.translator.view.main.MainViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single<Repository<List<DataModel>>>(named(NAME_REMOTE)) {
        RepositoryImplementation(
            RetrofitImplementation()
        )
    }
    single<Repository<List<DataModel>>>(named(NAME_LOCAL)) {
        RepositoryImplementation(
            RoomDataBaseImplementation()
        )
    }
}

val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { MainViewModel(get()) }
}