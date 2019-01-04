package bryan.quickenloansproject.com.Data.GiantBomb;

import com.google.gson.annotations.SerializedName;


public class GameItem {
    
    @SerializedName("name")
    private String title;

    @SerializedName("image")
    private GameItemImage image;

    public String getTitle() {
        return title;
    }

    public GameItemImage getImage() {
        return image;
    }
}
