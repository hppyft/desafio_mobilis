package com.example.desafiomobilis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafiomobilis.databinding.ResumoDespesaViewBinding


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
        mBinding.criarBtn.setOnClickListener {
            mViewModel.onCriarClicked { navigate(CriarActivity::class.java) }
        }
    }

    private fun setupList() {
        val adapter = DespesaAdapter()
        mBinding.list.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(
            context,
            mBinding.list.layoutManager?.layoutDirection ?: LinearLayoutManager.VERTICAL
        )
        mBinding.list.addItemDecoration(dividerItemDecoration)
        mViewModel.getDespesaList().observe(viewLifecycleOwner) {
            adapter.setList(it)
        }
    }
}