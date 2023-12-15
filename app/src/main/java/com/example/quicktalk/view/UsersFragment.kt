package com.example.quicktalk.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quicktalk.R
import com.example.quicktalk.UsersViewModel
import com.example.quicktalk.databinding.FragmentUsersBinding

class UsersFragment : Fragment() {

    private lateinit var binding: FragmentUsersBinding
    private val usersViewModel: UsersViewModel by lazy {
        ViewModelProvider(this)[UsersViewModel::class.java]
    }

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
        usersViewModel.user.observe(viewLifecycleOwner) {
            if (it == null) {
                findNavController().popBackStack()
                findNavController().navigate(R.id.authorizationFragment)
            }
        }

        binding.toolbar.setOnMenuItemClickListener {
           when(it.itemId){
               R.id.menu_item_logout -> {
                   usersViewModel.logout()
                   true
               }

               else -> {false}
           }
        }


    }
}