package md.pavel.icehoney.pagedspaceflightarticles.mvp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list_mvp.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import md.pavel.icehoney.pagedspaceflightarticles.R
import md.pavel.icehoney.pagedspaceflightarticles.adapter.ArticlesListAdapter
import md.pavel.icehoney.pagedspaceflightarticles.data.APIService
import md.pavel.icehoney.pagedspaceflightarticles.data.response.Article
import md.pavel.icehoney.pagedspaceflightarticles.mvp.presenter.ListPresenterMVP
import md.pavel.icehoney.pagedspaceflightarticles.mvp.presenter.ListPresenterMVPImp
import md.pavel.icehoney.pagedspaceflightarticles.mvp.presenter.ListViewMVP

class ListFragmentMVP : Fragment(R.layout.fragment_list_mvp), ListViewMVP {

    private var presenter: ListPresenterMVP? = null
    lateinit var mainListAdapter: ArticlesListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
        presenter = ListPresenterMVPImp(APIService.getApiService())
        presenter?.attach(this)
        presenter?.getListData()
    }

    override fun collectFlow(flow: Flow<PagingData<Article>>) {
        lifecycleScope.launch {
            flow.collect {
                mainListAdapter.submitData(it)
            }
        }
    }

    private fun setupList() {
        mainListAdapter = ArticlesListAdapter(context)
        recyclerViewMVP.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mainListAdapter
        }
    }
}
