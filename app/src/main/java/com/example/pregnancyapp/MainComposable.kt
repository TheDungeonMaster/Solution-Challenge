package com.example.pregnancyapp

import android.media.tv.TvContract.Channels.Logo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pregnancyapp.aichat.ChatScreen
import com.example.pregnancyapp.aichat.MainChatScreen
import com.example.pregnancyapp.authentication_logic.AuthViewModel
import com.example.pregnancyapp.authentication_logic.User
import com.example.pregnancyapp.pages.CheckUpQuestions
import com.example.pregnancyapp.pages.CheckUpQuestionsViewModel
import com.example.pregnancyapp.pages.FailPregnancyQuestion
import com.example.pregnancyapp.pages.FirstPregnancyQuestion
import com.example.pregnancyapp.pages.LoginPage
import com.example.pregnancyapp.pages.LogoWelcomePage
import com.example.pregnancyapp.pages.MedicalData
import com.example.pregnancyapp.pages.MommyQuestionnairePage
import com.example.pregnancyapp.pages.NumOfFailedPregnancies
import com.example.pregnancyapp.pages.NumOfPregnancies
import com.example.pregnancyapp.pages.PersonalData
import com.example.pregnancyapp.pages.PersonalDataViewModel
import com.example.pregnancyapp.pages.PreExistingMedConditions
import com.example.pregnancyapp.pages.Profile
import com.example.pregnancyapp.pages.SignUpPage
import com.example.pregnancyapp.pages.WelcomePage
import com.example.pregnancyapp.pages.WelcomePageViewModel


// MainComposable.kt
@Composable
fun MainComposable(authViewModel: AuthViewModel) {
    val navController = rememberNavController()
    val viewModel: WelcomePageViewModel = viewModel()

    NavHost(navController = navController , startDestination =
    "logowelcomepage") {
        addLogoWelcomePage(navController)
        addWelcomePage(navController)
        addLoginPage(navController)
        addSignUpPage(navController)
        addMommyQuestionnairePage(navController)
        addNumOfPregnanciesQuestion(navController , authViewModel)
        addFailedPregnanciesQuestion(navController , authViewModel)
        addNumOfPregnanciesAnswer(navController , authViewModel)
        addFailedPregnanciesAnswer(navController , authViewModel)
        addPrevMedicalConditionsQuestion(navController , authViewModel)
        addProfile(navController, authViewModel)
        addPersonalDataPage(navController, authViewModel)
        addMedicalDataPage(navController, authViewModel)
        addJournalPage(navController)
        addChatPage(navController)

    }
}

fun NavGraphBuilder.addLogoWelcomePage(
    navController: NavController
) {
    composable("logowelcomepage") {
        LogoWelcomePage(navController = navController)
    }
}

fun NavGraphBuilder.addWelcomePage(navController: NavController) {
    composable("welcome") {
        val viewModel: WelcomePageViewModel = viewModel()
        viewModel.getUserData()
        WelcomePage(
            navController,
            viewModel.user.value.dayOfPregnancy?.toInt() ?: 0 ,
            viewModel.toWeekDay(),
            viewModel = viewModel
        ) { navController.navigate("journal") }
    }
}

fun NavGraphBuilder.addJournalPage(navController: NavController) {
    composable("journal") {
        val viewModel: CheckUpQuestionsViewModel = viewModel()
        CheckUpQuestions(viewModel = viewModel, navController = navController)
    }
}


fun NavGraphBuilder.addLoginPage(navController: NavController) {
    composable("login") {
        LoginPage(navController)
    }
}

fun NavGraphBuilder.addSignUpPage(navController: NavController) {
    composable("signup") {
        SignUpPage(navController)
    }
}

fun NavGraphBuilder.addMommyQuestionnairePage(
    navController: NavController
) {
    composable("mommyquestionnairepage") {
        MommyQuestionnairePage(navController)
    }
}

fun NavGraphBuilder.addNumOfPregnanciesQuestion(
    navController: NavController , authViewModel: AuthViewModel
) {
    composable("questionpregnancynumber") {
        FirstPregnancyQuestion(navController , authViewModel)
    }
}

fun NavGraphBuilder.addFailedPregnanciesQuestion(
    navController: NavController , authViewModel: AuthViewModel
) {
    composable("questionfailedpregnancies") {
        FailPregnancyQuestion(navController , authViewModel)
    }
}

fun NavGraphBuilder.addNumOfPregnanciesAnswer(
    navController: NavController ,
    authViewModel: AuthViewModel
) {
    composable("pregnancynumber") {
        NumOfPregnancies(navController , authViewModel)
    }
}

fun NavGraphBuilder.addFailedPregnanciesAnswer(
    navController: NavController ,
    authViewModel: AuthViewModel
) {
    composable("failnumber") {
        NumOfFailedPregnancies(navController , authViewModel)
    }
}

fun NavGraphBuilder.addPrevMedicalConditionsQuestion(
    navController: NavController ,
    authViewModel: AuthViewModel
) {
    composable("medicalconditions") {
        PreExistingMedConditions(navController , authViewModel)
    }
}
fun NavGraphBuilder.addProfile(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    composable("profile") {
        Profile(navController, authViewModel)
    }
}

fun NavGraphBuilder.addPersonalDataPage(
    navController: NavController,
    authViewModel: AuthViewModel
) {

    composable("personaldata") {
        PersonalData(navController)
    }
}

fun NavGraphBuilder.addMedicalDataPage(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    composable("medicaldata") {
        MedicalData(navController, authViewModel)
    }
}

fun NavGraphBuilder.addChatPage(navController: NavController) {
    composable("chat") {
        MainChatScreen(navController)
    }
}
