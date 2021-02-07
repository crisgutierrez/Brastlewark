package com.example.brastlewark.util

import java.lang.StringBuilder

object Utils {

    fun printList(list: List<String>) : String = list.toString().removePrefix("[").removeSuffix("]")

}