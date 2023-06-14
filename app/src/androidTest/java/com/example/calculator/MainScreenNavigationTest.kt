package com.example.calculator

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.calculator.nav.NavigationComponent
import com.example.calculator.nav.SecondScreenRoute
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class MainScreenNavigationTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var navController: NavHostController

    @Before
    fun setup() {
        hiltRule.inject()

        composeTestRule.activity.setContent {
            navController = rememberNavController()
            NavigationComponent(navHostController = navController, mainActivityViewModel = hiltViewModel())
        }
    }

    @Test
    fun testNavigatingFromFirstScreenToSecond() {
        composeTestRule.apply {
            onNodeWithText("5").performClick()
            onNodeWithText("*").performClick()
            onNodeWithText("3").performClick()
            onNodeWithText("0").performClick()
            onNodeWithText("=").performClick()

            waitForIdle()
            val route = navController.currentDestination?.route
            Assert.assertEquals(route, SecondScreenRoute.route)
        }
    }
}