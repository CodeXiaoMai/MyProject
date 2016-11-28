
package com.xiaomai.myproject.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

/**
 * 这是拦截短信的Receiver<br>
 * Created by XiaoMai on 2016/11/28 13:58.
 */
public class MessageReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        // 提取短信消息数组，每一个pdu代表一条消息
        Object[] pdus = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[pdus.length];
        for (int i = 0; i < messages.length; i++) {
            messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
        }
        // 获取发送方号码
        String address = messages[0].getOriginatingAddress();
        String fullMessage = "";
        for (SmsMessage message : messages) {
            // 获取短信内容
            fullMessage += message.getMessageBody();
        }
    }
}
