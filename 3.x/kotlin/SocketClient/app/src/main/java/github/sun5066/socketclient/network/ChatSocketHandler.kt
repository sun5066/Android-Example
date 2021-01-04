package github.sun5066.socketclient.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import com.google.gson.JsonParseException
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
    private lateinit var chatList: MutableList<ChatData>
    private lateinit var chatData: LiveData<MutableList<ChatData>>
    /**********************************************************************************************/

    fun run() {
        mSocket = Socket(_ip, _port)
        mReader = Scanner(mSocket.getInputStream())
        mWriter = mSocket.getOutputStream()
        mIsConnected = true
    }

    fun read() {
        while (mIsConnected) {
            try {
                chatList = gson.fromJson<MutableList<ChatData>>(
                    mReader.nextLine().toString(),
                    listType.type
                )
                val chatData: LiveData<MutableList<ChatData>>
                chatData = MutableLiveData<MutableList<ChatData>>()
                chatData.value = chatList
                this.chatData = chatData
                Log.d(TAG, "${chatList.toString()}")
            } catch (ex: JsonParseException) {
                Log.d(TAG, "Json 변환 예외 발생!")
            } finally {
                Log.d(TAG, mReader.nextLine())
            }
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

    fun getList(): LiveData<MutableList<ChatData>> {
        Log.d("TAGAAAA", chatData.toString())
        return chatData
    }
}