package org.nkoro.medanalytics.feature.home.mainScreen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import medanalytics.feature.home.generated.resources.*
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.getKoin
import org.nkoro.medanalytics.core.ui.components.InfoCard
import org.nkoro.medanalytics.core.ui.components.VerticalProgressBar
import org.nkoro.medanalytics.core.ui.extensions.secondsToMinutes
import org.nkoro.medanalytics.core.ui.extensions.setDashText
import org.nkoro.medanalytics.core.ui.theme.*
import org.nkoro.medanalytics.feature.home.data.model.AdminKPIModel
import org.nkoro.medanalytics.feature.home.data.model.RingModel
import org.nkoro.medanalytics.feature.home.data.model.getStringRes

class MainScreen : Screen {

    @Composable
    override fun Content() =
        MainScreen(
            screenModel = getKoin().get()
        )
}

@Composable
private fun MainScreen(
    screenModel: MainScreenModel
) {
    val state by screenModel.state.collectAsState()
    if (state.rings.isNotEmpty()) {
        ScreenContent(
            state = state,
            onAction = screenModel::pushAction,
        )
    } else {
        EmptyScreenContent(onAction = screenModel::pushAction)
    }
}

@Composable
private fun EmptyScreenContent(
    onAction: (Action) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(Res.string.empty_rings_description),
            style = MaterialTheme.typography.inter36Normal,
            color = MaterialTheme.colorScheme.black,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                onAction(Action.LoadFile)
            },
            colors = ButtonDefaults.buttonColors(
                contentColor = MaterialTheme.colorScheme.backgroundColor,
                containerColor = MaterialTheme.colorScheme.darkGray,
            )
        ) {
            Text(
                text = stringResource(Res.string.empty_rings_btn_text),
                style = MaterialTheme.typography.inter14Normal
            )
        }
    }
}

@Composable
private fun ScreenContent(
    state: State,
    onAction: (Action) -> Unit
) {
    var selectedIndex by remember { mutableStateOf(0) }
    val selectedAdminAnalise by remember(selectedIndex, state.adminsEvaluation) {
        derivedStateOf {
            state.adminsEvaluation.getOrNull(selectedIndex)
        }
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .background(MaterialTheme.colorScheme.backgroundColor)
            .safeContentPadding()
            .fillMaxSize()
            .padding(start = 40.dp, top = 20.dp, end = 40.dp, bottom = 45.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            InfoCard(
                header = stringResource(Res.string.conversion_rate),
                text = "${state.conversionRate}%",
                modifier = Modifier.weight(1f)
            )
            InfoCard(
                header = stringResource(Res.string.arg_duration),
                text = state.avgDuration.secondsToMinutes(),
                modifier = Modifier.weight(1f)
            )
            InfoCard(
                header = stringResource(Res.string.transcription_accuracy),
                text = "${state.transcriptionAccuracy}%",
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.height(IntrinsicSize.Min)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxHeight().padding(20.dp),
            ) {
                Column {
                    Text(
                        text = stringResource(Res.string.evaluation_of_administrators_work),
                        style = MaterialTheme.typography.inter14Normal,
                        color = MaterialTheme.colorScheme.black,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                    Text(
                        text = stringResource(Res.string.evaluation_kpi),
                        style = MaterialTheme.typography.inter14Normal,
                        color = MaterialTheme.colorScheme.darkGray,
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    state.adminsEvaluation.forEachIndexed { index, model ->
                        VerticalProgressBar(
                            progress = model.evaluation,
                            selected = index == selectedIndex,
                            description = model.fio,
                            onClick = { selectedIndex = index },
                        )
                    }
                }
            }
            selectedAdminAnalise?.let {
                WorkAnaliseBox(
                    model = it,
                    modifier = Modifier.weight(1f),
                )
            }
        }
        RingsTable(rings = state.rings)
    }
}

@Composable
private fun WorkAnaliseBox(
    model: AdminKPIModel,
    modifier: Modifier,
) {
    Column(
        modifier = modifier
            .clip(MaterialTheme.shapes.cardRadius30)
            .border(1.dp, MaterialTheme.colorScheme.darkGray, MaterialTheme.shapes.cardRadius30)
            .padding(30.dp)
    ) {
        Row(
            modifier = Modifier.padding(bottom = 25.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp),
                modifier = Modifier.padding(end = 50.dp)
            ) {
                Text(
                    text = stringResource(Res.string.work_analise),
                    style = MaterialTheme.typography.inter14Normal,
                    color = MaterialTheme.colorScheme.black,
                )
                Text(
                    text = stringResource(Res.string.evaluation_kpi),
                    style = MaterialTheme.typography.inter14Normal,
                    color = MaterialTheme.colorScheme.darkGray,
                )
            }
            Text(
                text = model.fio,
                style = MaterialTheme.typography.inter14Normal,
                color = MaterialTheme.colorScheme.black,
            )
            Spacer(modifier = Modifier.weight(1f))
            Column {
                Text(
                    text = "${model.evaluation}%",
                    style = MaterialTheme.typography.inter36Normal,
                    color = MaterialTheme.colorScheme.black,
                )
                Text(
                    text = stringResource(model.evaluationText),
                    style = MaterialTheme.typography.inter10Normal,
                    color = MaterialTheme.colorScheme.darkGray,
                )
            }
        }
        PropertyProgressRow(
            text = stringResource(Res.string.arg_duration_ring),
            progressText = model.avgDuration.secondsToMinutes(),
            progress = 90.0,
            modifier = Modifier.padding(bottom = 2.dp),
        )
        PropertyProgressRow(
            text = stringResource(Res.string.lead_percent),
            progressText = "${model.leadPercent}%",
            progress = model.leadPercent,
            modifier = Modifier.padding(bottom = 2.dp),
        )
        PropertyProgressRow(
            text = stringResource(Res.string.check_list_accordance),
            progressText = "${model.accordance}%",
            progress = model.accordance,
            modifier = Modifier.padding(bottom = 2.dp),
        )
        PropertyProgressRow(
            text = stringResource(Res.string.improvisation),
            progressText = "${model.improvisation}%",
            progress = model.improvisation,
        )
    }
}

@Composable
private fun PropertyProgressRow(
    text: String,
    progressText: String,
    progress: Double,
    modifier: Modifier = Modifier,
    maxProgress: Double = 100.0,
) {
    val coercedProgress by remember(progress, maxProgress) {
        derivedStateOf {
            (progress / maxProgress).coerceIn(0.0, 1.0).toFloat()
        }
    }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .clip(MaterialTheme.shapes.cardRadius30)
            .border(1.dp, MaterialTheme.colorScheme.darkGray, MaterialTheme.shapes.cardRadius30)
            .background(
                brush =
                    Brush.horizontalGradient(
                        0f to MaterialTheme.colorScheme.backgroundColor,
                        coercedProgress * 0.2f to MaterialTheme.colorScheme.secondaryGray,
                        coercedProgress * 0.8f to MaterialTheme.colorScheme.secondaryGray,
                        coercedProgress to MaterialTheme.colorScheme.backgroundColor,
                        1f to MaterialTheme.colorScheme.backgroundColor,
                    )
            )
            .fillMaxWidth()
            .padding(20.dp, 5.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.inter10Normal,
            color = MaterialTheme.colorScheme.black,
        )
        Text(
            text = progressText,
            style = MaterialTheme.typography.inter10Normal,
            color = MaterialTheme.colorScheme.black,
        )
    }
}

