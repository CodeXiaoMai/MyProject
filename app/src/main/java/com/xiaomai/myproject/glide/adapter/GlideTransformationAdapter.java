
package com.xiaomai.myproject.glide.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.xiaomai.myproject.R;
import com.xiaomai.myproject.utils.DisplayUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.CropSquareTransformation;
import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.MaskTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import jp.wasabeef.glide.transformations.gpu.BrightnessFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ContrastFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.InvertFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.KuwaharaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.PixelationFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SepiaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SketchFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SwirlFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ToonFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation;

/**
 * Created by XiaoMai on 2017/2/10 11:21.</br>
 * compile 'jp.wasabeef:glide-transformations:2.0.1'</br>
 * // If you want to use the GPU Filters compile
 * 'jp.co.cyberagent.android.gpuimage:gpuimage-library:1.4.1'
 */
public class GlideTransformationAdapter
        extends RecyclerView.Adapter<GlideTransformationAdapter.ViewHolder> {

    private Context context;

    private String[] datas;

    public GlideTransformationAdapter(Context context, String[] datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(context, R.layout.item_glide_transformation, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int integer = Integer.parseInt(datas[position]);
        holder.textView.setText("item " + (position + 1));
        switch (integer) {
            case 1: {
                int width = DisplayUtils.dip2px(context, 133.33f);
                int height = DisplayUtils.dip2px(context, 126.33f);
                Glide.with(context).load(R.drawable.ic_item).override(width, height)
                        .bitmapTransform(new CenterCrop(context),
                                new MaskTransformation(context, R.drawable.mask_starfish))
                        .into(holder.imageView);
                break;
            }
            case 2: {
                int width = DisplayUtils.dip2px(context, 150.0f);
                int height = DisplayUtils.dip2px(context, 100.0f);
                Glide.with(context).load(R.drawable.ic_item).override(width, height)
                        .bitmapTransform(new CenterCrop(context),
                                new MaskTransformation(context, R.drawable.mask_chat_right))
                        .into(holder.imageView);
                break;
            }
            case 3:
                Glide.with(context).load(R.drawable.demo).bitmapTransform(
                        new CropTransformation(context, 300, 100, CropTransformation.CropType.TOP))
                        .into(holder.imageView);
                break;
            case 4:
                Glide.with(context).load(R.drawable.demo)
                        .bitmapTransform(new CropTransformation(context, 300, 100))
                        .into(holder.imageView);
                break;
            case 5:
                Glide.with(context).load(R.drawable.demo)
                        .bitmapTransform(new CropTransformation(context, 300, 100,
                                CropTransformation.CropType.BOTTOM))
                        .into(holder.imageView);

                break;
            case 6:
                Glide.with(context).load(R.drawable.demo)
                        .bitmapTransform(new CropSquareTransformation(context))
                        .into(holder.imageView);
                break;
            case 7:
                Glide.with(context).load(R.drawable.demo)
                        .bitmapTransform(new CropCircleTransformation(context))
                        .into(holder.imageView);
                break;
            case 8:
                Glide.with(context).load(R.drawable.demo)
                        .bitmapTransform(
                                new ColorFilterTransformation(context, Color.argb(80, 255, 0, 0)))
                        .into(holder.imageView);
                break;
            case 9:
                Glide.with(context).load(R.drawable.demo)
                        .bitmapTransform(new GrayscaleTransformation(context))
                        .into(holder.imageView);
                break;
            case 10:
                Glide.with(context).load(R.drawable.demo)
                        .bitmapTransform(new RoundedCornersTransformation(context, 30, 0,
                                RoundedCornersTransformation.CornerType.BOTTOM))
                        .into(holder.imageView);
                break;
            case 11:
                Glide.with(context).load(R.drawable.ic_item)
                        .bitmapTransform(new BlurTransformation(context, 25))
                        .into(holder.imageView);
                break;
            case 12:
                Glide.with(context).load(R.drawable.demo)
                        .bitmapTransform(new ToonFilterTransformation(context))
                        .into(holder.imageView);
                break;
            case 13:
                Glide.with(context).load(R.drawable.ic_item)
                        .bitmapTransform(new SepiaFilterTransformation(context))
                        .into(holder.imageView);
                break;
            case 14:
                Glide.with(context).load(R.drawable.ic_item)
                        .bitmapTransform(new ContrastFilterTransformation(context, 2.0f))
                        .into(holder.imageView);
                break;
            case 15:
                Glide.with(context).load(R.drawable.ic_item)
                        .bitmapTransform(new InvertFilterTransformation(context))
                        .into(holder.imageView);
                break;
            case 16:
                Glide.with(context).load(R.drawable.ic_item)
                        .bitmapTransform(new PixelationFilterTransformation(context, 20))
                        .into(holder.imageView);
                break;
            case 17:
                Glide.with(context).load(R.drawable.ic_item)
                        .bitmapTransform(new SketchFilterTransformation(context))
                        .into(holder.imageView);
                break;
            case 18:
                Glide.with(context).load(R.drawable.ic_item).bitmapTransform(
                        new SwirlFilterTransformation(context, 0.5f, 1.0f, new PointF(0.5f, 0.5f)))
                        .into(holder.imageView);
                break;
            case 19:
                Glide.with(context).load(R.drawable.ic_item)
                        .bitmapTransform(new BrightnessFilterTransformation(context, 0.5f))
                        .into(holder.imageView);
                break;
            case 20:
                Glide.with(context).load(R.drawable.ic_item)
                        .bitmapTransform(new KuwaharaFilterTransformation(context, 25))
                        .into(holder.imageView);
                break;
            case 21:
                Glide.with(context).load(R.drawable.ic_item)
                        .bitmapTransform(new VignetteFilterTransformation(context,
                                new PointF(0.5f, 0.5f), new float[] {
                                        0.0f, 0.0f, 0.0f
                                }, 0f, 0.75f)).into(holder.imageView);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_glide_transformation)
        ImageView imageView;

        @BindView(R.id.tv_glide_transformation)
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
