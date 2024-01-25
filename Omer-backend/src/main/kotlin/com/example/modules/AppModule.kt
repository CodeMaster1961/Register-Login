package com.example.modules

import com.example.business.service.*
import com.example.data.repositories.*
import com.example.data.repositoryImplementations.*
import org.koin.dsl.*

/**
 * @author Ömer Aynaci
 * app module for koin
 */
val appModule = module {
    single<UserRepository> { UserRepositoryImplementation() }
    single { UserService(get()) }
}