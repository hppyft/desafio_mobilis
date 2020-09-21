package com.example.desafiomobilis.view
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
//import androidx.lifecycle.observe
//import com.example.desafiomobilis.databinding.GraficoViewBinding
//import com.example.desafiomobilis.util.setSafeOnClickListener
//import com.example.desafiomobilis.util.toFormattedString
//import com.example.desafiomobilis.viewmodel.GraficoViewModel
//import com.github.mikephil.charting.components.AxisBase
//import com.github.mikephil.charting.formatter.ValueFormatter
//import java.util.*
//
//class GraficoView : Fragment() {
//
//    private lateinit var mBinding: GraficoViewBinding
//    private val mViewModel: GraficoViewModel by viewModels()
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        setupBinding()
//        setupViewModel()
//        setupCharts()
//        setupError()
//        return mBinding.root
//    }
//
//    private fun setupBinding() {
//        mBinding = GraficoViewBinding.inflate(layoutInflater, null, false)
//        mBinding.lifecycleOwner = this
//    }
//
//    private fun setupViewModel() {
//        viewLifecycleOwner.lifecycle.addObserver(mViewModel)
//    }
//
//    private fun setupCharts() {
////        mBinding.chart.xAxis.apply {
////            valueFormatter = DataFormatter()
////            granularity = 86400000f
////        }
//        mViewModel.getData().observe(viewLifecycleOwner) {
//            mBinding.chart.data = it
//            mBinding.chart.invalidate()
////            mBinding.chart.setFitBars(true)
//            mBinding.loading.visibility = View.GONE
//            mBinding.chart.visibility = View.VISIBLE
//        }
//    }
//
//    private fun setupError() {
//        mBinding.reloadBtn.setSafeOnClickListener {
//            mBinding.errorGroup.visibility = View.GONE
//            mBinding.loading.visibility = View.VISIBLE
//            mViewModel.load()
//        }
//        mViewModel.getError().observe(viewLifecycleOwner) {
//            if (it == GraficoViewModel.ERROR_GET_RECEITA_LIST || it == GraficoViewModel.ERROR_GET_DESPESA_LIST) {
//                mBinding.loading.visibility = View.GONE
//                mBinding.errorGroup.visibility = View.VISIBLE
//            }
//        }
//    }
//
//    class DataFormatter() : ValueFormatter() {
//        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
//            val data = Date(value.toLong())
//            return data.toFormattedString()
//        }
//    }
//}