package norakomi.com.mvvm_code_example;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import norakomi.com.mvvm_code_example.DataModel.Model.Poster;
import norakomi.com.mvvm_code_example.DataModel.Model.SovietArtMePosters;
import norakomi.com.mvvm_code_example.ViewModel.PosterOverviewViewModel;
import norakomi.com.mvvm_code_example.ViewModel.ViewModels;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PosterOverviewActivity extends MVVMBaseActivity implements IPosterOverviewNavigator {

    private final String TAG = getClass().getSimpleName();
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewModel = (PosterOverviewViewModel) getViewModel(ViewModels.POSTER_OVERVIEW);
        mViewModel.setNavigator(this);

        setupViews();
    }

    private void setupViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.overview_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new PosterAdapter(mViewModel));

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
        ((PosterAdapter) mRecyclerView.getAdapter()).updatePosters(sovietArtMePosters.getPosters());
    }

    @Override
    public void openPosterDetail(Poster poster) {
        Intent intent = new Intent(this, PosterDetailActivity.class);
        intent.putExtra(Poster.INTENT_KEY, poster);
        startActivity(intent);
    }
}
