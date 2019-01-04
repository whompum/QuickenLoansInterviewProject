package bryan.quickenloansproject.com.GameList.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import bryan.quickenloansproject.com.Data.GiantBomb.GameItem;
import bryan.quickenloansproject.com.ListUtils.DynamicListAdapter;
import bryan.quickenloansproject.com.ListUtils.GenericViewHolder;
import bryan.quickenloansproject.com.R;

@SuppressWarnings("unchecked")
public class GameListItemAdapter extends DynamicListAdapter<GameItem> {

    @LayoutRes
    public static final int LAYOUT = R.layout.layout_game_list_item;
    
    private LayoutInflater inflater;

    private ItemViewResizer itemViewResizer;

    public GameListItemAdapter(){

    }

    public GameListItemAdapter(@NonNull final List<GameItem> data){
        this.data.addAll( data );
    }

    @NonNull
    @Override
    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        
        if( inflater == null )
            inflater = LayoutInflater.from( parent.getContext() );

        if( itemViewResizer == null )
            itemViewResizer = new ItemViewResizer( parent.getContext() );

        final View itemView = inflater.inflate( LAYOUT, parent, false );

        itemViewResizer.resize( itemView );

        return new GameListViewHolder( itemView, itemViewResizer.getViewSize() );
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder holder, int position) {
        holder.bind( data.get( position ) );
    }

    private static class ItemViewResizer{

        static final int UNSCALED = -1;

        private int viewSize = UNSCALED;

        private int widthPixels;

        ItemViewResizer(@NonNull final Context context){
            widthPixels = context.getResources().getDisplayMetrics().widthPixels;
        }

        public void resize(@NonNull final View item){

            if( viewSize == UNSCALED )
                viewSize = widthPixels >> 1; //We want our items to be half the screen size

            final ViewGroup.LayoutParams p = item.getLayoutParams();

            p.width = viewSize;
            p.height = viewSize;

            item.setLayoutParams( p );

        }

        public int getViewSize(){
            return viewSize;
        }

    }


}
