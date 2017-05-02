package norakomi.com.mvvm_code_example.Views.ViewAdapters;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import norakomi.com.mvvm_code_example.DataModel.Model.Poster;
import norakomi.com.mvvm_code_example.R;
import norakomi.com.mvvm_code_example.ViewModels.PosterOverviewViewModel;
import norakomi.com.mvvm_code_example.databinding.RecyclerItemBinding;


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
    public static ArrayList<Poster> mPosters = new ArrayList<>();

    public PosterAdapter(PosterOverviewViewModel viewModel) {
        mViewModel = viewModel;
    }

    @BindingAdapter("posters")
    public static void setPosters(RecyclerView view, List<Poster> posters) {
        mPosters.clear();
        mPosters.addAll(posters);
        view.getAdapter().notifyDataSetChanged();
    }

    @Override
    public PosterAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.recycler_item,
                parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(PosterAdapter.ViewHolder holder, int position) {
        Poster poster = mPosters.get(position);
        holder.binding.setPoster(poster);
    }

    @Override
    public int getItemCount() {
        return mPosters.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerItemBinding binding;

        private ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(view -> mViewModel.posterClicked(binding.getPoster()));
        }
    }
}
