package norakomi.com.mvvm_code_example.Providers;

import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

/**
 * Created by Rik van Velzen, Norakomi.com, on 23-4-2017.
 * <p>
 * Check out:
 * https://github.com/RikNorakomi?tab=repositories
 * http://norakomi.blogspot.nl/
 * www.norakomi.com
 */

public class GlideProvider implements IGlideProvider {

    private final String TAG = getClass().getSimpleName();

    @Override
    public void loadImage(ImageView view, String imageUrl, IGliderErrorCallback callback) {
        Log.d(TAG, "loading image with url" + imageUrl);
        Glide.with(view.getContext())
                .load(imageUrl)
                .placeholder(null) // todo: add placeHolder
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String s, Target<GlideDrawable> target, boolean b) {
                        Log.e(TAG, "Glide load exception for url: " + imageUrl +
                                ", String = " + s +
                                ", Exception = " + e.toString());
                        callback.onError(e, s, target, b);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable glideDrawable, String s, Target<GlideDrawable> target, boolean b, boolean b1) {
                        // successful
                        Log.d(TAG, "image successfully loaded!");
                        return false;
                    }
                })
                .into(view);
    }
}
