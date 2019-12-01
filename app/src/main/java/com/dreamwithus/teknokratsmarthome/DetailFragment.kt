package com.dreamwithus.teknokratsmarthome

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dreamwithus.teknokratsmarthome.models.Iot
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {
    private var model: MainViewModel? = null
    private lateinit var  btnLampu : LinearLayout;
    private lateinit var  btnAC: LinearLayout;
    private lateinit var  btnPintu: LinearLayout;
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
        btnAC = view.findViewById(R.id.cv_kipas)
        btnLampu = view.findViewById(R.id.cv_lampu)
        btnPintu  = view.findViewById(R.id.cv_lock)



        val idRoom = arguments?.getString(KEY.KEYTODETAIL)
        activity?.title = idRoom
        ruang.text = idRoom!!.capitalize()
        idRoom?.let {
            model!!.getDataRuang(it)
            model!!.retriveData.observe(this@DetailFragment, Observer { data ->
                println("liveModel")
                setUI(data,idRoom)
            })
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setUI(data: Iot?, idRoom: String) {
        data?.let {d->
            tv_kelembaban.text = "${d.kelembaban} %"
            tv_suhu.text = "${d.suhu} °C"

            if(d.fire){
                cv_api.setCardBackgroundColor(ContextCompat.getColor(context!!,R.color.on))
                tv_api.text = "Terdeteksi"
            }else{
                cv_api.setCardBackgroundColor(ContextCompat.getColor(context!!,R.color.offf))
                tv_api.text = "Tidak Terdeteksi"
            }

            if(d.gas){
                cv_asap.setCardBackgroundColor(ContextCompat.getColor(context!!,R.color.on))
                tv_asap.text = "Terdeteksi"
            }else{
                cv_asap.setCardBackgroundColor(ContextCompat.getColor(context!!,R.color.offf))
                tv_asap.text = "Terdeteksi"
            }

            if(d.pintu){
                iv_lock.setImageResource(R.drawable.ic_lock_open )
                tv_lock.text = "Status : Terbuka"
            }else{
                iv_lock.setImageResource(R.drawable.ic_lock_outlin )
                tv_lock.text = "Status : Tertutup"
            }


            if(d.lampu){
                iv_lampu.setImageResource(R.drawable.ic_wb_on )
                tv_lampu.text = "Status : Hidup"
            }else{
                iv_lampu.setImageResource(R.drawable.ic_wb_off )
                tv_lampu.text = "Status : Mati"
            }

            if(d.ac){
                iv_kipas.setImageResource(R.drawable.ic_kipas_on )
                tv_kipas.text = "Status : Hidup"
            }else{
                iv_kipas.setImageResource(R.drawable.ic_kipas_off )
                tv_kipas.text = "Status : Mati"
            }


            btnLampu.setOnClickListener {

                if(d.lampu){
                   model?.clickTurn(idRoom,"lampu",false)
                }else{
                    model?.clickTurn(idRoom,"lampu",true)
                }
            }


            btnPintu.setOnClickListener {

                if(d.pintu){
                    model?.clickTurn(idRoom,"pintu",false)
                }else{
                    model?.clickTurn(idRoom,"pintu",true)
                }
            }

            btnAC.setOnClickListener {

                if(d.ac){
                    model?.clickTurn(idRoom,"ac",false)
                }else{
                    model?.clickTurn(idRoom,"ac",true)
                }
            }





        }
    }




}
