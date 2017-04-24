package norakomi.com.mvvm_code_example.ViewModels;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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
    static IGlideProvider mGlideProvider;

    @NonNull
    private final IDataModel mDataModel;

    ABaseViewModel(@NonNull final IDataModel dataModel) {
        this(dataModel, null);
    }

    ABaseViewModel(@NonNull IDataModel dataModel, @Nullable IGlideProvider glideProvider) {
        mDataModel = dataModel;
        mGlideProvider = glideProvider;
    }

    @NonNull
    public Observable<SovietArtMePosters> loadPosters() {
        return mDataModel.loadPosters();
    }

    public void onActivityDestroyed() {
        // Clear references to avoid potential memory leaks.
        mNavigator = null;
        mGlideProvider = null;
    }
}


