package com.challenge.larionbabych.codingchallenge;

import com.challenge.larionbabych.codingchallenge.di.Config;
import com.challenge.larionbabych.codingchallenge.di.InjectableApp;

public class CodingApp extends InjectableApp implements Config.ShowLog {

    //https://github.com/datamindedsolutions/coding-challenge/blob/master/mobile_coding_challenge.md

    @Override
    public boolean showLogs() {
        return true;
    }

}
