package com.example.desafiomobilis.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafiomobilis.*
import com.example.desafiomobilis.databinding.ResumoDespesaViewBinding
import com.example.desafiomobilis.util.navigate
import com.example.desafiomobilis.util.setSafeOnClickListener
import com.example.desafiomobilis.viewmodel.ResumoViewModel

class ResumoDespesaView : Fragment() {

    private val mViewModel: ResumoViewModel by viewModels()
    private lateinit var mBinding: ResumoDespesaViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupViewModel()
        setupBinding()
        setupCriarButton()
        setupList()
        setupError()
        return mBinding.root
    }

    private fun setupViewModel() {
        viewLifecycleOwner.lifecycle.addObserver(mViewModel)
    }

    private fun setupBinding() {
        mBinding = ResumoDespesaViewBinding.inflate(layoutInflater, null, false)
        mBinding.lifecycleOwner = this
    }

    private fun setupCriarButton() {
        mBinding.criarBtn.setSafeOnClickListener {
            mViewModel.onCriarClicked { navigate(CriarActivity::class.java) }
        }
    }

    private fun setupList() {
        val adapter = DespesaAdapter { id ->
            mViewModel.onAlterarClicked {
                navigate(
                    CriarActivity::class.java,
                    Bundle().apply { putString(CriarView.DESPESA_ID_KEY, id) })
            }
        }
        mBinding.list.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(
            context,
            mBinding.list.layoutManager?.layoutDirection ?: LinearLayoutManager.VERTICAL
        )
        mBinding.list.addItemDecoration(dividerItemDecoration)
        mViewModel.getDespesaList().observe(viewLifecycleOwner) {
            adapter.setList(it)
            mBinding.loading.visibility = View.GONE
            mBinding.list.visibility = View.VISIBLE
        }
    }

    private fun setupError() {
        mBinding.reloadBtn.setSafeOnClickListener {
            mBinding.errorGroup.visibility = View.GONE
            mBinding.loading.visibility = View.VISIBLE
            mViewModel.load()
        }
        mViewModel.getError().observe(viewLifecycleOwner) {
            when (it) {
                ResumoViewModel.ERROR_GET_LIST -> {
                    mBinding.errorText = getString(R.string.error_get_list_text)
                    mBinding.list.visibility = View.GONE
                    mBinding.errorGroup.visibility = View.VISIBLE
                }
            }
        }
    }
}