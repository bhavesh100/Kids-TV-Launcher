package com.bhavesh.kidstvlauncher

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.Settings
import android.view.KeyEvent
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Button
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.NonInteractiveSurfaceDefaults
import androidx.tv.material3.Surface
import androidx.tv.material3.Text

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun KidsTVLauncher() {
    val context = LocalContext.current
    val focusRequester = remember { FocusRequester() }

    val approvedApps = listOf("com.google.android.youtube", "com.netflix.mediaclient") // Allowed apps
    val apps = getInstalledApps(context, approvedApps)
    var showPinScreen by remember { mutableStateOf(false) }

    Surface(modifier = Modifier.fillMaxSize(), colors =  NonInteractiveSurfaceDefaults.colors(Color(0xFF4CAF50))) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                "Kids TV Launcher",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            )

            // Display approved apps in a column

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
                    .focusable()
                    .onKeyEvent { keyEvent ->
                        when (keyEvent.nativeKeyEvent.keyCode) {
                            KeyEvent.KEYCODE_DPAD_DOWN -> {
                                focusRequester.requestFocus()
                                true
                            }
                            KeyEvent.KEYCODE_DPAD_UP -> {
                                focusRequester.requestFocus()
                                true
                            }
                            KeyEvent.KEYCODE_DPAD_LEFT, KeyEvent.KEYCODE_DPAD_RIGHT -> {
                                focusRequester.requestFocus()
                                true
                            }
                            else -> false
                        }
                    }
            )
            {
                items(apps.size) { index ->
                    val (appLabel, packageName) = apps[index]
                    AppItem(appName = appLabel, onClick = { launchApp(context, packageName) })
                }
            }

            Button(
                onClick = { showPinScreen = true },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Exit Launcher")
            }
        }
//        PinScreen(onDismiss = { /*TODO*/ }) {
//
//        }
        if (showPinScreen) {
            PinScreen(
                onDismiss = { showPinScreen = false },
                onConfirm = { exitLauncher(context) }
            )
        }
    }

}
fun getInstalledApps(context: Context, approvedApps: List<String>): List<Pair<String, String>> {
    val pm = context.packageManager
    return pm.getInstalledApplications(PackageManager.GET_META_DATA)
        .filter { it.packageName in approvedApps }
        .map { it.loadLabel(pm).toString() to it.packageName }
}

fun launchApp(context: Context, packageName: String) {
    val launchIntent = context.packageManager.getLaunchIntentForPackage(packageName)
    launchIntent?.let { context.startActivity(it) }
}

fun exitLauncher(context: Context) {
    val packageManager = context.packageManager
    packageManager.clearPackagePreferredActivities(context.packageName)

    val intent = Intent(Settings.ACTION_HOME_SETTINGS)
    context.startActivity(intent)
}