package com.example.ktlt

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import org.w3c.dom.Text
import java.util.*

class tab2 : AppCompatActivity() {

    companion object {
        val EXTRA_ADDRESS: String = "Device_address"
        var m_myUUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
        var m_bluetoothSocket: BluetoothSocket? = null
        var checkPaired = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab2)

        val load = findViewById<ProgressBar>(R.id.progressBar)
        val find = findViewById<ImageButton>(R.id.find)
        val connectCar = findViewById<Button>(R.id.connectCar)

//        val intent = Intent(this, tab3::class.java)
        val intent = Intent(this, tabControl::class.java)

        connectCar.isVisible = false
        connectCar.isClickable = false
        load.isVisible = false

        val permission = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.BLUETOOTH
        )

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.BLUETOOTH), 1)
        }
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        bluetoothAdapter.enable()

        find.setOnClickListener {
            find.setBackgroundResource(R.drawable.find)
            find.isClickable = false
            load.isVisible = true

            val pairedDevices: Set<BluetoothDevice>? = bluetoothAdapter?.bondedDevices
            pairedDevices?.forEach { device ->
                val deviceName = device.name
                val deviceAddress: String = device.address
                Log.e("", deviceName)
                m_bluetoothSocket = device.createInsecureRfcommSocketToServiceRecord(m_myUUID)
                if (deviceName == "HC-05"){
                    checkPaired = 1
                    if (m_bluetoothSocket != null) {
                        object : CountDownTimer(500, 100) {
                            override fun onTick(millisUntilFinished: Long) {
                            }
                            override fun onFinish() {
                                load.isVisible = false
                                connectCar.isVisible = true
                                find.isVisible = false
                            }
                        }.start()

                        object : CountDownTimer(1200, 100) {
                            override fun onTick(millisUntilFinished: Long) {
                            }
                            override fun onFinish() {
                                intent.putExtra(EXTRA_ADDRESS, deviceAddress)
                                startActivity(intent)
                            }
                        }.start()
                    }
                }
            }

            if (checkPaired == 0) {
            Toast.makeText(applicationContext, "DOT CAR was not paired!", Toast.LENGTH_SHORT).show()
            find.isClickable = true
            find.setBackgroundResource(R.drawable.found)
        }
        }


    }
}