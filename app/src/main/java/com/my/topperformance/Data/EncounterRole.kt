package com.my.topperforming

import com.google.gson.annotations.SerializedName


data class EncounterRole (

  @SerializedName("uuid"    ) var uuid    : String?          = null,
  @SerializedName("display" ) var display : String?          = null,
  @SerializedName("links"   ) var links   : ArrayList<Links> = arrayListOf()

)