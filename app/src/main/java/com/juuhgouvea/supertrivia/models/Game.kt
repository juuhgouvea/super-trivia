package com.juuhgouvea.supertrivia.models

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Game(
    var creation: String,
    var status: String,
    @SerializedName("started_at") var startedAt: Date,
    @SerializedName("finished_at") var finishedAt: Date,
    var score: Long
)
