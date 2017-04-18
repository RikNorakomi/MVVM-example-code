package norakomi.com.mvvm_code_example;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import norakomi.com.mvvm_code_example.DataModel.Model.Poster;
import norakomi.com.mvvm_code_example.ViewModel.PosterDetailViewModel;
import norakomi.com.mvvm_code_example.ViewModel.ViewModels;
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

    private final String TAG = getClass().getSimpleName();

    @NonNull
    private PosterDetailViewModel mViewModel;
    private TextView mTitleText;
    private ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster_detail);

        Poster poster = (Poster) getIntent().getSerializableExtra(Poster.INTENT_KEY);
        mViewModel = (PosterDetailViewModel) getViewModel(ViewModels.POSTER_DETAIL);
        mViewModel.setNavigator(this); // navigator not used yet, but for sake of pattern we're setting navigator here
        mViewModel.setDisplayablePoster(poster);

        setupViews();
    }

    @Override
    public void bindRxSubscriptions() {
        mSubscription.add(mViewModel.getDisplayablePoster()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::displayPoster));
    }

    private void displayPoster(Poster poster) {
        mTitleText.setText(poster.getTitle());
        Glide.with(this)
                .load(poster.getUrl())
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String s, Target<GlideDrawable> target, boolean b) {
                        Log.e(TAG, "Glide load exception for url: " + poster.getUrl() +
                                "String = " + s +
                                "Exception = " + e.toString());
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable glideDrawable, String s, Target<GlideDrawable> target, boolean b, boolean b1) {
                        // successful
                        return false;
                    }
                })
                .into(mImageView);
    }


    private void setupViews() {
        mTitleText = (TextView) findViewById(R.id.tv_poster_detail);
        mImageView = (ImageView) findViewById(R.id.iv_poster_detail);
    }

    @Override
    public void navigateBackToOverview() {
        // unused, but for sake of pattern it's implemented here
    }


}
