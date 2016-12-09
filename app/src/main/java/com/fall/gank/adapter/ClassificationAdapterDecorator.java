package com.fall.gank.adapter;

import android.util.Log;

import com.fall.gank.callback.LikeClickListener;
import com.fall.gank.database.Collection;
import com.github.markzhai.recyclerview.BR;
import com.github.markzhai.recyclerview.BaseViewAdapter;
import com.github.markzhai.recyclerview.BindingViewHolder;

import java.util.List;

/**
 * Created by qqq34 on 2016/12/8.
 */

public class ClassificationAdapterDecorator implements BaseViewAdapter.Decorator {
    @Override
    public void decorator(BindingViewHolder holder, int position, int viewType) {
        holder.getBinding().setVariable(BR.like, (LikeClickListener) classificationItemViewModel -> {

            if (!classificationItemViewModel.isLike.get()){
                Collection collection = new Collection(classificationItemViewModel.classificationName.get(),classificationItemViewModel.url.get(),classificationItemViewModel.monthAndDay.get(),classificationItemViewModel.year.get(),classificationItemViewModel.dimension.get());
                collection.save();
                classificationItemViewModel.isLike.set(true)  ;
            }else {
                List<Collection> list = Collection.find(Collection.class,"url=?",classificationItemViewModel.url.get());
                if (list!=null&&list.size()>0){
                    Collection collection = list.get(0);
                   classificationItemViewModel.isLike.set(!collection.delete());
                }

            }

        });
    }
}
