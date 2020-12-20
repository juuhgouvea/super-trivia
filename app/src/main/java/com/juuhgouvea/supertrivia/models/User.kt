package com.juuhgouvea.supertrivia.models

data class User(
    var email: String,
    var name: String,
    var password: String,
    var token: String
) {
    override fun equals(other: Any?) = other is User && this.email.equals(other.email)
}
