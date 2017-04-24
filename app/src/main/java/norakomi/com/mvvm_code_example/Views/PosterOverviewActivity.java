package norakomi.com.mvvm_code_example.Views;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import norakomi.com.mvvm_code_example.DataModel.Model.Poster;
import norakomi.com.mvvm_code_example.DataModel.Model.SovietArtMePosters;
import norakomi.com.mvvm_code_example.R;
import norakomi.com.mvvm_code_example.ViewModels.PosterOverviewViewModel;
import norakomi.com.mvvm_code_example.ViewModels.ViewModels;
import norakomi.com.mvvm_code_example.Views.ViewAdapters.PosterAdapter;
import norakomi.com.mvvm_code_example.Views.ViewInterfaces.IPosterOverviewNavigator;
import norakomi.com.mvvm_code_example.databinding.ActivityMainBinding;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PosterOverviewActivity extends MVVMBaseActivity implements IPosterOverviewNavigator {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = (PosterOverviewViewModel) getViewModel(ViewModels.POSTER_OVERVIEW);
        mViewModel.setNavigator(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(mViewModel);
        binding.overviewRecycler.setAdapter(new PosterAdapter(mViewModel));
    }

    @Override
    public void bindRxSubscriptions() {
        // todo: add error handling
        mSubscription.add(mViewModel.getPosters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setPosters));
    }

    private void setPosters(@NonNull final SovietArtMePosters sovietArtMePosters) {
        ((PosterAdapter) binding.overviewRecycler.getAdapter()).updatePosters(sovietArtMePosters.getPosters());
    }

    @Override
    public void openPosterDetail(Poster poster) {
        Intent intent = new Intent(this, PosterDetailActivity.class);
        intent.putExtra(Poster.INTENT_KEY, poster);
        startActivity(intent);
    }
}
