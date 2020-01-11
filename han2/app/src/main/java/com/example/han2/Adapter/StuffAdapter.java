package com.example.han2.Adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class StuffAdapter extends RecyclerView.ItemDecoration {
    // 에디트 텍스트 버그 수정부분
    private static final int[] ATTRS = new int[]{   android.R.attr.listDivider  };
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    private Drawable mDivider;
    private int mOrientation;

    public StuffAdapter(Context context, int orientation){
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
        setOrientation(orientation);
    }

    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent) {

        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }

    }



    public void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView v = new RecyclerView(parent.getContext());
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        if (mOrientation == VERTICAL_LIST) {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        } else {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        }
    }
}

/*
public class StuffAdapter extends RecyclerView.Adapter<StuffAdapter.ViewHolder> implements OnStuffItemClickListener {
    ArrayList<Stuff> arrayList = new ArrayList<>();

    OnStuffItemClickListener listener;

    public StuffAdapter(ArrayList<Stuff> arrayList){
        this.arrayList = arrayList;
    }

    public StuffAdapter() {

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.cok_stuff, parent, false);

        return new ViewHolder(itemView, this);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("main:adapter",""+position);

        Stuff item = arrayList.get(position);
        holder.setitem(item);
    }

    @Override
    public int getItemCount() {   return arrayList.size();   }

    public void addItem(Stuff item){
        arrayList.add(item);
    }

    public Stuff getItem(int position){
        return arrayList.get(position);
    }

    public void setItem(int position, Stuff item){
        arrayList.set(position, item);
    }

    public void removeItem(int item){
        arrayList.remove(item);
    }

    public void allRemoveitem(){
        arrayList.clear();
    }

    public void setItems(ArrayList<Stuff> arrayList){
        this.arrayList = arrayList;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {

    }

    public void OnStuffItemClickListener(OnStuffItemClickListener listener){
       this.listener = listener;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title_et, subtitle_et;

        public ViewHolder(@NonNull View itemView, final OnStuffItemClickListener listener) {
            super(itemView);
            title_et = itemView.findViewById(R.id.title_et);
            subtitle_et = itemView.findViewById(R.id.subtitle_et);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(listener != null){
                        listener.onItemClick(ViewHolder.this,view,position);
                    }
                }
            });
        }

        public void setitem(Stuff item) {
            title_et.setHint(item.getTitle());
            subtitle_et.setHint(item.getSubtitle());
        }
    }

}*/
