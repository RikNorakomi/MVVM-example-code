package norakomi.com.mvvm_code_example;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import norakomi.com.mvvm_code_example.DataModel.Model.Poster;
import norakomi.com.mvvm_code_example.ViewModel.PosterDetailViewModel;
import norakomi.com.mvvm_code_example.ViewModel.ViewModels;
import norakomi.com.mvvm_code_example.databinding.ActivityPosterDetailBinding;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Rik van Velzen, Norakomi.com, on 16-4-2017.
 * <p>
 * Check out:
 * https://github.com/RikNorakomi?tab=repositories
 * http://norakomi.blogspot.nl/
 * www.norakomi.com
 */

public class PosterDetailActivity extends MVVMBaseActivity implements IPosterDetailNavigator {

    @NonNull
    private PosterDetailViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPosterDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_poster_detail);

        Poster poster = (Poster) getIntent().getSerializableExtra(Poster.INTENT_KEY);
        mViewModel = (PosterDetailViewModel) getViewModel(ViewModels.POSTER_DETAIL);
        mViewModel.setNavigator(this); // navigator not used yet, but for sake of pattern we're setting navigator here
        mViewModel.setDisplayablePoster(poster);

        binding.setViewModel(mViewModel);
    }

    @Override
    public void bindRxSubscriptions() {
        mSubscription.add(mViewModel.getDisplayablePoster()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }

    @Override
    public void navigateBackToOverview() {
        // unused, but for sake of example/pattern it's implemented here
    }
}
