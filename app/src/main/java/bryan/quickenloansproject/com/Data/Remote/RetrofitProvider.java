package bryan.quickenloansproject.com.Data.Remote;

import androidx.annotation.NonNull;
import bryan.quickenloansproject.com.Data.GiantBomb.GiantBombSchema;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitProvider {

    private static Retrofit instance;

    public static Retrofit getInstance(){

        if( instance == null )
            makeInstance( );

        return instance;
    }

    private static void makeInstance(){

        instance = new Retrofit.Builder()
                .baseUrl( GiantBombSchema.BASE_URL )
                .addCallAdapterFactory( RxJava2CallAdapterFactory.createWithScheduler( Schedulers.io() ))
                .addConverterFactory(GsonConverterFactory.create() )
                .build();

    }

    public static <T> T createServiceContract(@NonNull final Class<T> c){
        if( instance == null )
            makeInstance();

        return instance.create( c );
    }

}
