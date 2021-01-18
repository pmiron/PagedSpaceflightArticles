package md.pavel.icehoney.pagedspaceflightarticles.list.data.response

import com.squareup.moshi.Json

data class Article(
    @Json(name = "id")
    val id: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "summary")
    val summary: String,
)
