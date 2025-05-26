package com.example.bus_schedule_app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bus_schedule_app.screens.busstop.BusStopDestination
import com.example.bus_schedule_app.screens.busstop.BusStopScreen
import com.example.bus_schedule_app.screens.home.HomeDestination
import com.example.bus_schedule_app.screens.home.HomeScreen

@Composable
fun ScheduleNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToBusStop = { navController.navigate("${BusStopDestination.route}/${it}") }
            )
        }
        composable(route = BusStopDestination.routeWithArgs,
            arguments = listOf(navArgument(BusStopDestination.busStopNameArgs) {
                type = NavType.StringType
            })
        ) {
            BusStopScreen(
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}