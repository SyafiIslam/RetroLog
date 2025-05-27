package com.example.retrolog.feature.detail.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.retrolog.R
import com.example.retrolog.ui.theme.Neutral50
import com.example.retrolog.ui.theme.Neutral700
import com.example.retrolog.ui.theme.Primary700
import com.example.retrolog.ui.theme.RatingColor
import com.example.retrolog.util.changeRatingFormatValue

@Composable
fun FilmOverview(
    overview: String?,
    voteAverage: Double?,
    watchlist: MutableState<Boolean>,
    onWatchlistClick: () -> Unit,
    onButtonReviewClick: () -> Unit,
    onAddRatingClick: () -> Unit
) {

    val rating = changeRatingFormatValue(voteAverage ?: 0.0)
    val fullStars = rating.toInt()
    val hasHalfStar = (rating - fullStars) >= 0.5
    val totalStars = 5

    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .offset(y = (-10).dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column (
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    rating.toString(),
                    color = RatingColor,
                    fontSize = 22.sp
                )
                Spacer(Modifier.width(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    repeat(fullStars) {
                        Icon(
                            painter = painterResource(R.drawable.ic_star),
                            contentDescription = "Full Star",
                            tint = RatingColor,
                        )
                    }

                    if (hasHalfStar) {
                        Icon(
                            painter = painterResource(R.drawable.ic_half_star),
                            contentDescription = "Half Star",
                            tint = RatingColor,
                        )
                    }

                    val emptyStars = totalStars - fullStars - if (hasHalfStar) 1 else 0
                    repeat(emptyStars) {
                        Icon(
                            painter = painterResource(R.drawable.ic_star),
                            contentDescription = "Empty Star",
                            tint = Neutral50,
                        )
                    }
                }
            }

            Text(
                "Add Rating",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable {
                    onAddRatingClick()
                }
            )
        }

        VerticalDivider(
            Modifier
                .height(60.dp)
                .padding(end = 8.dp, start = 16.dp),
            color = Neutral700,
            thickness = 2.dp
        )

        Row(
            Modifier.clickable {
                onWatchlistClick()
            },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                if (watchlist.value) {
                    Icons.Default.Check
                } else {
                    Icons.Default.Add
                },
                "WatchList",
                tint = Neutral50,
                modifier = Modifier.size(28.dp)
            )
            Text(
                if (watchlist.value) {
                    "Added to watchlist"
                } else {
                    "Add to watchlist"
                },
                color = Neutral50,
                fontSize = 16.sp
            )
        }
    }
    Spacer(Modifier.height(24.dp))
    Column(
        Modifier
            .fillMaxWidth()
            .offset(y = (-20).dp)
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Button(
            onClick = {
                onButtonReviewClick()
            },
            colors = ButtonDefaults.buttonColors(containerColor = Primary700),
            modifier = Modifier.fillMaxWidth().fillMaxHeight(.4f),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text("See Review")
        }

        Text(
            "Overview",
            color = Neutral50,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            overview ?: "",
            color = Neutral50,
            fontSize = 16.sp,
            lineHeight = 24.sp
        )
    }
}