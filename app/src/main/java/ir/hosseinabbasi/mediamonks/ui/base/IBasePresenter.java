package ir.hosseinabbasi.mediamonks.ui.base;

/**
 * Created by Dr.jacky on 2017/12/01.
 */

public interface IBasePresenter <V extends IBaseView> {

    void onAttach(V baseView);

    void onDetach();

}
