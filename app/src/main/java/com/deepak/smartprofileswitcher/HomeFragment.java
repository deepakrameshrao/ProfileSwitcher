package com.deepak.smartprofileswitcher;

/**
 * Created by Deepak on 18/09/15.
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import com.deepak.smartprofileswitcher.R;

public class HomeFragment extends Fragment {

    AudioManager audioManager;
    ProgressBar ringSeekBar;
    ProgressBar mediaSeekBar;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        LinearLayout addProfile = (LinearLayout)rootView.findViewById(R.id.addprofilelayout);

        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        ringSeekBar = (ProgressBar) rootView.findViewById(R.id.ringingprogress);
        mediaSeekBar = (ProgressBar) rootView.findViewById(R.id.mediaprogress);

        addProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialog();
            }
        });
                /*button.setSize(FloatingActionButton.);
        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.floatingbutton);

        button.setColorNormalResId(R.color.pink);
        button.setColorPressedResId(R.color.pink_pressed);
        button.setIcon(R.drawable.ic_fab_star);
        button.setStrokeVisible(false);*/

        /*View addButton = v.findViewById(R.id.add_button);
        addButton.setOutlineProvider(new ViewOutlineProvider() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void getOutline(View view, Outline outline) {
                int diameter = getResources().getDimensionPixelSize(R.dimen.diameter);
                outline.setOval(0, 0, diameter, diameter);
            }
        });
        addButton.setClipToOutline(true);*/
        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);
    }

    @Override
    public void onResume() {
        super.onResume();

        int ringVol = audioManager.getStreamVolume(AudioManager.STREAM_RING);
        int mediaVol = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        int maxRingVol = audioManager.getStreamMaxVolume(AudioManager.STREAM_RING);
        int maxMediaVol = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        Log.d("HomeFragment", "RingVol: "+ringVol+" Media vol: "+mediaVol+" Max Ring vol: "+maxRingVol+" Max media vol: "+maxMediaVol);

        ringSeekBar.setProgress((ringVol *100)/maxRingVol);
        mediaSeekBar.setProgress((mediaVol * 100) / maxMediaVol);
    }

    @Override
    public void onDetach() {

        super.onDetach();
    }

    public void showDialog() {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(
                getActivity());
        builderSingle.setIcon(android.R.drawable.ic_input_add);
        builderSingle.setTitle("Select Profile");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("Location based");
        arrayAdapter.add("Time based");
        arrayAdapter.add("WiFi based");
        builderSingle.setNegativeButton("cancel",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builderSingle.setAdapter(arrayAdapter,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String strName = arrayAdapter.getItem(which);
                        if (strName.contains("Location")) {
                            Intent intent = new Intent(getActivity(), LocationBasedProfilerActiviy.class);
                            startActivity(intent);
                        } else {
                            AlertDialog.Builder builderInner = new AlertDialog.Builder(
                                    getActivity());
                            builderInner.setMessage(strName);
                            builderInner.setTitle("Profile selected is");
                            builderInner.setPositiveButton("Ok",
                                    new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(
                                                DialogInterface dialog,
                                                int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            builderInner.show();
                        }
                    }
                });
        builderSingle.show();
    }
}

