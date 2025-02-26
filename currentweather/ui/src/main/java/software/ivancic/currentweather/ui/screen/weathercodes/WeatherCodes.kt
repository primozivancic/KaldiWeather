package software.ivancic.currentweather.ui.screen.weathercodes

object Codes {

    fun getDetailsFor(code: Int, type: DayOrNight): Details {
        return codes.first { it.code == code }.details.first { it.type == type }
    }

    private val codes = listOf(
        Code(
            code = 0,
            details = listOf(
                Details(
                    type = DayOrNight.DAY,
                    description = "Sunny",
                    icon = "http://openweathermap.org/img/wn/01d@2x.png",
                ),
                Details(
                    type = DayOrNight.NIGHT,
                    description = "Clear",
                    icon = "http://openweathermap.org/img/wn/01n@2x.png"
                ),
            )
        ),
        Code(
            code = 1,
            details = listOf(
                Details(
                    type = DayOrNight.DAY,
                    description = "Mainly Sunny",
                    icon = "http://openweathermap.org/img/wn/01d@2x.png",
                ),
                Details(
                    type = DayOrNight.NIGHT,
                    description = "Mainly Clear",
                    icon = "http://openweathermap.org/img/wn/01n@2x.png"
                ),
            )
        ),
        Code(
            code = 2,
            details = listOf(
                Details(
                    type = DayOrNight.DAY,
                    description = "Partly Cloudy",
                    icon = "http://openweathermap.org/img/wn/02d@2x.png",
                ),
                Details(
                    type = DayOrNight.NIGHT,
                    description = "Partly Cloudy",
                    icon = "http://openweathermap.org/img/wn/02n@2x.png"
                ),
            )
        ),
        Code(
            code = 3,
            details = listOf(
                Details(
                    type = DayOrNight.DAY,
                    description = "Cloudy",
                    icon = "http://openweathermap.org/img/wn/03d@2x.png",
                ),
                Details(
                    type = DayOrNight.NIGHT,
                    description = "Cloudy",
                    icon = "http://openweathermap.org/img/wn/03n@2x.png"
                ),
            )
        ),
        Code(
            code = 45,
            details = listOf(
                Details(
                    type = DayOrNight.DAY,
                    description = "Foggy",
                    icon = "http://openweathermap.org/img/wn/50d@2x.png",
                ),
                Details(
                    type = DayOrNight.NIGHT,
                    description = "Foggy",
                    icon = "http://openweathermap.org/img/wn/50n@2x.png"
                ),
            )
        ),
        Code(
            code = 48,
            details = listOf(
                Details(
                    type = DayOrNight.DAY,
                    description = "Rime Fog",
                    icon = "http://openweathermap.org/img/wn/50d@2x.png",
                ),
                Details(
                    type = DayOrNight.NIGHT,
                    description = "Rime Fog",
                    icon = "http://openweathermap.org/img/wn/50n@2x.png"
                ),
            )
        ),
        Code(
            code = 51,
            details = listOf(
                Details(
                    type = DayOrNight.DAY,
                    description = "Light Drizzle",
                    icon = "http://openweathermap.org/img/wn/09d@2x.png",
                ),
                Details(
                    type = DayOrNight.NIGHT,
                    description = "Light Drizzle",
                    icon = "http://openweathermap.org/img/wn/09n@2x.png"
                ),
            )
        ),
        Code(
            code = 53,
            details = listOf(
                Details(
                    type = DayOrNight.DAY,
                    description = "Drizzle",
                    icon = "http://openweathermap.org/img/wn/09d@2x.png",
                ),
                Details(
                    type = DayOrNight.NIGHT,
                    description = "Drizzle",
                    icon = "http://openweathermap.org/img/wn/09n@2x.png"
                ),
            )
        ),
        Code(
            code = 55,
            details = listOf(
                Details(
                    type = DayOrNight.DAY,
                    description = "Heavy Drizzle",
                    icon = "http://openweathermap.org/img/wn/09d@2x.png",
                ),
                Details(
                    type = DayOrNight.NIGHT,
                    description = "Heavy Drizzle",
                    icon = "http://openweathermap.org/img/wn/09n@2x.png"
                ),
            )
        ),
        Code(
            code = 56,
            details = listOf(
                Details(
                    type = DayOrNight.DAY,
                    description = "Light Freezing Drizzle",
                    icon = "http://openweathermap.org/img/wn/09d@2x.png",
                ),
                Details(
                    type = DayOrNight.NIGHT,
                    description = "Light Freezing Drizzle",
                    icon = "http://openweathermap.org/img/wn/09n@2x.png"
                ),
            )
        ),
        Code(
            code = 57,
            details = listOf(
                Details(
                    type = DayOrNight.DAY,
                    description = "Freezing Drizzle",
                    icon = "http://openweathermap.org/img/wn/09d@2x.png",
                ),
                Details(
                    type = DayOrNight.NIGHT,
                    description = "Freezing Drizzle",
                    icon = "http://openweathermap.org/img/wn/09n@2x.png"
                ),
            )
        ),
        Code(
            code = 61,
            details = listOf(
                Details(
                    type = DayOrNight.DAY,
                    description = "Light Rain",
                    icon = "http://openweathermap.org/img/wn/10d@2x.png",
                ),
                Details(
                    type = DayOrNight.NIGHT,
                    description = "Light Rain",
                    icon = "http://openweathermap.org/img/wn/10n@2x.png"
                ),
            )
        ),
        Code(
            code = 63,
            details = listOf(
                Details(
                    type = DayOrNight.DAY,
                    description = "Rain",
                    icon = "http://openweathermap.org/img/wn/10d@2x.png",
                ),
                Details(
                    type = DayOrNight.NIGHT,
                    description = "Rain",
                    icon = "http://openweathermap.org/img/wn/10n@2x.png"
                ),
            )
        ),
        Code(
            code = 65,
            details = listOf(
                Details(
                    type = DayOrNight.DAY,
                    description = "Heavy Rain",
                    icon = "http://openweathermap.org/img/wn/10d@2x.png",
                ),
                Details(
                    type = DayOrNight.NIGHT,
                    description = "Heavy Rain",
                    icon = "http://openweathermap.org/img/wn/10n@2x.png"
                ),
            )
        ),
        Code(
            code = 66,
            details = listOf(
                Details(
                    type = DayOrNight.DAY,
                    description = "Light Freezing Rain",
                    icon = "http://openweathermap.org/img/wn/10d@2x.png",
                ),
                Details(
                    type = DayOrNight.NIGHT,
                    description = "Light Freezing Rain",
                    icon = "http://openweathermap.org/img/wn/10n@2x.png"
                ),
            )
        ),
        Code(
            code = 67,
            details = listOf(
                Details(
                    type = DayOrNight.DAY,
                    description = "Freezing Rain",
                    icon = "http://openweathermap.org/img/wn/10d@2x.png",
                ),
                Details(
                    type = DayOrNight.NIGHT,
                    description = "Freezing Rain",
                    icon = "http://openweathermap.org/img/wn/10n@2x.png"
                ),
            )
        ),
        Code(
            code = 71,
            details = listOf(
                Details(
                    type = DayOrNight.DAY,
                    description = "Light Snow",
                    icon = "http://openweathermap.org/img/wn/13d@2x.png",
                ),
                Details(
                    type = DayOrNight.NIGHT,
                    description = "Light Snow",
                    icon = "http://openweathermap.org/img/wn/13n@2x.png"
                ),
            )
        ),
        Code(
            code = 73,
            details = listOf(
                Details(
                    type = DayOrNight.DAY,
                    description = "Snow",
                    icon = "http://openweathermap.org/img/wn/13d@2x.png",
                ),
                Details(
                    type = DayOrNight.NIGHT,
                    description = "Snow",
                    icon = "http://openweathermap.org/img/wn/13n@2x.png"
                ),
            )
        ),
        Code(
            code = 75,
            details = listOf(
                Details(
                    type = DayOrNight.DAY,
                    description = "Heavy Snow",
                    icon = "http://openweathermap.org/img/wn/13d@2x.png",
                ),
                Details(
                    type = DayOrNight.NIGHT,
                    description = "Heavy Snow",
                    icon = "http://openweathermap.org/img/wn/13n@2x.png"
                ),
            )
        ),
        Code(
            code = 77,
            details = listOf(
                Details(
                    type = DayOrNight.DAY,
                    description = "Snow Grains",
                    icon = "http://openweathermap.org/img/wn/13d@2x.png",
                ),
                Details(
                    type = DayOrNight.NIGHT,
                    description = "Snow Grains",
                    icon = "http://openweathermap.org/img/wn/13n@2x.png"
                ),
            )
        ),
        Code(
            code = 80,
            details = listOf(
                Details(
                    type = DayOrNight.DAY,
                    description = "Light Showers",
                    icon = "http://openweathermap.org/img/wn/09d@2x.png",
                ),
                Details(
                    type = DayOrNight.NIGHT,
                    description = "Light Showers",
                    icon = "http://openweathermap.org/img/wn/09n@2x.png"
                ),
            )
        ),
        Code(
            code = 81,
            details = listOf(
                Details(
                    type = DayOrNight.DAY,
                    description = "Showers",
                    icon = "http://openweathermap.org/img/wn/09d@2x.png",
                ),
                Details(
                    type = DayOrNight.NIGHT,
                    description = "Showers",
                    icon = "http://openweathermap.org/img/wn/09n@2x.png"
                ),
            )
        ),
        Code(
            code = 82,
            details = listOf(
                Details(
                    type = DayOrNight.DAY,
                    description = "Heavy Showers",
                    icon = "http://openweathermap.org/img/wn/09d@2x.png",
                ),
                Details(
                    type = DayOrNight.NIGHT,
                    description = "Heavy Showers",
                    icon = "http://openweathermap.org/img/wn/09n@2x.png"
                ),
            )
        ),
        Code(
            code = 85,
            details = listOf(
                Details(
                    type = DayOrNight.DAY,
                    description = "Light Snow Showers",
                    icon = "http://openweathermap.org/img/wn/13d@2x.png",
                ),
                Details(
                    type = DayOrNight.NIGHT,
                    description = "Light Snow Showers",
                    icon = "http://openweathermap.org/img/wn/13n@2x.png"
                ),
            )
        ),
        Code(
            code = 86,
            details = listOf(
                Details(
                    type = DayOrNight.DAY,
                    description = "Snow Showers",
                    icon = "http://openweathermap.org/img/wn/13d@2x.png",
                ),
                Details(
                    type = DayOrNight.NIGHT,
                    description = "Snow Showers",
                    icon = "http://openweathermap.org/img/wn/13n@2x.png"
                ),
            )
        ),
        Code(
            code = 95,
            details = listOf(
                Details(
                    type = DayOrNight.DAY,
                    description = "Thunderstorm",
                    icon = "http://openweathermap.org/img/wn/11d@2x.png",
                ),
                Details(
                    type = DayOrNight.NIGHT,
                    description = "Thunderstorm",
                    icon = "http://openweathermap.org/img/wn/11n@2x.png"
                ),
            )
        ),
        Code(
            code = 96,
            details = listOf(
                Details(
                    type = DayOrNight.DAY,
                    description = "Light Thunderstorms With Hail",
                    icon = "http://openweathermap.org/img/wn/11d@2x.png",
                ),
                Details(
                    type = DayOrNight.NIGHT,
                    description = "Light Thunderstorms With Hail",
                    icon = "http://openweathermap.org/img/wn/11n@2x.png"
                ),
            )
        ),
        Code(
            code = 99,
            details = listOf(
                Details(
                    type = DayOrNight.DAY,
                    description = "Thunderstorm With Hail",
                    icon = "http://openweathermap.org/img/wn/11d@2x.png",
                ),
                Details(
                    type = DayOrNight.NIGHT,
                    description = "Thunderstorm With Hail",
                    icon = "http://openweathermap.org/img/wn/11n@2x.png"
                ),
            )
        ),
    )
}

data class Code(
    val code: Int,
    val details: List<Details>,
)

enum class DayOrNight {
    DAY, NIGHT
}

data class Details(
    val type: DayOrNight,
    val description: String,
    val icon: String,
)
