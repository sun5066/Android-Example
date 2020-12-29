package github.sun5066.mvvm_v1.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import github.sun5066.mvvm_v1.R
import github.sun5066.mvvm_v1.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private var fragments: Array<BlankFragment> = arrayOf(
        BlankFragment.newInstance(1),
        BlankFragment.newInstance(2),
        BlankFragment.newInstance(3)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider.NewInstanceFactory().create(MainViewModel::class.java)
    }

    fun onClick(_view: View) {
        viewModel.add()
    }

    private fun setViewPager() {
        val viewPager = findViewById<ViewPager2>(R.id.viewpager)
        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        adapter.setList(fragments)
        viewPager.adapter = adapter
    }

    inner class ViewPagerAdapter(supportFragmentManager: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(supportFragmentManager, lifecycle) {

        private lateinit var fragments: Array<BlankFragment>

        fun setList(fragments: Array<BlankFragment>) {
            this.fragments = fragments
        }

        fun getList(): Array<BlankFragment> = fragments

        override fun getItemCount(): Int = fragments.size

        override fun createFragment(position: Int): Fragment = fragments[position]
    }
}
