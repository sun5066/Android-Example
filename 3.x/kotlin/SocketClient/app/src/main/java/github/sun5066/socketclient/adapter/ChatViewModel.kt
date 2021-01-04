package github.sun5066.socketclient.adapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import github.sun5066.socketclient.model.ChatData
import github.sun5066.socketclient.network.ChatSocketHandler

class ChatViewModel(private val _chatSocketHandler: ChatSocketHandler): ViewModel() {
    fun selectAll(): LiveData<MutableList<ChatData>> = _chatSocketHandler.getList()
}