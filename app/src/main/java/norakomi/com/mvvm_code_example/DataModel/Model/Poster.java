package norakomi.com.mvvm_code_example.DataModel.Model;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by Rik van Velzen, Norakomi.com, on 16-4-2017.
 * <p>
 * Check out:
 * https://github.com/RikNorakomi?tab=repositories
 * http://norakomi.blogspot.nl/
 * www.norakomi.com
 */

public class Poster implements Serializable {

    public static final String INTENT_KEY = "poster";

    @NonNull
    private final String mTitle;

    @NonNull
    private final String mUrl;


    public Poster(@NonNull String title, @NonNull String url) {
        mTitle = title;
        mUrl = url;
    }

    @NonNull
    public String getTitle() {
        return mTitle;
    }

    @NonNull
    public String getUrl() {
        return mUrl;
    }
}
