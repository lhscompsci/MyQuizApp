package com.sbtech.myquizapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class HighScoresAdapter extends RecyclerView.Adapter<HighScoresAdapter.MyViewHolder> {

    private List<Player> highScoreList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, score;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.highname);
            score = (TextView) view.findViewById(R.id.highscore);
        }
    }

    public HighScoresAdapter(List<Player> highScoreList) {
        this.highScoreList = highScoreList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.high_score_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Player player = highScoreList.get(position);
        holder.name.setText(player.getName());
        holder.score.setText(player.getScore());
    }

    @Override
    public int getItemCount() {
        return highScoreList.size();
    }
}