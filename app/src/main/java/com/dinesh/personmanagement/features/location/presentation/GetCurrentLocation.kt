package com.dinesh.personmanagement.features.location.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task

@SuppressLint("MissingPermission")
@Composable
fun GetCurrentLocation(
    context: Context,
    onLocationRetrieved: (Location) -> Unit
) {
    var hasLocationPermission by remember { mutableStateOf(false) }

    val locationPermissionRequest = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            hasLocationPermission = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true &&
                    permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        }
    )

    if (!hasLocationPermission) {
        LaunchedEffect(Unit) {
            locationPermissionRequest.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    } else {
        val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        val locationTask: Task<Location> = fusedLocationClient.lastLocation

        locationTask
            .addOnSuccessListener { location: Location? ->
                location?.let {
                    onLocationRetrieved(it)
                }
            }
            .addOnFailureListener { exception ->
                Log.d("LocationScreen", "getCurrentLocation: ${exception.message}")
            }
    }
}
