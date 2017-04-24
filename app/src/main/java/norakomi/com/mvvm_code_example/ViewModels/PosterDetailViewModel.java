package norakomi.com.mvvm_code_example.ViewModels;

import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

import norakomi.com.mvvm_code_example.DataModel.IDataModel;
import norakomi.com.mvvm_code_example.DataModel.Model.Poster;
import norakomi.com.mvvm_code_example.Views.ViewInterfaces.IPosterDetailNavigator;
import norakomi.com.mvvm_code_example.Providers.IGlideProvider;
import rx.Observable;

/**
 * Created by Rik van Velzen, Norakomi.com, on 17-4-2017.
 * <p>
 * Check out:
 * https://github.com/RikNorakomi?tab=repositories
 * http://norakomi.blogspot.nl/
 * www.norakomi.com
 */

public class PosterDetailViewModel extends ABaseViewModel {

    private Poster displayablePoster;
    public String title;
    public String imageUrl;

    public PosterDetailViewModel(@NonNull IDataModel dataModel, @NonNull IGlideProvider glideProvider) {
        super(dataModel, glideProvider);
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

    @BindingAdapter("loadImage")
    public static void loadImage(ImageView view, String imageUrl) {
        if (mGlideProvider != null) {
            mGlideProvider.loadImage(view, imageUrl, (e, s, target, b) -> {
                // todo logError
                onErrorLoadingImage();
            });
        }
    }

    private static void onErrorLoadingImage() {
        // todo: notify user of error
    }

    public void navigateBack() {
        // unused, but for sake of pattern/example it's implemented here
        if (mNavigator != null && mNavigator.get() != null) {
            ((IPosterDetailNavigator) mNavigator.get()).navigateBackToOverview();
        }
    }
}
