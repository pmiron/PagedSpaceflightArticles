package md.pavel.icehoney.pagedspaceflightarticles.viewmodel.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import md.pavel.icehoney.pagedspaceflightarticles.viewmodel.list.data.APIService
import md.pavel.icehoney.pagedspaceflightarticles.viewmodel.list.data.datasource.ArticleDataSource

class MainViewModel(private val apiService: APIService) : ViewModel() {

    val listData = Pager(PagingConfig(pageSize = PAGE_SIZE)) {
        ArticleDataSource(apiService)
    }.flow.cachedIn(viewModelScope)

    companion object {
        const val PAGE_SIZE = 4
    }
}
