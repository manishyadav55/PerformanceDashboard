package com.my.topperforming

import com.google.gson.annotations.SerializedName


data class Links (

  @SerializedName("rel" ) var rel : String? = null,
  @SerializedName("uri" ) var uri : String? = null

)