@Composable
private fun RingsTable(
    rings: List<RingModel>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .border(1.dp, MaterialTheme.colorScheme.darkGray, MaterialTheme.shapes.cardRadius30)
            .padding(30.dp)
    ) {
        Text(
            text = stringResource(Res.string.rings_title),
            style = MaterialTheme.typography.inter14Normal,
            color = MaterialTheme.colorScheme.black,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 2.dp)
        )
        Text(
            text = stringResource(Res.string.auto_analise_dialogs),
            style = MaterialTheme.typography.inter10Normal,
            color = MaterialTheme.colorScheme.darkGray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        HorizontalDivider(color = MaterialTheme.colorScheme.darkGray)
        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(5.dp, 12.dp)
        ) {
            Text(
                text = stringResource(Res.string.ring_date),
                style = MaterialTheme.typography.inter10Normal,
                color = MaterialTheme.colorScheme.darkGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = stringResource(Res.string.ring_duration),
                style = MaterialTheme.typography.inter10Normal,
                color = MaterialTheme.colorScheme.darkGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = stringResource(Res.string.operator),
                style = MaterialTheme.typography.inter10Normal,
                color = MaterialTheme.colorScheme.darkGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = stringResource(Res.string.patient),
                style = MaterialTheme.typography.inter10Normal,
                color = MaterialTheme.colorScheme.darkGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = stringResource(Res.string.service),
                style = MaterialTheme.typography.inter10Normal,
                color = MaterialTheme.colorScheme.darkGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = stringResource(Res.string.doctor),
                style = MaterialTheme.typography.inter10Normal,
                color = MaterialTheme.colorScheme.darkGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = stringResource(Res.string.status),
                style = MaterialTheme.typography.inter10Normal,
                color = MaterialTheme.colorScheme.darkGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.verticalScroll(rememberScrollState()),
        ) {
            rings.forEach {
                RingRow(it, {
                    TODO("Details")
                })
            }
        }
    }
}

@Composable
private fun RingRow(
    ring: RingModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(MaterialTheme.shapes.cardRadius30)
            .clickable(onClick = onClick)
            .background(
                brush = Brush.horizontalGradient(
                    0f to MaterialTheme.colorScheme.backgroundColor,
                    0.5f to MaterialTheme.colorScheme.secondaryGray,
                    1f to MaterialTheme.colorScheme.backgroundColor,
                )
            )
            .fillMaxWidth()
            .padding(5.dp, 12.dp)
    ) {
        Text(
            text = ring.dateTime,
            style = MaterialTheme.typography.inter10Normal,
            color = MaterialTheme.colorScheme.darkGray,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = ring.duration.secondsToMinutes(),
            style = MaterialTheme.typography.inter10Normal,
            color = MaterialTheme.colorScheme.darkGray,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = ring.operator,
            style = MaterialTheme.typography.inter10Normal,
            color = MaterialTheme.colorScheme.black,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = ring.patient.setDashText(),
            style = MaterialTheme.typography.inter10Normal,
            color = MaterialTheme.colorScheme.black,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = ring.service.setDashText(),
            style = MaterialTheme.typography.inter10Normal,
            color = MaterialTheme.colorScheme.black,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = ring.doctor.setDashText(),
            style = MaterialTheme.typography.inter10Normal,
            color = MaterialTheme.colorScheme.black,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = stringResource(ring.status.getStringRes()),
            style = MaterialTheme.typography.inter10Normal,
            color = MaterialTheme.colorScheme.black,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
    }
}