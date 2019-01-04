package bryan.quickenloansproject.com.ListUtils;


import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class DynamicListAdapter<T> extends RecyclerView.Adapter<GenericViewHolder<T>> {

    protected List<T> data = Collections.emptyList();

    public void swapDataset(@NonNull final List<T> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public void clearData(){
        data.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
