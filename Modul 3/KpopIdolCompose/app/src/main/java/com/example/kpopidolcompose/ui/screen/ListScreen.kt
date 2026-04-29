package com.example.kpopidolcompose.ui.screen

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kpopidolcompose.data.DataIdol
import com.example.kpopidolcompose.ui.Screen
import com.example.kpopidolcompose.ui.components.ItemHorizontal
import com.example.kpopidolcompose.ui.components.ItemVertikal

@Composable
fun ListScreen(navController: NavController) {
    val statusListVertikal = rememberLazyListState()
    val statusListHorizontal = rememberLazyListState()
    val listIdol = DataIdol.listIdol

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
                        onDetailClick = { nama ->
                            navController.navigate(Screen.Detail.createRoute(nama))
                        }
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
                onDetailClick = { nama ->
                    navController.navigate(Screen.Detail.createRoute(nama))
                }
            )
        }
    }
}