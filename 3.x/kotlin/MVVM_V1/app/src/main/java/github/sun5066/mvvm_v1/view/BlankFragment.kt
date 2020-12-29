package github.sun5066.mvvm_v1.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import github.sun5066.mvvm_v1.R
import github.sun5066.mvvm_v1.databinding.FragmentBlankBinding
import github.sun5066.mvvm_v1.viewmodel.MainViewModel

class BlankFragment : Fragment() {

    private var fragmentNumber = "Fragment "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            fragmentNumber += it.getString("fragment_number")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_blank,
            container,
            false
        ) as FragmentBlankBinding
        val view = binding.root
        val viewModel = ViewModelProvider.NewInstanceFactory().create(MainViewModel::class.java)

        binding.fragmentNumber = fragmentNumber
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(fragmentNumber: Int) =
            BlankFragment().apply {
                arguments = Bundle().apply {
                    putString("fragment_number", "$fragmentNumber")
                }
            }
    }
}
