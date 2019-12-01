package com.dreamwithus.teknokratsmarthome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dreamwithus.teknokratsmarthome.models.Iot
import com.google.firebase.database.*

class MainViewModel : ViewModel(){
    private var database: DatabaseReference = FirebaseDatabase.getInstance().reference

    private val dataIot : MutableLiveData<Iot> =MutableLiveData();
    val retriveData : LiveData<Iot>  = dataIot;


    fun getDataRuang(value : String){
        database.child(value).addValueEventListener(object  : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(snaphot: DataSnapshot) {
                val data = snaphot.getValue(Iot::class.java);
                println("${snaphot.key} : "+data)
                dataIot.postValue(data);
            }

        })
    }

    fun getAlldata(){
        database.addValueEventListener(object  : ValueEventListener{
            override fun onDataChange(snaphot: DataSnapshot) {
                println(snaphot)
            }

            override fun onCancelled(p0: DatabaseError) {

            }

        })
    }

    fun clickTurn(keyRuangan : String ,keyVariable : String ,value : Any){
        database.child(keyRuangan).child(keyVariable).setValue(value).addOnSuccessListener {
            print("Success")

        }.addOnFailureListener {
            println(it.message);
        }
    }
}

object KEY {
    const val RUANG1 : String = "ruang1"
    const val RUANG2 : String = "ruang2"
    const val DAPUR : String = "dapur"
    const val KELUARGA : String = "keluarga"
    const val GARANSI : String = "garasi"
    const val KEBUN : String = "kebun"
    const val KEYTODETAIL : String = "idRoom"
}