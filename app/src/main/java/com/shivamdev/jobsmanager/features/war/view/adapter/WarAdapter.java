package com.shivamdev.jobsmanager.features.war.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.shivamdev.jobsmanager.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shivam on 13/3/17.
 */

public class WarAdapter extends RecyclerView.Adapter<WarAdapter.WarHolder> {

    private List<String> warList = new ArrayList<>();

    public WarAdapter(List<String> warList) {
        this.warList = warList;
    }

    @Override
    public WarHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_war, parent, false);
        return new WarHolder(view);
    }

    @Override
    public void onBindViewHolder(WarHolder holder, int position) {
        holder.etWarTitle.clearFocus();
        if (warList != null && warList.size() > 0) {
            holder.etWarTitle.setText(warList.get(position));
            holder.etWarTitle.setEnabled(false);
        } else {
            if (position == 2) {
                holder.etWarTitle.setText("Me");
                holder.etWarTitle.setEnabled(false);
            }
        }


    }

    @Override
    public int getItemCount() {
        return warList.size() > 0 ? warList.size() : 5;
    }

    public class WarHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.et_war_title)
        public EditText etWarTitle;

        WarHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}