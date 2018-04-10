package pe.tumicro.android.util.livedata.firebase;

import android.arch.lifecycle.LiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.atomic.AtomicBoolean;

import pe.tumicro.android.vo.Resource;

/**
 * Created by josue on 2/28/18.
 */

public class FSingleLiveData<T> extends LiveData<Resource<T>> {
    private AtomicBoolean started = new AtomicBoolean(false);
    private final DatabaseReference dbRef;
    private final Class<T> type;

    public FSingleLiveData(DatabaseReference dbRef, Class<T> type) {
        this.dbRef = dbRef;
        this.type = type;
        postValue(Resource.loading(null));
    }

    @Override
    protected void onActive() {
        super.onActive();
        if (started.compareAndSet(false, true)) {
            dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    T value = dataSnapshot.getValue(type);
                    postValue(Resource.success(value));
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // this only happens on database permission error,
                    // be careful, when firebase is not online and there is no local data about
                    // the node no callback will be triggered
                    postValue(Resource.error(databaseError.getMessage(), null));
                }
            });
        }
    }

}
