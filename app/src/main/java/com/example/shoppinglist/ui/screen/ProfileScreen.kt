package com.example.shoppinglist.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.shoppinglist.R

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Nama: Siti Aliani Husnah.F")
        Text("NIM: 2311522006")
        Text("Hobi: Membaca, Menyanyi")
        Text("TTL: Pariaman, 20 September 2005")
        Text("Peminatan: Data Analyst")
        Spacer(Modifier.height(12.dp))
        Image(
            painter = painterResource(id = R.drawable.foto_diri),
            contentDescription = "Foto Diri"
        )
    }
}
