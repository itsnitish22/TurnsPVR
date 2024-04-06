package com.nitishsharma.turnspvr.main.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nitishsharma.turnspvr.application.common.SwipeRefreshCallback
import com.nitishsharma.turnspvr.application.utils.EventObserver
import com.nitishsharma.turnspvr.application.utils.navigate
import com.nitishsharma.turnspvr.application.utils.scrollTopTop
import com.nitishsharma.turnspvr.application.utils.setupSwipeRefresh
import com.nitishsharma.turnspvr.databinding.FragmentHomeBinding
import com.nitishsharma.turnspvr.main.home.callbacks.HomeItemClickCallback
import com.nitishsharma.turnspvr.main.home.epoxy.HomeEpoxyController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), SwipeRefreshCallback {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private val homeEpoxyController: HomeEpoxyController by lazy {
        val callback = HomeItemClickCallback(viewModel)
        HomeEpoxyController(callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentHomeBinding.inflate(layoutInflater, container, false).also {
        binding = it
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObservers()
    }

    private fun initObservers() {
        viewModel.homeMainModel.observe(viewLifecycleOwner) {
            homeEpoxyController.setData(it)
            Handler(Looper.getMainLooper()).postDelayed({
                binding.recylerHome.scrollTopTop()
            }, 500)
        }
        viewModel.navDirections.observe(viewLifecycleOwner, EventObserver {
            navigate(it)
        })
    }

    private fun initRecyclerView() {
        binding.swipRefresh.setupSwipeRefresh(this)
        homeEpoxyController.setFilterDuplicates(true)
        binding.recylerHome.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = homeEpoxyController.adapter
        }
    }

    override fun onForcedRefresh() {
        viewModel.getAllMovies()
    }

}