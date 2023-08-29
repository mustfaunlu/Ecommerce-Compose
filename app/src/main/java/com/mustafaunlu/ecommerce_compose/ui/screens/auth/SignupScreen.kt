package com.mustafaunlu.ecommerce_compose.ui.screens.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mustafaunlu.ecommerce_compose.R
import com.mustafaunlu.ecommerce_compose.common.ScreenState
import com.mustafaunlu.ecommerce_compose.ui.Error
import com.mustafaunlu.ecommerce_compose.ui.uiData.UserInformationUiData
import com.mustafaunlu.ecommerce_compose.ui.screens.auth.viewModels.SignUpViewModel
import kotlinx.coroutines.delay

@Composable
fun SignUpRoute(
    viewModel: SignUpViewModel = hiltViewModel(),
    navigateToSignInScreen: () -> Unit,
) {
    val signUpState by viewModel.signUp.observeAsState(initial = ScreenState.Loading)
    val onCreateAccountButtonClicked = { user: UserInformationUiData ->
        viewModel.signUp(user)
    }
    SignUpScreen(
        onCreateAccountButtonClicked = onCreateAccountButtonClicked,
        navigateToSignInScreen = navigateToSignInScreen,
    )

    when (signUpState) {
        is ScreenState.Loading -> {}
        is ScreenState.Error -> {
            Error(message = (signUpState as ScreenState.Error).message)}
        is ScreenState.Success -> {
            navigateToSignInScreen()
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignUpScreen(
    onCreateAccountButtonClicked: (UserInformationUiData) -> Unit,
    navigateToSignInScreen: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Spacer(modifier = Modifier.heightIn(56.dp))

        var showSnackbar by remember {
            mutableStateOf(false)
        }

        LaunchedEffect(key1 = showSnackbar) {
            if (showSnackbar) {
                delay(2000)
                showSnackbar = false
            }
        }

        var name by remember { mutableStateOf("") }
        var surname by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var phone by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        val keyboardController = LocalSoftwareKeyboardController.current
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            maxLines = 1,
            label = { Text(text = stringResource(R.string.your_name_hint)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    // Handle next action
                },
            ),
        )

        OutlinedTextField(
            value = surname,
            onValueChange = { surname = it },
            maxLines = 1,
            label = { Text(text = stringResource(R.string.your_surname_hint)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    // Handle next action
                },
            ),
        )

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            maxLines = 1,
            label = { Text(text = stringResource(R.string.your_phone)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next,
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    // Handle next action
                },
            ),
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            maxLines = 1,
            label = { Text(text = stringResource(R.string.prompt_email)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    // Handle next action
                },
            ),
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            maxLines = 1,
            label = { Text(text = stringResource(R.string.prompt_password)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next,
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    // Handle next action
                },
            ),
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                if (
                    name.isNotEmpty() &&
                    surname.isNotEmpty() &&
                    email.isNotEmpty() &&
                    phone.isNotEmpty() &&
                    password.isNotEmpty()
                ) {
                    onCreateAccountButtonClicked(
                        UserInformationUiData(
                            id = "",
                            name = name,
                            surname = surname,
                            email = email,
                            phone = phone,
                            password = password,
                            image = "",
                            token = "",
                        ),
                    )
                } else {
                    keyboardController?.hide()
                    showSnackbar = true
                }
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = stringResource(R.string.create_account))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = stringResource(R.string.already_have_account),
                fontSize = 12.sp,
                color = Color.Gray,
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = stringResource(R.string.log_in),
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable(onClick = {
                    navigateToSignInScreen()
                }),
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        if (showSnackbar) {
            androidx.compose.material.Snackbar(
                modifier = Modifier.padding(16.dp),
                backgroundColor = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.medium
                ) {
                Text(
                    text = stringResource(id = R.string.please_not_blanks),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

        }
    }
}