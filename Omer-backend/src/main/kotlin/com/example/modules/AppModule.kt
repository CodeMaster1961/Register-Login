package com.example.modules

import com.example.business.service.*
import com.example.data.repositories.*
import com.example.data.repositoryImplementations.*
import org.koin.dsl.*
import kotlin.math.*

/**
 * @author Ömer Aynaci
 * app module for koin
 */
val appModule = module {
    single<UserRepository> { UserRepositoryImplementation() }
    single<GameRepository> { GameRepositoryImplementation() }
    single<ClickShopItemRepository> { ClickShopRepositoryImplementation() }
    single<AchievementRepository> { AchievementRepositoryImplementation() }
    single { AchievementService(get()) }
    single { ShopItemService(get()) }
    single { GameService(get()) }
    single { UserService(get()) }
}