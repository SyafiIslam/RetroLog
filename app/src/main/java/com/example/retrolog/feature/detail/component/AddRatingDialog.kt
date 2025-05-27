package com.example.retrolog.feature.detail.component

import android.app.Dialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.retrolog.R
import com.example.retrolog.ui.theme.Neutral50
import com.example.retrolog.ui.theme.Neutral700
import com.example.retrolog.ui.theme.Primary700
import com.example.retrolog.ui.theme.RatingColor

@Composable
fun AddRatingDialog(onDialogDismiss: () -> Unit, onClickSubmit: (Int) -> Unit) {

    val totalStar= 5
    var ratingValue by remember { mutableIntStateOf(0) }
    Dialog(
        onDismissRequest = { onDialogDismiss() },
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Neutral700)
                .padding(vertical = 20.dp, horizontal = 40.dp)
        ) {

            Row(

            ) {
                Text(
                    "Add Your Rating",
                    color = Neutral50,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(Modifier.height(20.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {

                for(index in 1..totalStar) {
                    Icon(
                        painter = painterResource(R.drawable.ic_star),
                        contentDescription = "Full Star",
                        tint = if (index <= ratingValue) RatingColor else Neutral50,
                        modifier = Modifier.clickable { ratingValue= index }
                    )
                }
            }
            Spacer(Modifier.height(8.dp))
            Button(
                onClick = {
                    onClickSubmit(ratingValue)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Primary700),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Submit")
            }
        }
    }
}