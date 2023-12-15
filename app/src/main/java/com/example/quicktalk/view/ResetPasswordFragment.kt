package com.example.quicktalk.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.quicktalk.ResetPasswordViewModel
import com.example.quicktalk.databinding.FragmentResetPasswordBinding

class ResetPasswordFragment : Fragment() {

    private lateinit var binding: FragmentResetPasswordBinding
    private val resetPasswordViewModel: ResetPasswordViewModel by lazy {
        ViewModelProvider(this)[ResetPasswordViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResetPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resetPasswordViewModel.error.observe(viewLifecycleOwner){
            Toast.makeText(context, "$it", Toast.LENGTH_LONG).show()
        }

        resetPasswordViewModel.isSuccess.observe(viewLifecycleOwner){
            Toast.makeText(context, "$it", Toast.LENGTH_LONG).show()
        }

        binding.run {
            loginButton.setOnClickListener {
                val email = emailField.text.toString().trim()
                    resetPasswordViewModel.resetPassword(email)
            }
        }

    }


}