package com.biz.hello_03.adapter

import android.content.Context
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.biz.hello_03.R
import com.biz.hello_03.model.MemoVO
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class MemoViewAdapter(
    var memoList: MutableList<MemoVO>,
    var onDelete: (Any) -> Unit,
    var onUpdate: (Any) -> Unit
) :
    RecyclerView.Adapter<MemoViewAdapter.MemoHolder?>() {

    // 매개변수로 받은 onDelete 메서드를 잠시 변수에 담아놓기기
//    private val onDeleteClick: (Any) -> Unit = onDelete
//    private val ononUpdateClick: (Any) -> Unit = onUpdate


    // 생성자를 클래스 생성자(first Constructor) 로 선언하면
    // 별도로 private 변수를 선언하지 않는다.
    // 자동으로 생성이 되기 때문에
    // 그리고 이 변수를 클래스 내에서 사용할 때는 null 값을 체크할 필요가 없어진다.
    // private lateinit var memoList: MutableList<MemoVO>

    fun setList(memoList: MutableList<MemoVO>) {
        this.memoList = memoList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoHolder {

        // layout/*.xml 파일을 읽어서 화면의
        // 일부 컴포넌트(View)에 부착하여 사용하기 위한
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.memo_item, parent, false)

        // MemoHolder 클래스에 layout view 를 주입하고 객체로 생성하여 return
        return MemoHolder(view)
    }

    // 생성된 Holder 를 list 의 item 개수만큼 복제하여 Holder 들을 생성한다.
    // 이 method 는 list 의 item 개수만큼 반복 실행 되면서
    // Holder 들을 생성하고 RecyclerView 에 보여지도록 한다.
    // 단 한번에 실행되는 회수는 데이터개수가 아니고 한 화면에 보여지는 리스트 만큼만 실행되고 기다린다.
    override fun onBindViewHolder(holder: MemoHolder, position: Int) {
        // holder.txtDate.setDate(memoList.get(position).getDate()
        // ? : 혹시 memoList[position] 값이 null 이더라도
        // 오류를 내지말라는 null safe code
        holder.txtDate.text = memoList[position]?.date.toString()
        holder.txtTime.text = memoList[position]?.time.toString()
        holder.txtMemo.text = memoList[position]?.memo.toString()

        // 삭제버튼이 클릭되면 클릭 이벤트를 발생시키고 그 이벤트를
        // MainActivity 로 전달하는 코드
        val id: Long = memoList[position].id.toLong()
        holder.btnDelete.setOnClickListener(View.OnClickListener { onDelete(id) })
        holder.txtMemo.setOnClickListener(View.OnClickListener { onUpdate(id) })
    }

    override fun getItemCount(): Int {
        return memoList.size
    }

    // RecyclerView
    // 데이터가 아무리 많아도
    // 현재 화면에 보일수 있는 칸수만큼 index 들을 설정해서
    // 스크롤할때마다 index 가 초기화된다.
    class MemoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtDate: TextView = itemView.findViewById(R.id.txt_date)
        var txtTime: TextView = itemView.findViewById(R.id.txt_time)
        var txtMemo: TextView = itemView.findViewById(R.id.txt_memo_item)
        var btnDelete: Button = itemView.findViewById(R.id.btn_memo_item_delete)
    }
}