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
        val serverUri = URI("wss://ws.postman-echo.com/raw") // Websocket server manzili
        val client = MyWebSocketClient(serverUri)

        binding.btnSendMessage.setOnClickListener {

            // Xabarni serverga yuborish
            client.send(binding.edtMessage.text.toString())
            Toast.makeText(this, "Send message", Toast.LENGTH_SHORT).show()
        }

        try {
            client.connect() // Websocketni ulash


        } catch (e: Exception) {
            e.printStackTrace()
        }


        MyData.liveData.observe(this){
            binding.tvMessage.text = it
        }
    }
}