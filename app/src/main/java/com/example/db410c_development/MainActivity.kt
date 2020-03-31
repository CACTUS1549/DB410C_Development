package com.example.db410c_development

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import java.io.File


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gpioPortList = gpioController()
        gpioPortList.getPortList()
    }


    companion object{
        val TAG = "funGpioController"
        val PATH = "/sys/class/gpio/"
        var gpioArrayList = ArrayList<String>()
    }

    class gpioController {

        fun getPortList(){
            Log.i(TAG, "Getting GPIO Port List")
            val directory = File(PATH)
            val files = directory.list {directory, name -> name.startsWith("gpio")}
            for ((index, item) in files.withIndex())
            {
                if (index >12){
                    gpioArrayList.add(item.toUpperCase())
                }
            }
            Log.i(TAG, "GPIO Ports are: ${gpioArrayList[0]}, ${gpioArrayList[1]}, ${gpioArrayList[2]}, ${gpioArrayList[3]}, " +
                    "${gpioArrayList[4]}, ${gpioArrayList[5]}, ${gpioArrayList[6]}, " +
                    "${gpioArrayList[7]}, ${gpioArrayList[8]}, ${gpioArrayList[9]}, ${gpioArrayList[10]}")
        }
    }
}

