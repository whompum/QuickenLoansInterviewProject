package bryan.quickenloansproject.com.ConnectivityUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import androidx.annotation.WorkerThread;
import io.reactivex.Observable;

public class ConnectivityUtils {

    public static final int TIMEOUT = 1500;
    public static final String GOOGLE_DNS = "8.8.8.8";

    public Observable<Boolean> checkNetwork() {
        return Observable.create( subscriber ->
            new Thread(
                        () -> { subscriber.onNext( isOnline() ); subscriber.onComplete(); }
                      ).start()
        );
    }

    @WorkerThread
    private boolean isOnline(){
        try( Socket socket = new Socket() ) {
            socket.connect( new InetSocketAddress( GOOGLE_DNS, 53 ), TIMEOUT );
            return true;
        } catch (IOException e) { return false; }

    }

}
