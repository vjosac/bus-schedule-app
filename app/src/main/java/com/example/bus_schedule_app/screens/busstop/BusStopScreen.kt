package com.example.bus_schedule_app.screens.busstop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bus_schedule_app.R
import com.example.bus_schedule_app.ScheduleTopAppBar
import com.example.bus_schedule_app.data.models.Schedule
import com.example.bus_schedule_app.navigation.NavigationDestination
import com.example.bus_schedule_app.ui.AppViewModelProvider
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object BusStopDestination : NavigationDestination {
    override val route: String = "bus_stop"
    override val titleRes: Int = R.string.bus_stop_title
    const val busStopNameArgs = "busStopName"
    val routeWithArgs = "$route/{$busStopNameArgs}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusStopScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BusStopViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val uiState = viewModel.busStopUiState.collectAsState()

    Scaffold(
        topBar = {
            ScheduleTopAppBar(
                title = stringResource(BusStopDestination.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack,
                scrollBehavior = scrollBehavior
            )
        },
        modifier = modifier
    ) { innerPadding ->
        BusStopBody(
            itemList = uiState.value.fullSchedulesList,
            modifier = modifier.padding(innerPadding).background(MaterialTheme.colorScheme.background),
            contentPadding = innerPadding
        )
    }
}

@Composable
private fun BusStopBody(
    itemList: List<Schedule>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (itemList.isEmpty()) {
            Text(
                text = stringResource(R.string.no_items),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(contentPadding)
            )
        } else {
            Column(verticalArrangement = Arrangement.Top){
                Row(
                    modifier = Modifier
                        .fillMaxWidth().padding(
                            vertical = dimensionResource(id = R.dimen.padding_medium),
                            horizontal = dimensionResource(id = R.dimen.padding_medium)
                        )
                ) {
                    Text(
                        text = stringResource(R.string.stop_name),
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = stringResource(R.string.arrival_time),
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
                Divider(
                    color = Color.LightGray,
                    thickness = 1.dp,
                    modifier = Modifier.fillMaxWidth()
                )
                SchedulesList(
                    itemList = itemList,
                    modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small))
                )
            }
        }
    }
}

@Composable
private fun SchedulesList(
    itemList: List<Schedule>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(items = itemList, key = { it.id }) { item ->
            ScheduleItem(
                item = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.padding_small),
                        vertical = dimensionResource(id = R.dimen.padding_12)
                    )
            )
        }
    }
}

@Composable
private fun ScheduleItem(
    item: Schedule,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = stringResource(R.string.dash),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_extra_large))
        )
        Text(
            text = item.arrivalTime.toAmPmTime(),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
    }
}

fun Int.toAmPmTime(): String {
    val hours = this / 60
    val minutes = this % 60

    val calendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, hours)
        set(Calendar.MINUTE, minutes)
    }

    val formatter = SimpleDateFormat("h:mm a", Locale.getDefault())
    return formatter.format(calendar.time)
}
