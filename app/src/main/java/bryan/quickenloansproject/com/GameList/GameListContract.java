package bryan.quickenloansproject.com.GameList;


import java.util.List;

import androidx.annotation.NonNull;
import bryan.quickenloansproject.com.Data.GiantBomb.GameItem;
import bryan.quickenloansproject.com.MvpContract;

interface GameListContract {

    interface Presenter extends MvpContract.Presenter {
        void queryGames(@NonNull final String query);
        String getQuery();
    }

    interface View extends MvpContract.View {
        void displayGames(@NonNull final List<GameItem> o);
        void displayNoDataMessage();
        void displayConnectionError();
    }

}
