package br.com.fiap.edu.xboxone.core.network.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Albums: ArrayList<AlbumItem>()

data class AlbumItem(
    @SerializedName("id")
    val id:Int,

    @SerializedName("userid")
    val userid:Int,

    @SerializedName("title")
    val title:String
): Serializable