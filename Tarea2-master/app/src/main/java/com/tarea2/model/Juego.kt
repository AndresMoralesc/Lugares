package com.tarea2.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName="Juego")
data class Juego(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name="marca")
    val marca: String?,
    @ColumnInfo(name="modelo")
    val modelo: String?,
    @ColumnInfo(name="Placa")
    val placa: String?,
    @ColumnInfo(name="telefono")
    val telefono: String?,
    @ColumnInfo(name="apellido1")
    val apellido1: String?,
    @ColumnInfo(name="apellido2")
    val apellido2: String?,
    @ColumnInfo(name="nombre")
    val nombre: String?,
    @ColumnInfo(name="web")
    val web: String?
) : Parcelable
