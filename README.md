# Kids TV Launcher

Kids TV Launcher is an Android TV launcher designed to provide a safe and controlled environment for kids. It allows only approved applications to be accessed and includes a PIN-protected exit feature.

## Features
- **Custom Launcher:** Replaces the default home screen with a kid-friendly launcher.
- **Approved Apps Only:** Displays only a predefined list of applications.
- **D-Pad Navigation:** Supports TV remote navigation.
- **PIN-Protected Exit:** Prevents kids from exiting the launcher without a PIN.
- **Minimalist UI:** Simple and distraction-free interface for kids.

## Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/your-repo/kids-tv-launcher.git
   ```
2. Open the project in Android Studio.
3. Ensure you have the latest SDK and dependencies installed.
4. Build and install the app on your Android TV device.

## Usage
1. Launch the **Kids TV Launcher** app.
2. The home screen will display only the approved applications.
3. Use the remote’s D-pad to navigate and select apps.
4. Press the **Exit Launcher** button to enter the PIN screen.
5. Enter the correct PIN (default: `1234`) to exit the launcher.

## Implementation Details
### Code Structure
- **`MainActivity.kt`**: Entry point of the app, initializes the launcher UI.
- **`KidsTVLauncher.kt`**: Core logic for displaying approved apps.
- **`PinScreen.kt`**: Handles PIN verification for exiting the launcher.

### Dependencies
This project uses several libraries to enhance functionality:
- **Jetpack Compose** for UI components.
- **Android TV Material 3** for TV-friendly UI elements.

Dependencies are managed using Gradle, with the key ones included in `build.gradle.kts`:
```kotlin
implementation(libs.androidx.core.ktx)
implementation(libs.androidx.appcompat)
implementation(platform(libs.androidx.compose.bom))
implementation(libs.androidx.ui.tooling.preview)
implementation(libs.androidx.tv.foundation)
implementation(libs.androidx.tv.material)
implementation(libs.androidx.lifecycle.runtime.ktx)
implementation(libs.androidx.activity.compose)
```

## Customization
### Adding Approved Apps
Modify the `approvedApps` list in `KidsTVLauncher.kt` to include the package names of the allowed applications:
```kotlin
val approvedApps = listOf(
    "com.google.android.youtube",
    "com.netflix.mediaclient",
    "com.disney.disneyplus"
)
```

### Changing the PIN
Update the `correctPin` variable in `PinScreen`:
```kotlin
val correctPin = "1234" // Change this to your desired PIN
```

### UI Customization
You can modify the UI by editing the Compose UI elements in `MainScreen.kt` to match your design preferences.

## Author
Developed by **Bhavesh Kumawat**.

