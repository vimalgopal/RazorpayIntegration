package com.redrawlife.epos.myapplication;


import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements PaymentResultListener {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void selfDestruct1(View view) {
        startPayment(5000);
    }
    public void selfDestruct2(View view) {
        startPayment(10000);
    }

    public void selfDestruct3(View view) {
        startPayment(15000);
    }

    public void selfDestruct4(View view) {
        startPayment(20000);
    }

    public void makepayment(View view) {
        EditText newEditView = (EditText)findViewById(R.id.editText);
        String val = newEditView.getText().toString();
        int value = Integer.valueOf(val)*100;

        startPayment(value);
    }


    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        /**
         * Add your logic here for a successfull payment response
         */
        try {
            Toast.makeText(this, "Payment Successful: " + razorpayPaymentID, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentSuccess", e);
        }
    }

    @Override
    public void onPaymentError(int code, String response) {
        /**
         * Add your logic here for a failed payment response
         */ try {
            Toast.makeText(this, "Payment failed: " + code + " " + response, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentError", e);
        }
    }


    public void startPayment(int amount) {
        /**
         * Instantiate Checkout
         */
        Checkout checkout = new Checkout();

        /**
         * Set your logo here
         */
        checkout.setImage(R.drawable.logo);

        /**
         * Reference to current activity
         */
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            /**
             * Merchant Name
             * eg: Rentomojo || HasGeek etc.
             */
            options.put("name", "Redrawlife ");

            /**
             * Description can be anything
             * eg: Order #123123
             *     Invoice Payment
             *     etc.
             */
            options.put("description", "Event Payment");

            options.put("currency", "INR");

            /**
             * Amount is always passed in PAISE
             * Eg: "500" = Rs 5.00
             */
            options.put("amount", amount);
            options.put("prefill", new JSONObject("{email: 'joinus@redrawlife.com', contact: '9497864497', name: 'Joel Kuraikose'}"));
            options.put("theme.color","#FF9800");

            checkout.open(activity, options);

        } catch (Exception e) {
           Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }

}
