package com.cqg.Factory;

import android.content.Context;

import com.cqg.base.IBatchable;
import com.cqg.base.action.ContactsBatch;

/**
 * Created by chen on 2016/9/22.
 */
public class BatchFactory {
    public static final int CONTACTS = 1;
    public static final int SMS = 2;

    public static IBatchable newInstance(Context context, int type) {
        IBatchable batchable = null;
        switch (type) {
            case CONTACTS:
                batchable = new ContactsBatch(context);
                break;
            case SMS:
                // TODO: 2016/9/22
                break;
        }
        return batchable;
    }


}
