package github.sun5066.socket

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatViewInflater
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.lang.Exception
import java.net.Socket

class ChatActivity : AppCompatActivity(), View.OnClickListener, ReceiverThread.OnReceiveListener {

    private lateinit var mMessageTextView: TextView
    private lateinit var mMessageEditText: EditText
    private lateinit var mThread1: SenderThread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        mMessageTextView = findViewById(R.id.txt_msg)
        mMessageEditText = findViewById(R.id.edit_message)
        findViewById<Button>(R.id.btn_send).setOnClickListener(this)

        try {
            val socket = Socket("125.136.6.85", 5002)
            mThread1 = SenderThread(socket, "김민석")
            val thread2 = ReceiverThread(socket)

            mThread1.start()
            thread2.start()
            thread2.setOnReceiveListener(this)
        } catch (e: Exception) {
            println(e.message)
        }
    }

    override fun onClick(v: View?) {
        mThread1.sendMessage("111")
    }

    override fun onReceive(msg: String) {
        val message = mMessageTextView.text.toString()
        mMessageTextView.text = message
    }
}

class ReceiverThread(private val _socket: Socket) : Thread() {

    interface OnReceiveListener {
        fun onReceive(msg: String)
    }

    lateinit var listener: OnReceiveListener

    fun setOnReceiveListener(_listener: OnReceiveListener) {
        this.listener = _listener
    }

    override fun run() {
        try {
            val reader = BufferedReader(InputStreamReader(_socket.getInputStream()))
            while (true) {
                val str = reader.readLine() ?: break
                println(str)
                listener ?: listener.onReceive(str)
            }
        } catch (e: Exception) {
            println(e.message)
        }
    }
}

class SenderThread(private val socket: Socket, name: String) : Thread() {
    private lateinit var writer: PrintWriter

    fun close() = socket.close()

    fun sendMessage(msg: String) {
        writer.println(msg)
        writer.flush()
    }

    override fun run() {
        try {
            writer = PrintWriter(socket.getOutputStream())

            // 서버명 송신
            writer.println(name)
            writer.flush()

//            val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
//            while (true) {
//                val str = reader.readLine()
//                if (str == "bye") {
//                    break
//                }
//                writer.println(str)
//                writer.flush()
//            }
        } catch (e: Exception) {
            println(e.message)
        } finally {
            socket.close()
        }
    }
}