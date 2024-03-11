package com.example.quiz1.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quiz1.R
import com.example.quiz1.ui.screen.CampusViewModel
import com.example.quiz1.ui.screen.HomeScreen
import com.example.quiz1.ui.theme.Quiz5Theme

@Composable
fun CampusApp() {
    Scaffold(
        topBar = { AppBar() }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            val campusViewModel: CampusViewModel = viewModel(factory = CampusViewModel.Factory)
            HomeScreen(
                campusUiState = campusViewModel.campusUiState,
                contentPadding = it,
                retryAction = campusViewModel::getListCampus
            )
        }
    }
}

@Composable
fun AppBar(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.search_campus),
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
            )
            Icon(
                painter = painterResource(id = R.drawable.filteroutline_svgrepo_com),
                contentDescription = stringResource(R.string.filter),
                modifier = modifier.size(20.dp)
            )
        }
        Text(
            text = stringResource(R.string.detail_title),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            ),
            modifier = modifier.padding(vertical = 20.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Test2() {
    Quiz5Theme {
        CampusApp()
    }
}