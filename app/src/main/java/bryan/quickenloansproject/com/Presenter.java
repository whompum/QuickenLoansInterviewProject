package bryan.quickenloansproject.com;


import androidx.annotation.NonNull;

public class Presenter<T extends MvpContract.View> {

    protected T view;

    public Presenter(@NonNull final T view){
        this.view = view;
    }

}
