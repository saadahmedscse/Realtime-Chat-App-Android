package com.saadahmedsoft.sparkconvo.view.dashboard.tabs.home

import android.annotation.SuppressLint
import android.os.Bundle
import com.saadahmedsoft.sparkconvo.R
import com.saadahmedsoft.sparkconvo.base.BaseFragment
import com.saadahmedsoft.sparkconvo.base.BaseRecyclerAdapter
import com.saadahmedsoft.sparkconvo.databinding.FragmentHomeBinding
import com.saadahmedsoft.sparkconvo.databinding.ItemLayoutFirendsBinding
import com.saadahmedsoft.sparkconvo.helper.setHorizontalLayoutManager
import com.saadahmedsoft.sparkconvo.helper.visible
import com.saadahmedsoft.sparkconvo.service.dto.user.ProfileResponse
import com.squareup.picasso.Picasso

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val friendsAdapter by lazy {
        FriendsAdapter()
    }

    @SuppressLint("SetTextI18n")
    override fun onFragmentCreate(savedInstanceState: Bundle?) {
        binding.recyclerViewFriends.setHorizontalLayoutManager(requireContext())
        binding.recyclerViewFriends.adapter = friendsAdapter

        apiService.getProfile(session.bearerToken!!).getResponse("Getting user info, please wait.") {
            binding.tvName.text = "Hello ${it.name?.split(" ")?.get(0)},"
            Picasso.get().load("http://192.168.0.114/${it.photo}").into(binding.profilePicture)
        }

        apiService.getFriends(session.bearerToken!!).getNoProgressResponse {
            friendsAdapter.addItems(it)
        }

        binding.layoutNoData.root.visible()
        binding.layoutNoData.tvNoData.text = "Oops! You have no conversations opened yet"
    }

    override fun observeData() {}

    private class FriendsAdapter : BaseRecyclerAdapter<ProfileResponse, ItemLayoutFirendsBinding>() {

        override val layoutRes: Int
            get() = R.layout.item_layout_firends

        override fun onBind(
            binding: ItemLayoutFirendsBinding,
            item: ProfileResponse,
            position: Int
        ) {
            Picasso.get().load("http://192.168.0.114/${item.photo}").into(binding.profilePicture)
            binding.tvName.text = item.name?.split(" ")?.get(0)
        }
    }
}