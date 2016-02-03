package com.example.tmnt.test4;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.IOException;
import java.util.List;

/**
 * Created by tmnt on 2016/2/1.
 */
public class CrimeCameraFragment extends Fragment {
    private Camera camera;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.crime_camera_fragment, null, false);
        Button take = (Button) view.findViewById(R.id.takePhoto);
        take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        SurfaceView show = (SurfaceView) view.findViewById(R.id.showPhoto);
        SurfaceHolder holder = show.getHolder();
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if (camera != null) {
                    try {
                        camera.setPreviewDisplay(holder);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                if (camera == null) return;
                ;
                Camera.Parameters parameters = camera.getParameters();
                Camera.Size size = null;
                parameters.setPreviewSize(size.width, size.height);
                camera.setParameters(parameters);
                camera.startPreview();

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if (camera != null) {
                    camera.stopPreview();
                }
            }
        });
        return view;
    }

    private Camera.Size getBestSupportSize(List<Camera.Size> sizes, int width, int height) {
        Camera.Size best = sizes.get(0);
        int area = best.width * best.height;
        for (Camera.Size size : sizes) {
            int ss = size.width * size.height;
            if (ss > area) {
                area = ss;
            }
        }
        return best;
    }


    @Override
    public void onResume() {
        super.onResume();
        camera = Camera.open(0);
    }


    @Override
    public void onPause() {
        super.onPause();
        if (camera != null) {
            camera.release();
        }
    }
}
