package md.pavel.icehoney.pagedspaceflightarticles.mvp.presenter

import androidx.paging.Pager
import androidx.paging.PagingConfig
import md.pavel.icehoney.pagedspaceflightarticles.PAGE_SIZE
import md.pavel.icehoney.pagedspaceflightarticles.data.APIService
import md.pavel.icehoney.pagedspaceflightarticles.data.datasource.ArticleDataSource

class ListPresenterMVPImp(private val apiService: APIService) : ListPresenterMVP {

    override var view: ListViewMVP? = null

    private val innerListData = Pager(PagingConfig(pageSize = PAGE_SIZE)) {
        ArticleDataSource(apiService)
    }

    override fun attach(view: ListViewMVP) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

    override fun getListData() {
        view?.collectFlow(innerListData.flow)
    }
}
