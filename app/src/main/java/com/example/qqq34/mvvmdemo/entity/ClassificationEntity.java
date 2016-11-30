package com.example.qqq34.mvvmdemo.entity;

import java.util.List;

/**
 * Created by qqq34 on 2016/11/30.
 */

public class ClassificationEntity {

    /**
     * error : false
     * results : [{"_id":"583c0452421aa9710cf54c47","createdAt":"2016-11-28T18:17:54.556Z","desc":"六种二维码生成的样式","images":["http://img.gank.io/2f0b6c5f-6de7-4ba3-94ad-98bf721ee447"],"publishedAt":"2016-11-29T11:38:58.378Z","source":"web","type":"Android","url":"https://github.com/vivian8725118/ZXingDemo/","used":true,"who":"Vivian"},{"_id":"583c4dc6421aa9710cf54c4a","createdAt":"2016-11-28T23:31:18.761Z","desc":"Java设计模式之单例模式","publishedAt":"2016-11-29T11:38:58.378Z","source":"web","type":"Android","url":"http://www.haotianyi.win/2016/11/java%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F%E4%B9%8B%E5%8D%95%E4%BE%8B%E6%A8%A1%E5%BC%8F.html","used":true,"who":"HaoTianYi"},{"_id":"583ce4dd421aa939befafac7","createdAt":"2016-11-29T10:15:57.829Z","desc":"验证码图片 ImageView，相当实用！","images":["http://img.gank.io/940c9fd7-3c57-4152-b496-271bca9f20ae"],"publishedAt":"2016-11-29T11:38:58.378Z","source":"chrome","type":"Android","url":"https://github.com/jineshfrancs/CaptchaImageView","used":true,"who":"代码家"},{"_id":"583ce5df421aa939bb4637bc","createdAt":"2016-11-29T10:20:15.562Z","desc":"超漂亮的，支持展开菜单的 Fab 按钮。","images":["http://img.gank.io/76f6993b-d103-40e0-8c30-fb1d246e23a0"],"publishedAt":"2016-11-29T11:38:58.378Z","source":"chrome","type":"Android","url":"https://github.com/JoaquimLey/faboptions","used":true,"who":"嗲马甲"},{"_id":"583ce6d8421aa939bb4637bd","createdAt":"2016-11-29T10:24:24.521Z","desc":"类似 Google Inbox 的实现，做的不错","images":["http://img.gank.io/bc5b51d8-2974-4c87-a6ec-8ccc451aea0b"],"publishedAt":"2016-11-29T11:38:58.378Z","source":"chrome","type":"Android","url":"https://github.com/memfis19/Cadar","used":true,"who":"代码家"},{"_id":"583129bf421aa929ac960afc","createdAt":"2016-11-20T12:42:39.884Z","desc":"Android 实现视屏播放器、边播边缓存功能、外加铲屎（IJKPlayer）","images":["http://img.gank.io/8196d110-32cf-41bc-86c6-801af152a743"],"publishedAt":"2016-11-28T11:32:07.534Z","source":"chrome","type":"Android","url":"http://www.jianshu.com/p/9fe377dd9750","used":true,"who":"Jason"},{"_id":"583a2a98421aa91cb7afe7f4","createdAt":"2016-11-27T08:36:40.493Z","desc":"很赞的登录注册布局","images":["http://img.gank.io/dacc7f4c-3872-4c00-b669-3ab13b430e01"],"publishedAt":"2016-11-28T11:32:07.534Z","source":"chrome","type":"Android","url":"https://github.com/irfaan008/OnePageSigninSignup","used":true,"who":"蒋朋"},{"_id":"583b7e97421aa9711460f744","createdAt":"2016-11-28T08:47:19.286Z","desc":"清晰灵活简单易用的应用更新库","images":["http://img.gank.io/9d7deebb-3fa8-43dc-a36c-81a11044b394"],"publishedAt":"2016-11-28T11:32:07.534Z","source":"web","type":"Android","url":"https://github.com/czy1121/update","used":true,"who":"ezy"},{"_id":"583b99d1421aa9710cf54c3e","createdAt":"2016-11-28T10:43:29.756Z","desc":"目测是目前来看做 Blur 效果速度最快的库","images":["http://img.gank.io/f826f969-027d-43d6-bb00-a89684e37346"],"publishedAt":"2016-11-28T11:32:07.534Z","source":"chrome","type":"Android","url":"https://github.com/wonderkiln/blurkit-android","used":true,"who":"嗲马甲"},{"_id":"5836a7fc421aa91cb7afe7e0","createdAt":"2016-11-24T16:42:36.919Z","desc":"支持https的ijkplayer播放器","images":["http://img.gank.io/22aa7a50-de1f-4697-8eb8-7bcc247cce58"],"publishedAt":"2016-11-25T11:29:49.832Z","source":"web","type":"Android","url":"https://github.com/l123456789jy/ijkplayer","used":true,"who":"Lazy"}]
     */

    private List<ClassificationResultsEntity> results;

    public List<ClassificationResultsEntity> getResults() {
        return results;
    }

    public void setResults(List<ClassificationResultsEntity> results) {
        this.results = results;
    }

}
