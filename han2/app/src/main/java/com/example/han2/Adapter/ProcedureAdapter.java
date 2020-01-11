package com.example.han2.Adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.han2.Dto.ProcedureDTO;
import com.example.han2.R;
import com.example.han2.RecWrite.OnproceItemClickListener;

import java.io.File;
import java.util.ArrayList;

/*public class ProcedureAdapter extends RecyclerView.Adapter<ProcedureAdapter.ViewHolder> implements OnproceItemClickListener {*/
public class ProcedureAdapter extends RecyclerView.ItemDecoration {

    ArrayList<ProcedureDTO> items = new ArrayList<>();
    OnproceItemClickListener listener;

    private File imagesFile;

    public ProcedureAdapter(){}

    public ProcedureAdapter(File imagesFile) {
        this.imagesFile = imagesFile;
    }

    // 에디트 텍스트 버그 수정부분
    private static final int[] ATTRS = new int[]{   android.R.attr.listDivider  };
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    private Drawable mDivider;
    private int mOrientation;

    public ProcedureAdapter(Context context, int orientation){
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView pro_tv;
        EditText pro_et;
        ImageView pro_iv;

        public ViewHolder(@NonNull View itemView, final OnproceItemClickListener listener) {
            super(itemView);
            pro_tv = itemView.findViewById(R.id.pro_tv);
            pro_et = itemView.findViewById(R.id.pro_et);
            pro_iv = itemView.findViewById(R.id.pro_iv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null) {
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });
        }

    }





    //여기까지


    /*@NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.cok_procedure, parent, false);

        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("main:adapter",""+position);

        ProcedureDTO item = items.get(position);
        holder.setitem(item);
    }
    public void removeItem(int item){
        items.remove(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(ProcedureDTO item){
        items.add(item);
    }

    public void allRemoveitem(){
        items.clear();
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {

    }



    public void setOnItemClickLstner(OnproceItemClickListener listener){
        this.listener = listener;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView cok_procedure_proc_tv;
        EditText cok_procedure_sub_et;
        ImageView cok_procedure_img_iv;

        public ViewHolder(@NonNull View itemView, final OnproceItemClickListener listener) {
            super(itemView);
            cok_procedure_proc_tv = itemView.findViewById(R.id.cok_procedure_proc_tv);
            cok_procedure_sub_et = itemView.findViewById(R.id.cok_procedure_sub_et);
            cok_procedure_img_iv = itemView.findViewById(R.id.cok_procedure_img_iv);

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

        public void setitem(ProcedureDTO item) {
            cok_procedure_proc_tv.setText(item.getCok_procedure_proc());
            cok_procedure_sub_et.setHint(item.getCok_procedure_sub());
            cok_procedure_img_iv.setBackgroundResource(item.getCok_procedure_img());
        }
    }*/
}