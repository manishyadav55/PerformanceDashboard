package com.my.topperforming.Auth

import android.location.Location
import android.provider.ContactsContract
import com.my.topperforming.DoctorData
import com.my.topperforming.Results
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface DemoRemoteService {

    //https://demo.intelehealth.org/openmrs/ws/rest/v1/provider?q=clerk&v=default:(uuid,patient:(uuid,identifiers:(identifier),person:(display,gender,age,birthdate)), location:(display),encounters:(display,encounterDatetime,voided,encounterType:(display),encounterProviders),attributes)

    ///openmrs/ws/rest/v1/provider?q=clerk&v=default

   @GET("/openmrs/ws/rest/v1/visit?includeInactive=false&v=custom:(uuid,patient:(uuid,identifiers:(identifier),person:(display,gender,age,birthdate)),location:(display),encounters:(display,encounterDatetime,voided,encounterType:(display),encounterProviders),attributes)")


    fun getProfile():Call<DoctorData>
}