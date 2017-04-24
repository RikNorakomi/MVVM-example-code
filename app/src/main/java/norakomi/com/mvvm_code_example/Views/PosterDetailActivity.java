package norakomi.com.mvvm_code_example.Views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import norakomi.com.mvvm_code_example.DataModel.Model.Poster;
import norakomi.com.mvvm_code_example.R;
import norakomi.com.mvvm_code_example.ViewModels.PosterDetailViewModel;
import norakomi.com.mvvm_code_example.ViewModels.ViewModels;
import norakomi.com.mvvm_code_example.Views.ViewInterfaces.IPosterDetailNavigator;
import norakomi.com.mvvm_code_example.databinding.ActivityPosterDetailBinding;

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
        // unused, but for sake of example/pattern it's implemented here
    }

    @Override
    public void navigateBackToOverview() {
        // unused, but for sake of example/pattern it's implemented here
    }
}
