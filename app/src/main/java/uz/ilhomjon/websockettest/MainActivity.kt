package uz.ilhomjon.websockettest

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import uz.ilhomjon.websockettest.databinding.ActivityMainBinding
import uz.ilhomjon.websockettest.utils.MyData
import java.net.URI


class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//        val serverUri = URI("wss://ws.postman-echo.com/raw") // Websocket server manzili
        val serverUri = URI("ws://147.182.206.31/ws/orders/?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNzMyMzU0ODI2LCJpYXQiOjE3MDA4MTg4MjYsImp0aSI6Ijg3ZDc5NDNjMDk5ODQ2ODNiNjA5OTQwMDhmYjExYmMxIiwidXNlcl9pZCI6MTd9.D-8ZUxZt7hyxV_wbGpeNHpbjWlHgZRyyk0I1bEPM4W0") // Websocket server manzili
        val client = MyWebSocketClient(serverUri)
//        client.addHeader("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNzMyMzU0ODI2LCJpYXQiOjE3MDA4MTg4MjYsImp0aSI6Ijg3ZDc5NDNjMDk5ODQ2ODNiNjA5OTQwMDhmYjExYmMxIiwidXNlcl9pZCI6MTd9.D-8ZUxZt7hyxV_wbGpeNHpbjWlHgZRyyk0I1bEPM4W0")

        binding.btnSendMessage.setOnClickListener {

            // Xabarni serverga yuborish
            client.send(binding.edtMessage.text.toString())
            Toast.makeText(this, "Send message", Toast.LENGTH_SHORT).show()
        }

        try {
            client.connect() // Websocketni ulash
            Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }


        MyData.liveData.observe(this){
            binding.tvMessage.text = it
        }
    }
}