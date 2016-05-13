package com.kcode.wximportment.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kcode.wximportment.R;
import com.kcode.wximportment.bean.Article;
import com.kcode.wximportment.bean.Result;
import com.kcode.wximportment.bean.ResultData;
import com.kcode.wximportment.http.HttpMethods;
import com.kcode.wximportment.ui.adapter.ArticleAdapter;

import rx.Subscriber;

public class ListFragment extends Fragment {

    private static final String TAG = "ListFragment";

    private Subscriber subscriber;

    private RecyclerView recyclerView;
    private ArticleAdapter adapter;

    private OnFragmentInteractionListener mListener = new OnFragmentInteractionListener() {
        @Override
        public void onFragmentInteraction(Uri uri) {

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new ArticleAdapter(getActivity());
        recyclerView.setAdapter(adapter);

        loadData(0,10);
    }

    private void loadData(int pno ,int ps){
        subscriber = new Subscriber<Result<ResultData<Article>>>() {
            @Override
            public void onCompleted() {
                Log.i(TAG,"onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG,"title : " +e.getMessage());
            }

            @Override
            public void onNext(Result<ResultData<Article>> resultDataResult) {
                for (Article a : resultDataResult.getResult().getList()) {
                    Log.i(TAG,"title : " + a.getTitle());
                }
                if(TextUtils.equals("success",resultDataResult.getReason())){
                    adapter.addData(resultDataResult.getResult().getList());
                }

            }

        };

        HttpMethods.getInstance().getArticle(subscriber,pno,ps);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
