package ir.hosseinabbasi.mediamonks.ui.photo;

import android.content.Context;
import android.content.res.Resources;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.hosseinabbasi.mediamonks.R;
import ir.hosseinabbasi.mediamonks.data.db.model.Photo;

/**
 * Created by Dr.jacky on 2017/12/01.
 */

public class PhotoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private IPhotoListView mListener;
    private Context mContext;
    private List<Photo> allData = new ArrayList<>();

    public PhotoListAdapter(Context context, IPhotoListView listener) {
        mContext = context;
        this.mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DataHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Photo photoModel = allData.get(position);
        String tempUrl = photoModel.getThumbnailUrl();
        ((DataHolder) holder).render(photoModel);

        if (tempUrl != null && !tempUrl.isEmpty()) {
            Picasso.with(mContext)
                    .load(tempUrl) // Thumbnail URL
                    .placeholder(R.mipmap.ic_launcher)
                    .resize(128, 128) // ToDo Calculate it by device screen size.
                    .into(((DataHolder) holder).thumbnail);
        } else {
            Picasso.with(mContext)
                    .load(R.mipmap.ic_launcher)
                    .placeholder(R.mipmap.ic_launcher)
                    .resize(128, 128) // ToDo Calculate it by device screen size.
                    .into(((DataHolder) holder).thumbnail);
        }
    }

    @Override
    public int getItemCount() {
        return allData.size();
    }

    public void addAll(List<Photo> photoList) {
        allData.addAll(photoList);
    }

    class DataHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Resources res = mContext.getResources();

        @BindView(R.id.photo_item_cnsMain)
        ConstraintLayout mainRow;

        @BindView(R.id.photo_list_item_txtTitle)
        TextView title;

        @BindView(R.id.photo_list_item_imgThumbnail)
        ImageView thumbnail;

        @BindView(R.id.photo_list_item_txtId)
        TextView id;

        DataHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void render(Photo photoModel) {
            title.setText(photoModel.getTitle());
            id.setText(photoModel.getId()+""); //ToDo
            mainRow.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Photo photo = allData.get(this.getLayoutPosition());
            mListener.loadPhotoDetail(photo);
        }

    }
}
