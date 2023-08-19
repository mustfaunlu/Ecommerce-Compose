package com.mustafaunlu.ecommerce_compose.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mustafaunlu.ecommerce_compose.R

@Composable
fun ProfileScreen() {
    val profileName by remember { mutableStateOf("John") }
    val profileSurname by remember { mutableStateOf("Doe") }
    val profileMail by remember { mutableStateOf("john.doe@example.com") }
    val profilePhone by remember { mutableStateOf("+1 (555) 123-4567") }
    val switchTheme by remember { mutableStateOf("Switch Theme") }
    var isDarkTheme by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_plus),
            contentDescription = "Profile Picture",
            modifier = Modifier.clickable { /* Handle click */ }
                .size(100.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
                .padding(32.dp)
                .align(Alignment.CenterHorizontally),
        )

        Text(
            text = profileName,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
        )

        Text(
            text = profileSurname,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
        )

        Text(
            text = profileMail,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
        )

        Text(
            text = profilePhone,
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
                text = switchTheme,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .weight(1f)
                    .padding(15.dp),
            )

            Switch(
                checked = isDarkTheme,
                onCheckedChange = { isDarkTheme = it },
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(15.dp),
            )
        }

        Button(
            onClick = { /* Handle logout */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
        ) {
            Text(text = "Logout")
        }
    }
}

@Composable
@Preview
fun ProfileScreenPreview() {
    ProfileScreen()
}
