package bryan.quickenloansproject.com.Data.GiantBomb;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * General base class for data returned from the
 * GiantBomb api
 *
 * @param <T> The Modeled type of the queried data
 */
public class ServerResponse<T> {

    @SerializedName("status_code")
    private int statusCode;

    private String error;

    @SerializedName("number_of_total_results")
    private int count;

    private List<T> results;

    public int getStatusCode() {
        return statusCode;
    }

    public String getError() {
        return error;
    }

    public int getCount() {
        return count;
    }

    public List<T> getResults() {
        return results;
    }
}
