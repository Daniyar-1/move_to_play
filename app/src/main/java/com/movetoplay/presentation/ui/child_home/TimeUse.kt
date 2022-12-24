package com.movetoplay.presentation.ui.child_home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.movetoplay.R
import com.movetoplay.presentation.ui.component_widgets.Button
import com.movetoplay.util.toStrTime

@Composable
fun TimeUse(
    availableForDayMinutes: Long,
    remainderMinutes: Long,
    needSeconds: Int,
    addTime: () -> Unit,
    sizeButton: DpSize
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .background(colorScheme.surface, RoundedCornerShape(10.dp))
            .padding(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Log.e("timeUse", "TimeUse: ${availableForDayMinutes.toStrTime()}", )
        Text(
            text = "${stringResource(R.string.available_for_day)}: ${availableForDayMinutes.toStrTime()}",
            color = Color.Gray,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.remainder),
            color = colorScheme.primary,
            fontSize = 18.sp,
            fontWeight = FontWeight.W600
        )
        Text(
            text = remainderMinutes.toStrTime(),
            color = colorScheme.primary,
            fontSize = 28.sp,
            fontWeight = FontWeight.W600
        )
        Spacer(modifier = Modifier.height(26.dp))
        Button(
            label = "+ ${needSeconds/60} минут",
            onClick = addTime,
            size = sizeButton
        )
    }
}
