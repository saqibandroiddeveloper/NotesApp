@file:Suppress("DEPRECATED_ANNOTATION")

package com.example.notesapp.model

import android.os.Parcelable
import android.webkit.WebSettings.RenderPriority
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Notes")
class Notes (
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var title:String,
    var sbTitle:String,
    var notes:String,
    var date:String,
    var priority: String
        ) : Parcelable