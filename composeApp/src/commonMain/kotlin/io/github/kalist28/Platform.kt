package io.github.kalist28

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform