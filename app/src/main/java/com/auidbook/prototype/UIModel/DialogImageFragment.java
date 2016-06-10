package com.auidbook.prototype.UIModel;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.auidbook.prototype.R;

import java.io.ByteArrayOutputStream;

/**
 * Created by mgundappan on 17-05-2016.
 */
public class DialogImageFragment extends DialogFragment {

    ImageView image_dp;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        System.out.println(" I am in Oncreate");

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_imageview, null);

        image_dp = (ImageView) view.findViewById(R.id.dialogue_image);

        byte[] imageByteArray = getArguments().getByteArray("DP_IMAGE_ARRAY");

        Bitmap bmp = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);

        //Bitmap bmp1 = RoundedImageView.scaleDown(bmp, 1000, true);

        image_dp.setImageBitmap(bmp);
        Dialog settingsDialog = new Dialog(getActivity());
        settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        settingsDialog.setContentView(view);
        return settingsDialog;
}

    public DialogImageFragment() {
    }
    public static DialogImageFragment newInstance(Bitmap bitmap){

        Bundle bundle = new Bundle();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

        byte[] byteArray = stream.toByteArray();

        bundle.putByteArray("DP_IMAGE_ARRAY", byteArray);

        DialogImageFragment dialougeImageFragment =  new DialogImageFragment();

        dialougeImageFragment.setArguments(bundle);

        /*dialougeImageFragment.setSharedElementEnterTransition(new Fade());

        dialougeImageFragment.setExitTransition(new Fade());*/

        return  dialougeImageFragment;
    }

}
