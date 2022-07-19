package com.example.ktlt

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import java.util.*

class tab3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab3)

        val deviceAdress = intent.getStringExtra(tab2.EXTRA_ADDRESS)

        val up = findViewById<Button>(R.id.up)
        val down = findViewById<Button>(R.id.down)
        val stop = findViewById<Button>(R.id.stop)

        val left = findViewById<Button>(R.id.left)
        val right = findViewById<Button>(R.id.right)
        val straight = findViewById<Button>(R.id.straight)

        val pairDevice = findViewById<ImageButton>(R.id.pairDevice)
        val load = findViewById<ProgressBar>(R.id.progressBar)
        var m_myUUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")

        val m_bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        val device: BluetoothDevice = m_bluetoothAdapter.getRemoteDevice(deviceAdress)

        val permission = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.BLUETOOTH
        )

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.BLUETOOTH), 1)
        }
        val m_bluetoothSocket = device.createInsecureRfcommSocketToServiceRecord(m_myUUID)
        up.isClickable = false
        up.isVisible = false

        down.isClickable = false
        down.isVisible = false

        stop.isClickable = false
        stop.isVisible = false

        left.isClickable = false
        left.isVisible = false

        right.isClickable = false
        right.isVisible = false

        straight.isClickable = false
        straight.isVisible = false

        load.isVisible = false
        pairDevice.isClickable = false
        pairDevice.isVisible = false

        if (!m_bluetoothSocket.isConnected){
            try{
                m_bluetoothSocket.connect()
                up.isClickable = true
                up.isVisible = true

                down.isClickable = true
                down.isVisible = true
            } catch (e: Exception){
                Toast.makeText(applicationContext, "Check DOT CAR bluetooth!", Toast.LENGTH_SHORT).show()
                pairDevice.isClickable = true
                pairDevice.isVisible = true
            }
        }

        up.setOnClickListener{
            try { m_bluetoothSocket.outputStream.write(1) }
            catch (e:Exception){
                Toast.makeText(applicationContext, "Check DOT CAR bluetooth!", Toast.LENGTH_SHORT).show()
                pairDevice.isClickable = true
                pairDevice.isVisible = true
                up.isClickable = false
                up.isVisible = false

                down.isClickable = false
                down.isVisible = false
            }
        }

        down.setOnClickListener{
            try { m_bluetoothSocket.outputStream.write(2) }
            catch (e:Exception){
                Toast.makeText(applicationContext, "Check DOT CAR bluetooth!", Toast.LENGTH_SHORT).show()
                pairDevice.isClickable = true
                pairDevice.isVisible = true
                up.isClickable = false
                up.isVisible = false

                down.isClickable = false
                down.isVisible = false
            }
        }
        pairDevice.setOnClickListener {
            Log.e("", "not connected!")
            try{
                m_bluetoothSocket.connect()
                up.isClickable = true
                up.isVisible = true

                down.isClickable = true
                down.isVisible = true
                pairDevice.isVisible = false
                pairDevice.isClickable = false
            } catch (e: Exception){
                Toast.makeText(applicationContext, "Check DOT CAR bluetooth!", Toast.LENGTH_SHORT).show()
                pairDevice.isClickable = true
                pairDevice.isVisible = true
            }
        }
    }
}