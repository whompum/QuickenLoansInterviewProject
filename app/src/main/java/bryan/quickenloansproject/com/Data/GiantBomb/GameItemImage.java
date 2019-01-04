package bryan.quickenloansproject.com.Data.GiantBomb;

import com.google.gson.annotations.SerializedName;

public class GameItemImage {

    @SerializedName("original_url")
    private String mainImage;

    @SerializedName("small_url")
    private String smallImage;

    public String getMainImage() {
        return mainImage;
    }

    public String getSmallImage() {
        return smallImage;
    }
}
