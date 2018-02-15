package com.giri.listview.views.description;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.giri.listview.R;
import com.giri.listview.helpers.Utils;

/**
 * Created by Giri on 14-Feb-18.
 */

public class MovieDescription extends Fragment {

    private View rootView;
    private ImageView imgTheme;
    private TextView textTitle, txtRating, textReleaseyear, textDescription;
    private String title, img, release_year, rate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_description, container, false);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupViews();
    }

    private void setupViews() {

        imgTheme = (ImageView) rootView.findViewById(R.id.imageMovie);
        textTitle = (TextView) rootView.findViewById(R.id.textName);
        txtRating = (TextView) rootView.findViewById(R.id.textRating);
        textReleaseyear = (TextView) rootView.findViewById(R.id.textReleaseyear);
        textDescription = (TextView) rootView.findViewById(R.id.textStatiDesc);

        title = getArguments().getString("Title");
        img = getArguments().getString("Theme");
        release_year = getArguments().getString("ReleaseYear");
        rate = getArguments().getString("Rate");

        if (title != null) {
            textTitle.setText(title);
            imgTheme.setImageBitmap(Utils.getBitmapFromURL(img));
            textReleaseyear.setText("Release Year: " + release_year);
            txtRating.setText("Rating: " + rate);
            textDescription.setText(title + " " + getResources().getString(R.string.desc));
        }

        imgTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog previewDialog = new Dialog(getContext(), android.R.style.Theme_Light_NoTitleBar_Fullscreen);
                previewDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                previewDialog.setCancelable(false);
                previewDialog.setContentView(R.layout.preview_image);
                ImageView ivPreview = (ImageView) previewDialog.findViewById(R.id.iv_preview_image);
                ivPreview.setImageBitmap(Utils.getBitmapFromURL(img));
                previewDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                            dialog.dismiss();
                        }
                        return true;
                    }
                });
                previewDialog.show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
