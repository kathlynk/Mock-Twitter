package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder>{
    private final String TAG = "TweetsAdapter";

    Context context;
    List<Tweet> tweets;

    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tweet tweet = tweets.get(position);
        holder.bind(tweet);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Tweet> tweets) {
        this.tweets.addAll(tweets);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProfileImage;
        ImageView tvMediaImage;
        TextView tvBody;
        TextView tvScreenName;
        TextView tvTime;
        TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
             tvBody = itemView.findViewById(R.id.tvBody);
             tvScreenName = itemView.findViewById(R.id.tvScreenName);
             tvTime = itemView.findViewById(R.id.tvTime);
             tvName = itemView.findViewById(R.id.tvName);
             tvMediaImage = itemView.findViewById(R.id.tvMediaImage);
        }

        public void bind(Tweet tweet) {
            tvBody.setText(tweet.body);
            tvTime.setText(tweet.formatedDate);
            if (tweet.user.name.length() <= 30) {
                tvScreenName.setText(tweet.user.name);
                tvName.setText("@" + tweet.user.screenName);
            } else if (tweet.user.name.length() < 35) {
                tvScreenName.setText(tweet.user.name);
            } else {
                tvScreenName.setText(tweet.user.name.substring(0, 36) + "...");
            }
            Glide.with(context).load(tweet.user.profileImageUrl).circleCrop().into(ivProfileImage);
            Glide.with(context).load(tweet.mediaImageUrl).
                    transform(new RoundedCornersTransformation(100, 10)).
                    into(tvMediaImage);
        }
    }

}
