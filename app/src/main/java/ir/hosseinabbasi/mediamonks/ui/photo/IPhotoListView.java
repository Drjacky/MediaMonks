package ir.hosseinabbasi.mediamonks.ui.photo;

import java.util.List;

import ir.hosseinabbasi.mediamonks.data.db.model.Photo;
import ir.hosseinabbasi.mediamonks.ui.base.IBaseView;

/**
 * Created by Dr.jacky on 2017/12/01.
 */

public interface IPhotoListView extends IBaseView{

    void loadPhotoList(List<Photo> photoList);

    void loadPhotoDetail(Photo photo);

    void setIsLoading(Boolean isLoading);

}
