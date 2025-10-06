package com.example.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.shoppinglist.ui.screen.*
import com.example.shoppinglist.ui.theme.ShoppingListTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingListTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    ShoppingListApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun ShoppingListApp() {
    val navController: NavHostController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Menu", modifier = Modifier.padding(16.dp))
                NavigationDrawerItem(
                    label = { Text("Setting") },
                    selected = currentRoute == Screen.Setting.route,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate(Screen.Setting.route) {
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Shopping List App") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            },

            bottomBar = {
                NavigationBar {
                    NavigationBarItem(
                        selected = currentRoute == Screen.Home.route,
                        onClick = {
                            navController.navigate(Screen.Home.route) {
                                launchSingleTop = true
                                popUpTo(Screen.Home.route)
                            }
                        },
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                        label = { Text("Home") }
                    )
                    NavigationBarItem(
                        selected = currentRoute == Screen.Profile.route,
                        onClick = {
                            navController.navigate(Screen.Profile.route) {
                                launchSingleTop = true
                            }
                        },
                        icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                        label = { Text("Profile") }
                    )
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Screen.Home.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(
                    route = Screen.Home.route,
                    enterTransition = {
                        slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(300)) + fadeIn()
                    },
                    exitTransition = {
                        slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(300)) + fadeOut()
                    }
                ) {
                    ShoppingListScreen()
                }

                composable(
                    route = Screen.Profile.route,
                    enterTransition = {
                        slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(300)) + fadeIn()
                    },
                    exitTransition = {
                        slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(300)) + fadeOut()
                    },
                    popEnterTransition = {
                        slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(300)) + fadeIn()
                    },
                    popExitTransition = {
                        slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(300)) + fadeOut()
                    }
                ) {
                    ProfileScreen()
                }

                composable(
                    route = Screen.Setting.route,
                    enterTransition = {
                        slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(300)) + fadeIn()
                    },
                    exitTransition = {
                        slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(300)) + fadeOut()
                    },
                    popEnterTransition = {
                        slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(300)) + fadeIn()
                    },
                    popExitTransition = {
                        slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(300)) + fadeOut()
                    }
                ) {
                    SettingScreen()
                }
            }
        }
    }
}
