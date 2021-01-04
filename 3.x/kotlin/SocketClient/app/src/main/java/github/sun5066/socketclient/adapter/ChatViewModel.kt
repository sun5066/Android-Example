package github.sun5066.socketclient.adapter

import github.sun5066.socketclient.model.ChatData
import github.sun5066.socketclient.network.ChatSocketHandler

class ChatViewModel(private val chatSocketHandler: ChatSocketHandler) {
    fun selectAll(): MutableList<ChatData> = chatSocketHandler.chatList

}