package com.example.desafiomobilis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.desafiomobilis.databinding.LoginViewBinding

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
        mBinding.loginBtn.setOnClickListener {
            mViewModel.onLoginBtnClicked {navigate(ResumoActivity::class.java)}
        }
    }
}