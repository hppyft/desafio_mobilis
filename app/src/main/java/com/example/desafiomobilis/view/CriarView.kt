package com.example.desafiomobilis.view

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.example.desafiomobilis.databinding.CriarViewBinding
import com.example.desafiomobilis.model.MovimentacaoFinanceira
import com.example.desafiomobilis.util.setSafeOnClickListener
import com.example.desafiomobilis.util.toFormattedString
import com.example.desafiomobilis.viewmodel.CriarViewModel
import java.util.*

abstract class CriarView<T : MovimentacaoFinanceira> : Fragment() {

    abstract fun getMF(): T
    abstract fun getViewModel(): CriarViewModel<T>
    abstract fun getTitle(): String
    abstract fun getEfetuadoLabel(): String
    abstract fun getNaoEfetuadoLabel(): String

    companion object {
        const val MF_ID_KEY = "com.example.desafiomobilis.CriacaoView.MF_ID_KEY"
        const val MF_KEY = "com.example.desafiomobilis.CriacaoView.MF_KEY"

        const val DESPESA = 1
        const val RECEITA = 2
    }

    private lateinit var mBinding: CriarViewBinding
    protected var mId: String? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mId = arguments?.getString(MF_ID_KEY)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupBinding()
        getViewModel().onIdLoaded(mId) { fillIn() }
        setupTitle()
        setupLabels()
        setupBtn()
        setupValores()
        return mBinding.root
    }

    private fun setupBinding() {
        mBinding = CriarViewBinding.inflate(layoutInflater, null, false)
        mBinding.lifecycleOwner = viewLifecycleOwner
    }

    private fun setupTitle() {
        mBinding.title = getTitle()
    }

    private fun setupLabels() {
        mBinding.efetuadoLabel = getEfetuadoLabel()
        mBinding.naoEfetuadoLabel = getNaoEfetuadoLabel()
    }

    private fun setupBtn() {
        mBinding.finalizarBtn.setSafeOnClickListener {
            fillOut()
            getViewModel().onFinalizarClicked(
                mf = mBinding.mf as T
            ) {
                activity?.finish()
            }
        }
    }

    private fun fillOut() {
        mBinding.mf?.valor = mBinding.valorValue.text.toString().toDouble()
        mBinding.mf?.efetuado = mBinding.pagoRb.isChecked
    }

    private fun setupValores() {
        getViewModel().getMF().observe(viewLifecycleOwner) {
            fillIn(it)
        }
    }

    private fun fillIn(mf: T? = getMF()) {
        mBinding.mf = mf ?: getMF()
        setupDatepicker()
        if (mf?.valor != null) mBinding.valorValue.setText(mf.valor.toString())
        when (mf?.efetuado) {
            true -> mBinding.pagoRb.isChecked = true
            false, null -> mBinding.naoPagoRb.isChecked = true
        }
    }

    private fun setupDatepicker() {
        mBinding.dataValue.inputType = InputType.TYPE_NULL
        if (mBinding.mf?.data != null) mBinding.dataValue.setText(
            mBinding.mf?.data?.toFormattedString() ?: ""
        )
        mBinding.dataValue.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                activity?.let {
                    val defaultCalendar = Calendar.getInstance()
                    defaultCalendar.time = mBinding.mf?.data ?: Date()
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
                            mBinding.mf?.data = Date(calendar.timeInMillis)
                            mBinding.dataValue.setText(
                                mBinding.mf?.data?.toFormattedString() ?: ""
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
