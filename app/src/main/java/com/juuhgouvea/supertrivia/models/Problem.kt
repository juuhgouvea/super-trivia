package com.juuhgouvea.supertrivia.models

data class Problem(
    var id: Long,
    var question: String,
    var difficulty: String,
    var category: Category,
    var answers: List<Answer>
) {
    override fun equals(other: Any?) = other is Problem && this.id == other.id
}
