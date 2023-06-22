package si.razum.catskmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform