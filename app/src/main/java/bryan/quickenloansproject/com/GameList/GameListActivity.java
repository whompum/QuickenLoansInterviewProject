package bryan.quickenloansproject.com.GameList;

import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import bryan.quickenloansproject.com.ConnectivityUtils.ConnectivityUtils;
import bryan.quickenloansproject.com.GameList.Adapter.GameListItemAdapter;
import bryan.quickenloansproject.com.Data.GiantBomb.GameItem;
import bryan.quickenloansproject.com.ListUtils.DynamicListAdapter;
import bryan.quickenloansproject.com.MvpActivity;
import bryan.quickenloansproject.com.QueryDialog;
import bryan.quickenloansproject.com.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class GameListActivity extends MvpActivity<GameListContract.Presenter>
        implements GameListContract.View {

    public static final String QUERY_BUNDLE_KEY = "query.ky";

    @BindView(R.id.id_local_no_data_frame)
    public ViewGroup root;

    @BindView(R.id.id_global_toolbar)
    public Toolbar toolbar;

    @BindView(R.id.id_global_list)
    public RecyclerView gameList;

    private View noDataLayout;

    private DynamicListAdapter<GameItem> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        ButterKnife.bind( this );

        setSupportActionBar( toolbar );

        noDataLayout = LayoutInflater.from( this ).inflate( R.layout.layout_no_data, root, false );

        root.addView( noDataLayout );

        adapter = new GameListItemAdapter();

        gameList.setLayoutManager( getLayoutManager() );
        gameList.setAdapter( adapter );

        adapter.registerAdapterDataObserver( gamesListObserver );

        if( savedInstanceState != null ){

            String query;

            if( (query = savedInstanceState.getString( QUERY_BUNDLE_KEY ) ) != null )
                handleQuery( query );

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.unregisterAdapterDataObserver( gamesListObserver );
    }

    @Override
    protected GameListContract.Presenter getPresenterInterface() {
        return new GameListPresenter( this );
    }

    @Override
    public void displayGames(@NonNull List<GameItem> o) {
        adapter.swapDataset( o );
    }

    @Override
    public void displayNoDataMessage() {
        adapter.clearData();
    }

    @Override
    public void displayConnectionError() {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        final String query = presenter.getQuery();

        if( query != null )
            outState.putString( QUERY_BUNDLE_KEY, query );

    }

    @OnClick(R.id.id_search_button)
    public void onSearchButtonClick(){
        new QueryDialog( this, this::handleQuery ).show();
    }

    private void handleQuery(@NonNull final String query){

        new ConnectivityUtils()
                .checkNetwork()
                .observeOn( AndroidSchedulers.from( Looper.getMainLooper() ) )
                .subscribe( c -> {
                    if( c )
                        deliverQuery( query );
                    else postToastNotif( R.string.string_no_connection );

                });

    }

    private void deliverQuery(@NonNull final String query){
        presenter.queryGames(query);
        toolbar.setSubtitle( query );
    }

    private void postToastNotif(@StringRes final int msg){
        Toast.makeText( this, msg, Toast.LENGTH_SHORT ).show();
    }

    private RecyclerView.LayoutManager getLayoutManager(){
        return new GridLayoutManager(  this, 2 );
    }

    private RecyclerView.AdapterDataObserver gamesListObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();

            final int itemCount = adapter.getItemCount();

            if( itemCount == 0 ){
                 gameList.setVisibility( View.GONE );
                 noDataLayout.setVisibility( View.VISIBLE );
            }
            else{

                if( gameList.getVisibility() != View.VISIBLE )
                    gameList.setVisibility( View.VISIBLE );

                if( noDataLayout.getVisibility() != View.GONE )
                    noDataLayout.setVisibility( View.GONE );


            }

        }
    };

}
