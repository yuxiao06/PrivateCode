package com.qiyi.video.readerdemo;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.net.URLEncoder;

import io.reactivex.plugins.RxJavaPlugins;


public class PageControllerImpl implements PageControllerInterface {
    private View container;

    @Override
    public void onCreate(final View view) {
        Toast.makeText(view.getContext(), "hahahhahh", Toast.LENGTH_SHORT).show();

        Log.e("TAG", getClass().getClassLoader().toString());
        Log.e("TAG", RxJavaPlugins.class.getClassLoader().toString());
        container = view;

        //  ImageView imageView = view.findViewById(R.id.image);
        // Drawable drawable = DataUtils.getPluginResources(view.getContext()).getDrawable(R.mipmap.ic_launcher);
        // imageView.setImageDrawable(drawable);
        view.findViewById(R.id.ceshi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setData(Uri.parse("reader-redirect://web?url=https://www.iqiyi.com/common/loginProtocol.html"));
                view.getContext().startActivity(intent);
            }
        });
        //232988739
        view.findViewById(R.id.shujia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String json = "reader-redirect://registerPage?bizParam=" + URLEncoder.encode("{\"biz_params\": {\n" +
                        "\"biz_sub_id\": \"1\",\n" +
                        "\"biz_params\": \"\",\n" +
                        "\"biz_dynamic_params\": \"bookid=232988739\",\n" +
                        "\"biz_extend_params\": \"from_where=13\",\n" +
                        "\"biz_statistics\": \"page_id=0&from_subtype=fx01\"\n" +
                        "}}");
                intent.setData(Uri.parse(json));
                view.getContext().startActivity(intent);
            }
        });

        view.findViewById(R.id.jingxuan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String json = "reader-redirect://registerPage?bizParam=" + URLEncoder.encode("{\"biz_params\": {\n" +
                        "\"biz_sub_id\": \"1\",\n" +
                        "\"biz_params\": \"\",\n" +
                        "\"biz_dynamic_params\": \"bookid=232988739\",\n" +
                        "\"biz_extend_params\": \"from_where=13\",\n" +
                        "\"biz_statistics\": \"page_id=1&from_subtype=fx01\"\n" +
                        "}}");
                intent.setData(Uri.parse(json));
                view.getContext().startActivity(intent);
            }
        });

        view.findViewById(R.id.xiangqign).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String json = "reader-redirect://registerPage?bizParam=" + URLEncoder.encode("{\"biz_params\": {\n" +
                        "\"biz_sub_id\": \"2\",\n" +
                        "\"biz_params\": \"\",\n" +
                        "\"biz_dynamic_params\": \"bookid=232988739\",\n" +
                        "\"biz_extend_params\": \"from_where=13\",\n" +
                        "\"biz_statistics\": \"page_id=1&from_subtype=fx01\"\n" +
                        "}}");
                intent.setData(Uri.parse(json));
                view.getContext().startActivity(intent);
            }
        });

        view.findViewById(R.id.yueduqi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String json = "reader-redirect://registerPage?bizParam=" + URLEncoder.encode("{\"biz_params\": {\n" +
                        "\"biz_sub_id\": \"3\",\n" +
                        "\"biz_params\": \"\",\n" +
                        "\"biz_dynamic_params\": \"bookid=232988739\",\n" +
                        "\"biz_extend_params\": \"from_where=13\",\n" +
                        "\"biz_statistics\": \"page_id=1&from_subtype=fx01\"\n" +
                        "}}");
                intent.setData(Uri.parse(json));
                view.getContext().startActivity(intent);
            }
        });

    }


}