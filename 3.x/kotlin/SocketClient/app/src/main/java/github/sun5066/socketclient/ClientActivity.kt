package github.sun5066.socketclient

import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import github.sun5066.socketclient.adapter.ChatRecyclerAdapter
import github.sun5066.socketclient.adapter.ChatViewModel
import github.sun5066.socketclient.model.ChatData
import github.sun5066.socketclient.network.ChatSocketHandler
import kotlin.concurrent.thread

const val TAG = "ClientActivity"

class ClientActivity : AppCompatActivity(), View.OnClickListener {
    /****************************************************************/
    private lateinit var mTxtSend: EditText
    private lateinit var mChatSocketHandler: ChatSocketHandler
    private lateinit var mChatRecyclerAdapter: ChatRecyclerAdapter
    private lateinit var mChatViewModel: ChatViewModel
    private lateinit var mRecyclerView: RecyclerView
    /****************************************************************/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client)

        if (android.os.Build.VERSION.SDK_INT > 9) { // 안드로이드 SDK 버전이 9 이상일때

            // 메인스레드에서 네트워킹 관련 처리를 할수 있게 함.
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy);
        }

        // 서버 연결
        val address = intent.getStringExtra("ip")
        address?.let {
            mChatSocketHandler = ChatSocketHandler(address, 1004)
            mChatSocketHandler.run()
        }

        mTxtSend = findViewById(R.id.txt_send)
        findViewById<Button>(R.id.btn_send).setOnClickListener(this)

        mRecyclerView = findViewById(R.id.recycler_view)
        val chatList = mutableListOf<ChatData>()
        mChatRecyclerAdapter = ChatRecyclerAdapter(chatList)

        mChatViewModel = ChatViewModel(mChatSocketHandler)
        mChatViewModel.selectAll().observe(this, Observer {
            it?.let { mChatRecyclerAdapter.setList(it) }
            mChatRecyclerAdapter.notifyDataSetChanged()
        })

        mRecyclerView.adapter = mChatRecyclerAdapter

        val layoutManager = LinearLayoutManager(this)
        mRecyclerView.layoutManager = layoutManager
    }

    override fun onResume() {
        super.onResume()
        try {
            thread { mChatSocketHandler.read() }
        } catch (e: Exception) {
            Toast.makeText(this, "에러발생", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "${e.message}")

            mChatSocketHandler.close()
        }
    }

    override fun onDestroy() {
        mChatSocketHandler.close()
        super.onDestroy()
    }

    override fun onClick(v: View?) {
        val msg = mTxtSend.text.toString()
        mChatSocketHandler.sendMessage(msg)
        mTxtSend.text = null
    }
}