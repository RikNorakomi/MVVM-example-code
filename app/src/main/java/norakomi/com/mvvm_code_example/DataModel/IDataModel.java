package norakomi.com.mvvm_code_example.DataModel;

import android.support.annotation.NonNull;

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

public interface IDataModel {

    @NonNull
    Observable<List<Poster>> getPosters();
}
