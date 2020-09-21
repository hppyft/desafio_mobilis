package com.example.desafiomobilis.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.desafiomobilis.R
import com.google.android.material.tabs.TabLayout
import java.lang.Exception

class ResumoActivity : AppCompatActivity() {

    private lateinit var mPagerAdapter: MFPagerAdapter
    private lateinit var mViewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resumo)
        mPagerAdapter = MFPagerAdapter(supportFragmentManager)
        mViewPager = findViewById(R.id.pager)
        mViewPager.adapter = mPagerAdapter
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        tabLayout.setupWithViewPager(mViewPager)
    }

    class MFPagerAdapter(fm: FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> createResumoDespesaFragment()
                1 -> createResumoReceitaFragment()
                else -> throw Exception("No fragment for this id")
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> "Despesas" //TODO
                1 -> "Receitas" //TODO
                else -> throw Exception("No fragment for this id")
            }
        }

        override fun getCount(): Int = 2

        private fun createResumoDespesaFragment() =
            ResumoDespesaView()

        private fun createResumoReceitaFragment() =
            ResumoReceitaView()
    }
}