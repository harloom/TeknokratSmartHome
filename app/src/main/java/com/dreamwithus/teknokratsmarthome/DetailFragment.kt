package com.dreamwithus.teknokratsmarthome

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.dreamwithus.teknokratsmarthome.models.Iot
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {
    private var model: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = activity?.run {
            ViewModelProviders.of(this).get(MainViewModel::class.java)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)






        val idRoom = arguments?.getString(KEY.KEYTODETAIL)
        activity?.title = idRoom
        ruang.text = ""+idRoom;
        idRoom?.let {
            model!!.getDataRuang(it)
            model!!.retriveData.observe(this@DetailFragment, Observer { data ->
                println("liveModel")
                setUI(data)
            })
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setUI(data: Iot?) {
        data?.let {
            tv_kelembaban.text = "${it.kelembaban} %"
            tv_suhu.text = "${it.suhu} °C"

            if(it.fire){
                cv_api.setCardBackgroundColor(ContextCompat.getColor(context!!,R.color.on))
            }else{
                cv_api.setCardBackgroundColor(ContextCompat.getColor(context!!,R.color.offf))
            }

            if(it.gas){
                cv_asap.setCardBackgroundColor(ContextCompat.getColor(context!!,R.color.on))
            }else{
                cv_asap.setCardBackgroundColor(ContextCompat.getColor(context!!,R.color.offf))
            }

            if(it.pintu){
                iv_lock.setImageResource(R.drawable.ic_lock_open )
                tv_lock.text = "Status : Terbuka"
            }else{
                iv_lock.setImageResource(R.drawable.ic_lock_outlin )
                tv_lock.text = "Status : Tertutup"
            }


            if(it.lampu){
                iv_lampu.setImageResource(R.drawable.ic_wb_on )
                tv_lampu.text = "Status : Hidup"
            }else{
                iv_lampu.setImageResource(R.drawable.ic_wb_off )
                tv_lampu.text = "Status : Mati"
            }

            if(it.ac){
                iv_kipas.setImageResource(R.drawable.ic_kipas_on )
                tv_kipas.text = "Status : Hidup"
            }else{
                iv_kipas.setImageResource(R.drawable.ic_kipas_off )
                tv_kipas.text = "Status : Mati"
            }




        }
    }


}
