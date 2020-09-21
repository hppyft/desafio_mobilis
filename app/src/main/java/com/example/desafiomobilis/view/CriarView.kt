package com.example.desafiomobilis.view

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.example.desafiomobilis.*
import com.example.desafiomobilis.databinding.CriarViewBinding
import com.example.desafiomobilis.model.Despesa
import com.example.desafiomobilis.util.setSafeOnClickListener
import com.example.desafiomobilis.util.toFormattedString
import com.example.desafiomobilis.viewmodel.CriarViewModel
import java.util.*

class CriarView() : Fragment() {

    companion object {
        const val DESPESA_ID_KEY = "com.example.desafiomobilis.CriacaoView.DESPESA_ID_KEY"

        @JvmStatic
        fun getInstance(despesaId: String): CriarView {
            return CriarView().apply {
                arguments = Bundle().apply {
                    putString(DESPESA_ID_KEY, despesaId)
                }
            }
        }
    }

    private val mViewModel: CriarViewModel by viewModels()
    private lateinit var mBinding: CriarViewBinding
    private var mId: String? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mId = arguments?.getString(DESPESA_ID_KEY)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupBinding()
        mViewModel.onIdLoaded(mId) { fillIn() }
        setupTitle()
        setupBtn()
        setupValores()
        return mBinding.root
    }

    private fun setupBinding() {
        mBinding = CriarViewBinding.inflate(layoutInflater, null, false)
        mBinding.lifecycleOwner = viewLifecycleOwner
    }

    private fun setupTitle() {
        mBinding.title =
            if (mId != null) getString(R.string.criar_view_alteracao_despesa_title) else getString(
                R.string.criar_view_nova_despesa_title
            )
    }

    private fun setupBtn() {
        mBinding.finalizarBtn.setSafeOnClickListener {
            fillOut()
            mViewModel.onFinalizarClicked(
                mBinding.despesa!!
            ) {
                activity?.finish()
            }
        }
    }

    private fun fillOut() {
        mBinding.despesa?.valor = mBinding.valorValue.text.toString().toDouble()
        mBinding.despesa?.pago = mBinding.pagoRb.isChecked
    }

    private fun setupValores() {
        mViewModel.getDespesa().observe(viewLifecycleOwner) {
            fillIn(it)
        }
    }

    private fun fillIn(despesa: Despesa? = Despesa()) {
        mBinding.despesa = despesa ?: Despesa()
        setupDatepicker()
        if (despesa?.valor!= null) mBinding.valorValue.setText(despesa.valor.toString())
        when(despesa?.pago){
            true -> mBinding.pagoRb.isChecked = true
            false, null -> mBinding.naoPagoRb.isChecked = true
        }
    }

    private fun setupDatepicker() {
        mBinding.dataValue.inputType = InputType.TYPE_NULL
        if (mBinding.despesa?.data != null) mBinding.dataValue.setText(mBinding.despesa?.data?.toFormattedString() ?: "")
        mBinding.dataValue.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                activity?.let {
                    val defaultCalendar = Calendar.getInstance()
                    defaultCalendar.time = mBinding.despesa?.data ?: Date()
                    val yearDefault = defaultCalendar.get(Calendar.YEAR)
                    val monthDefault = defaultCalendar.get(Calendar.MONTH)
                    val dayDefault = defaultCalendar.get(Calendar.DAY_OF_MONTH)
                    DatePickerDialog(
                        it,
                        DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                            val calendar = Calendar.getInstance()
                            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                            calendar.set(Calendar.MONTH, month)
                            calendar.set(Calendar.YEAR, year)
                            mBinding.despesa?.data = Date(calendar.timeInMillis)
                            mBinding.dataValue.setText(
                                mBinding.despesa?.data?.toFormattedString() ?: ""
                            )
                        },
                        yearDefault, monthDefault, dayDefault
                    ).show()
                }
                mBinding.dataValue.clearFocus()
            }
        }
    }
}
