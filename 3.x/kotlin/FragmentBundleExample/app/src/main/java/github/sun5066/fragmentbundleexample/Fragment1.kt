package github.sun5066.fragmentbundleexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment

class Fragment1 : Fragment() {

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_1, container, false)
        val txt_frag1 = view.findViewById<TextView>(R.id.txt_frag1)
        val btn_frag1 = view.findViewById<Button>(R.id.btn_frag1)
        var result = ""

        if (arguments != null) {
            result = arguments?.getString("key1").toString()
            txt_frag1.text = result
        }

        btn_frag1.setOnClickListener {
            val bundle = Bundle()
            val transaction = activity!!.supportFragmentManager.beginTransaction()
            val fragment2 = Fragment2()
            bundle.putString("key1", "김민석 프래그먼트1")
            fragment2.arguments = bundle // setArguments 에 번들을 담기
            transaction.replace(R.id.framelayout, fragment2) // 이동할 프레그먼트
            transaction.commit() // 저장
        }
        return view
    }
}