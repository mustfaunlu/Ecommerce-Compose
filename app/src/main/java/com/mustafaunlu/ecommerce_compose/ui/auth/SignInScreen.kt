package com.mustafaunlu.ecommerce_compose.ui.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mustafaunlu.ecommerce_compose.R
import com.mustafaunlu.ecommerce_compose.common.ScreenState
import com.mustafaunlu.ecommerce_compose.domain.entity.user.FirebaseSignInUserEntity
import com.mustafaunlu.ecommerce_compose.ui.Error
import com.mustafaunlu.ecommerce_compose.ui.uiData.UserInformationUiData
import com.mustafaunlu.ecommerce_compose.ui.viewModels.SigInViewModel
import kotlinx.coroutines.delay

@Composable
fun SignInRoute(
    viewModel: SigInViewModel = hiltViewModel(),
    onGoSignUpButtonClicked: () -> Unit,
    navigateToHomeScreen: () -> Unit,
) {
    val firebaseLoginState by viewModel.firebaseLoginState.observeAsState(initial = ScreenState.Loading)
    val onSignInButtonClicked = { user: FirebaseSignInUserEntity ->
        viewModel.loginWithFirebase(user)
    }
    SignInScreen(
        onGoSignUpButtonClicked = onGoSignUpButtonClicked,
        onSignInButtonClicked = onSignInButtonClicked,
    )

    SuccessScreen(
        loginState = firebaseLoginState,
        navigateToHomeScreen = navigateToHomeScreen,
    )
}

@Composable
fun SuccessScreen(
    loginState: ScreenState<UserInformationUiData>,
    navigateToHomeScreen: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (loginState) {
            is ScreenState.Success -> {
                navigateToHomeScreen()
            }
            ScreenState.Loading -> {
                // Loading()
            }

            is ScreenState.Error -> {
                Error(message = loginState.message)
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignInScreen(
    onSignInButtonClicked: (FirebaseSignInUserEntity) -> Unit,
    onGoSignUpButtonClicked: () -> Unit,
) {

    var showSnackbar by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = showSnackbar) {
        if (showSnackbar) {
            delay(2000)
            showSnackbar = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Spacer(modifier = Modifier.height(80.dp))

        val keyboardController = LocalSoftwareKeyboardController.current
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var showSheet by remember { mutableStateOf(false) }

        if (showSheet) {
            ResetPasswordBottomSheet {
                showSheet = false
            }
        }

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
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

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = stringResource(R.string.prompt_password)) },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    // Handle done action
                },
            ),
        )

        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Forgot password?",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.clickable(onClick = {
                showSheet = true
            }),
        )
        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                if (
                    email.isNotEmpty() &&
                    password.isNotEmpty()
                ) {
                    onSignInButtonClicked(
                        FirebaseSignInUserEntity(
                            email = email,
                            password = password,
                        ),
                    )
                } else {
                    keyboardController?.hide()
                    showSnackbar = true
                }
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = stringResource(R.string.action_sign_in))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = stringResource(R.string.don_t_have_account),
                fontSize = 12.sp,
                color = Color.Gray,
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = stringResource(R.string.sign_up),
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable(onClick = {
                    onGoSignUpButtonClicked()
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

@Preview
@Composable
fun SignInScreenPreview() {
}
