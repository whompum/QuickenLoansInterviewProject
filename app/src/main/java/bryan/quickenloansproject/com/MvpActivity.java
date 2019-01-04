package bryan.quickenloansproject.com;

import android.os.Bundle;

import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class MvpActivity<T extends MvpContract.Presenter> extends AppCompatActivity {

    protected T presenter;

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = getPresenterInterface();
    }

    protected abstract T getPresenterInterface();

}
