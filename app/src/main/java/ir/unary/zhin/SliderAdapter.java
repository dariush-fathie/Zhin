package ir.unary.zhin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.ItemHolder> {
    private Context mContext;
    private int count ;

    public SliderAdapter(Context c, int countOfSlide) {
        mContext = c;
        count = countOfSlide;
    }

    @NonNull
    @Override
    public SliderAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.slieder_item, parent, false);
        return new ItemHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderAdapter.ItemHolder holder, int position) {

        switch (position) {
            case 0:
                holder.ivSlide.setImageResource(R.drawable.image1);
                break;
            case 1:
                holder.ivSlide.setImageResource(R.drawable.image2);
                break;
            case 2:
                holder.ivSlide.setImageResource(R.drawable.image3);
                break;
            case 3:
                holder.ivSlide.setImageResource(R.drawable.image4);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return count;
    }

    class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivSlide;

        ItemHolder(View itemView) {
            super(itemView);
            ivSlide = itemView.findViewById(R.id.iv_slider);
            ivSlide.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (getAdapterPosition()) {

            }
        }
    }

}
