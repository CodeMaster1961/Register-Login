package com.example.plugins


import com.example.business.service.*
import com.example.controllers.*
import com.example.routes.*
import com.example.security.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.*


/**
 * the routes of the resources
 * @author Ömer Aynaci
 */
fun Application.configureRouting() {
    val userService by inject<UserService>()
    val gameService by inject<GameService>()
    val shopItemService by inject<ShopItemService>()
    val achievementService by inject<AchievementService>()
    val achievementController = AchievementController(achievementService)
    val shopItemController = ShopItemController(shopItemService)
    val gameController = GameController(gameService)
    val userController = UserController(userService)
    Security().apply {
        authenticateUser()
//        authenticateUserBasic()
    }
    routing {
        UserRoutes(userController).createUser(this)
        UserRoutes(userController).login(this)
        UserRoutes(userController).getAuthenticatedUser(this)
        UserRoutes(userController).getUsers(this)
        GameRoutes(gameController).getAllGames(this)
        GameRoutes(gameController).getGameById(this)
        ShopItemRoutes(shopItemController).getAllShopItems(this)
        AchievementRoutes(achievementController).getAllAchievements(this)
    }
}
