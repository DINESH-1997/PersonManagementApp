import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.IntentSender
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.SettingsClient

@Composable
fun CheckAndRequestLocationServices(
    context: Context,
    onLocationEnabled: () -> Unit,
    onLocationDisabled: () -> Unit
) {
    var showRequestLocationSettings by remember { mutableStateOf(false) }

    val locationSettingsRequest = LocationSettingsRequest.Builder()
        .addLocationRequest(LocationRequest.create())
        .build()

    val settingsClient: SettingsClient = LocationServices.getSettingsClient(context)

    val locationSettingsLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            onLocationEnabled()
        } else {
            onLocationDisabled()
        }
    }

    LaunchedEffect(showRequestLocationSettings) {
        if (showRequestLocationSettings) {
            settingsClient.checkLocationSettings(locationSettingsRequest)
                .addOnSuccessListener {
                    onLocationEnabled()
                }
                .addOnFailureListener { exception ->
                    if (exception is ResolvableApiException) {
                        try {
                            val intentSenderRequest = IntentSenderRequest.Builder(exception.resolution).build()
                            locationSettingsLauncher.launch(intentSenderRequest)
                        } catch (sendEx: IntentSender.SendIntentException) {
                            // Handle the error
                        }
                    } else {
                        onLocationDisabled()
                    }
                }
        }
    }



    showRequestLocationSettings = true
}
