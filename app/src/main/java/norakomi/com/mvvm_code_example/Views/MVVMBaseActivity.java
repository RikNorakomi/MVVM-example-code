package norakomi.com.mvvm_code_example.Views;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import norakomi.com.mvvm_code_example.MVVMExampleApplication;
import norakomi.com.mvvm_code_example.ViewModels.ABaseViewModel;
import norakomi.com.mvvm_code_example.ViewModels.PosterOverviewViewModel;
import norakomi.com.mvvm_code_example.ViewModels.ViewModels;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Rik van Velzen, Norakomi.com, on 17-4-2017.
 * <p>
 * Check out:
 * https://github.com/RikNorakomi?tab=repositories
 * http://norakomi.blogspot.nl/
 * www.norakomi.com
 */

abstract class MVVMBaseActivity extends AppCompatActivity {

    @NonNull
    public PosterOverviewViewModel mViewModel;

    @NonNull
    public CompositeSubscription mSubscription;

    /**
     * Every sub class implements bindRxSubscriptions method where rx java subscription can be managed
     * This base class takes care of
     */
    public abstract void bindRxSubscriptions();

    private void unBindRxSubscriptions() {
        mSubscription.unsubscribe();
    }

    @NonNull
    public ABaseViewModel getViewModel(ViewModels requestedViewModel) {
        return ((MVVMExampleApplication) getApplication()).getViewModel(requestedViewModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSubscription = new CompositeSubscription();
        bindRxSubscriptions();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unBindRxSubscriptions();
    }

    @Override
    protected void onDestroy() {
        // call viewModel to cleanup references that could cause memory leaks
        if (mViewModel != null)
            mViewModel.onActivityDestroyed();
        super.onDestroy();
    }




}
