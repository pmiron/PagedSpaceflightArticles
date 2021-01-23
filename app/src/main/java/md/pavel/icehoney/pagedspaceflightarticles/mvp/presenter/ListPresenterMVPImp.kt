package md.pavel.icehoney.pagedspaceflightarticles.mvp.presenter

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import md.pavel.icehoney.pagedspaceflightarticles.viewmodel.list.data.APIService
import md.pavel.icehoney.pagedspaceflightarticles.viewmodel.list.data.datasource.ArticleDataSource
import md.pavel.icehoney.pagedspaceflightarticles.viewmodel.list.data.response.Article
import md.pavel.icehoney.pagedspaceflightarticles.viewmodel.list.viewmodel.MainViewModel

class ListPresenterMVPImp(private val apiService: APIService) : ListPresenterMVP {

    override var view: ListViewMVP? = null

    private val innerListData = Pager(PagingConfig(pageSize = MainViewModel.PAGE_SIZE)) {
        ArticleDataSource(apiService)
    }

    override fun attach(view: ListViewMVP) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

    override fun getListData(): Flow<PagingData<Article>> {
        return innerListData.flow
    }

}
