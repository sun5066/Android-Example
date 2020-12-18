package github.sun5066.fragmentbundleexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment

class Fragment2 : Fragment() {

    private lateinit var txt_frag2: TextView
    private lateinit var btn_frag2: Button

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_2, container, false)
        val txt_frag2 = view.findViewById<TextView>(R.id.txt_frag2)
        val btn_frag2 = view.findViewById<Button>(R.id.btn_frag2)

        if (arguments != null) {
            txt_frag2.text = arguments?.getString("key1").toString()
        }

        btn_frag2.setOnClickListener {
            val bundle = Bundle()
            val transaction = activity!!.supportFragmentManager.beginTransaction()
            val fragment1 = Fragment1()
            bundle.putString("key1", "이이이이")
            fragment1.arguments = bundle
            transaction.replace(R.id.framelayout, fragment1)
            transaction.commit()
        }
        return view
    }
}