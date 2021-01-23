package md.pavel.icehoney.pagedspaceflightarticles.data.datasource

import androidx.paging.PagingSource
import md.pavel.icehoney.pagedspaceflightarticles.data.APIService
import md.pavel.icehoney.pagedspaceflightarticles.data.response.Article

class ArticleDataSource(private val apiService: APIService) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        try {
            val currentLoadingPageKey = params.key ?: FIRST_PAGE
            val pageSize = params.loadSize
            val pageOffset = currentLoadingPageKey * pageSize

            val response = apiService.getArticles(pageOffset, pageSize)
            val responseData = mutableListOf<Article>()
            val data = response.body() ?: emptyList()
            responseData.addAll(data)

            val prevKey =
                if (currentLoadingPageKey == FIRST_PAGE) null
                else currentLoadingPageKey.minus(ONE_PAGE)
            val nextKey =
                if (data.size < params.loadSize) null
                else currentLoadingPageKey.plus(ONE_PAGE)

            return LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = nextKey,
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    companion object {
        const val FIRST_PAGE = 1
        const val ONE_PAGE = 1
    }
}
