package com.kcode.wximportment.http;

import com.kcode.wximportment.api.ArticleApi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by caik on 16/5/13.
 */
public class HttpMethods {
    private static final String key = "030b3ed8e1fe93a00539d3c34c482258";

    public static final String BASE_URL = "http://v.juhe.cn/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private ArticleApi api;

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create());

    public HttpMethods() {
        api = createService(ArticleApi.class);
    }

    private <S> S createService(Class<S> serviceCalss) {
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceCalss);
    }

    public static HttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    /**
     * @param pno 获取第几页的内容
     * @param ps  一页获取多少条数据
     */
    public void getArticle(Subscriber subscriber, int pno, int ps) {
        api.getArticle(pno, ps, key)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
