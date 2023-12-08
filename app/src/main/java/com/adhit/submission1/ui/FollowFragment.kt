package com.adhit.submission1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.adhit.submission1.adapter.ListFollowAdapter
import com.adhit.submission1.data.remote.response.Items
import com.adhit.submission1.databinding.FragmentFollowBinding
import com.adhit.submission1.viewmodel.DetailViewModel

class FollowFragment : Fragment() {

    private lateinit var binding: FragmentFollowBinding
    private val detailViewModel: DetailViewModel by viewModels<DetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val position = it.getInt(ARG_POSITION)
            val username = it.getString(ARG_USERNAME)
            detailViewModel.getFollowers(username.toString())
            detailViewModel.getFollowing(username.toString())
            detailViewModel.isLoading.observe(viewLifecycleOwner) {showLoading(it)}
            if (position == 1) {
                detailViewModel.followers.observe(viewLifecycleOwner) {setList(it)}
            } else {
                detailViewModel.following.observe(viewLifecycleOwner) {setList(it)}
            }
        }
    }

    fun setList(item: List<Items>){
        val  listUser = ListFollowAdapter(item)
        binding.follow.layoutManager = LinearLayoutManager(requireActivity())
        binding.follow.adapter = listUser
    }

    private fun showLoading(state: Boolean) { binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE }

    companion object {
        const val ARG_POSITION = "position"
        const val ARG_USERNAME = "USERNAME"
    }
}