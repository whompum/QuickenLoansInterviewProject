package bryan.quickenloansproject.com;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NoDataFrameLayoutContainer extends FrameLayout {

    private boolean showNoDataContainer;

    public NoDataFrameLayoutContainer(@NonNull Context context, @Nullable AttributeSet attrs) {
        super( context, attrs );
    }
}
