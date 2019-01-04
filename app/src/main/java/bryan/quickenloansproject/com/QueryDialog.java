package bryan.quickenloansproject.com;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

public class QueryDialog extends Dialog {

    @LayoutRes
    public static final int DIALOG_LAYOUT = R.layout.layout_query_text_dialog;

    @BindView(R.id.id_local_search_editor)
    public EditText editor;

    private QueryParamCallback client;

    public QueryDialog(@NonNull Context context, @NonNull final QueryParamCallback client) {
        super( context, R.style.Theme_AppCompat_Light_Dialog_QueryDialog );

        this.client = client;

        requestWindowFeature( Window.FEATURE_NO_TITLE );

        final View content = LayoutInflater.from(context)
                .inflate( DIALOG_LAYOUT, null, false );

        setContentView( content );

        WindowManager.LayoutParams windowParams = null;

        if( getWindow()!=null && getWindow().getAttributes() != null ){

            windowParams = getWindow().getAttributes();

            final DisplayMetrics m = new DisplayMetrics();

            getWindow().getWindowManager().getDefaultDisplay().getMetrics( m );

            windowParams.width = m.widthPixels;
            windowParams.dimAmount = .7F;
        }

        ButterKnife.bind( this );
    }

    @OnClick(R.id.id_local_button)
    public void launchQuery(){
        if( client != null && editor.getText().toString().length() > 0 ) {
            client.onNewQueryParam(editor.getText().toString());
            dismiss();
        }

    }

    public interface QueryParamCallback{
        void onNewQueryParam(@NonNull final String result);
    }

}
