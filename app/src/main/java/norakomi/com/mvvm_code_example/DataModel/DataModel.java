package norakomi.com.mvvm_code_example.DataModel;

import android.support.annotation.NonNull;

import norakomi.com.mvvm_code_example.DataModel.Model.SovietArtMePosters;
import norakomi.com.mvvm_code_example.DataModel.api.PosterService;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by Rik van Velzen, Norakomi.com, on 16-4-2017.
 * <p>
 * Check out:
 * https://github.com/RikNorakomi?tab=repositories
 * http://norakomi.blogspot.nl/
 * www.norakomi.com
 */

public class DataModel implements IDataModel {

    public static String BASE_URL = "http://www.norakomi.com/assets/json/";

    //todo: abstract retrofit impl. out of datamodel
    private Retrofit mRetrofit;
    private RxJavaCallAdapterFactory mRxAdapter;

    private Retrofit getRetrofitInstance() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(getRxJavaCallAdapter())
                    .build();
        }

        return mRetrofit;
    }

    private CallAdapter.Factory getRxJavaCallAdapter() {
        if (mRxAdapter == null) {
            mRxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
        }

        return mRxAdapter;
    }

    private PosterService getPosterApiService() {
        return getRetrofitInstance().create(PosterService.class);
    }

    @NonNull
    @Override
    public Observable<SovietArtMePosters> getPosters() {
        return getPosterApiService().loadPostersData();
    }
}
