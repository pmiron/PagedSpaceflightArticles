package md.pavel.icehoney.pagedspaceflightarticles.list.data.datasource

import androidx.paging.PagingSource
import md.pavel.icehoney.pagedspaceflightarticles.list.data.APIService
import md.pavel.icehoney.pagedspaceflightarticles.list.data.response.Article

class ArticleDataSource(private val apiService: APIService) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        try {
            val currentLoadingPageKey = params.key ?: 1
            val response = apiService.getArticles()
            val responseData = mutableListOf<Article>()
            val data = response.body() ?: emptyList()
            responseData.addAll(data)

            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

            return LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = currentLoadingPageKey.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

}