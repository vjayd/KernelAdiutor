/*
 * Copyright (C) 2015-2016 Willi Ye <williye97@gmail.com>
 *
 * This file is part of Kernel Adiutor.
 *
 * Kernel Adiutor is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Kernel Adiutor is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Kernel Adiutor.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.grarak.kerneladiutor.modified;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.grarak.kerneladiutor.R;
import com.grarak.kerneladiutor.fragments.RecyclerViewFragment;
import com.grarak.kerneladiutor.modified.C;
import com.grarak.kerneladiutor.modified.ServiceReader;
import com.grarak.kerneladiutor.views.recyclerview.DescriptionView;
import com.grarak.kerneladiutor.views.recyclerview.RecyclerViewItem;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by willi on 25.07.16.
 */
public class Cpustats extends RecyclerViewFragment {


    private Button mLButtonRecord;
    //private Resources res;
    private ServiceReader mSR;
    private List<Map<String, Object>> mListSelected;
    private Intent tempIntent;


    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @SuppressLint("NewApi")
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            mSR = ((ServiceReader.ServiceReaderDataBinder) service).getService();

            //setIconRecording();
            if (tempIntent != null) {
                tempIntent.putExtra(C.screenRotated, true);
                onActivityResult(1, 1, tempIntent);
                tempIntent = null;
            } else onActivityResult(1, 1, null);


        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };



    private BroadcastReceiver receiverSetIconRecord = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //setIconRecording();
        }
    }, receiverDeadProcess = new BroadcastReceiver() {
        @SuppressWarnings("unchecked")
        @Override
        public void onReceive(Context context, Intent intent) {
            //switchParameterForProcess((Map<String, Object>) intent.getSerializableExtra(C.process));
        }
    }, receiverFinish = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
           // finish();
        }
    };


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Intent intent = new Intent(getActivity(), ActivityMain.class);
        startActivity(intent);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void init() {
        super.init();





    }


    @Override
    public void onStart() {
        super.onStart();
         }


    @Override
    protected boolean showViewPager() {
        return false;
    }

    @Override
    protected void addItems(List<RecyclerViewItem> items) {

    }
}
