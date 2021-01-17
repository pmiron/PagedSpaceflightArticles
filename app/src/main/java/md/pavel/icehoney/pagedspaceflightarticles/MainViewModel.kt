package md.pavel.icehoney.pagedspaceflightarticles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import md.pavel.icehoney.pagedspaceflightarticles.list.data.APIService
import md.pavel.icehoney.pagedspaceflightarticles.list.data.datasource.ArticleDataSource

class MainViewModel(private val apiService: APIService) : ViewModel() {

    val listData = Pager(PagingConfig(pageSize = 10)) {
        ArticleDataSource(apiService)
    }.flow.cachedIn(viewModelScope)
}