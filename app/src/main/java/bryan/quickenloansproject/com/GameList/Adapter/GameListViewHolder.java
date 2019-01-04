package bryan.quickenloansproject.com.GameList.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import bryan.quickenloansproject.com.Data.GiantBomb.GameItem;
import bryan.quickenloansproject.com.Data.GiantBomb.GameItemImage;
import bryan.quickenloansproject.com.ListUtils.GenericViewHolder;
import bryan.quickenloansproject.com.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class GameListViewHolder extends GenericViewHolder<GameItem> {

    @BindView(R.id.id_local_image_container)
    public ImageView thumbnailPreview;

    @BindView(R.id.id_local_title)
    public TextView titlePreview;

    private int imgSize;

    public GameListViewHolder(@NonNull View itemView, final int imgSize) {
        super( itemView );
        ButterKnife.bind( this, itemView );

        this.imgSize = imgSize;
    }

    @Override
    public void bind(@NonNull GameItem o) {

        String imgUrl = null;

        final GameItemImage img = o.getImage();

        if( img != null ) {

            final String mainImg = o.getImage().getMainImage();

            imgUrl = ( mainImg != null ) ? mainImg : o.getImage().getSmallImage();
        }

        if( imgUrl != null )
            loadThumbnail( o.getImage().getMainImage() );
        setTitle( o.getTitle() );
    }

    private void loadThumbnail(@NonNull final String url){

        Picasso.get()
                .load( url )
                .resize( imgSize, imgSize )
                .centerCrop()
                .into( thumbnailPreview );

    }

    private void setTitle(@NonNull final String title){
        titlePreview.setText( title );
    }

}
