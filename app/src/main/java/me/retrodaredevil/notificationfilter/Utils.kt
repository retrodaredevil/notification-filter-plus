package me.retrodaredevil.notificationfilter

import java.io.BufferedInputStream
import java.io.InputStream

fun InputStream.readToString(): String{
    BufferedInputStream(this).use {
        return reader().readLines().joinToString("\n")
    }
}