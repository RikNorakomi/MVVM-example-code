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

    private final String THUMBNAIL_BASE_PATH = "http://sovietart.me/img/posters/190px/";

    private String title;
    private String filename;

    public Poster(@NonNull String title, @NonNull String url) {
        this.title = title;
        filename = url;
    }

    public String getThumbnailUrl() {
        return THUMBNAIL_BASE_PATH + filename;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getFilename() {
        return filename;
    }
}
