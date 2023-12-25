package com.example.quicktalk.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quicktalk.models.AuthorizationViewModel
import com.example.quicktalk.R
import com.example.quicktalk.databinding.FragmentAuthorizationBinding

class AuthorizationFragment : Fragment() {

    private lateinit var binding: FragmentAuthorizationBinding
    private val authorizationViewModel: AuthorizationViewModel by lazy {
        ViewModelProvider(this)[AuthorizationViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthorizationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeState()

        binding.run {
            loginButton.setOnClickListener {
                authorizationViewModel.login(
                    email = emailField.text.toString().ifEmpty { " " },
                    password = passwordField.text.toString().ifEmpty { " " }
                )

            }

            forgotPasswordTxt.setOnClickListener {
                findNavController().navigate(R.id.resetPasswordFragment)
            }

            registerTxt.setOnClickListener {
                findNavController().navigate(R.id.registrationFragment)
            }
        }


    }

    private fun observeState() {
        authorizationViewModel.user.observe(viewLifecycleOwner) {
            if (it != null){
                navigateToUserFragment()
            }
        }

        authorizationViewModel.error.observe(viewLifecycleOwner) {
            if (it != null){
                Toast.makeText(context, "$it", Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun navigateToUserFragment() {
        findNavController().popBackStack()
        findNavController().navigate(R.id.usersFragment)

    }


}