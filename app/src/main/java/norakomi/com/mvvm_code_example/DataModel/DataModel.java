package norakomi.com.mvvm_code_example.DataModel;

import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

import norakomi.com.mvvm_code_example.DataModel.Model.Poster;
import rx.Observable;

/**
 * Created by Rik van Velzen, Norakomi.com, on 16-4-2017.
 * <p>
 * Check out:
 * https://github.com/RikNorakomi?tab=repositories
 * http://norakomi.blogspot.nl/
 * www.norakomi.com
 */

public class DataModel implements IDataModel {

    @NonNull
    @Override
    public Observable<List<Poster>> getPosters() {
        return Observable.fromCallable(this::getTestPosters);
    }

    private List<Poster> getTestPosters(){
        return Arrays
                .asList(new Poster("Poster1", "http://norakomi.com/assets/img/mvvp_example/0323.jpg"),
                        new Poster("Poster2", "http://norakomi.com/assets/img/mvvp_example/0350.jpg"),
                        new Poster("Poster3", "http://norakomi.com/assets/img/mvvp_example/0421.jpg"),
                        new Poster("Poster4", "http://norakomi.com/assets/img/mvvp_example/0430.jpg"));
    }
}
