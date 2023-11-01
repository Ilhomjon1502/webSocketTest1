package uz.ilhomjon.websockettest;

import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;

import uz.ilhomjon.websockettest.utils.MyData;

public class MyWebSocketClient extends WebSocketClient {
    private static final String TAG="MyWebSocketClient";
    public MyWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        // Websocket ochilganda bajariladigan amallar
        Log.d(TAG, "onOpen: ");
    }

    @Override
    public void onMessage(String message) {
        // Yangi xabar keldi
        Log.d(TAG, "onMessage: "+message);
        MyData.INSTANCE.getLiveData().postValue(message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        // Websocket yopilganda bajariladigan amallar
        Log.d(TAG, "onClose: ");
    }

    @Override
    public void onError(Exception ex) {
        // Xatolik yuz berdi
        Log.d(TAG, "onError: "+ex.getMessage());
    }
}
