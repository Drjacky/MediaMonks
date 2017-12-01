package ir.hosseinabbasi.mediamonks.ui.album;

import android.content.Context;
import android.content.res.Resources;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.hosseinabbasi.mediamonks.R;
import ir.hosseinabbasi.mediamonks.data.db.model.Album;

/**
 * Created by Dr.jacky on 2017/12/01.
 */

public class AlbumListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private IAlbumListView mListener;
    private Context mContext;
    private List<Album> allData = new ArrayList<>();

    public AlbumListAdapter(Context context, IAlbumListView listener) {
        mContext = context;
        this.mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DataHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Album albumModel = allData.get(position);
        ((DataHolder) holder).render(albumModel);
    }

    @Override
    public int getItemCount() {
        return allData.size();
    }

    public void addAll(List<Album> albumList) {
        allData.addAll(albumList);
    }

    class DataHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Resources res = mContext.getResources();

        @BindView(R.id.album_item_cnsMain)
        ConstraintLayout mainRow;

        @BindView(R.id.album_list_item_txtTitle)
        TextView title;

        DataHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void render(Album albumModel) {
            title.setText(albumModel.getTitle());
            mainRow.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Album album = allData.get(this.getLayoutPosition());
            mListener.loadAlbumDetail(album.getId());
        }

    }
}
