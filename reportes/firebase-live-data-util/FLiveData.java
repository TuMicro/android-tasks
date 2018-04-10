package pe.tumicro.android.util.livedata.firebase;

import android.arch.lifecycle.LiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import pe.tumicro.android.vo.Resource;
import timber.log.Timber;

/**
 * Created by josue on 3/19/2018.
 * Inspiration: https://firebase.googleblog.com/2017/12/using-android-architecture-components.html
 */

public class FLiveData<T> extends LiveData<Resource<T>> {
    private final Query query;
    private final Class<T> type;
    private final MyValueEventListener listener = new MyValueEventListener();


    public FLiveData(Query query, Class<T> type) {
        this.query = query;
        this.type = type;
        setValue(Resource.loading(null));
    }

    @Override
    protected void onActive() {
        // onStart or onResume over activity,
        // remember that always receive tha latest data on onCreate, but
        // receives on onStart only if changed during background
        super.onActive();
        query.addValueEventListener(listener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        query.removeEventListener(listener);
    }

    private class MyValueEventListener implements ValueEventListener {
        /**
         * BIG NOTE:
         * change is needed for firebase to trigger updates on this onDataChange
         */
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            T value = dataSnapshot.getValue(type);
            setValue(Resource.success(value));
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            // this only happens on database permission error,
            // be careful, when firebase is not online and there is no local data about
            // the node no callback will be triggered
            Timber.e("Can't listen to query " + query + " Exception: " + databaseError.toException());
            setValue(Resource.error(databaseError.getMessage(), null));
        }
    }
}
