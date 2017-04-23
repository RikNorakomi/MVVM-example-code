package norakomi.com.mvvm_code_example.Views.ViewAdapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import norakomi.com.mvvm_code_example.DataModel.Model.Poster;
import norakomi.com.mvvm_code_example.R;
import norakomi.com.mvvm_code_example.ViewModels.PosterOverviewViewModel;


/**
 * Created by Rik van Velzen, Norakomi.com, on 16-4-2017.
 * <p>
 * Check out:
 * https://github.com/RikNorakomi?tab=repositories
 * http://norakomi.blogspot.nl/
 * www.norakomi.com
 */

public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.ViewHolder> {

    private final PosterOverviewViewModel mViewModel;
    private ArrayList<Poster> mPosters = new ArrayList<>();

    public PosterAdapter(PosterOverviewViewModel viewModel) {
        mViewModel = viewModel;
    }

    public void updatePosters(@NonNull List<Poster> posters) {
        mPosters.clear();
        mPosters.addAll(posters);
        notifyDataSetChanged();
    }

    @Override
    public PosterAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyler_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PosterAdapter.ViewHolder holder, int position) {
        holder.updateViewHolder(mPosters.get(position));
    }

    @Override
    public int getItemCount() {
        return mPosters.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private Poster poster;

        private ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.recycler_item_title);
            title.setOnClickListener(view -> mViewModel.posterClicked(poster));
        }

        void updateViewHolder(Poster poster) {
            this.poster = poster;
            this.title.setText(poster.getTitle());

        }

    }
}
