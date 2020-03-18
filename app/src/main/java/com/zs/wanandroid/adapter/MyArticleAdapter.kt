package com.zs.wanandroid.adapter

import android.text.Html
import android.text.TextUtils
import android.widget.FrameLayout
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.zs_wan_android.R
import com.zs.wanandroid.entity.ArticleEntity
import com.zs.wanandroid.utils.ColorUtils
import com.zs.wanandroid.weight.OnLimitClickHelper
import com.zs.wanandroid.weight.OnLimitClickListener

/**
 * 我的文章适配器
 * @author zs
 * @data 2020-03-17
 */
class MyArticleAdapter(layoutId:Int):BaseQuickAdapter<ArticleEntity.DatasBean,BaseViewHolder>(layoutId) {

    private var onDeleteClickListener:OnDeleteClickListener? = null
    fun setonDeleteClickListener(onDeleteClickListener:OnDeleteClickListener){
        this.onDeleteClickListener = onDeleteClickListener
    }
    override fun convert(helper: BaseViewHolder, item: ArticleEntity.DatasBean?) {
        item?.run {
            helper.setText(R.id.tvAuthor,if (!TextUtils.isEmpty(author))author else shareUser)
            helper.setText(R.id.tvDate,niceDate)
            helper.setText(R.id.tvTitle, Html.fromHtml(title))
            helper.setText(R.id.tvChapterName,superChapterName)
            helper.getView<FrameLayout>(R.id.flDelete).setOnClickListener(OnLimitClickHelper(OnLimitClickListener {
                onDeleteClickListener?.onDeleteClick(helper, helper.adapterPosition)
            }))
        }
    }

    /**
     * 单个删除
     */
    fun delete(position:Int){
        data.removeAt(position)
        notifyItemRemoved(position)
    }
}