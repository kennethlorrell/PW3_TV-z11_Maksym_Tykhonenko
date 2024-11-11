package com.deepdark.lab3.data

import kotlin.math.PI
import kotlin.math.exp
import kotlin.math.sqrt

// Обчислюємо чистий прибуток з фактичним і бажаним відхиленням
fun calculateSolarProfit(
    dailyPower: Double,
    actualDeviation: Double,
    desiredDeviation: Double,
    electricityCost: Double
): SolarProfitWithDeviation {
    val netProfitActual = calculateNetProfit(dailyPower, actualDeviation, electricityCost)
    val netProfitDesired = calculateNetProfit(dailyPower, desiredDeviation, electricityCost)

    return SolarProfitWithDeviation(
        netProfitActual,
        netProfitDesired
    )
}

// Обчислюємо чистий прибуток з відхиленням
fun calculateNetProfit(
    dailyPower: Double,
    deviation: Double,
    electricityCost: Double
): Double {
    val (lowerBound, upperBound) = calculateBounds(dailyPower)

    val energyDistribution = calculateEnergyDistribution(lowerBound, upperBound, dailyPower, deviation)
    val energyWithoutImbalance = dailyPower * 24 * energyDistribution
    val profit = energyWithoutImbalance * electricityCost

    val energyWithImbalance = (dailyPower * 24) - energyWithoutImbalance
    val penalty = energyWithImbalance * electricityCost

    return profit - penalty
}

// Вираховуємо нижню та верхню границі допустимого відхилення
fun calculateBounds(dailyPower: Double): Pair<Double, Double> {
    val lowerBound = dailyPower * (1 - 0.05)
    val upperBound = dailyPower * (1 + 0.05)

    return Pair(lowerBound, upperBound)
}

// Інтегруємо значення у межах допустимих границь
fun calculateEnergyDistribution(
    lowerBound: Double,
    upperBound: Double,
    mean: Double,
    standardDeviation: Double
): Double {
    val step = 0.001
    var result = 0.0
    var x = lowerBound

    while (x < upperBound) {
        val y1 = normalDistribution(x, mean, standardDeviation)
        val y2 = normalDistribution(x + step, mean, standardDeviation)

        result += (y1 + y2) * step / 2
        x += step
    }

    return result
}

// Рахуємо значення нормального розподілу у певній точці
fun normalDistribution(
    x: Double,
    mean: Double,
    standardDeviation: Double
): Double {
    return (1 / (standardDeviation * sqrt(2 * PI))) * exp(-((x - mean) * (x - mean)) / (2 * standardDeviation * standardDeviation))
}
