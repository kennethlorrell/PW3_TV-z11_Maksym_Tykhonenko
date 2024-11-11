package com.deepdark.lab3.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.deepdark.lab3.data.SolarProfitWithDeviation
import com.deepdark.lab3.data.calculateSolarProfit
import com.deepdark.lab3.utils.roundTo

@Preview(showBackground = true)
@Composable
fun SolarProfitCalculatorPagePreview() {
    SolarProfitCalculatorPage()
}

@Composable
fun SolarProfitCalculatorPage() {
    var dailyAveragePower by remember { mutableStateOf("5") }
    var actualDeviation by remember { mutableStateOf("1") }
    var desiredDeviation by remember { mutableStateOf("0.25") }
    var electricityCost by remember { mutableStateOf("7") }

    var result by remember { mutableStateOf<SolarProfitWithDeviation?>(null) }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Введіть параметри для розрахунку прибутку:")

        // Input fields
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
            val dailyPower = dailyAveragePower.toDoubleOrNull() ?: 0.0
            val actualDev = actualDeviation.toDoubleOrNull() ?: 0.0
            val desiredDev = desiredDeviation.toDoubleOrNull() ?: 0.0
            val cost = electricityCost.toDoubleOrNull() ?: 0.0

            result = calculateSolarProfit(dailyPower, actualDev, desiredDev, cost)
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Розрахувати прибуток")
        }

        Spacer(modifier = Modifier.height(16.dp))

        result?.let {
            Text("Прибуток до удосконалення: ${it.netProfitWithActualDeviation.roundTo(1)} тис. грн")
            Text("Прибуток після удосконалення: ${it.netProfitWithDesiredDeviation.roundTo(1)} тис. грн")
        }
    }
}