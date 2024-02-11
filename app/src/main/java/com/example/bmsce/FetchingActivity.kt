package com.example.bmsce

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bmsce.adapters.CmpAdapter
import com.example.bmsce.models.CompanyModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FetchingActivity:AppCompatActivity() {

    private lateinit var cmpRecyclerView: RecyclerView
    private lateinit var tvLoadingData:TextView
    private lateinit var cmplist: ArrayList<CompanyModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching)

        cmpRecyclerView= findViewById(R.id.rvCmp)
        cmpRecyclerView.layoutManager = LinearLayoutManager(this)
        cmpRecyclerView.setHasFixedSize(true)
        tvLoadingData= findViewById(R.id.loading)

        cmplist= arrayListOf<CompanyModel>()

        getCompanyData()
    }

    private fun getCompanyData(){
        cmpRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("company")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                cmplist.clear()
                if(snapshot.exists()){
                    for (cmpSnap in snapshot.children){
                        val cmpData = cmpSnap.getValue(CompanyModel::class.java)
                        cmplist.add(cmpData!!)
                    }
                    val mAdapter = CmpAdapter(cmplist)
                    cmpRecyclerView.adapter= mAdapter

                    mAdapter.setOnItemClickListener(object : CmpAdapter.OnItemClickListener {
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@FetchingActivity,CompanyDetailsActivity::class.java)

                            intent.putExtra("cmpId",cmplist[position].cmpId)
                            intent.putExtra("cmpname",cmplist[position].cmpname)
                            intent.putExtra("cmprole",cmplist[position].cmprole)
                            intent.putExtra("cmppackage",cmplist[position].cmppackage)
                            intent.putExtra("cmplink",cmplist[position].cmplink)
                            startActivity(intent)
                        }


                    })

                    cmpRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}