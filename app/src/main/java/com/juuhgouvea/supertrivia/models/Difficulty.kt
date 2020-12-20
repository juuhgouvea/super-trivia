package com.juuhgouvea.supertrivia.models

data class Difficulty(
    var name: String,
    var value: String
) {
    override fun toString() = name
}
