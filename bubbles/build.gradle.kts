import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinMultiplatform
import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.androidLint)

    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)

    alias(libs.plugins.vanniktech.maven.publish)
    signing
}

kotlin {
    applyDefaultHierarchyTemplate()

    androidLibrary {
        namespace = "io.github.kalist28.bubbles"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    val xcfName = "bubblesKit"

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
        macosArm64(),
        macosX64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = xcfName
        }
    }

    jvm {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_11)
                }
            }
        }
    }

    js(IR) {
        browser()
    }

    @Suppress("OPT_IN_USAGE")
    wasmJs {
        browser()
    }

    sourceSets {
        val wasmJsMain by getting

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.composables)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)
        }

        val nonIosMain by creating {
            dependsOn(commonMain.get())
            macosMain.get().dependsOn(this)
            androidMain.get().dependsOn(this)
            jvmMain.get().dependsOn(this)
            jsMain.get().dependsOn(this)
            wasmJsMain.dependsOn(this)
        }
    }
}

val publishProperties = Properties().apply {
    load(file("publish.properties").inputStream())
}

val isGithubActions = System.getenv("GITHUB_ACTIONS") == "true"

version = System.getenv("VERSION") ?: run {
    if (isGithubActions) error("VERSION must be set for GitHub Actions")
    else "0.0.1"
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL, automaticRelease = true)

    coordinates(
        groupId = project.group as String,
        artifactId = project.name,
        version = project.version as String
    )

    configure(
        KotlinMultiplatform(
            javadocJar = JavadocJar.Empty(),
            sourcesJar = true,
        )
    )

    if (isGithubActions) signAllPublications()

    pom {
        name.set(project.name)
        description.set(publishProperties.getProperty("description"))
        url.set("https://github.com/kalist28/bubbles-compose")

        licenses {
            license {
                name.set("Apache-2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0")
            }
        }
        developers {
            developer {
                id.set("kalist28")
                name.set("Dmitry Kalistratov")
                email.set("kalistratov.d.m@gmail.com")
            }
        }
        scm {
            connection.set("scm:git:https://github.com/kalist28/bubbles-compose.git")
            developerConnection.set("scm:git:ssh://github.com/kalist28/bubbles-compose.git")
            url.set("https://github.com/kalist28/bubbles-compose")
        }
    }
}