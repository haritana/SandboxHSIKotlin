package com.example.quiz1.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quiz1.R
import com.example.quiz1.model.CampusModel
import com.example.quiz1.ui.theme.Quiz5Theme

@Composable
fun HomeScreen(
    campusUiState: CampusUiState,
    contentPadding: PaddingValues = PaddingValues(50.dp),
    retryAction: () -> Unit,
) {
    when (campusUiState) {
        is CampusUiState.Loading -> LoadingScreen()
        is CampusUiState.Success -> SuccessScreen(
            campus = campusUiState.campus,
            contentPadding = contentPadding
        )

        is CampusUiState.Error -> ErrorScreen(retryAction = retryAction)
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading),
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.loading_failed))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun SuccessScreen(campus: List<CampusModel>, contentPadding: PaddingValues) {
    LazyColumn(
        contentPadding = contentPadding,
    ) {
        items(campus.size) { index ->
            DetailPage(
                avatar = campus[index].name.substring(0, 1),
                header = campus[index].name,
                subHeader = campus[index].webPages[0]
            )
        }
    }
}

@Composable
fun DetailPage(
    avatar: String,
    header: String,
    subHeader: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 10.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.padding(vertical = 20.dp, horizontal = 10.dp)

        ) {
            Box(
                modifier = modifier
                    .clip(shape = CircleShape)
                    .size(50.dp)
                    .background(Color(0xFF438cc4))
            ) {

                Text(
                    text = avatar.substring(0, 1),
                    modifier = modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.bodyLarge.copy(Color.White)
                )
            }
            Spacer(modifier = modifier.width(10.dp))
            Column(modifier = modifier.width(250.dp)) {
                Text(
                    header,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = subHeader,
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                )
            }
            Spacer(modifier = modifier.weight(1F))
            Icon(
                Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                contentDescription = stringResource(R.string.expanded),
                modifier = modifier.size(30.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Test() {
    Quiz5Theme {
        DetailPage(
            avatar = "A",
            header = "Akademi Farmasi Mitra Sehat Mandiri Sidoarjo",
            subHeader = "http://www.akfarmitseda.ac.id"
        )
    }
}