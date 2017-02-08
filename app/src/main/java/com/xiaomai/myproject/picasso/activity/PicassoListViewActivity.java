package com.xiaomai.myproject.picasso.activity;

import android.widget.ListView;

import com.xiaomai.myproject.R;
import com.xiaomai.myproject.base.BaseActivity;
import com.xiaomai.myproject.picasso.adapter.PicassoListViewAdapter;

import butterknife.BindView;

/**
 * Created by XiaoMai on 2017/2/8.
 */
public class PicassoListViewActivity extends BaseActivity {
    @BindView(R.id.lv_picasso_listview)
    ListView lvPicassoListview;

    private String[] images = new String[]{
            "http://www.sinaimg.cn/qc/photo_auto/photo/84/35/39698435/39698435_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/44/23/39674423/39674423_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/74/19/39657419/39657419_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/34/09/39653409/39653409_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/53/97/39645397/39645397_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/93/94/39629394/39629394_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/63/79/39616379/39616379_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/53/77/39615377/39615377_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/43/70/39594370/39594370_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/93/58/39579358/39579358_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/23/45/39572345/39572345_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/13/41/39571341/39571341_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/03/29/39550329/39550329_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/53/27/39525327/39525327_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/93/10/39399310/39399310_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/93/06/39389306/39389306_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/92/90/39329290/39329290_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/62/88/39326288/39326288_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/52/86/39325286/39325286_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/72/69/39277269/39277269_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/62/67/39276267/39276267_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/82/78/39308278/39308278_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/32/60/39273260/39273260_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/22/59/39272259/39272259_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/12/56/39271256/39271256_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/02/55/39270255/39270255_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/72/49/39267249/39267249_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/42/43/39254243/39254243_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/42/43/39254243/39254243_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/92/31/39239231/39239231_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/82/29/39238229/39238229_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/72/27/39237227/39237227_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/62/24/39226224/39226224_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/72/10/39197210/39197210_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/42/06/39194206/39194206_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/82/00/39188200/39188200_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/61/98/39186198/39186198_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/31/96/39183196/39183196_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/82/10/39198210/39198210_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/61/83/39176183/39176183_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/12/12/39201212/39201212_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/91/87/39179187/39179187_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/11/69/39171169/39171169_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/91/66/39169166/39169166_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/61/62/39166162/39166162_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/51/60/39165160/39165160_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/81/52/39158152/39158152_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/71/27/39127127/39127127_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/10/98/39111098/39111098_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/10/88/39081088/39081088_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/10/84/39071084/39071084_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/10/84/39071084/39071084_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/50/72/39045072/39045072_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/20/67/39042067/39042067_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/10/65/39041065/39041065_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/90/58/39039058/39039058_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/60/56/39036056/39036056_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/30/32/38943032/38943032_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/70/26/38907026/38907026_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/50/20/38855020/38855020_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/70/02/38847002/38847002_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/39/95/38843995/38843995_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/09/89/38840989/38840989_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/62/08/39196208/39196208_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/28/50/38752850/38752850_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/28/39/38732839/38732839_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/18/37/38731837/38731837_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/38/39/38733839/38733839_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/47/31/38634731/38634731_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/85/55/38468555/38468555_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/45/53/38464553/38464553_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/05/49/38450549/38450549_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/45/45/38424545/38424545_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/65/32/38396532/38396532_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/45/30/38384530/38384530_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/14/72/38381472/38381472_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/95/20/38379520/38379520_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/95/05/38349505/38349505_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/25/02/38342502/38342502_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/94/98/38339498/38339498_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/84/96/38338496/38338496_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/74/94/38337494/38337494_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/24/56/38302456/38302456_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/03/88/38270388/38270388_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/63/62/38256362/38256362_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/21/69/39172169/39172169_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/83/57/38248357/38248357_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/73/55/38247355/38247355_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/23/44/38242344/38242344_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/43/36/38224336/38224336_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/33/33/38223333/38223333_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/13/28/38221328/38221328_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/43/26/38214326/38214326_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/43/19/38204319/38204319_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/03/04/38200304/38200304_140.jpg",
            "http://www.sinaimg.cn/qc/photo_auto/photo/42/56/38134256/38134256_140.jpg"
    };

    @Override
    protected int getContentLayout() {
        return R.layout.activity_picasso_listview;
    }

    @Override
    protected void initViews() {
        super.initViews();
        setTitle("Picasso在ListView中使用");
        lvPicassoListview.setAdapter(new PicassoListViewAdapter(this, images));
    }
}
