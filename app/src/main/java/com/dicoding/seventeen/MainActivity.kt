package com.dicoding.seventeen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvMembers: RecyclerView
    private val list = ArrayList<Member>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMembers = findViewById(R.id.rv_members)
        rvMembers.setHasFixedSize(true)

        list.addAll(getListMembers())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_list -> {
                rvMembers.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvMembers.layoutManager = GridLayoutManager(this, 2)
            }
            R.id.action_about -> {
                val intent = Intent(this, AboutMeActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListMembers(): ArrayList<Member>{
        val dataName = resources.getStringArray(R.array.member_name_data)
        val dataRealName = resources.getStringArray(R.array.member_real_name_data)
        val dataDOB = resources.getStringArray(R.array.member_dob_data)
        val dataPosition = resources.getStringArray(R.array.member_position_data)
        val dataNationality = resources.getStringArray(R.array.member_nationality_data)
        val dataDescription = resources.getStringArray(R.array.member_description_data)
        val dataPhoto = resources.obtainTypedArray(R.array.member_photo_data)

        val listMember = ArrayList<Member>()
        for (i in dataName.indices){
            val member = Member(
                dataName[i],
                dataRealName[i],
                dataDOB[i],
                dataPosition[i],
                dataNationality[i],
                dataDescription[i],
                dataPhoto.getResourceId(i, -1)
            )
            listMember.add(member)
        }
        dataPhoto.recycle()
        return listMember
    }

    private fun showRecyclerList(){
        rvMembers.layoutManager = LinearLayoutManager(this)
        val listMemberAdapter = ListMemberAdapter(list)
        rvMembers.adapter = listMemberAdapter

        listMemberAdapter.setOnItemClickCallback(object : ListMemberAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Member) {
                val detailIntent = Intent(this@MainActivity, DetailActivity::class.java)

                detailIntent.putExtra("name", data.name)
                detailIntent.putExtra("real_name", data.realName)
                detailIntent.putExtra("dob", data.dob)
                detailIntent.putExtra("position", data.position)
                detailIntent.putExtra("nationality", data.nationality)
                detailIntent.putExtra("description", data.description)
                detailIntent.putExtra("member_photo", data.photo)

                startActivity(detailIntent)
            }
        })
    }
}
