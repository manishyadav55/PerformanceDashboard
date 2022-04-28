package com.my.topperformance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.my.topperformance.Adapter.DoctorAdapter
import com.my.topperformance.Adapter.NurseAdapter
import com.my.topperformance.databinding.ActivityDoctorBinding
import com.my.topperformance.databinding.ActivityNurseBinding
import com.my.topperforming.Auth.BasicAuthClient
import com.my.topperforming.Auth.DemoRemoteService
import com.my.topperforming.DoctorData
import com.my.topperforming.data.Datamodel
import com.my.topperforming.data.RankLogo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NurseActivity : AppCompatActivity() {
    lateinit var binding: ActivityNurseBinding

    private var ranklogo = ArrayList<RankLogo>()
    var DoctorNames: ArrayList<String> = ArrayList()
    var NurseNames: ArrayList<String> = ArrayList()
    var ConsultationSize: ArrayList<String> = ArrayList()
    var nurseConsultationSize: ArrayList<String> = ArrayList()
    val DoctorDatalist = ArrayList<Datamodel>()
    val NurseDatalist = ArrayList<Datamodel>()
    lateinit var sorts: String
    lateinit var sorts1: String
    init {
        ranklogo.add(RankLogo(R.drawable.rank_1,"Be proud of the fact that you have the power to rise above any situation and deliver the best results no matter the circumstances. Excellent work!"))
        ranklogo.add(RankLogo(R.drawable.rank_2, "To be honest, I don’t know how you manage to do such a good job every single time. Very well done!"))
        ranklogo.add(RankLogo(R.drawable.rank_3, "We are fortunate to be able to witness and work amongst an industry expert such as you!"))
        ranklogo.add(RankLogo(R.drawable.rank_4, "Your level of quality work remains unprecedented in our organization!"))
        ranklogo.add(RankLogo(R.drawable.rank_5, "They say that the Devil works hard. Everyone’s wrong. You work harder. We’re so proud of you!"))
        ranklogo.add(RankLogo(R.drawable.rank_6,"You manage to go above and beyond for every piece of job that you do. Great work!"))
        ranklogo.add(RankLogo(R.drawable.rank_7, "Please take a breather from working so hard. You have already done such excellent work!"))
        ranklogo.add(RankLogo(R.drawable.rank_8, "For being the first person to come in and the last person to leave, we commend your dedication and hard work!"))
        ranklogo.add(RankLogo(R.drawable.rank_9, "Your hard work has not gone unnoticed. I and the entire senior management would like to congratulate you on doing a great job!"))
        ranklogo.add(RankLogo(R.drawable.rank_10, "Your great work has resulted in tangible, beneficial results to us. You’re a force to be reckoned!"))


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nurse)
        binding = ActivityNurseBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        loadProfile()
    }
    private fun loadProfile() {
        val call =
            BasicAuthClient<DemoRemoteService>().create(DemoRemoteService::class.java).getProfile()
        call.enqueue(object : Callback<DoctorData> {
            override fun onResponse(call: Call<DoctorData>, response: Response<DoctorData>) {
                val details = response.body()
                Log.d("resp", details.toString())
                if (response.isSuccessful) {
                    val items = response.body()?.results
                    if (items != null) {
                        for (i in 0 until items.size) {
                            for (j in 0 until items[i].encounters.size) {
                                for (k in 0 until items[i].encounters[j].encounterProviders.size) {
                                    if (details != null) {
                                        //list of nurse
                                        if (details.results[i].encounters[j].encounterProviders[k].encounterRole?.uuid?.contains(
                                                "73bbb069-9781-4afc-a9d1-54b6b2270e04") == true)
                                        {
                                            if (details.results[i].encounters[j].encounterProviders[k].provider?.display?.contains(
                                                    "nurse") == true)
                                            {
                                                NurseNames.add("" + details.results[i].encounters[j].encounterProviders[k].provider?.display)
                                                Log.d("data", details.results[i].encounters[j].encounterProviders[k].provider?.display.toString())

                                            }
                                        }
                                        // list of Doctor
                                        if (details.results[i].encounters[j].encounterProviders[k].encounterRole?.uuid?.contains(
                                                "73bbb069-9781-4afc-a9d1-54b6b2270e04") == true)
                                        {
                                            if (details.results[i].encounters[j].encounterProviders[k].provider?.display?.contains(
                                                    "nurse") == true)
                                            {
                                                DoctorNames.add("" + details.results[i].encounters[j].encounterProviders[k].provider?.display)
                                                Log.d("data", items[i].encounters[j].encounterProviders[k].provider?.display.toString())

                                            }
                                        }

                                    }
                                }
                            }

                        }
                    }
                    //No. of consultation for doctor
                    var strings: String =
                        DoctorNames.groupingBy { it }.eachCount().filter { it.value > 1 }.toString()
                    sorts = strings.dropLast(1)
                    ConsultationSize.add(sorts)
                    val yourArray = ConsultationSize.toString().split(",")
                    Log.d("sorting", ConsultationSize.toString())
                    for (i in yourArray) {
                        val iarray = i.split("=")
                        DoctorDatalist.add(Datamodel(iarray[0], iarray[1]))
                    }
                    // No. of consulation for Nurse
                    var strings1: String =
                        NurseNames.groupingBy { it }.eachCount().filter { it.value > 1 }.toString()
                    sorts1 = strings1.dropLast(1)
                    nurseConsultationSize.add(sorts1)
                    val yourArray1 = ConsultationSize.toString().split(",")
                    Log.d("sorting", ConsultationSize.toString())
                    for (i in yourArray1) {
                        val iarray = i.split("=")
                        NurseDatalist.add(Datamodel(iarray[0], iarray[1]))
                    }
                }
                val NurseAdapter = NurseAdapter( this@NurseActivity,ranklogo ,NurseDatalist)
                binding.drList.adapter = NurseAdapter
            }

            override fun onFailure(call: Call<DoctorData>, t: Throwable) {
                Log.d("tag", "error", t)
            }
        })
    }
}