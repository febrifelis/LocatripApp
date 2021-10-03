package com.fbrproject.locatripapp.home.tiket

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.fbrproject.locatrip.R
import com.fbrproject.locatrip.utils.Preferences
import com.fbrproject.locatripapp.home.dashboard.ComingSoonAdapter
import com.fbrproject.locatripapp.model.Penumpang
import com.fbrproject.locatripapp.model.Trip
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_tiket.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * A simple [Fragment] subclass.
 * Use the [TiketFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TiketFragment : Fragment() {

    private lateinit var preferences: Preferences
    private lateinit var mDatabase: DatabaseReference
    private var dataList = ArrayList<Pair<Trip, Int>>()
    private var mapPenumpang = HashMap<String, ArrayList<Penumpang>>()
    private var tanggal = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tiket, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            preferences = Preferences(requireContext())

            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DATE, 1)
            tanggal = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(calendar.time)

            mDatabase = FirebaseDatabase.getInstance().getReference("Trip")

            rc_tiket.layoutManager = LinearLayoutManager(context)
            getData()
        }
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()
                for (getdataSnapshot in snapshot.children) {
                    val mTrip = getdataSnapshot.getValue(Trip::class.java)
                    var mTripKursi = 0
                    mTrip?.let { trip ->
                        mDatabase.child(trip.judul ?: "").child("jadwal").child(tanggal)
                            .addValueEventListener(object : ValueEventListener {
                                override fun onDataChange(snapshot: DataSnapshot) {
                                    var penumpangList = ArrayList<Penumpang>()
                                    for (snap in snapshot.children) {
                                        val mPenumpang = snap.getValue(Penumpang::class.java)
                                        mPenumpang?.let { penumpang ->
                                            if (penumpang.username == preferences.getValues("user")) {
                                                penumpangList.add(penumpang)
                                            }
                                        }
                                    }
                                    mapPenumpang[trip.judul!!] = penumpangList
                                }

                                override fun onCancelled(error: DatabaseError) {
                                }
                            })
                        mapPenumpang[trip.judul]?.let {
                            mTripKursi = it.size
                        }
                        if (mTripKursi > 0)
                            dataList.add(Pair(trip, mTripKursi))
                    }
                }
                rc_tiket.adapter = TiketListAdapter(dataList) {
                    val intent = Intent(context, TiketActivity::class.java)
                        .putExtra("data", it.first)
                        .putParcelableArrayListExtra("dataList", mapPenumpang[it.first.judul])
                        .putExtra("tanggal", tanggal)
                    startActivity(intent)
                }

                tv_total.setText("${dataList.size} Tiket")
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "" + error.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}