package com.mustafaunlu.ecommerce_compose.ui.profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mustafaunlu.ecommerce_compose.R
import com.mustafaunlu.ecommerce_compose.common.ScreenState
import com.mustafaunlu.ecommerce_compose.ui.Error
import com.mustafaunlu.ecommerce_compose.ui.Loading
import com.mustafaunlu.ecommerce_compose.ui.uiData.UserInformationUiData
import com.mustafaunlu.ecommerce_compose.ui.viewModels.ProfileViewModel

@Composable
fun ProfileRoute(
    viewModel: ProfileViewModel = hiltViewModel(),
    logout: () -> Unit,
) {
    val profileState by viewModel.userInfos.observeAsState(initial = ScreenState.Loading)

    ProfileScreen(profileState = profileState, logout = logout)
}

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    profileState: ScreenState<UserInformationUiData>,
    logout: () -> Unit,
) {
    Box(modifier = modifier.fillMaxSize()) {
        when (profileState) {
            is ScreenState.Success -> {
                SuccessScreen(
                    profileState = profileState.uiData,
                    logout = logout,
                )
            }

            is ScreenState.Error -> {
                Error(message = R.string.error)
            }

            ScreenState.Loading -> {
                Loading()
            }
        }
    }
}

@Composable
fun SuccessScreen(
    profileState: UserInformationUiData,
    logout: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        var selectedImageUri by remember {
            mutableStateOf<Uri?>(null)
        }

        val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri -> selectedImageUri = uri },
        )

        var darkTheme by remember {
            mutableStateOf(false)
        }

        AsyncImage(
            model = selectedImageUri,
            contentScale = ContentScale.Crop,
            error = painterResource(id = R.drawable.ic_plus),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .clickable {
                    singlePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly),
                    )
                }
                .size(100.dp)
                .clip(CircleShape)
                .border(BorderStroke(1.dp, MaterialTheme.colorScheme.primary), CircleShape)
                .background(MaterialTheme.colorScheme.onPrimary)
                .align(Alignment.CenterHorizontally),
        )

        Text(
            text = profileState.name,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
        )

        Text(
            text = profileState.surname,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
        )

        Text(
            text = profileState.email,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
        )

        Text(
            text = profileState.phone,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp),
        ) {
            Text(
                text = "Switch Theme",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .weight(1f)
                    .padding(15.dp),
            )

            Switch(
                checked = darkTheme,
                onCheckedChange = { darkTheme = !darkTheme },
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(15.dp),
            )
        }

        Button(
            onClick = { logout() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
        ) {
            Text(text = "Logout")
        }
    }
}
