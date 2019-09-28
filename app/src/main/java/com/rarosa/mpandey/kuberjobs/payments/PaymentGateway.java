package com.rarosa.mpandey.kuberjobs.payments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rarosa.mpandey.kuberjobs.R;

public class PaymentGateway extends AppCompatActivity {

    private static final int TEZ_REQUEST_CODE = 123;
    private static final String GOOGLE_TEZ_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user";
    private Button payButton;
    private EditText editTextPayment;

    private Intent intent;
    private Uri uri;

    String payeeAddress = "8866616231@upi";
    String payeeName = "Mohinder Pandey";
    String transactionNote = "Test for Deeplinking";
    String amount = "1";
    String currencyUnit = "INR";
    String merchantUrl = "https://test.merchant.website";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_checkout);

        payButton = findViewById(R.id.pay_button);
        editTextPayment = (EditText) findViewById(R.id.editTextPayment);

        payButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextPayment.getText().toString().equals(""))
                {
                    Toast.makeText(PaymentGateway.this, "Please fill payment", Toast.LENGTH_LONG).show();
                    return;
                }

                /*uri =
                        new Uri.Builder()
                                .scheme("upi")
                                .authority("pay")
                                .appendQueryParameter("pa", "test@axisbank")
                                .appendQueryParameter("pn", "Test Merchant")
                                .appendQueryParameter("mc", "1234")
                                .appendQueryParameter("tr", "123456789")
                                .appendQueryParameter("tn", "test transaction note")
                                .appendQueryParameter("am", "10.01")
                                .appendQueryParameter("cu", "INR")
                                .appendQueryParameter("url", "https://test.merchant.website")
                                .build();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(uri);
                intent.setPackage(GOOGLE_TEZ_PACKAGE_NAME);
                startActivityForResult(intent, TEZ_REQUEST_CODE);*/

                //bhim
                /*uri =
                        new Uri.Builder()
                                .scheme("upi")
                                .authority("pay")
                                .appendQueryParameter("pa", "8866616231@upi")
                                .appendQueryParameter("pn", "Test Merchant")
                                .appendQueryParameter("mc", "1234")
                                .appendQueryParameter("tr", "123456789")
                                .appendQueryParameter("tn", "test transaction note")
                                .appendQueryParameter("am", "10.01")
                                .appendQueryParameter("cu", "INR")
                                .appendQueryParameter("url", "https://test.merchant.website")
                                .build();*/

                Uri uri = Uri.parse("upi://pay?pa="+payeeAddress+"&pn="+payeeName+"&tn="+transactionNote+
                        "&am="+amount+"&cu="+currencyUnit+"&url="+merchantUrl);
                Log.d("myInfo", "onClick: uri: "+uri);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.setClassName("in.org.npci.upiapp","in.org.npci.upiapp.HomeActivity");
                startActivityForResult(intent,1);

                //uri = Uri.parse("upi://pay?pa=8866616231@upi&pn=Aayushi%20Shah&tn=Test%20for%20Deeplinking&am=1&cu=INR&url=https://mystar.co"); // missing 'http://' will cause crashed
                //Log.d("myInfo", "onClick: uri: "+uri);
                //intent = new Intent(Intent.ACTION_VIEW, uri);
                //intent.setClassName("in.org.npci.upiapp","in.org.npci.upiapp.HomeActivity");
                //startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //if (requestCode == TEZ_REQUEST_CODE) {
            // Process based on the data in response.
            //Log.d("result", data.getStringExtra("Status"));
        //}

        //if (resultCode == RESULT_OK) {
            //Log.d("myInfo", data.getStringExtra("Status"));
        //}

        Log.d("myInfo", "onActivityResult: requestCode: "+requestCode);
        Log.d("myInfo", "onActivityResult: resultCode: "+resultCode);
        //txnId=UPI20b6226edaef4c139ed7cc38710095a3&responseCode=00&ApprovalRefNo=null&Status=SUCCESS&txnRef=undefined
        //txnId=UPI608f070ee644467aa78d1ccf5c9ce39b&responseCode=ZM&ApprovalRefNo=null&Status=FAILURE&txnRef=undefined

        if(data!=null) {
            Log.d("myInfo", "onActivityResult: data: " + data.getStringExtra("response"));
            String res = data.getStringExtra("response");
            String search = "SUCCESS";
            if (res.toLowerCase().contains(search.toLowerCase())) {
                Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Payment Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //accessors
    public String getCurrencyUnit() {
        return this.currencyUnit;
    }

    public void setCurrencyUnit(String currency_unit) {
        this.currencyUnit = currency_unit;
    }
}