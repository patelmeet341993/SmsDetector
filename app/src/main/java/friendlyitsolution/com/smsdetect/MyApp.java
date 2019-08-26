package friendlyitsolution.com.smsdetect;

import android.app.Application;
import android.content.Context;

/**
 * Created by Meet on 26-08-2017.
 */

public class MyApp extends Application
{

    static Context con;

    @Override
    public void onCreate() {
        super.onCreate();

        con=getBaseContext();

//        Toast.makeText(con,"Sms Recv",Toast.LENGTH_LONG).show();

    }
}
