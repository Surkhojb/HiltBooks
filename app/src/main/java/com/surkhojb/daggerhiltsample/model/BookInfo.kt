package com.surkhojb.daggerhiltsample.model

import java.io.Serializable

data class BookInfo(
    var volumeId: String,
    var title : String,
    var subtitle : String,
    var authors : List<String>,
    var publisher : String,
    var publishedDate : String,
    var description : String,
    var pageCount : Int,
    var categories : List<String>,
    var smallThumbnail : String,
    var thumbnail : String,
    var previewLink : String,
    var infoLink : String,
    var canonicalVolumeLink : String
): Serializable{
   constructor(): this(
       "",
       "",
       "",
       emptyList(),
       "",
       "",
       "",
       0,
       emptyList(),
       "",
       "",
       "",
       "",
       ""
   )
}