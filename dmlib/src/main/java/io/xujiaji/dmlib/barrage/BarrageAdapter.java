package io.xujiaji.dmlib.barrage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import io.xujiaji.dmlib.R;
public class BarrageAdapter extends RecyclerView.Adapter<BarrageAdapter.BarrageViewHolder>{
    private Context context;
    private List<BarrageEntity> barrageList;
    public BarrageAdapter(List<BarrageEntity> barrageList) {
        this.barrageList = barrageList;
    }
    @Override
    public BarrageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new BarrageViewHolder(LayoutInflater.from(context).inflate(R.layout.barrage, parent, false));
    }


    @Override
    public void onBindViewHolder(BarrageViewHolder holder, int position) {
        BarrageEntity barrage = barrageList.get(position);
        holder.tvBarrageMsg.setText(barrage.getChatStr());
        holder.tvBarrageName.setText(barrage.getPname());
        Glide.with(context).load(barrage.getPic()).into(holder.imgBarrageHead);
    }

    @Override
    public int getItemCount() {
        return barrageList.size();
    }

    static class BarrageViewHolder extends RecyclerView.ViewHolder {
        TextView tvBarrageName;
        TextView tvBarrageMsg;
        ImageView imgBarrageHead;
        public BarrageViewHolder(View itemView) {
            super(itemView);
            tvBarrageName = (TextView) itemView.findViewById(R.id.tvBarrageName);
            tvBarrageMsg = (TextView) itemView.findViewById(R.id.tvBarrageMsg);
            imgBarrageHead = (ImageView) itemView.findViewById(R.id.imgBarrageHead);
        }
    }
}
