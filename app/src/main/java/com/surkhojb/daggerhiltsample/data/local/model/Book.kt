package com.surkhojb.daggerhiltsample.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.surkhojb.daggerhiltsample.data.local.db.ListStringTypeConverter

@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "title")
    val title : String,
    @ColumnInfo(name = "subtitle")
    val subtitle : String,
    @ColumnInfo(name = "authors")
    @TypeConverters(ListStringTypeConverter::class)
    val authors : List<String>,
    @ColumnInfo(name = "publisher")
    val publisher : String,
    @ColumnInfo(name = "publishedDate")
    val publishedDate : String,
    @ColumnInfo(name = "description")
    val description : String,
    @ColumnInfo(name = "pageCount")
    val pageCount : Int,
    @ColumnInfo(name = "printType")
    val printType : String,
    @ColumnInfo(name = "categories")
    @TypeConverters(ListStringTypeConverter::class)
    val categories : List<String>,
    @ColumnInfo(name = "maturityRating")
    val maturityRating : String,
    @ColumnInfo(name = "allowAnonLogging")
    val allowAnonLogging : Boolean,
    @ColumnInfo(name = "contentVersion")
    val contentVersion : String,
    @ColumnInfo(name = "smallThumbnail")
    val smallThumbnail : String,
    @ColumnInfo(name = "thumbnail")
    val thumbnail : String,
    @ColumnInfo(name = "language")
    val language : String,
    @ColumnInfo(name = "previewLink")
    val previewLink : String,
    @ColumnInfo(name = "infoLink")
    val infoLink : String,
    @ColumnInfo(name = "canonicalVolumeLink")
    val canonicalVolumeLink : String
) {
}