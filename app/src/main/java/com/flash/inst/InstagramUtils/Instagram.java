package com.flash.inst.InstagramUtils;

import android.content.Context;

/**
 * Created by fdh on 14.09.15.
 */
public class Instagram {
    private Context mContext;

    private InstagramDialog mDialog;
    private InstagramSession mSession;

    /**
     * Instantiate new object of this class.
     *
     * @param context Context
     */
    public Instagram(Context context) {
        mContext = context;
        String authUrl = Constant.GET_TOKEN_URL;
        mSession = new InstagramSession(context);
        mDialog = new InstagramDialog(context, authUrl);
    }

    public void authorize() {
        mDialog.show();
    }

    /**
     * Reset session.
     */
    public void resetSession() {
        mSession.reset();
        mDialog.clearCache();
    }

    /**
     * Get session.
     *
     * @return Instagram session.
     */
    public InstagramSession getSession() {
        return mSession;
    }
}
