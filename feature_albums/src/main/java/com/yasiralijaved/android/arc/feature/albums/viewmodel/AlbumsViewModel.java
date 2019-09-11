package com.yasiralijaved.android.arc.feature.albums.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.yasiralijaved.android.arc.component.db.dao.AlbumDao;
import com.yasiralijaved.android.arc.component.db.entity.AlbumEntity;
import com.yasiralijaved.android.arc.component.http.BackendService;
import com.yasiralijaved.android.arc.core.repository.AlbumRepository;
import com.yasiralijaved.android.arc.core.utils.Resource;

import java.util.List;

import javax.inject.Inject;

public class AlbumsViewModel extends ViewModel {

    private AlbumRepository mAlbumRepository;
    private MutableLiveData<ParamAlbumList> getAlbumsListCommand = new MutableLiveData<>();

    /*
     * We are injecting the UserDao class
     * and the BackendService class to the ViewModel.
     * */
    @Inject
    AlbumsViewModel(AlbumDao albumDao, BackendService backendService) {
        super();
        mAlbumRepository = new AlbumRepository(albumDao, backendService);
        albumsListLiveData = Transformations.switchMap(getAlbumsListCommand,
                param -> mAlbumRepository.getAlbumsList(param.forceUpdate, param.userId));
    }

    /* START - Get Albums List */
    // Albums List Observable Field
    private LiveData<Resource<List<AlbumEntity>>> albumsListLiveData;
    public LiveData<Resource<List<AlbumEntity>>> getAlbumsListLiveData() {
        return albumsListLiveData;
    }


    public void loadAlbumsList(boolean forceUpdate, int userId) {
        getAlbumsListCommand.setValue(new ParamAlbumList(forceUpdate, userId));
    }
    /* END - Get Albums List */

    class ParamAlbumList {
        private boolean forceUpdate;
        private int userId;

        ParamAlbumList(boolean forceUpdate, int userId) {
            this.forceUpdate = forceUpdate;
            this.userId = userId;
        }

        public boolean isForceUpdate() {
            return forceUpdate;
        }

        public void setForceUpdate(boolean forceUpdate) {
            this.forceUpdate = forceUpdate;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
