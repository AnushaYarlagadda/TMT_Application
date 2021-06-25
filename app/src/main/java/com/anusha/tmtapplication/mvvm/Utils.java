package com.anusha.tmtapplication.mvvm;

import android.content.Context;
import android.net.ConnectivityManager;

public class Utils {

    //TODO if given more time, might have added internect connection check and have handled UI accordingly
    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }


}
