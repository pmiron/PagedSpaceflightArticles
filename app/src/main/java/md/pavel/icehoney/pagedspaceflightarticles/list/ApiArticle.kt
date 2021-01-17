package md.pavel.icehoney.pagedspaceflightarticles.list

data class ApiArticle(
    val id: String,
    val tittle: String,
    val url: String,
    val imageUrl: String,
    val newsSite: String,
    val summary: String,
    val publishedAt: String,
    val updatedAt: String,
)