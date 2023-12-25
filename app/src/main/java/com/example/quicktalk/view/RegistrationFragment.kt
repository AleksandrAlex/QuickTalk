package com.example.quicktalk.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quicktalk.R
import com.example.quicktalk.models.RegistrationViewModel
import com.example.quicktalk.databinding.FragmentRegistrationBinding


class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding
    private val registrationViewModel: RegistrationViewModel by lazy {
        ViewModelProvider(this)[RegistrationViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            loginButton.setOnClickListener {
                val email = emailField.text.toString().trim()
                val password = passwordField.text.toString().trim()
                val name = nameField.text.toString().trim()
                val lastName = lastNameField.text.toString().trim()
                Toast.makeText(it.context, "$email", Toast.LENGTH_LONG).show()

                registrationViewModel.signUp(email = email, password = password, name = name, lastName = lastName)
            }
        }

        registrationViewModel.error.observe(viewLifecycleOwner){
            if (it != null){
                Toast.makeText(context, "$it", Toast.LENGTH_LONG).show()
            }
        }

        registrationViewModel.user.observe(viewLifecycleOwner){
            if (it != null){
                findNavController().popBackStack(R.id.authorizationFragment, true)
                findNavController().navigate(R.id.usersFragment)
            }
        }
    }

}