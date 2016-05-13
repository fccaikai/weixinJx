package com.kcode.wximportment.api;

import com.kcode.wximportment.bean.Article;
import com.kcode.wximportment.bean.Result;
import com.kcode.wximportment.bean.ResultData;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by caik on 16/5/12.
 */
public interface ArticleApi {

    @GET("weixin/query")
    Observable<Result<ResultData<Article>>> getArticle(@Query("pno") int pno,
                                                       @Query("ps") int ps,
                                                       @Query("key") String key);
}
