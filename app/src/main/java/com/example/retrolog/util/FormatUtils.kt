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

fun changeRatingFormatValue(rating: Double): String {
    if (rating == 0.0) {
        return ""
    } else {
        val originalRating = rating
        val normalizedRating = originalRating / 2
        val formattedRating = String.format("%.1f", normalizedRating)

        return formattedRating
    }
}

fun getStartAmount(rating: Double) {
    fun getStarRating(rating: Double): String {
        val fullStars = rating.toInt()
        val hasHalfStar = (rating - fullStars) >= 0.5

        val stars = StringBuilder()

        repeat(fullStars) {
            stars.append("★")
        }

        if (hasHalfStar) {
            stars.append("½") // You can use a different symbol or emoji for half-star
        }

        return stars.toString()
    }
}