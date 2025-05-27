package com.example.retrolog.util

fun formatDuration(minute: Int): String {
    if (minute != 0) {
        val hours = minute / 60
        val remainingMinutes = minute % 60

        return "${hours}h ${remainingMinutes}m"
    } else {
        return ""
    }
}

fun changeRatingFormatValue(rating: Double): Double {
    if (rating == 0.0) {
        return rating
    } else {
        val normalizedRating = rating / 2
        val formattedRating = String.format("%.1f", normalizedRating).toDouble()

        return formattedRating
    }
}
