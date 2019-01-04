package bryan.quickenloansproject.com.GameList;

import androidx.annotation.NonNull;
import bryan.quickenloansproject.com.BuildConfig;
import bryan.quickenloansproject.com.Data.GiantBomb.GameItem;
import bryan.quickenloansproject.com.Data.GiantBomb.ServerResponse;
import bryan.quickenloansproject.com.Data.Remote.GiantBombRetrofitContract;
import bryan.quickenloansproject.com.Data.Remote.RetrofitProvider;
import bryan.quickenloansproject.com.Presenter;
import bryan.quickenloansproject.com.QueryUtils.QueryMapUtils;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

import static bryan.quickenloansproject.com.Data.GiantBomb.GiantBombSchema.API_KEY_PARAM;
import static bryan.quickenloansproject.com.Data.GiantBomb.GiantBombSchema.FIELD_LIST_PARAM;
import static bryan.quickenloansproject.com.Data.GiantBomb.GiantBombSchema.FORMAT_PARAM;
import static bryan.quickenloansproject.com.Data.GiantBomb.GiantBombSchema.QUERY_PARAM;
import static bryan.quickenloansproject.com.Data.GiantBomb.GiantBombSchema.RESOURCES_PARAM;

public class GameListPresenter extends Presenter<GameListContract.View> implements GameListContract.Presenter {

    private GiantBombRetrofitContract contract;

    private QueryMapUtils standardQueryConfig;

    private String lastKnownQuery = null;

    public GameListPresenter(@NonNull GameListContract.View view) {
        super(view);
        initQueryConfig();
    }

    @Override
    public void queryGames(@NonNull String query) {
        //Run query

        lastKnownQuery = query;

        if( contract == null )
         contract = RetrofitProvider.createServiceContract( GiantBombRetrofitContract.class );

        standardQueryConfig.appendQuery( QUERY_PARAM, query );

        contract.searchGames( standardQueryConfig.getQuery() ).observeOn( AndroidSchedulers.mainThread() )
        .subscribe( new DataObserver() );



    }

    @Override
    public String getQuery() {
        return lastKnownQuery;
    }

    private void initQueryConfig(){

        this.standardQueryConfig = new QueryMapUtils()
                    .appendQuery( FORMAT_PARAM, "json" )
                    .appendQuery( RESOURCES_PARAM, "game" )
                    .appendQuery( FIELD_LIST_PARAM, "name,image" )
                    .appendQuery( API_KEY_PARAM, BuildConfig.giantBombApiKey );

    }

    private class DataObserver implements SingleObserver<ServerResponse<GameItem>>{
        @Override
        public void onSubscribe(Disposable d) {
        }

        @Override
        public void onSuccess(ServerResponse<GameItem> gameItemServerResponse) {

            final boolean dataFound = gameItemServerResponse.getCount() > 0;

            if( !dataFound )
                view.displayNoDataMessage();

            else
                view.displayGames( gameItemServerResponse.getResults() );

        }

        @Override
        public void onError(Throwable e) {
        }
    }

}
