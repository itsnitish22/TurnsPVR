package com.nitishsharma.turnspvr.main.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.nitishsharma.turnspvr.application.common.SwipeRefreshCallback
import com.nitishsharma.turnspvr.application.utils.setupSwipeRefresh
import com.nitishsharma.turnspvr.databinding.FragmentMovieDetailsBinding
import com.nitishsharma.turnspvr.main.details.factory.MovieDetailsVMFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailsFragment : Fragment(), SwipeRefreshCallback {
    private lateinit var binding: FragmentMovieDetailsBinding
    private val args: MovieDetailsFragmentArgs by navArgs()

    @Inject
    lateinit var factory: MovieDetailsVMFactory
    private val viewModel: MovieDetailsViewmodel by viewModels {
        MovieDetailsViewmodel.provideFactory(factory, args.movieId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentMovieDetailsBinding.inflate(layoutInflater, container, false).also {
        binding = it
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSwipeRefresh()
        initClickListener()
    }

    private fun initClickListener() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initSwipeRefresh() {
        binding.swipRefresh.setupSwipeRefresh(this)
    }

    override fun onForcedRefresh() {
        viewModel.getTopLevelMovieDetails()
    }
}