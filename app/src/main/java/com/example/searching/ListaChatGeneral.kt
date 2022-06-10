package com.example.searching

data class ListaChatGeneral(
    val id:String="",
    val nombrePersona: String ="",
    val mensaje: String="",
    val hora: String="",
    val imagen: String="",
    var users: List<String> = emptyList()
)
