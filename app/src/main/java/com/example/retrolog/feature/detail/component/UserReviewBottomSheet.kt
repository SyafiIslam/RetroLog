package com.example.retrolog.feature.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.retrolog.R
import com.example.retrolog.data.remote.response.review.Review
import com.example.retrolog.ui.theme.Neutral50
import com.example.retrolog.ui.theme.Neutral700
import com.example.retrolog.ui.theme.Primary700
import com.example.retrolog.ui.theme.RatingColor
import com.example.retrolog.util.Constant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserReviewBottomSheet(
    onDismiss: () -> Unit,
    userReview: List<Review>,
) {

    val sheetState = rememberModalBottomSheetState()
    val totalStars = 5

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { onDismiss() },
        containerColor = Neutral700,
        dragHandle = {
            Box(
                Modifier
                    .padding(vertical = 20.dp)
                    .size(width = 50.dp, height = 4.dp)
                    .clip(CircleShape)
                    .background(Neutral50)
            )
        }
    ) {
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(.8f)
                .padding(horizontal = 20.dp),
        ) {
            if (userReview.isEmpty()) {
                item {
                    Column(
                        Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "No reviews yet",
                            color = Neutral50,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            } else {
                items(userReview) {

                    val fullStars = it.author_details.rating.toInt() / 2

                    Column(
                        Modifier.padding(bottom = 20.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            if (it.author_details.avatar_path != null) {
                                AsyncImage(
                                    model = Constant.BASE_URL_IMAGE + it.author_details.avatar_path,
                                    modifier = Modifier
                                        .size(50.dp)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop,
                                    contentDescription = "Avatar"
                                )
                            } else {
                                Box(
                                    Modifier
                                        .size(50.dp)
                                        .clip(CircleShape)
                                        .background(Primary700),
                                )
                            }

                            Column(
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    it.author,
                                    color = Neutral50
                                )
                                if (it.author_details.rating > 0) {
                                    Row(
                                    ) {
                                        repeat(fullStars) {
                                            Icon(
                                                painter = painterResource(R.drawable.ic_star),
                                                contentDescription = "Full Star",
                                                tint = RatingColor,
                                                modifier = Modifier.size(18.dp)
                                            )
                                        }

                                        val emptyStars = totalStars - fullStars
                                        repeat(emptyStars) {
                                            Icon(
                                                painter = painterResource(R.drawable.ic_star),
                                                contentDescription = "Empty Star",
                                                tint = Neutral50,
                                                modifier = Modifier.size(18.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        Text(
                            it.content,
                            color = Neutral50,
                            textAlign = TextAlign.Justify
                        )
                    }
                }
            }
        }
    }
}