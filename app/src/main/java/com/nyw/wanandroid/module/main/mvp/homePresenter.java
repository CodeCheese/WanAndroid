package com.nyw.wanandroid.module.main.mvp;

import android.content.Context;

import com.bakerj.rxretrohttp.RxRetroHttp;
import com.blankj.utilcode.util.ToastUtils;
import com.nyw.domain.common.loadmore.PageLoadMoreResponse;
import com.nyw.domain.domain.bean.request.home.HomeReq;
import com.nyw.domain.domain.bean.response.home.ArticleBean;
import com.nyw.domain.domain.bean.response.home.BannerBean;
import com.nyw.libproject.common.api.CBApiObserver;
import com.nyw.wanandroid.module.main.data.repository.ImainRepository;
import com.nyw.wanandroid.module.main.data.repository.mainRepositoryImpl;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author nyw
 * @date 2019/06/27
 *
 * Generated by MVPGenerator
 */
public class homePresenter extends homeContract.Presenter{
    private ImainRepository mRepository = new mainRepositoryImpl();

    public homePresenter(homeContract.View view) {
        super(view);
    }

    @Override
    protected HomeReq getQuestBody() {
        HomeReq req = new HomeReq();
        return req;
    }

    @Override
    protected ArticleBean castDataToDest(ArticleBean articleBean) {
        return articleBean;
    }

    @Override
    protected Observable<List<ArticleBean>> getRefreshLoadObservable(HomeReq body) {
        return mRepository.getArticleBean(body).map(PageLoadMoreResponse::getDatas);
    }


    @Override
    public void getBanner() {
        RxRetroHttp.composeRequest(mRepository.getBanner(), mView)
                .subscribe(new CBApiObserver<List<BannerBean>>() {
                    @Override
                    protected void success(List<BannerBean> data) {
                        ((homeContract.View) mView).BannerBeanGet(data);
                    }
                });
    }


}
