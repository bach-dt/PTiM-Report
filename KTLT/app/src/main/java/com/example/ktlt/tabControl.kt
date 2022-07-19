package com.example.ktlt

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import java.util.*

class tabControl : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_control)

        val deviceAdress = intent.getStringExtra(tab2.EXTRA_ADDRESS)

        val up = findViewById<Button>(R.id.up)
        val down = findViewById<Button>(R.id.down)
        val stop = findViewById<Button>(R.id.stop)

        val left = findViewById<Button>(R.id.left)
        val right = findViewById<Button>(R.id.right)
        val straight = findViewById<Button>(R.id.straight)

        val ledOn = findViewById<Button>(R.id.ledon)
        val ledOff = findViewById<Button>(R.id.ledoff)
        val tracking = findViewById<Button>(R.id.tracking)
        val remote = findViewById<Button>(R.id.remote)

        val viewControl = findViewById<View>(R.id.controlView)

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

        load.isVisible = false
        viewControl.isEnabled = false
        viewControl.isVisible = false

        if (!m_bluetoothSocket.isConnected){
            try{
                m_bluetoothSocket.connect()

                viewControl.isEnabled = true
                viewControl.isVisible = true

                pairDevice.isVisible = false
                pairDevice.isClickable = false

            } catch (e: Exception){
                Toast.makeText(applicationContext, "Check DOT CAR bluetooth!", Toast.LENGTH_SHORT).show()
                pairDevice.isClickable = true
                pairDevice.isVisible = true
            }
        }

        up.setOnClickListener{
            try { m_bluetoothSocket.outputStream.write(0) }
            catch (e:Exception){
                Toast.makeText(applicationContext, "Check DOT CAR bluetooth!", Toast.LENGTH_SHORT).show()
                pairDevice.isClickable = true
                pairDevice.isVisible = true

                viewControl.isEnabled = false
                viewControl.isVisible = false

            }
        }

        down.setOnClickListener{
            try { m_bluetoothSocket.outputStream.write(1) }
            catch (e:Exception){
                Toast.makeText(applicationContext, "Check DOT CAR bluetooth!", Toast.LENGTH_SHORT).show()
                pairDevice.isClickable = true
                pairDevice.isVisible = true

                viewControl.isEnabled = false
                viewControl.isVisible = false
            }
        }

        stop.setOnClickListener{
            try { m_bluetoothSocket.outputStream.write(2) }
            catch (e:Exception){
                Toast.makeText(applicationContext, "Check DOT CAR bluetooth!", Toast.LENGTH_SHORT).show()
                pairDevice.isClickable = true
                pairDevice.isVisible = true

                viewControl.isEnabled = false
                viewControl.isVisible = false
            }
        }

        left.setOnClickListener{
            try { m_bluetoothSocket.outputStream.write(3) }
            catch (e:Exception){
                Toast.makeText(applicationContext, "Check DOT CAR bluetooth!", Toast.LENGTH_SHORT).show()
                pairDevice.isClickable = true
                pairDevice.isVisible = true

                viewControl.isEnabled = false
                viewControl.isVisible = false
            }
        }

        right.setOnClickListener{
            try { m_bluetoothSocket.outputStream.write(4) }
            catch (e:Exception){
                Toast.makeText(applicationContext, "Check DOT CAR bluetooth!", Toast.LENGTH_SHORT).show()
                pairDevice.isClickable = true
                pairDevice.isVisible = true

                viewControl.isEnabled = false
                viewControl.isVisible = false
            }
        }

        straight.setOnClickListener{
            try { m_bluetoothSocket.outputStream.write(5) }
            catch (e:Exception){
                Toast.makeText(applicationContext, "Check DOT CAR bluetooth!", Toast.LENGTH_SHORT).show()
                pairDevice.isClickable = true
                pairDevice.isVisible = true

                viewControl.isEnabled = false
                viewControl.isVisible = false
            }
        }

        ledOn.setOnClickListener{
            try { m_bluetoothSocket.outputStream.write(6) }
            catch (e:Exception){
                Toast.makeText(applicationContext, "Check DOT CAR bluetooth!", Toast.LENGTH_SHORT).show()
                pairDevice.isClickable = true
                pairDevice.isVisible = true

                viewControl.isEnabled = false
                viewControl.isVisible = false
            }
        }

        ledOff.setOnClickListener{
            try { m_bluetoothSocket.outputStream.write(7) }
            catch (e:Exception){
                Toast.makeText(applicationContext, "Check DOT CAR bluetooth!", Toast.LENGTH_SHORT).show()
                pairDevice.isClickable = true
                pairDevice.isVisible = true

                viewControl.isEnabled = false
                viewControl.isVisible = false
            }
        }

        tracking.setOnClickListener{
            try { m_bluetoothSocket.outputStream.write(8) }
            catch (e:Exception){
                Toast.makeText(applicationContext, "Check DOT CAR bluetooth!", Toast.LENGTH_SHORT).show()
                pairDevice.isClickable = true
                pairDevice.isVisible = true

                viewControl.isEnabled = false
                viewControl.isVisible = false
            }
        }

        remote.setOnClickListener{
            try { m_bluetoothSocket.outputStream.write(9) }
            catch (e:Exception){
                Toast.makeText(applicationContext, "Check DOT CAR bluetooth!", Toast.LENGTH_SHORT).show()
                pairDevice.isClickable = true
                pairDevice.isVisible = true

                viewControl.isEnabled = false
                viewControl.isVisible = false
            }
        }

        pairDevice.setOnClickListener {
            Log.e("", "not connected!")
            try{
                m_bluetoothSocket.connect()
                viewControl.isEnabled = true
                viewControl.isVisible = true

                pairDevice.isVisible = false
                pairDevice.isClickable = false

            } catch (e: Exception){
                Toast.makeText(applicationContext, "Check DOT CAR bluetooth!", Toast.LENGTH_SHORT).show()
                pairDevice.isClickable = true
                pairDevice.isVisible = true
            }
        }

        val videoView = findViewById<VideoView>(R.id.videoView)
        videoView.setVideoPath("http://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4")
        videoView.start()

    }
}