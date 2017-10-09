package com.xiaomai.myproject.edittext;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.xiaomai.myproject.R;

/**
 * Created by XiaoMai on 2017/8/23.
 */

public class EditActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        final EditText editText = (EditText) findViewById(R.id.edit_text);
        editText.setImeOptions(EditorInfo.IME_FLAG_NO_FULLSCREEN);
        findViewById(R.id.bt_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("content", editText.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
