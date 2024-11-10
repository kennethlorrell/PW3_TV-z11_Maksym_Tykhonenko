package com.deepdark.lab3.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun SolarProfitCalculatorPagePreview() {
    SolarProfitCalculatorPage()
}

@Composable
fun SolarProfitCalculatorPage() {
    var dailyAveragePower by remember { mutableStateOf("") }
    var actualDeviation by remember { mutableStateOf("") }
    var desiredDeviation by remember { mutableStateOf("") }
    var electricityCost by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Введіть параметри для розрахунку прибутку:")

        OutlinedTextField(
            value = dailyAveragePower,
            onValueChange = { dailyAveragePower = it },
            label = { Text("Середньодобова потужність (МВт)") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = actualDeviation,
            onValueChange = { actualDeviation = it },
            label = { Text("Фактичне відхилення (МВт)") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = desiredDeviation,
            onValueChange = { desiredDeviation = it },
            label = { Text("Бажане відхилення (МВт)") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = electricityCost,
            onValueChange = { electricityCost = it },
            label = { Text("Вартість електроенергії (грн / кВт·год)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {

        }) {
            Text("Розрахувати прибуток")
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}
