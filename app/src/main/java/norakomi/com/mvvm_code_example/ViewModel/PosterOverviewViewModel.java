package norakomi.com.mvvm_code_example.ViewModel;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

import norakomi.com.mvvm_code_example.DataModel.IDataModel;
import norakomi.com.mvvm_code_example.DataModel.Model.Poster;
import norakomi.com.mvvm_code_example.IPosterOverviewNavigator;

/**
 * Created by Rik van Velzen, Norakomi.com, on 16-4-2017.
 * <p>
 * Check out:
 * https://github.com/RikNorakomi?tab=repositories
 * http://norakomi.blogspot.nl/
 * www.norakomi.com
 */

public class PosterOverviewViewModel extends BaseViewModel {

    public PosterOverviewViewModel(@NonNull IDataModel dataModel) {
        super(dataModel);
    }

    public void setNavigator(@Nullable IPosterOverviewNavigator navigator) {
        mNavigator = new WeakReference<>(navigator);
    }

    public void posterClicked(@NonNull Poster poster) {
        if (mNavigator != null && mNavigator.get() != null) {
            ((IPosterOverviewNavigator) mNavigator.get()).openPosterDetail(poster);
        }
    }
}