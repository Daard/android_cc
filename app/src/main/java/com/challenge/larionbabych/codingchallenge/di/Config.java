package com.challenge.larionbabych.codingchallenge.di;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.challenge.larionbabych.codingchallenge.a_details.DetailActivity;
import com.challenge.larionbabych.codingchallenge.a_details.DetailFragment;
import com.challenge.larionbabych.codingchallenge.a_details.DetailViewModel;
import com.challenge.larionbabych.codingchallenge.a_main.MainActivity;
import com.challenge.larionbabych.codingchallenge.a_main.PlacesFragment;
import com.challenge.larionbabych.codingchallenge.a_main.PlacesViewModel;
import com.challenge.larionbabych.codingchallenge.api.Api;
import com.challenge.larionbabych.codingchallenge.api.JacksonConverterFactory;
import com.challenge.larionbabych.codingchallenge.utils.LogUtil;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.inject.Singleton;
import dagger.Binds;
import dagger.MapKey;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Module(includes = {
        Config.ActivitiesModule.class,
        Config.ViewModelModule.class,
        Config.ApiModule.class,
        Config.LogModule.class
})
public interface Config {

    @Module
    abstract class ActivitiesModule {

        @ContributesAndroidInjector(modules = FragmentsModule.class)
        abstract MainActivity contributeMainActivity();

        @ContributesAndroidInjector(modules = FragmentsModule.class)
        abstract DetailActivity contributeDetailActivity();

        @Module
        abstract class FragmentsModule {

            @ContributesAndroidInjector
            abstract PlacesFragment contributePlacesFragment();

            @ContributesAndroidInjector
            abstract DetailFragment contributeDetailFragment();

        }

    }

    @Module
    class LogModule {

        @Singleton
        @Provides
        LogUtil provideLogUtil(Application app) {
            boolean show = false;
            if (app instanceof ShowLog) {
                show = ((ShowLog) app).showLogs();
            }
            return new LogUtil(show);
        }

    }

    @Module()
    abstract class ViewModelModule {

        @Binds
        @IntoMap
        @ViewModelKey(PlacesViewModel.class)
        abstract ViewModel bindPlacesViewModel(PlacesViewModel viewModel);

        @Binds
        @IntoMap
        @ViewModelKey(DetailViewModel.class)
        abstract ViewModel bindDetailViewModel(DetailViewModel viewModel);

        @Binds
        abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    }

    @Documented
    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @MapKey
    @interface ViewModelKey {
        Class<? extends ViewModel> value();
    }

    @Module
    class ApiModule {

        @Singleton
        @Provides
        public Api provideApi(Application app) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            if (app instanceof ShowLog && ((ShowLog) app).showLogs()) {
                LogUtil util = new LogUtil(true);
                builder.interceptors().add(chain -> {
                    util.log(LogUtil.type.d, "request message:", chain.request().toString());
                    return chain.proceed(chain.request());
                });
            }
            return new Retrofit.Builder().
                    baseUrl(Api.BASE_URL).client(builder.build()).
                    addConverterFactory(JacksonConverterFactory.create()).
                    build().create(Api.class);
        }

    }

    interface ShowLog {
        boolean showLogs();
    }

}
