package norakomi.com.mvvm_code_example;

import android.app.Application;
import android.support.annotation.NonNull;

import norakomi.com.mvvm_code_example.DataModel.DataModel;
import norakomi.com.mvvm_code_example.DataModel.IDataModel;
import norakomi.com.mvvm_code_example.Providers.GlideProvider;
import norakomi.com.mvvm_code_example.ViewModels.PosterDetailViewModel;
import norakomi.com.mvvm_code_example.ViewModels.ABaseViewModel;
import norakomi.com.mvvm_code_example.ViewModels.PosterOverviewViewModel;
import norakomi.com.mvvm_code_example.ViewModels.ViewModels;

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
                // todo: preferably never throw an exception
                throw new IllegalArgumentException("Unhandled argument: " + requestedViewModel.name());
        }
    }
}
