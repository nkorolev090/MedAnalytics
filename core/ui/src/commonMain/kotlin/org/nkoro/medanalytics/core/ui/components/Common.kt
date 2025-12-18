package org.nkoro.medanalytics.core.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastCoerceIn
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.nkoro.medanalytics.core.ui.theme.*

@Composable
fun InfoCard(
    header: String,
    text: String,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(7.dp),
        modifier = modifier
            .clip(MaterialTheme.shapes.cardRadius10)
            .background(MaterialTheme.colorScheme.liteGray)
            .padding(horizontal = 30.dp, vertical = 15.dp)
    ) {
        Text(text = header, style = MaterialTheme.typography.inter10Normal, color = MaterialTheme.colorScheme.darkGray)
        Text(text = text, style = MaterialTheme.typography.inter40Normal, color = MaterialTheme.colorScheme.black)
    }
}

@Composable
fun VerticalProgressBar(
    progress: Double,
    selected: Boolean,
    modifier: Modifier = Modifier,
    maxProgress: Double = 100.0,
    description: String? = null,
    onClick: (() -> Unit)? = null,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(7.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.width(IntrinsicSize.Min).widthIn(min = 150.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(MaterialTheme.shapes.cardRadius30)
                .clickable(enabled = onClick != null, onClick = { onClick?.let { it() } })
                .fillMaxWidth()
                .height(120.dp)
                .background(MaterialTheme.colorScheme.liteWhite)
        ) {
            this@Column.AnimatedVisibility(
                visible = selected,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
            ) {
                Box(
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.cardRadius30)
                        .fillMaxHeight((progress / maxProgress).fastCoerceIn(0.0, 1.0).toFloat())
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.secondaryGray)
                )
            }
            Text(
                text = "$progress%",
                style = MaterialTheme.typography.inter36Normal,
                color = MaterialTheme.colorScheme.black,
                modifier = Modifier.padding(horizontal = 30.dp, vertical = 10.dp).align(Alignment.BottomCenter),
            )
        }
        description?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.inter14Normal,
                color = MaterialTheme.colorScheme.black,
            )
        }
    }
}

@Preview
@Composable
private fun InfoCardPreview() {
    MaterialTheme {
        InfoCard(
            header = "Конверсия звонков",
            text = "78.7%",
        )
    }
}