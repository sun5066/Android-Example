package github.sun5066.socketclient.network

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import github.sun5066.socketclient.TAG
import github.sun5066.socketclient.model.ChatData
import java.io.OutputStream
import java.net.Socket
import java.nio.charset.Charset
import java.util.*

class ChatSocketHandler(private val _ip: String, private val _port: Int) {
    /**********************************************************************************************/
    private lateinit var mSocket: Socket
    private lateinit var mReader: Scanner
    private lateinit var mWriter: OutputStream
    private var mIsConnected = false

    private val listType: TypeToken<MutableList<ChatData>> = object :
        TypeToken<MutableList<ChatData>>() {}

    private val gson = GsonBuilder().create()
    val chatList = mutableListOf<ChatData>()

    /**********************************************************************************************/

    fun run() {
        mSocket = Socket(_ip, _port)
        mReader = Scanner(mSocket.getInputStream())
        mWriter = mSocket.getOutputStream()
        mIsConnected = true
    }

    fun read() {
        while (mIsConnected) {
            val chatData = gson.fromJson(mReader.nextLine(), ChatData::class.java)
            chatList.add(chatData)

            Log.d(TAG, chatList.toString())
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

    fun getList() {
        if (mIsConnected) {
            this.sendMessage("getList")
        }
    }
}