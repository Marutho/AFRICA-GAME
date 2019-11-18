package com.mygdx.theafrica;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {

    //FOR MUSIC THEME
    public static Music principalTheme = Gdx.audio.newMusic(Gdx.files.internal("Africa.mp3"));
    public static Sound actionDone = Gdx.audio.newSound(Gdx.files.internal("ActionDone.mp3"));
    public static Sound accelProcess = Gdx.audio.newSound(Gdx.files.internal("accelSound.mp3"));
    public static Sound endTurn = Gdx.audio.newSound(Gdx.files.internal("endTurn.mp3"));
    public static Sound resourceObtained = Gdx.audio.newSound(Gdx.files.internal("resourceObtained.mp3"));
    public static Sound buildDone = Gdx.audio.newSound(Gdx.files.internal("buildDone.mp3"));
    public static Sound walk = Gdx.audio.newSound(Gdx.files.internal("walkSound.mp3"));

    //FOR SIMPLE SOUNDS
    //public static Sound Shoot = Gdx.audio.newSound(Gdx.files.internal(""));



    public static void reproduceSounds(int soundID){
        switch (soundID){
            case 1:
                actionDone.play();
                break;
            case 2:
                accelProcess.play();
                break;
            case 3:
                endTurn.play();
                break;
            case 4:
                resourceObtained.setVolume(4,0.5f);
                resourceObtained.play();
                break;
            case 5:
                buildDone.play();
                break;
            case 6:
                walk.play();
                break;
            default:
                break;

        }
    }


    public static void reproduceMusic(){
       principalTheme.play();
       principalTheme.setLooping(true);
    }
}
