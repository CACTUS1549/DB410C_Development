package com.example.db410c_development

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import java.io.BufferedReader
import java.io.FileReader


class MainActivity : AppCompatActivity() {

    val GPIO_PIN = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gpio_Control = gpioController()
        gpio_Control.gpioPin(GPIO_PIN)
        gpio_Control.getPinDirection()
        gpio_Control.getPinValue()
    }


    companion object {
        val TAG = "funGpioController"

        //path of gpio directory
        val PATH = "/sys/class/gpio/"

        //Don't think too much. Pin numbers are Hard coded from schematic
        val gpioPortList = arrayOf(
            "gpio1017", "gpio901", "gpio914", "gpio915", "gpio926",
            "gpio930", "gpio935", "gpio936", "gpio937", "gpio938", "gpio971"
        )
        var valid_gpio_pin: Int = 111   //some default value
        var flag: Boolean = false       //default value
    }

    class gpioController {

        fun getPortList() {
            //show user available pins.

            Log.v(TAG, "Getting GPIO Port List")
            for (item in gpioPortList) Log.i(TAG, "$item")
        }

        fun gpioPin(pin: Int){

            if (gpio_is_valid(pin)){
                valid_gpio_pin = pin
                flag = true
            }
            else flag = false


        }

        //check if user is drunk
        fun gpio_is_valid(pin: Int): Boolean {

            val abc: Boolean
            val selected_pin = pin
            Log.v(TAG, "Checking if $selected_pin is valid.")
            val pinToCheck = "gpio$selected_pin"
            Log.v(TAG, "comparing $pinToCheck with gpioPortList")

            return if (gpioPortList.contains(pinToCheck)) {
                //user is not drunk. allocate the requested pin.
                Log.v(TAG, "User entered correct GPIO pin")
                true
            } else {
                //user is drunk. slap user with error!
                Log.e(TAG, "Please enter a Valid GPIO pin Number")
                false
            }

        }

        // Get GPIO pin direction
        fun getPinDirection() {
            if (flag){
                val selected_pin = valid_gpio_pin
                Log.v(TAG, "Getting Direction")
                val br: BufferedReader
                var line: String
                try {
                    br = BufferedReader(FileReader("$PATH/gpio$selected_pin/direction"))
                    line = br.readLine()
                    Log.v(TAG, "gpio$selected_pin direction: $line")
                    br.close()
                } catch (e: Exception) {
                    Log.e(TAG, "Error: " + e.message)
                }
            }

            else{
                Log.e(TAG, "Check gpioPin() before getPinDirection()")
            }

        }

        //get GPIO Pin value
        fun getPinValue(){
            if (flag){
                val selected_pin = valid_gpio_pin
                Log.v(TAG, "Getting Value")
                val br: BufferedReader
                var line: String
                try {
                    br = BufferedReader(FileReader("$PATH/gpio$selected_pin/value"))
                    line = br.readLine()
                    Log.v(TAG, "gpio$selected_pin value: $line")
                    br.close()
                } catch (e: Exception) {
                    Log.e(TAG, "Error: " + e.message)
                }
            }


            else{
                Log.e(TAG, "Check gpioPin() before getPinValue()")
            }
        }

    }
}

