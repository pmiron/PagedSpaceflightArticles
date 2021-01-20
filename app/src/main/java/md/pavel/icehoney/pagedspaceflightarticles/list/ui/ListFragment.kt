package md.pavel.icehoney.pagedspaceflightarticles.list.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import md.pavel.icehoney.pagedspaceflightarticles.MainViewModel
import md.pavel.icehoney.pagedspaceflightarticles.MainViewModelFactory
import md.pavel.icehoney.pagedspaceflightarticles.R
import md.pavel.icehoney.pagedspaceflightarticles.list.adapter.MainListAdapter
import md.pavel.icehoney.pagedspaceflightarticles.list.data.APIService

class ListFragment : Fragment(R.layout.fragment_list) {

    lateinit var viewModel: MainViewModel
    lateinit var mainListAdapter: MainListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupList()
        setupView()
    }

    private fun setupView() {
        lifecycleScope.launch {
            viewModel.listData.collect {
                mainListAdapter.submitData(it)
            }
        }
    }

    private fun setupList() {
        mainListAdapter = MainListAdapter(context)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mainListAdapter
        }
    }

    private fun setupViewModel() {
        viewModel =
            ViewModelProvider(
                this,
                MainViewModelFactory(APIService.getApiService())
            )[MainViewModel::class.java]
    }
}
