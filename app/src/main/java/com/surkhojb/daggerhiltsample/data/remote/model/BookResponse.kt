package com.surkhojb.daggerhiltsample.data.remote.model

data class BookResponse (
    val kind : String,
    val totalItems : Int,
    val items : List<Item>
)

data class Item (
    val kind : String,
    val id : String,
    val etag : String,
    val selfLink : String,
    val volumeInfo : VolumeInfo
)

data class VolumeInfo (
    val title : String?,
    val subtitle : String?,
    val authors : List<String>?,
    val publisher : String?,
    val publishedDate : String?,
    val description : String?,
    val pageCount : Int?,
    val printType : String?,
    val categories : List<String>?,
    val maturityRating : String?,
    val allowAnonLogging : Boolean?,
    val contentVersion : String?,
    val imageLinks : ImageLinks?,
    val language : String?,
    val previewLink : String?,
    val infoLink : String?,
    val canonicalVolumeLink : String?
)

data class ImageLinks (
    val smallThumbnail : String?,
    val thumbnail : String?
)