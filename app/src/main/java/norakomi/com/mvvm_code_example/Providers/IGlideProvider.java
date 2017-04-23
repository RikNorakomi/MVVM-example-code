package norakomi.com.mvvm_code_example.Providers;

import android.widget.ImageView;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.Target;

/**
 * Created by Rik van Velzen, Norakomi.com, on 23-4-2017.
 * <p>
 * Check out:
 * https://github.com/RikNorakomi?tab=repositories
 * http://norakomi.blogspot.nl/
 * www.norakomi.com
 */

public interface IGlideProvider {

    void loadImage(ImageView view, String imageUrl, IGliderErrorCallback callback);

    interface IGliderErrorCallback {
        void onError(Exception e, String s, Target<GlideDrawable> target, boolean b);
    }
}
