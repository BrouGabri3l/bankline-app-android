package me.dio.bankline.data


import android.util.Log
import androidx.lifecycle.liveData
import me.dio.bankline.data.remote.BanklineApi
import me.dio.bankline.data.remote.State
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BanklineRepository {

    private val TAG = javaClass.simpleName

    private val restApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://broug-dio-bankline-api.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BanklineApi::class.java)
    }
    fun findBankStatement(accountHolderId:Int) = liveData{
        emit(State.Wait)
        try{
            emit(State.Success(data = restApi.findBankStatement(accountHolderId)))
        }catch(e:Exception){
            Log.e(TAG, e.message,e)
            emit(State.Error(e.message))
        }
    }
}