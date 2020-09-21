package com.example.desafiomobilis.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.desafiomobilis.viewmodel.LoginViewModel
import com.example.desafiomobilis.databinding.LoginViewBinding
import com.example.desafiomobilis.util.navigate
import com.example.desafiomobilis.util.setSafeOnClickListener

class LoginView : Fragment() {
    private val mViewModel: LoginViewModel by viewModels()
    private lateinit var mBinding: LoginViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = LoginViewBinding.inflate(layoutInflater, null, false)
        mBinding.lifecycleOwner = this
        setupButton()
        return mBinding.root
    }

    private fun setupButton() {
        mBinding.loginBtn.setSafeOnClickListener {
            mViewModel.onLoginBtnClicked {navigate(ResumoActivity::class.java)}
        }
    }
}