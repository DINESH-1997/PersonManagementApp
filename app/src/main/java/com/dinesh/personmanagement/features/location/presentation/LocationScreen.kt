package com.dinesh.personmanagement.features.location.presentation

import CheckAndRequestLocationServices
import android.Manifest
import android.location.Location
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun LocationScreen() {
    val context = LocalContext.current
    var location by remember { mutableStateOf<Location?>(null) }

    var isLocationPermissionGranted by remember { mutableStateOf(false) }
    var isLocationEnabled by remember { mutableStateOf(false) }

    //permissions
    val permissions = listOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
    )

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { results ->
        isLocationPermissionGranted = results[Manifest.permission.ACCESS_FINE_LOCATION] == true &&
                results[Manifest.permission.ACCESS_COARSE_LOCATION] == true
    }

    Scaffold { padding ->
        Column(
            modifier = Modifier.padding(padding).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            location?.let {
                Text(text = "Latitude: ${it.latitude}, Longitude: ${it.longitude}")
            } ?: run {
                Text(text = "Fetching location...")
            }
            Button(
                onClick = {
                    if (!isLocationPermissionGranted) {
                        requestPermissionLauncher.launch(
                            input = permissions.toTypedArray()
                        )
                    }
                }
            ) {
                Text("Get Current Location")
            }

            if (isLocationPermissionGranted) {
                CheckAndRequestLocationServices(
                    context,
                    onLocationEnabled = { isLocationEnabled = true },
                    onLocationDisabled = {}
                )
            }

            if (isLocationPermissionGranted && isLocationEnabled) {
                GetCurrentLocation(
                    context,
                    onLocationRetrieved = {
                    location = it
                })
            }
        }
    }
}