package github.sun5066.mvvm_v1.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private lateinit var liveData: MutableLiveData<Int>

    init {

    }

    fun getMutableData(): LiveData<Int> {
        return liveData
    }

    fun add() {
        if (liveData == null) {
            liveData = MutableLiveData()
        }
        liveData.value!!.plus(1)
        Log.d("11", "1111111111111111111111111111111")
    }
}