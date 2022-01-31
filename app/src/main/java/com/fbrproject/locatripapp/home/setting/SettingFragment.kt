package com.fbrproject.locatripapp.home.setting

import android.content.Intent
import android.content.Intent.getIntent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import com.fbrproject.locatripapp.wallet.MyWalletActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fbrproject.locatrip.R
import com.fbrproject.locatrip.utils.Preferences
import com.fbrproject.locatripapp.wallet.TopUpWalletActivity
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.fragment_setting.*

/**
 *
 *
 * A simple [Fragment] subclass.
 */
class SettingFragment : Fragment() {

    lateinit var preferences: Preferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences = Preferences(requireActivity())

        tv_nama.text = preferences.getValues("nama")
        tv_email.text = preferences.getValues("email")

       Glide.with(this)
            .load(preferences.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(iv_profile)


        tv_my_wallet.setOnClickListener {
            startActivity(Intent(activity, TopUpWalletActivity::class.java))
        }

        tv_edit_profile.setOnClickListener {
            startActivity(Intent(requireActivity(), EditProfileActivity::class.java))
        }

        tv_edit_profile3.setOnClickListener{
            val url = "https://t.me/locatripcare"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }
}
