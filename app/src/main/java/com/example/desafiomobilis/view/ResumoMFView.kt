package com.example.desafiomobilis.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafiomobilis.*
import com.example.desafiomobilis.databinding.ResumoMfViewBinding
import com.example.desafiomobilis.model.MovimentacaoFinanceira
import com.example.desafiomobilis.util.navigate
import com.example.desafiomobilis.util.setSafeOnClickListener
import com.example.desafiomobilis.viewmodel.ResumoMFViewModel

abstract class ResumoMFView<T : MovimentacaoFinanceira> : Fragment() {

    abstract fun getViewModel(): ResumoMFViewModel<T>
    abstract fun getMFTypeKey(): Int
    abstract fun getErrorText(): String

    private lateinit var mBinding: ResumoMfViewBinding

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

    override fun onResume() {
        mBinding.list.visibility = View.GONE
        mBinding.loading.visibility = View.VISIBLE
        super.onResume()
    }

    private fun setupViewModel() {
        viewLifecycleOwner.lifecycle.addObserver(getViewModel())
    }

    private fun setupBinding() {
        mBinding = ResumoMfViewBinding.inflate(layoutInflater, null, false)
        mBinding.lifecycleOwner = this
    }

    private fun setupCriarButton() {
        mBinding.criarBtn.setSafeOnClickListener {
            getViewModel().onCriarClicked { navigate(
                CriarActivity::class.java,
                Bundle().apply {
                    putInt(CriarView.MF_KEY, getMFTypeKey())
                }) }
        }
    }

    private fun setupList() {
        val adapter = MFAdapter { id, view ->
            openPopupMenu(id, view)
        }
        mBinding.list.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(
            context,
            mBinding.list.layoutManager?.layoutDirection ?: LinearLayoutManager.VERTICAL
        )
        mBinding.list.addItemDecoration(dividerItemDecoration)
        getViewModel().getList().observe(viewLifecycleOwner) {
            adapter.setList(it)
            mBinding.loading.visibility = View.GONE
            mBinding.list.visibility = View.VISIBLE
        }
    }

    private fun openPopupMenu(id: String, view: View) {
        activity?.let {
            var popup = PopupMenu(it, view)
            popup.menuInflater.inflate(R.menu.despesa_item_menu, popup.menu)
            popup.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.atualizar_menu_option -> {
                        getViewModel().onAlterarClicked {
                            getViewModel().onAlterarClicked {
                                navigate(
                                    CriarActivity::class.java,
                                    Bundle().apply {
                                        putInt(CriarView.MF_KEY, getMFTypeKey())
                                        putString(CriarView.MF_ID_KEY, id)
                                    })
                            }
                        }
                    }
                    R.id.remover_menu_option -> {
                        mBinding.list.visibility = View.GONE
                        mBinding.loading.visibility = View.VISIBLE
                        getViewModel().onRemoverClicked(id)
                    }
                }
                true
            }
            popup.show()
        }
    }

    private fun setupError() {
        mBinding.reloadBtn.setSafeOnClickListener {
            mBinding.errorGroup.visibility = View.GONE
            mBinding.loading.visibility = View.VISIBLE
            getViewModel().load()
        }
        getViewModel().getError().observe(viewLifecycleOwner) {
            when (it) {
                ResumoMFViewModel.ERROR_GET_LIST -> {
                    mBinding.errorText = getErrorText()
                    mBinding.list.visibility = View.GONE
                    mBinding.errorGroup.visibility = View.VISIBLE
                }
            }
        }
    }
}