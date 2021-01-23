package md.pavel.icehoney.pagedspaceflightarticles.viewmodel.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import md.pavel.icehoney.pagedspaceflightarticles.PAGE_SIZE
import md.pavel.icehoney.pagedspaceflightarticles.data.APIService
import md.pavel.icehoney.pagedspaceflightarticles.data.datasource.ArticleDataSource

class MainViewModel(private val apiService: APIService) : ViewModel() {

    val listData = Pager(PagingConfig(pageSize = PAGE_SIZE)) {
        ArticleDataSource(apiService)
    }.flow.cachedIn(viewModelScope)
}
