package github.sun5066.socketclient.network

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonParseException
import com.google.gson.reflect.TypeToken
import github.sun5066.socketclient.model.ChatData
import java.io.OutputStream
import java.net.Socket
import java.nio.charset.Charset
import java.util.*

class ChatSocketHandler {
    /**********************************************************************************************/
    private val TAG = this.javaClass.simpleName

    private lateinit var mSocket: Socket
    private lateinit var mReader: Scanner
    private lateinit var mWriter: OutputStream
    private var mIsConnected = false

    private val listType: TypeToken<MutableList<ChatData>> = object :
        TypeToken<MutableList<ChatData>>() {}

    private val gson = GsonBuilder().create()
    val gChatList: MutableList<ChatData> = mutableListOf()
//    private val mChatList: MutableLiveData<MutableList<ChatData>> = MutableLiveData()

//    init {
//        mChatList.value = mutableListOf()
//    }

    companion object {
        private var instance: ChatSocketHandler? = null

        @JvmStatic
        fun getInstance() = instance ?: synchronized(this) {
            instance ?: ChatSocketHandler().also { instance = it }
        }
    }

    /**********************************************************************************************/

    fun run(_ip: String, _port: Int) {
        mSocket = Socket(_ip, _port)
        mReader = Scanner(mSocket.getInputStream())
        mWriter = mSocket.getOutputStream()
        mIsConnected = true
    }

    fun read() {
        while (mIsConnected) {
            val tempList: MutableList<ChatData> = gson.fromJson(
                mReader.nextLine().toString(),
                listType.type
            )

            gChatList.clear()
            gChatList.addAll(tempList)
            Log.d(TAG, "gChatList - ${gChatList.toString()}")
        }
    }

    fun close() {
        mIsConnected = false
        mReader.close()
        mWriter.close()
        mSocket.close()
    }

    fun sendMessage(msg: String) {
        if (mIsConnected) {
            mWriter.write((msg + '\n').toByteArray(Charset.defaultCharset()))
        }
    }

    fun getList(): MutableList<ChatData> {
        return gChatList
    }
}