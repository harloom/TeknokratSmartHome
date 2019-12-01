package com.dreamwithus.teknokratsmarthome

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {
    private  var model: MainViewModel? =null

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
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "SmartHome"
        btnDapur.setOnClickListener {
            val bundle  = bundleOf(KEY.KEYTODETAIL to KEY.DAPUR)
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment,bundle)
        }

        btnGarasi.setOnClickListener {
            val bundle  = bundleOf(KEY.KEYTODETAIL to KEY.GARANSI)
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment,bundle)
        }
        btnKebun.setOnClickListener {
            val bundle  = bundleOf(KEY.KEYTODETAIL to KEY.KEBUN)
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment,bundle)
        }

        btnKeluarga.setOnClickListener {
            val bundle  = bundleOf(KEY.KEYTODETAIL to KEY.KELUARGA)
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment,bundle)
        }

        btnRuang1.setOnClickListener {
            val bundle  = bundleOf(KEY.KEYTODETAIL to KEY.RUANG1)
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment,bundle)
        }
        btnRuang2.setOnClickListener {
            val bundle  = bundleOf(KEY.KEYTODETAIL to KEY.RUANG2)
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment,bundle)
        }

    }


}
