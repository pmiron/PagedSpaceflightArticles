package md.pavel.icehoney.pagedspaceflightarticles.mvp.presenter

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import md.pavel.icehoney.pagedspaceflightarticles.data.response.Article

interface ListViewMVP {

    fun collectFlow(flow: Flow<PagingData<Article>>)
}

interface ListPresenterMVP {

    var view: ListViewMVP?

    fun attach(view: ListViewMVP)

    fun detach()

    fun getListData()
}