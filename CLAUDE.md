# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

**Bubbles Compose** is a Kotlin Multiplatform (KMP) UI component library published to Maven Central (`io.github.kalist28:bubbles`). It provides iOS-design-inspired Compose Multiplatform components for Android, iOS, Desktop (JVM), and Web (WASM).

## Build Commands

```bash
# Build the library
./gradlew :bubbles:build

# Run the demo app (desktop JVM)
./gradlew :composeApp:run

# Run tests
./gradlew commonTest

# Build Android APK (demo app)
./gradlew :composeApp:assembleDebug

# Run web demo (WASM)
./gradlew :composeApp:wasmJsBrowserDevelopmentRun

# Publish to Maven Central (requires signing secrets, run in CI)
./gradlew publish --no-configuration-cache -Pversion=$VERSION
```

Code style is enforced via `kotlin.code.style=official` in `gradle.properties`. There is no separate lint step.

## Module Structure

- **`bubbles/`** — The published library module. All components live in `src/commonMain/kotlin/io/github/kalist28/bubbles/`.
- **`composeApp/`** — Demo/sample application showing library usage across platforms.
- **`iosApp/`** — Native iOS app wrapper (Xcode project).
- **`buildSrc/`** — Custom Gradle build plugins.
- **`gradle/libs.versions.toml`** — Single source of truth for all dependency versions.

## Source Set Layout

The library uses Kotlin Multiplatform source sets with platform-specific implementations:

| Source Set | Purpose |
|---|---|
| `commonMain` | Shared code: all components, theme, expect declarations |
| `androidMain` | Android-specific actuals |
| `iosMain` | iOS-specific actuals (accessibility, haptic feedback) |
| `nonIosMain` | Android + Desktop + Web actuals |
| `skikoMain` | Desktop + Web (Skiko renderer) actuals |
| `nativeMain` | Native platform common code |

## Architecture & Key Patterns

### Theme System

`BubblesTheme` is the entry point. It provides a `CompositionLocal`-based theming system:
- `LocalColorScheme.current` — semantic color scheme (iOS-inspired names: `systemBlue`, `label`, `secondaryLabel`, etc.)
- `LocalTypography.current` — text styles
- `LocalTextStyle.current` / `LocalContentColor.current` / `LocalContainerColor.current` — implicit style propagation
- Light/dark mode and high-contrast mode are handled automatically via `ColorScheme`.

The `BubblesTheme` object (in `core/theme/`) exposes `BubblesTheme.colorScheme`, `BubblesTheme.typography`, `BubblesTheme.shapes` as shortcuts to the current locals.

### Component Conventions

Every component follows the same pattern:
1. **Size enum** (e.g., `BubblesButtonSize`) — encodes shape and content padding per size variant.
2. **Colors data class** (e.g., `BubblesButtonColors`) — holds container/content colors per state.
3. **`*Defaults` object** (e.g., `BubblesButtonDefaults`) — factory methods for colors; serves as the public customization API.
4. **Main composable** — accepts `modifier`, `enabled`, `colors`, `interactionSource`, etc.

### Expect/Actual for Platform APIs

Platform-specific behavior is declared in `commonMain` as `expect` and implemented per platform:
- `core/Accessibility.kt` — `isHighContrastEnabled`, `isReduceTransparencyEnabled`
- `core/BubblesHapticFeedback.kt` — haptic feedback

### Component Categories

| Category | Files |
|---|---|
| Layout | `BubblesScaffold.kt`, `Surface.kt` |
| Navigation | `BubblesTopBars.kt`, `BubblesNavigationBar.kt`, `BubblesTabRow.kt` |
| Input | `BubblesTextField.kt`, `BubblesSlider.kt` |
| Selection | `BubblesButton.kt`, `BubblesCheckbox.kt`, `BubblesRadioButton.kt`, `BubblesSwitch.kt`, `BubblesSegmentedControl.kt` |
| Display | `BubblesText.kt`, `BubblesIcon.kt`, `BubblesSeparator.kt` |
| Effects | `animation/BubblesRipple.kt`, `effect/Haze.kt` |

## Release & Versioning

Releases are managed by **Nyx** via `.nyx.json` using **Conventional Commits**:
- `main` → stable releases
- `develop` → beta
- `release/*` → RC
- `hotfix/*` → hotfix patch

The CI pipeline (`.github/workflows/build_publish_release.yml`) infers the version, publishes to Maven Central, and creates a GitHub Release automatically.

## Key Versions

- Kotlin: 2.2.0
- Compose Multiplatform: 1.8.2
- Android compileSdk: 35 / minSdk: 24
- JVM target: 11