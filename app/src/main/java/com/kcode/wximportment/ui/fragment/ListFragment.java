package com.kcode.wximportment.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.kcode.wximportment.R;
import com.kcode.wximportment.bean.Article;
import com.kcode.wximportment.bean.Result;
import com.kcode.wximportment.bean.ResultData;
import com.kcode.wximportment.http.HttpMethods;
import com.kcode.wximportment.ui.adapter.ArticleAdapter;
import com.kcode.wximportment.ui.avtivity.WebViewActivity;
import com.kcode.wximportment.utils.L;

import rx.Subscriber;

public class ListFragment extends Fragment {

    private static final String TAG = "ListFragment";

    private int pno = 1;        //默认加载第一页
    private int ps = 20;        //默认每页20条数据

    private int firstVisibleItem, visibleItemCount, totalItemCount;

    private Subscriber subscriber;

    private RelativeLayout mLoadMore;
    private ProgressBar mProgressBar;
    private SwipeRefreshLayout mSwipeLayout;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private ArticleAdapter mAdapter;

    private boolean isLoading = false;

    private OnFragmentInteractionListener mListener = new OnFragmentInteractionListener() {
        @Override
        public void onFragmentInteraction(Article article) {
            showDetail(article);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {

        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeLayout);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData(pno, ps,true);
            }
        });

        mLoadMore = (RelativeLayout) view.findViewById(R.id.loadMore);

        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(dy > 0){
                    visibleItemCount = mRecyclerView.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();

                    firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();
                    L.i(TAG, visibleItemCount + " ;" + totalItemCount + ";" + firstVisibleItem);

                    if(!isLoading){

                        if(firstVisibleItem + visibleItemCount >= totalItemCount){
                            L.i(TAG,"到底了");
                        }

                        isLoading = true;

                        mLoadMore.setVisibility(View.VISIBLE);
                        pno ++ ;
                        loadData(pno, ps,false);
                    }


                }
            }
        });

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ArticleAdapter(getActivity(),mListener);
        mRecyclerView.setAdapter(mAdapter);

        loadData(pno, ps,true);
    }

    public void onRefresh(){
        mLayoutManager.scrollToPosition(0);
        mSwipeLayout.setRefreshing(true);
        loadData(1, ps,true);;
    }

    public void loadData(int pno ,int ps,final boolean isRefresh){
        subscriber = new Subscriber<Result<ResultData<Article>>>() {
            @Override
            public void onCompleted() {
                L.i(TAG,"onCompleted");
                loadFinish(true);
            }

            @Override
            public void onError(Throwable e) {
                L.i(TAG,"title : " +e.getMessage());
                loadFinish(false);
            }

            @Override
            public void onNext(Result<ResultData<Article>> resultDataResult) {
                for (Article a : resultDataResult.getResult().getList()) {
                    L.i(TAG,"title : " + a.getTitle());
                }
                if(TextUtils.equals("success",resultDataResult.getReason())){
                    mAdapter.addData(resultDataResult.getResult().getList(),isRefresh);
                }

            }

        };

        HttpMethods.getInstance().getArticle(subscriber,pno,ps);
    }

    private void loadFinish(boolean success){

        isLoading = false;
        mProgressBar.setVisibility(View.GONE);
        if(mSwipeLayout.isRefreshing()){
            mSwipeLayout.setRefreshing(false);
        }
        if(!success){
            Toast.makeText(getActivity().getApplicationContext(),
                    getResources().getString(R.string.load_failed),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void showDetail(Article article){
        Intent intent = new Intent(getActivity(), WebViewActivity.class);
        intent.putExtra("url",article.getUrl());
        startActivity(intent);
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Article article);
    }
}
