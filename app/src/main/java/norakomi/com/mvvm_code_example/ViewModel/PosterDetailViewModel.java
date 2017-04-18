package norakomi.com.mvvm_code_example.ViewModel;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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

    public PosterDetailViewModel(@NonNull IDataModel dataModel) {
        super(dataModel);
    }

    public void setNavigator(@Nullable IPosterDetailNavigator navigator) {
        mNavigator = new WeakReference<>(navigator);
    }

    public void setDisplayablePoster(@NonNull Poster displayablePoster) {
        this.displayablePoster = displayablePoster;
    }

    public Observable<Poster> getDisplayablePoster() {
        return Observable.just(displayablePoster);
    }

    public void navigateBack() {
        // unused, but for sake of pattern/example it's implemented here
        if (mNavigator != null && mNavigator.get() != null) {
            ((IPosterDetailNavigator) mNavigator.get()).navigateBackToOverview();
        }
    }
}
