package github.sun5066.socketclient

import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import github.sun5066.socketclient.adapter.ChatRecyclerAdapter
import github.sun5066.socketclient.adapter.ChatViewModel
import github.sun5066.socketclient.model.ChatData
import github.sun5066.socketclient.network.ChatSocketHandler
import kotlin.concurrent.thread

class ClientActivity : AppCompatActivity(), View.OnClickListener, LifecycleOwner {
    /****************************************************************/
    private val TAG = this.javaClass.simpleName

    private lateinit var mTxtSend: EditText
    private val mChatSocketHandler = ChatSocketHandler.getInstance()
    private lateinit var mChatRecyclerAdapter: ChatRecyclerAdapter
    private lateinit var mChatViewModel: ChatViewModel
    private lateinit var mRecyclerView: RecyclerView

    /****************************************************************/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client)

        // 메인스레드에서 네트워킹 관련 처리 허용
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        // 서버 연결
        val address = intent.getStringExtra("ip")
        address?.let {
            mChatSocketHandler.run(address, 1004)
        }

        try {
            thread { mChatSocketHandler.read() }
        } catch (e: Exception) {
            Toast.makeText(this, "에러발생", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "${e.message}")

            mChatSocketHandler.close()
        }

        mTxtSend = findViewById(R.id.txt_send)
        findViewById<Button>(R.id.btn_send).setOnClickListener(this)
        mRecyclerView = findViewById(R.id.recycler_view)

        val chatList = mutableListOf<ChatData>()
        mChatRecyclerAdapter = ChatRecyclerAdapter(chatList)
        mChatViewModel =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))
                .get(ChatViewModel::class.java)

        mChatViewModel.getModel().observe(this, {
            mChatRecyclerAdapter = ChatRecyclerAdapter(it)
            val layoutManager = LinearLayoutManager(this)
            Log.d(TAG, "얘 값 바뀜!! ${it.toString()}")

            mRecyclerView.layoutManager = layoutManager
            mRecyclerView.adapter = mChatRecyclerAdapter
        })
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        mChatSocketHandler.close()
        super.onDestroy()
    }

    override fun onClick(v: View?) {
        val msg = mTxtSend.text.toString()
        mChatSocketHandler.sendMessage(msg)
        mTxtSend.text = null
    }
}