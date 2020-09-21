package com.example.desafiomobilis

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.desafiomobilis.databinding.CriarViewBinding
import java.util.*

class CriarView() : Fragment() {

    companion object {
        const val DESPESA_ID_KEY = "com.example.desafiomobilis.CriacaoView.DESPESA_ID_KEY"

        @JvmStatic
        fun getInstance(despesaId: Long): CriarView {
            return CriarView().apply {
                arguments = Bundle().apply {
                    putLong(DESPESA_ID_KEY, despesaId)
                }
            }
        }

    }

    private val mViewModel: CriarViewModel by viewModels()
    private lateinit var mBinding: CriarViewBinding
    private lateinit var mTitle: String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val id = arguments?.getLong(DESPESA_ID_KEY)
        mViewModel.onIdLoaded(id)
        mTitle =
            if (id != null) getString(R.string.criar_view_alteracao_despesa_title) else getString(R.string.criar_view_nova_despesa_title)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupBinding()
        setupTitle()
        setupBtn()
        setupDatepicker()
        setupValores()
        return mBinding.root
    }

    private fun setupTitle() {
        mBinding.title = mTitle
    }

    private fun setupBinding() {
        mBinding = CriarViewBinding.inflate(layoutInflater, null, false)
        mBinding.lifecycleOwner = viewLifecycleOwner
    }

    private fun setupBtn() {
        mBinding.finalizarBtn.setOnClickListener {
            mViewModel.onFinalizarClicked(
                mBinding.dataValue.text.toString(),
                mBinding.valorValue.text.toString(),
                mBinding.descricaoValue.text.toString(),
                mBinding.pagoRg.checkedRadioButtonId
            ) {
                activity?.finish()
            }
        }
    }

    private fun setupDatepicker() {
        mBinding.dataValue.inputType = InputType.TYPE_NULL
        mBinding.dataValue.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                Log.d("hpp", "click")
                activity?.let {
                    Log.d("hpp", "let")
                    DatePickerDialog(
                        it,
                        DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                            val calendar = Calendar.getInstance()
                            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                            calendar.set(Calendar.MONTH, month)
                            calendar.set(Calendar.YEAR, year)
                            mBinding.dataValue.setText(Date(calendar.timeInMillis).toFormattedString())
                        },
                        2020, 1, 1
                    ).show()
                }
                mBinding.dataValue.clearFocus()
            }
        }
    }

    private fun setupValores() {
        //todo
    }
}
