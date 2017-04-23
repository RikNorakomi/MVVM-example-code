package norakomi.com.mvvm_code_example.ViewModel;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

import norakomi.com.mvvm_code_example.DataModel.IDataModel;
import norakomi.com.mvvm_code_example.DataModel.Model.SovietArtMePosters;
import norakomi.com.mvvm_code_example.Providers.IGlideProvider;
import rx.Observable;

/**
 * Created by Rik van Velzen, Norakomi.com, on 16-4-2017.
 * <p>
 * Check out:
 * https://github.com/RikNorakomi?tab=repositories
 * http://norakomi.blogspot.nl/
 * www.norakomi.com
 */

public abstract class ABaseViewModel {

    WeakReference<?> mNavigator;
    public static IGlideProvider mGlideProvider;

    @NonNull
    private final IDataModel mDataModel;

    public ABaseViewModel(@NonNull final IDataModel dataModel) {
        mDataModel = dataModel;
    }

    @NonNull
    public Observable<SovietArtMePosters> getPosters() {
        return mDataModel.getPosters();
    }

    public void onActivityDestroyed() {
        // Clear references to avoid potential memory leaks.
        mNavigator = null;
        mGlideProvider = null;
    }
}


