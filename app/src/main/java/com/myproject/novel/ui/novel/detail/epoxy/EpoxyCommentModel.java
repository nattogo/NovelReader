package com.myproject.novel.ui.novel.detail.epoxy;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.myproject.novel.R;
import com.myproject.novel.local.util.CommonUtils;
import com.myproject.novel.local.util.GlideApp;
import com.myproject.novel.model.CommentModel;

import de.hdodenhof.circleimageview.CircleImageView;

@EpoxyModelClass
public abstract class EpoxyCommentModel extends EpoxyModelWithHolder<EpoxyCommentModel.EpoxyCommentHolder> {


    @Override
    protected int getDefaultLayout() {
        return R.layout.comment_item;
    }

    @EpoxyAttribute
    public CommentModel commentModel;

    @EpoxyAttribute
    public View.OnClickListener clickListener;

    public EpoxyCommentModel(CommentModel commentModel, View.OnClickListener clickListener) {
        this.clickListener = clickListener;
        this.commentModel = commentModel;

    }


    @Override
    public void bind(@NonNull EpoxyCommentHolder holder) {
        super.bind(holder);
        GlideApp.with(holder.userAvatar.getContext()).asBitmap().load(commentModel.getAvatar())
                .placeholder(CommonUtils.shimmerEffect())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                        if (bitmap != null) {
                            holder.userAvatar.setImageBitmap(bitmap);
                        }
                    }

                    @Override
                    public void onLoadCleared(Drawable placeholder) {
                    }
                });
        holder.username.setText(commentModel.getNickName());
        holder.content.setText(commentModel.getContent());
        if (commentModel.getCreatedAt() != null) {
            holder.createdAt.setText(commentModel.getCreatedAt());
        }

        holder.likeComment.setOnClickListener(v -> holder.likeComment.setImageResource(R.drawable.liked));

    }

    static class EpoxyCommentHolder extends EpoxyHolder {

        public CircleImageView userAvatar;
        public TextView username, createdAt, content;
        public ImageView likeComment;

        @Override
        protected void bindView(@NonNull View itemView) {
            userAvatar = itemView.findViewById(R.id.user_avatar);
            username = itemView.findViewById(R.id.username);
            createdAt = itemView.findViewById(R.id.created_at);
            content = itemView.findViewById(R.id.content);
            likeComment = itemView.findViewById(R.id.like_comment);
        }

    }

}
