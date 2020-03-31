package com.example.db410c_development

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import java.io.File


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gpio_Control = gpioController()
        gpio_Control.getPortList()
        gpio_Control.gpioPin(915)

    }


    companion object{
        val TAG = "funGpioController"
        val PATH = "/sys/class/gpio/"
        val gpioPortList = arrayOf("gpio1017", "gpio901", "gpio914", "gpio915", "gpio926",
            "gpio930", "gpio935", "gpio936", "gpio937", "gpio938", "gpio971")
    }

    class gpioController {

        fun getPortList(){
            Log.v(TAG, "Getting GPIO Port List")
            for (item in gpioPortList) Log.i(TAG, "$item" )
        }

        fun gpioPin(pin: Int){

                Log.v(TAG, "Checking if $pin is valid.")
                val pinToCheck = "gpio$pin"
                Log.v(TAG, "comparing $pinToCheck with gpioPortList")

                    if (gpioPortList.contains(pinToCheck)){
                        Log.i(TAG, "Initializing pin: $pin")
                    } else{
                        Log.i(TAG, "Please enter a Valid GPIO pin Number")
                    }
                }
        }
}

