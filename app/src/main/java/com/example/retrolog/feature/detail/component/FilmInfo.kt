package com.example.retrolog.feature.detail.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.retrolog.data.remote.response.detail.FilmDetailResponse
import com.example.retrolog.ui.theme.Neutral200
import com.example.retrolog.ui.theme.Neutral400
import com.example.retrolog.ui.theme.Neutral50
import com.example.retrolog.ui.theme.Primary500
import com.example.retrolog.util.Constant
import com.example.retrolog.util.formatDuration

@Composable
fun FilmInfo(filmDetailResponse: FilmDetailResponse?, type: String) {

    val genreList= filmDetailResponse?.genres
    val genre= StringBuilder()

    genreList?.forEachIndexed { index, genreData ->
        if (index == 0) {
            genre
                .append(genreData.name)
        } else if (index == 1) {
            genre
                .append(", ")
                .append(genreData.name)
        }
    }
    Row(
        Modifier
            .offset(y = (-50).dp)
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.Top
    ) {

        if (filmDetailResponse == null) {

            Box(
                Modifier
                    .size(width = 125.dp, height = 180.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Primary500)
            )
        } else {
            AsyncImage(
                model = Constant.BASE_URL_IMAGE + filmDetailResponse.poster_path,
                "poster",
                modifier = Modifier
                    .size(width = 125.dp, height = 180.dp)
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop
            )
        }


        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.offset(y= 40.dp)
        ) {
            Text(
                if (type == Constant.TV) filmDetailResponse?.name ?: ""
                else filmDetailResponse?.original_title ?: "",
                color = Neutral50,
                fontSize = 17.sp,
                fontWeight = FontWeight.W600,
                overflow = TextOverflow.Ellipsis,
            )

            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                Text(
                    "Genre",
                    color = Neutral400
                )
                Text(
                    genre.toString(),
                    color = Neutral200
                )
            }

            if (type == Constant.MOVIE) {
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(23.dp)
                ) {
                    Text(
                        "Duration",
                        color = Neutral400
                    )
                    Text(
                        formatDuration(filmDetailResponse?.runtime ?: 0),
                        color = Neutral200
                    )
                }
            } else {

                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(23.dp)
                ) {
                    Text(
                        "Seasons",
                        color = Neutral400
                    )
                    Text(
                        "${filmDetailResponse?.number_of_seasons ?: 0}",
                        color = Neutral200
                    )
                }

                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Text(
                        "Episodes",
                        color = Neutral400
                    )
                    Text(
                        "${filmDetailResponse?.number_of_episodes ?: 0}",
                        color = Neutral200
                    )
                }
            }

            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(38.dp)
            ) {
                Text(
                    "Status",
                    color = Neutral400
                )
                Text(
                    filmDetailResponse?.status ?: "",
                    color = Neutral200
                )
            }
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(28.dp)
            ) {
                Text(
                    "Release",
                    color = Neutral400
                )
                Text(
                    if (type == Constant.TV) filmDetailResponse?.first_air_date ?: ""
                    else filmDetailResponse?.release_date ?: "",
                    color = Neutral200
                )
            }
        }
    }
}