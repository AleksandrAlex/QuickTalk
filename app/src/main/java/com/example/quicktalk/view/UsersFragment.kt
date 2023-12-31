package com.example.quicktalk.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quicktalk.ClickListener
import com.example.quicktalk.R
import com.example.quicktalk.data.User
import com.example.quicktalk.UsersAdapter
import com.example.quicktalk.models.UsersViewModel
import com.example.quicktalk.databinding.FragmentUsersBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UsersFragment : Fragment() {

    private lateinit var binding: FragmentUsersBinding
    private val usersViewModel: UsersViewModel by lazy {
        ViewModelProvider(this)[UsersViewModel::class.java]
    }

    private val usersAdapter = UsersAdapter(object : ClickListener {
        override fun onClick(user: User) {
            Toast.makeText(context, "${user.name}", Toast.LENGTH_LONG).show()
        }

        override fun onImageClick(user: User) {
            Toast.makeText(context, "${user.isOnline} - IMAGE Click!!!", Toast.LENGTH_LONG).show()
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.userList.adapter = usersAdapter

        usersViewModel.users.observe(viewLifecycleOwner) { users ->
            usersAdapter.submitList(users)
        }

        usersViewModel.user.observe(viewLifecycleOwner) {
            if (it == null) {
                findNavController().popBackStack()
                findNavController().navigate(R.id.authorizationFragment)
            }
        }

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_item_logout -> {
                    usersViewModel.logout()
                    true
                }

                else -> {
                    false
                }
            }
        }
    }
}