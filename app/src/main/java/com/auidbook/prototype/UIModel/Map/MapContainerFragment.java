package com.auidbook.prototype.UIModel.Map;

import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.auidbook.prototype.MainActivity;
import com.auidbook.prototype.R;

/**
 * Created by njagadeesan on 21-05-2016.
 */
public class MapContainerFragment extends Fragment implements View.OnClickListener {
    private MapViewFragment mapViewFragment;
    private IMap iMap;
    private Button btn_all_donars;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iMap=((MainActivity)getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.map_container_fragment, container, false);
        mapViewFragment = new MapViewFragment();
        System.out.println("imap **   " + iMap.toString());
        iMap.addMap(mapViewFragment);
        btn_all_donars=(Button)v.findViewById(R.id.all_donars);
       // btn_all_donars.setOnClickListener((View.OnClickListener) getActivity());

        return v;
    }

    @Override
    public void onClick(View v) {

    }
}
