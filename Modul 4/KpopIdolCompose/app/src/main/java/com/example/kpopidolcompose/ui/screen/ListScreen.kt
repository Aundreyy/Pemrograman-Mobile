package com.example.kpopidolcompose.ui.screen

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kpopidolcompose.data.DataIdol
import com.example.kpopidolcompose.ui.Screen
import com.example.kpopidolcompose.ui.components.ItemHorizontal
import com.example.kpopidolcompose.ui.components.ItemVertikal
import com.example.kpopidolcompose.viewmodel.KpopViewModel
import androidx.core.net.toUri

@Composable
fun ListScreen(navController: NavController, viewModel: KpopViewModel) {
    val statusListVertikal = rememberLazyListState()
    val statusListHorizontal = rememberLazyListState()
    val context = LocalContext.current

    val listIdol by viewModel.idolList.collectAsState()
    val navigateToDetail by viewModel.navigateToDetail.collectAsState()
    val navigateToUrl by viewModel.navigateToUrl.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadData(DataIdol.listIdol)
    }

    LaunchedEffect(navigateToDetail) {
        navigateToDetail?.let { idol ->
            navController.navigate(Screen.Detail.createRoute(idol.nama))
            viewModel.onDetailNavigated()
        }
    }

    LaunchedEffect(navigateToUrl) {
        navigateToUrl?.let { url ->
            val intent = Intent(Intent.ACTION_VIEW, url.toUri())
            context.startActivity(intent)
            viewModel.onExplicitIntentNavigated()
        }
    }

    LazyColumn(
        state = statusListVertikal,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Text(
                text = "Idol Pilihan",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp, top = 8.dp)
            )
        }

        item {
            LazyRow(
                state = statusListHorizontal,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                contentPadding = PaddingValues(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(listIdol) { idol ->
                    ItemHorizontal(
                        idol = idol,
                        onDetailClick = { _ -> viewModel.onDetailClicked(idol) },
                        onProfilClick = { viewModel.onExplicitIntentClicked(idol.urlProfil) }
                    )
                }
            }
        }

        item {
            Text(
                text = "Daftar Lengkap",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp, top = 16.dp, bottom = 8.dp)
            )
        }

        items(listIdol) { idol ->
            ItemVertikal(
                idol = idol,
                onDetailClick = { _ -> viewModel.onDetailClicked(idol) },
                onProfilClick = { viewModel.onExplicitIntentClicked(idol.urlProfil) }
            )
        }
    }
}