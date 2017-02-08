package com.xiaomai.myproject.picasso.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xiaomai.myproject.R;
import com.xiaomai.myproject.utils.DisplayUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.BlurTransformation;
import jp.wasabeef.picasso.transformations.ColorFilterTransformation;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import jp.wasabeef.picasso.transformations.CropSquareTransformation;
import jp.wasabeef.picasso.transformations.CropTransformation;
import jp.wasabeef.picasso.transformations.GrayscaleTransformation;
import jp.wasabeef.picasso.transformations.MaskTransformation;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;
import jp.wasabeef.picasso.transformations.gpu.BrightnessFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.ContrastFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.InvertFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.KuwaharaFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.PixelationFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.SepiaFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.SketchFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.SwirlFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.ToonFilterTransformation;
import jp.wasabeef.picasso.transformations.gpu.VignetteFilterTransformation;

/**
 * Created by XiaoMai on 2017/2/8.
 */
public class TransformationsAdapter extends BaseAdapter {

    private Context context;

    private List<String> data;

    public TransformationsAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_picasso_transformation, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvPicassoItem.setText("item" + (position + 1));
        int i = Integer.parseInt(data.get(position));
        switch (i) {
            case 1: {
                int width = DisplayUtils.dip2px(context, 133.33f);
                int height = DisplayUtils.dip2px(context, 126.33f);
                Picasso.with(context)
                        .load(R.drawable.ic_item)
                        .resize(width, height)
                        .centerCrop()
                        .transform((new MaskTransformation(context, R.drawable.mask_starfish)))
                        .into(holder.ivPicassoItem);
                break;
            }
            case 2: {
                int width = DisplayUtils.dip2px(context, 150.0f);
                int height = DisplayUtils.dip2px(context, 100.0f);
                Picasso.with(context)
                        .load(R.drawable.ic_item)
                        .resize(width, height)
                        .centerCrop()
                        .transform(new MaskTransformation(context, R.drawable.chat_me_mask))
                        .into(holder.ivPicassoItem);
                break;
            }
            case 3:
                Picasso.with(context)
                        .load(R.drawable.demo)
                        .transform(new CropTransformation(300, 100, CropTransformation.GravityHorizontal.LEFT,
                                CropTransformation.GravityVertical.TOP))
                        .into(holder.ivPicassoItem);
                break;
            case 4:
                Picasso.with(context).load(R.drawable.demo)
                        // 300, 100, CropTransformation.GravityHorizontal.LEFT, CropTransformation.GravityVertical.CENTER))
                        .transform(new CropTransformation(300, 100)).into(holder.ivPicassoItem);
                break;
            case 5:
                Picasso.with(context)
                        .load(R.drawable.demo)
                        .transform(new CropTransformation(300, 100, CropTransformation.GravityHorizontal.LEFT,
                                CropTransformation.GravityVertical.BOTTOM))
                        .into(holder.ivPicassoItem);
                break;
            case 6:
                Picasso.with(context)
                        .load(R.drawable.demo)
                        .transform(new CropTransformation(300, 100, CropTransformation.GravityHorizontal.CENTER,
                                CropTransformation.GravityVertical.TOP))
                        .into(holder.ivPicassoItem);
                break;
            case 7:
                Picasso.with(context)
                        .load(R.drawable.demo)
                        .transform(new CropTransformation(300, 100))
                        .into(holder.ivPicassoItem);
                break;
            case 8:
                Picasso.with(context)
                        .load(R.drawable.demo)
                        .transform(new CropTransformation(300, 100, CropTransformation.GravityHorizontal.CENTER,
                                CropTransformation.GravityVertical.BOTTOM))
                        .into(holder.ivPicassoItem);
                break;
            case 9:
                Picasso.with(context)
                        .load(R.drawable.demo)
                        .transform(new CropTransformation(300, 100, CropTransformation.GravityHorizontal.RIGHT,
                                CropTransformation.GravityVertical.TOP))
                        .into(holder.ivPicassoItem);
                break;
            case 10:
                Picasso.with(context)
                        .load(R.drawable.demo)
                        .transform(new CropTransformation(300, 100, CropTransformation.GravityHorizontal.RIGHT,
                                CropTransformation.GravityVertical.CENTER))
                        .into(holder.ivPicassoItem);
                break;
            case 11:
                Picasso.with(context)
                        .load(R.drawable.demo)
                        .transform(new CropTransformation(300, 100, CropTransformation.GravityHorizontal.RIGHT,
                                CropTransformation.GravityVertical.BOTTOM))
                        .into(holder.ivPicassoItem);
                break;
            case 12:
                Picasso.with(context)
                        .load(R.drawable.demo)
                        .transform(new CropTransformation((float) 16 / (float) 9,
                                CropTransformation.GravityHorizontal.CENTER,
                                CropTransformation.GravityVertical.CENTER))
                        .into(holder.ivPicassoItem);
                break;
            case 13:
                Picasso.with(context)
                        .load(R.drawable.demo)
                        .transform(new CropTransformation((float) 4 / (float) 3,
                                CropTransformation.GravityHorizontal.CENTER,
                                CropTransformation.GravityVertical.CENTER))
                        .into(holder.ivPicassoItem);
                break;
            case 14:
                Picasso.with(context)
                        .load(R.drawable.demo)
                        .transform(new CropTransformation(3, CropTransformation.GravityHorizontal.CENTER,
                                CropTransformation.GravityVertical.CENTER))
                        .into(holder.ivPicassoItem);
                break;
            case 15:
                Picasso.with(context)
                        .load(R.drawable.demo)
                        .transform(new CropTransformation(3, CropTransformation.GravityHorizontal.CENTER,
                                CropTransformation.GravityVertical.TOP))
                        .into(holder.ivPicassoItem);
                break;
            case 16:
                Picasso.with(context)
                        .load(R.drawable.demo)
                        .transform(new CropTransformation(1, CropTransformation.GravityHorizontal.CENTER,
                                CropTransformation.GravityVertical.CENTER))
                        .into(holder.ivPicassoItem);
                break;
            case 17:
                Picasso.with(context)
                        .load(R.drawable.demo)
                        .transform(new CropTransformation((float) 0.5, (float) 0.5,
                                CropTransformation.GravityHorizontal.CENTER,
                                CropTransformation.GravityVertical.CENTER))
                        .into(holder.ivPicassoItem);
                break;
            case 18:
                Picasso.with(context)
                        .load(R.drawable.demo)
                        .transform(new CropTransformation((float) 0.5, (float) 0.5,
                                CropTransformation.GravityHorizontal.CENTER,
                                CropTransformation.GravityVertical.TOP))
                        .into(holder.ivPicassoItem);
                break;
            case 19:
                Picasso.with(context)
                        .load(R.drawable.demo)
                        .transform(new CropTransformation((float) 0.5, (float) 0.5,
                                CropTransformation.GravityHorizontal.RIGHT,
                                CropTransformation.GravityVertical.BOTTOM))
                        .into(holder.ivPicassoItem);
                break;
            case 20:
                Picasso.with(context)
                        .load(R.drawable.demo)
                        .transform(new CropTransformation((float) 0.5, 0, (float) 4 / (float) 3,
                                CropTransformation.GravityHorizontal.CENTER,
                                CropTransformation.GravityVertical.CENTER))
                        .into(holder.ivPicassoItem);
                break;
            case 21:
                Picasso.with(context)
                        .load(R.drawable.demo)
                        .transform(new CropSquareTransformation())
                        .into(holder.ivPicassoItem);
                break;
            case 22:
                Picasso.with(context)
                        .load(R.drawable.demo)
                        .transform(new CropCircleTransformation())
                        .into(holder.ivPicassoItem);
                break;
            case 23:
                Picasso.with(context)
                        .load(R.drawable.demo)
                        .transform(new ColorFilterTransformation(Color.argb(80, 255, 0, 0)))
                        .into(holder.ivPicassoItem);
                break;
            case 24:
                Picasso.with(context)
                        .load(R.drawable.demo)
                        .transform(new GrayscaleTransformation())
                        .into(holder.ivPicassoItem);
                break;
            case 25:
                Picasso.with(context)
                        .load(R.drawable.demo)
                        .transform(new RoundedCornersTransformation(30, 0,
                                RoundedCornersTransformation.CornerType.BOTTOM_LEFT))
                        .into(holder.ivPicassoItem);
                break;
            case 26:
                Picasso.with(context)
                        .load(R.drawable.ic_item)
                        .transform(new BlurTransformation(context, 25, 1))
                        .into(holder.ivPicassoItem);
                break;
            case 27:
                Picasso.with(context)
                        .load(R.drawable.demo)
                        .transform(new ToonFilterTransformation(context))
                        .into(holder.ivPicassoItem);
                break;
            case 28:
                Picasso.with(context)
                        .load(R.drawable.ic_item)
                        .transform(new SepiaFilterTransformation(context))
                        .into(holder.ivPicassoItem);
                break;
            case 29:
                Picasso.with(context)
                        .load(R.drawable.ic_item)
                        .transform(new ContrastFilterTransformation(context, 2.0f))
                        .into(holder.ivPicassoItem);
                break;
            case 30:
                Picasso.with(context)
                        .load(R.drawable.ic_item)
                        .transform(new InvertFilterTransformation(context))
                        .into(holder.ivPicassoItem);
                break;
            case 31:
                Picasso.with(context)
                        .load(R.drawable.ic_item)
                        .transform(new PixelationFilterTransformation(context, 20))
                        .into(holder.ivPicassoItem);
                break;
            case 32:
                Picasso.with(context)
                        .load(R.drawable.ic_item)
                        .transform(new SketchFilterTransformation(context))
                        .into(holder.ivPicassoItem);
                break;
            case 33:
                Picasso.with(context)
                        .load(R.drawable.ic_item)
                        .transform(new SwirlFilterTransformation(context, 0.5f, 1.0f, new PointF(0.5f, 0.5f)))
                        .into(holder.ivPicassoItem);

                break;
            case 34:
                Picasso.with(context)
                        .load(R.drawable.ic_item)
                        .transform(new BrightnessFilterTransformation(context, 0.5f))
                        .into(holder.ivPicassoItem);
                break;
            case 35:
                Picasso.with(context)
                        .load(R.drawable.ic_item)
                        .transform(new KuwaharaFilterTransformation(context, 25))
                        .into(holder.ivPicassoItem);
                break;
            case 36:
                Picasso.with(context)
                        .load(R.drawable.ic_item)
                        .transform(new VignetteFilterTransformation(context, new PointF(0.5f, 0.5f),
                                new float[]{0.0f, 0.0f, 0.0f}, 0f, 0.75f))
                        .into(holder.ivPicassoItem);
                break;
        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_picasso_item)
        ImageView ivPicassoItem;
        @BindView(R.id.tv_picasso_item)
        TextView tvPicassoItem;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
