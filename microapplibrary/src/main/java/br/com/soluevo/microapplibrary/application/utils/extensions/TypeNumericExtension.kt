package br.com.soluevo.microapplibrary.application.utils.extensions

fun Int.notEqual(comparator: Int): Boolean {
    return this != comparator
}

fun Int.isEqual(comparator: Int): Boolean {
    return this == comparator
}