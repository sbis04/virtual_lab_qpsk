package com.souvikbiswas.VirtualLab

class DataStream {
    var message: String? = null
    var evenStr: String? = null
        private set
    var oddStr: String? = null
        private set

    fun setEvenString(str: String?) {
        evenStr = str
    }

    fun setOddString(str: String?) {
        oddStr = str
    }

}