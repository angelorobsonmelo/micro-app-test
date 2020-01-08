package br.com.soluevo.microapplibrary.application.commom.utils.extensions

import kotlin.random.Random

fun Int.randomRange(): Int {
    return Random.nextInt(this)
}