package com.example.lakmal.coffer.UI;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lakmal.coffer.R;

public class DetailActivity extends AppCompatActivity {

    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_QUOTE = "EXTRA_QUOTE";
    private static final String EXTRA_ATTR = "EXTRA_ATTR";
    private static final String EXTRA_IMAGE = "EXTRA_IMAGE";
    private static final String EXTRA_OFF_PERCENT = "EXTRA_OFF_PERCENT";
    private static final String EXTRA_DETAIL = "EXTRA_DETAIL";

    int width;
    int height;

    CountDownTimer c;
    AlertDialog.Builder alertDialog;

    @Override
    protected void onDestroy() {
        c.cancel();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getBundleExtra(BUNDLE_EXTRAS);

        Log.d("janitha", "paased image id:" + String.valueOf(extras.getInt(EXTRA_IMAGE)));
        Log.d("janitha", "paased title: " + extras.getString(EXTRA_QUOTE));
        Log.d("janitha", "paased off percent: " + extras.getString(EXTRA_OFF_PERCENT));
        Log.d("janitha", "paased description: " + extras.getString(EXTRA_DETAIL));

        ((TextView) findViewById(R.id.lbl_quote_text)).setText(extras.getString(EXTRA_QUOTE));
        ((TextView) findViewById(R.id.lbl_quote_attribution)).setText(extras.getString(EXTRA_ATTR));
        ((ImageView) findViewById(R.id.im_item_detail)).setImageResource(extras.getInt(EXTRA_IMAGE));
        ((TextView) findViewById(R.id.lbl_item_detail)).setText(extras.getString(EXTRA_DETAIL));
        ((TextView) findViewById(R.id.lbl_off_percent)).setText(extras.getString(EXTRA_OFF_PERCENT));

        //get the user name from the signin pasge if there isno user name it implies thart the user has taken a tour
        SharedPreferences sharedPref = getSharedPreferences("userinfo", Context.MODE_PRIVATE);

        String name = sharedPref.getString("username", "");

        Log.d("janitha", "trying" + name.length());

        //alert dialog for the tour end
        alertDialog = new AlertDialog.Builder(DetailActivity.this);


        if (name.length() == 0) {
            c = new CountDownTimer(2000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    alertDialog.setTitle("Make You Own Feed");
                    alertDialog.setMessage("Make most out of Coffer, customize Offer fed by adding you preffered banks and card. Yes you can do it,and it is very easy");
                    displayAlert("tour meaasge");
                }
            };
            c.start();

//            new CountDownTimer(2000, 1000) {
//
//                public void onTick(long millisUntilFinished) {
//
//
//                }
//
//                public void onFinish() {
//                    alertDialog.setTitle("Make You Own Feed");
//                    alertDialog.setMessage("Make most out of Coffer, customize Offer fed by adding you preffered banks and card. Yes you can do it,and it is very easy");
//                    displayAlert("tour meaasge");
//                }
//
//            }.start();

        }

        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;

    }

    public void displayAlert(final String code) {


        alertDialog.setPositiveButton(
                "NOT NOW",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (code.equals("tour message")) {


                        }
                    }
                }
        );

        alertDialog.setNegativeButton(
                "LET'S DO IT",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(DetailActivity.this, SignIn.class));

                    }
                }
        );

        AlertDialog alertDialog1 = alertDialog.create();
        alertDialog1.show();

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();

        lp.copyFrom(alertDialog1.getWindow().getAttributes());
        lp.width = (width * 4) / 5;
        lp.height = (height * 33) / 100;
        lp.x = 0;
        lp.y = 0;
        alertDialog1.getWindow().setAttributes(lp);
    }
}
