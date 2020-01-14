package br.com.soluevo.microapplibrary.domain

import java.io.Serializable

data class Theme(
    val toolbarHex: String,
    val bottomBarHex: String
): Serializable