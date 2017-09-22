package com.demo.cl.bakingtime.helper;

import android.content.Context;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;

import java.util.HashMap;

/**
 * Created by CL on 9/21/17.
 */

public class PlayerHelper {
    private static final PlayerHelper ourInstance = new PlayerHelper();

    SimpleExoPlayer simpleExoPlayer;

    private HashMap<Integer,Long> currentProgress=new HashMap();


    public static PlayerHelper getInstance() {
        return ourInstance;
    }

    private PlayerHelper() {

    }

    public Object getPlayer(Context context){
        if (simpleExoPlayer == null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector, loadControl);
        }
        return simpleExoPlayer;
    }
    public Object initPlayer(Context context){
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector, loadControl);
            return  simpleExoPlayer;
    }

    public void releasePlayer(){
        if (simpleExoPlayer != null) {
            simpleExoPlayer.release();
            simpleExoPlayer = null;
        }
        currentProgress=new HashMap();
    }

    public void stopPlayer(){
        if (simpleExoPlayer != null) {
            simpleExoPlayer.stop();
        }
    }

    public void putProgress(int position,long ms){
        currentProgress.put(position,ms);
    }

    public Long getProgress(int position){
        return currentProgress.get(position);
    }

    public long getCurrentVideoProgress(){
        if (simpleExoPlayer != null) {
            return simpleExoPlayer.getCurrentPosition();
        } else {
            return 0;
        }
    }
}
