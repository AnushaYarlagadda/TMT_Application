package com.anusha.tmtapplication.mvvm.ui.main;

import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.anusha.tmtapplication.R;
import com.anusha.tmtapplication.mvvm.data.model.Attributes;
import com.anusha.tmtapplication.mvvm.data.model.Card;
import com.anusha.tmtapplication.mvvm.data.model.Card2;
import com.anusha.tmtapplication.mvvm.data.model.Size;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    public interface CardListener {
        void onCardClicked(Card card);
    }

    private List<Card> items;
    private final CardListener listener;

    public CardAdapter(CardListener listener) {
        this.listener = listener;
        items = new ArrayList<>();
    }

    public void setItems(List<Card> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private Card getItem(int position) {
        return items.get(position);
    }

    public class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView image;
        TextView title;
        TextView desc;
        LinearLayout detailsLL;

        CardViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
            detailsLL = itemView.findViewById(R.id.details_ll);
        }

        void bind(int position) {
            //TODO given more time I would prefer to have multiple items views with multiple viewholders. As this view is short, I have handled this way
            Card card = getItem(position);
            Card2 card2 = card.card2;
            setClickListener(card);
            if("text".equalsIgnoreCase(card.card_type)){
                setTitle(card2.value);
                setViewStyle(title, card2.attributes);
            }else if("title_description".equalsIgnoreCase(card.card_type)){
                setTitle(card2.title.value);
                setViewStyle(title, card2.title.attributes);
                setDescription(card2.description.value);
                setViewStyle(desc, card2.description.attributes);
            }else if("image_title_description".equalsIgnoreCase(card.card_type)){
                setTitle(card2.title.value);
                setViewStyle(title, card2.title.attributes);
                setDescription(card2.description.value);
                setViewStyle(desc, card2.description.attributes);
                setImage(card2.image.url);
                setImageStyle(this.image, card2.image.size);
            }
        }

        private void setImageStyle(ImageView imageView, Size size){
            imageView.getLayoutParams().width = size.width;
            imageView.getLayoutParams().height = size.height;
            imageView.requestLayout();
            detailsLL.setGravity(Gravity.BOTTOM);
        }

        private void setViewStyle(TextView textView, Attributes attributes){
            textView.setTextColor(Color.parseColor(attributes.text_color));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,attributes.font.size);
        }

        private void setTitle(String title) {
            this.title.setText(title);
            this.title.setVisibility(View.VISIBLE);
        }

        private void setImage(String imageUrl) {
            Glide.with(itemView.getContext())
                    .load(imageUrl)
                    .into(image);
            this.image.setVisibility(View.VISIBLE);
        }

        private void setDescription(String description) {
            desc.setText(description);
            this.desc.setVisibility(View.VISIBLE);
        }

        private void setClickListener(Card card) {
            itemView.setTag(card);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onCardClicked((Card) view.getTag());
        }
    }
}
