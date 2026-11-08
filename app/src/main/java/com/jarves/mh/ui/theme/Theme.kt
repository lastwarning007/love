package com.jarves.mh.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Professional High-Tech Cyber & Modern Palette (Replacing old black & orange)
val CyberElectricCyan = Color(0xFF00E5FF)
val CyberIndigo = Color(0xFF6366F1)
val CyberSkyBlue = Color(0xFF38BDF8)
val CyberEmerald = Color(0xFF10B981)
val CyberNavyDark = Color(0xFF090D16)
val CyberSlateSurface = Color(0xFF0F172A)
val CyberSurfaceVariant = Color(0xFF1E293B)
val CyberBorderOutline = Color(0xFF334155)

// Kept for seamless symbol compatibility across all screens
val PocketOrange = CyberElectricCyan
val PocketBlue = CyberSkyBlue
val PocketGreen = CyberEmerald
val PocketBackground = CyberNavyDark
val PocketSurface = CyberSlateSurface
val PocketSurfaceVariant = CyberSurfaceVariant
val PocketOutline = CyberBorderOutline

private val DarkColors = darkColorScheme(
    primary = CyberElectricCyan,
    onPrimary = Color(0xFF002026),
    primaryContainer = Color(0xFF004D5A),
    onPrimaryContainer = Color(0xFFB8F5FF),
    secondary = CyberIndigo,
    onSecondary = Color(0xFF1E1B4B),
    secondaryContainer = Color(0xFF312E81),
    onSecondaryContainer = Color(0xFFE0E7FF),
    tertiary = CyberSkyBlue,
    onTertiary = Color(0xFF082F49),
    background = CyberNavyDark,
    onBackground = Color(0xFFF1F5F9),
    surface = CyberSlateSurface,
    onSurface = Color(0xFFF1F5F9),
    surfaceVariant = CyberSurfaceVariant,
    onSurfaceVariant = Color(0xFF94A3B8),
    outline = CyberBorderOutline,
    outlineVariant = Color(0xFF1E293B),
)

private val LightColors = lightColorScheme(
    primary = Color(0xFF0284C7),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFE0F2FE),
    onPrimaryContainer = Color(0xFF0369A1),
    secondary = Color(0xFF4F46E5),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFEEF2FF),
    onSecondaryContainer = Color(0xFF3730A3),
    tertiary = Color(0xFF0D9488),
    onTertiary = Color(0xFFFFFFFF),
    background = Color(0xFFF8FAFC),
    onBackground = Color(0xFF0F172A),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF0F172A),
    surfaceVariant = Color(0xFFF1F5F9),
    onSurfaceVariant = Color(0xFF64748B),
    outline = Color(0xFFCBD5E1),
    outlineVariant = Color(0xFFE2E8F0),
)

enum class AppThemeMode { SYSTEM, DARK, LIGHT }

@Composable
fun PocketTheme(themeMode: AppThemeMode = AppThemeMode.SYSTEM, content: @Composable () -> Unit) {
    val isDark = when (themeMode) {
        AppThemeMode.DARK -> true
        AppThemeMode.LIGHT -> false
        AppThemeMode.SYSTEM -> isSystemInDarkTheme()
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window ?: return@SideEffect
            val insetsController = WindowCompat.getInsetsController(window, view)
            insetsController.isAppearanceLightStatusBars = !isDark
            insetsController.isAppearanceLightNavigationBars = !isDark
        }
    }

    MaterialTheme(
        colorScheme = if (isDark) DarkColors else LightColors,
        content = content,
    )
}
