package github.sun5066.socketclient.adapter

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import github.sun5066.socketclient.model.ChatData
import github.sun5066.socketclient.network.ChatSocketHandler

class ChatViewModel : ViewModel() {
    private val TAG = this.javaClass.simpleName

    private val chatLiveData: MutableLiveData<MutableList<ChatData>> = MutableLiveData()

    init {
        chatLiveData.value = ChatSocketHandler.getInstance().gChatList
        Log.d(TAG, chatLiveData.value.toString())
    }

    fun setModel() {
        chatLiveData.value = ChatSocketHandler.getInstance().gChatList
    }

    fun getModel(): MutableLiveData<MutableList<ChatData>> {
        return chatLiveData
    }
}