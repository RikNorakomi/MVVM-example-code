package norakomi.com.mvvm_code_example;

import android.app.Application;
import android.support.annotation.NonNull;

import norakomi.com.mvvm_code_example.DataModel.DataModel;
import norakomi.com.mvvm_code_example.DataModel.IDataModel;
import norakomi.com.mvvm_code_example.Providers.GlideProvider;
import norakomi.com.mvvm_code_example.Providers.IGlideProvider;
import norakomi.com.mvvm_code_example.ViewModel.PosterDetailViewModel;
import norakomi.com.mvvm_code_example.ViewModel.ABaseViewModel;
import norakomi.com.mvvm_code_example.ViewModel.PosterOverviewViewModel;
import norakomi.com.mvvm_code_example.ViewModel.ViewModels;

/**
 * Created by Rik van Velzen, Norakomi.com, on 16-4-2017.
 * <p>
 * Check out:
 * https://github.com/RikNorakomi?tab=repositories
 * http://norakomi.blogspot.nl/
 * www.norakomi.com
 */

public class MVVMExampleApplication extends Application {

    @NonNull
    private final IDataModel mDataModel;

    public MVVMExampleApplication() {
        mDataModel = new DataModel();
    }

    @NonNull
    public IDataModel getDataModel() {
        return mDataModel;
    }

    @NonNull
    public ABaseViewModel getViewModel(@NonNull ViewModels requestedViewModel) {
        switch (requestedViewModel) {
            case POSTER_DETAIL:
                return new PosterDetailViewModel(getDataModel(), new GlideProvider());
            case POSTER_OVERVIEW:
                return new PosterOverviewViewModel(getDataModel());
            default:
                throw new IllegalArgumentException();
        }
    }
}
