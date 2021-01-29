package com.surkhojb.daggerhiltsample.model

data class BookInfo(
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
){
   constructor(): this(
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