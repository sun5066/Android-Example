package github.sun5066.socketclient.model

data class ChatData(
    val id: Long? = 0,
    var name: String? = "",
    var message: String? = "",
    var isMe: Boolean? = false
)