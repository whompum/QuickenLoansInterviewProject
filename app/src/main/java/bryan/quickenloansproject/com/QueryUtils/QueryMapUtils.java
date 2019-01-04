package bryan.quickenloansproject.com.QueryUtils;


import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;


public class QueryMapUtils {

    private Map<String, String> queryMap = new HashMap<>( 2 );

    public QueryMapUtils appendQuery(@NonNull final String queryKey, @NonNull final String param){
        queryMap.put( queryKey, param );

        return this;
    }

    public Map<String, String> getQuery(){
        return queryMap;
    }

}
