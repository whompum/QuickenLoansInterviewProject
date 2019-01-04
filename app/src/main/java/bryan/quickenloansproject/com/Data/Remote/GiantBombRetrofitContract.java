package bryan.quickenloansproject.com.Data.Remote;

import java.util.Map;
import java.util.Observable;

import androidx.annotation.NonNull;
import bryan.quickenloansproject.com.Data.GiantBomb.GameItem;
import bryan.quickenloansproject.com.Data.GiantBomb.ServerResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface GiantBombRetrofitContract {

    @GET("api/search/")
    Single<ServerResponse<GameItem>> searchGames(@QueryMap @NonNull final Map<String, String> queries);

}
