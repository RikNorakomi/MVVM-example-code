package norakomi.com.mvvm_code_example.ViewModel;

import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.lang.ref.WeakReference;

import norakomi.com.mvvm_code_example.DataModel.IDataModel;
import norakomi.com.mvvm_code_example.DataModel.Model.Poster;
import norakomi.com.mvvm_code_example.IPosterDetailNavigator;
import rx.Observable;

/**
 * Created by Rik van Velzen, Norakomi.com, on 17-4-2017.
 * <p>
 * Check out:
 * https://github.com/RikNorakomi?tab=repositories
 * http://norakomi.blogspot.nl/
 * www.norakomi.com
 */

public class PosterDetailViewModel extends BaseViewModel {

    private Poster displayablePoster;
    public String title;
    public String imageUrl;

    public PosterDetailViewModel(@NonNull IDataModel dataModel) {
        super(dataModel);
    }

    public void setNavigator(@Nullable IPosterDetailNavigator navigator) {
        mNavigator = new WeakReference<>(navigator);
    }

    public void setDisplayablePoster(@NonNull Poster displayablePoster) {
        this.displayablePoster = displayablePoster;
        this.title = displayablePoster.getTitle();
        this.imageUrl = displayablePoster.getThumbnailUrl();
    }

    public Observable<Poster> getDisplayablePoster() {
        return Observable.just(displayablePoster);
    }

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String imageUrl) {
        // todo: Glide implementation should be abstracted out of viewModel
        Glide.with(view.getContext())
                .load(imageUrl)
                .placeholder(null) // todo: add placeHolder
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String s, Target<GlideDrawable> target, boolean b) {
                        Log.e(getClass().getSimpleName(), "Glide load exception for url: " + imageUrl +
                                "String = " + s +
                                "Exception = " + e.toString());
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable glideDrawable, String s, Target<GlideDrawable> target, boolean b, boolean b1) {
                        // successful
                        return false;
                    }
                })
                .into(view);
    }

    public void navigateBack() {
        // unused, but for sake of pattern/example it's implemented here
        if (mNavigator != null && mNavigator.get() != null) {
            ((IPosterDetailNavigator) mNavigator.get()).navigateBackToOverview();
        }
    }
}
