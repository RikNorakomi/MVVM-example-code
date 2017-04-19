package norakomi.com.mvvm_code_example.DataModel.api;

import norakomi.com.mvvm_code_example.DataModel.Model.SovietArtMePosters;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Rik van Velzen, Norakomi.com, on 19-4-2017.
 * <p>
 * Check out:
 * https://github.com/RikNorakomi?tab=repositories
 * http://norakomi.blogspot.nl/
 * www.norakomi.com
 */

public interface PosterService {
    /**
     * Loads poster objects from json
     * For this example app the json can be found at:
     * http://www.norakomi.com/assets/json/mvvm_example_posters.json
     */
    @GET("mvvm_example_posters.json")
    Observable<SovietArtMePosters> loadPostersData();
}
