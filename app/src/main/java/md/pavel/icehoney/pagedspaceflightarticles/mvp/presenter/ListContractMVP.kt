package md.pavel.icehoney.pagedspaceflightarticles.mvp.presenter

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import md.pavel.icehoney.pagedspaceflightarticles.viewmodel.list.data.response.Article

interface ListViewMVP {

}

interface ListPresenterMVP {

    var view: ListViewMVP?

    fun attach(view: ListViewMVP)

    fun detach()

    fun getListData(): Flow<PagingData<Article>>
}