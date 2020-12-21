package com.juuhgouvea.supertrivia.models

data class Answer(
    var order: Long,
    var description: String
) {
    override fun equals(other: Any?) = other is Answer &&
            (this.order == other.order && this.description.equals(other.description))
}
