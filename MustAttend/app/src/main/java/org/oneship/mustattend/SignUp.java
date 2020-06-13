package org.oneship.mustattend;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class SignUp extends AppCompatActivity  {
    EditText email_id;
    String email;
    EditText pw_id;
    String pw;
    EditText phoneNum_id;
    String phoneNum;
    NumberPicker Year_id;
    int Year;
    NumberPicker month_id;
    int month;
    NumberPicker day_id;
    int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        email_id = (EditText)findViewById(R.id.email) ;
        pw_id = (EditText)findViewById(R.id.password);
        phoneNum_id = (EditText)findViewById(R.id.phone) ;
        Year_id = (NumberPicker)findViewById(R.id.year) ;
        month_id = (NumberPicker)findViewById(R.id.month) ;
        day_id = (NumberPicker)findViewById(R.id.day) ;
        // 연락처 입력시 하이픈(-) 자동 입력.

        phoneNum_id.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        //생년월일 numberpicker 설정
        final NumberPicker numberPickerYear = (NumberPicker) findViewById(R.id.year);
        final NumberPicker numberPickerMonth = (NumberPicker) findViewById(R.id.month);
        final NumberPicker numberPickerDay = (NumberPicker) findViewById(R.id.day);

        numberPickerYear.setMaxValue(3000);
        numberPickerYear.setMinValue(1900);
        numberPickerYear.setWrapSelectorWheel(false);
        numberPickerYear.setValue(2000);

        numberPickerMonth.setMaxValue(12);
        numberPickerMonth.setMinValue(1);
        numberPickerMonth.setWrapSelectorWheel(true);
        numberPickerMonth.setValue(1);

        numberPickerDay.setMaxValue(31);
        numberPickerDay.setMinValue(1);
        numberPickerDay.setWrapSelectorWheel(true);
        numberPickerDay.setValue(1);



    }

    public void SuccessRegisterCliked(View v){//가입 완료 버튼이 눌렸을 때
        new JSONTask().execute("http://192.168.43.231:3000/post");
        email = email_id.getText().toString();
        //Toast.makeText(getApplicationContext(),email, Toast.LENGTH_LONG).show();
        pw = pw_id.getText().toString();
        //Toast.makeText(getApplicationContext(),pw, Toast.LENGTH_LONG).show();
        phoneNum  = phoneNum_id.getText().toString();
        Year = Year_id.getValue();
        month = month_id.getValue();
        day = day_id.getValue();

    }

    public void registerStoreCliked(View v){//가게 등록 버튼이 눌렸을 때
        Intent intent = new Intent(this, OwnerSingUp.class);
        email = email_id.getText().toString();
        pw = pw_id.getText().toString();
        phoneNum  = phoneNum_id.getText().toString();
        Year = Year_id.getValue();
        month = month_id.getValue();
        day = day_id.getValue();
        //회원가입에서 입력한 정보를 가게등록 페이지로 넘겨줌
        intent.putExtra("email",email);
        intent.putExtra("password",pw);
        intent.putExtra("phoneNum",phoneNum);
        intent.putExtra("Year",Year);
        intent.putExtra("month",month);
        intent.putExtra("day",day);
        //가게등록 액티비티로 Intent
        startActivity(intent);

    }

    public class JSONTask extends AsyncTask<String, String, String> {
        //인자(string,string,string)=(doInBackground,onProgressUpdate,onPostExecute)자료형

        @Override
        protected String doInBackground(String... urls) {//파라미터 형 string
            try {
                //JSONObject를 만들고 key value 형식으로 값을 저장해준다.
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("email", email);
                jsonObject.put("password", pw);
                jsonObject.put("phoneNum", phoneNum);
                jsonObject.put("Year", Year);
                jsonObject.put("month", month);
                jsonObject.put("day", day);

                String data = jsonObject.toString();
                HttpURLConnection con = null;
                BufferedReader reader = null;

                System.out.println("***json 데이터"+data);
                try{
                    URL url = new URL(urls[0]);

                    con = (HttpURLConnection) url.openConnection();//url을 연결할 객체

                    con.setRequestMethod("POST");//POST방식으로 보냄
                    //Request 헤더 값 설정
                    con.setRequestProperty("Cache-Control", "no-cache");//캐시 설정
                    con.setRequestProperty("Content-Type", "application/json");//application JSON 형식으로 전송
                    con.setRequestProperty("Accept", "text/html");//서버에 response 데이터를 html로 받음

                    con.setDoOutput(true);//Outstream으로 post 데이터를 넘겨주겠다는 의미
                    con.setDoInput(true);//Inputstream으로 서버로부터 응답을 받겠다는 의미
                    con.connect();

                    //서버로 보내기위해서 스트림 만듬
                    OutputStream outStream = con.getOutputStream();
                    //버퍼를 생성하고 넣음
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outStream));
                    writer.write(jsonObject.toString());
                    writer.flush();
                    writer.close();//버퍼를 받아줌

                    //서버로 부터 데이터를 받음
                    InputStream stream = con.getInputStream();

                    reader = new BufferedReader(new InputStreamReader(stream));

                    StringBuffer buffer = new StringBuffer();

                    String line = "";
                    while((line = reader.readLine()) != null){
                        buffer.append(line);
                    }


                    return buffer.toString();//서버로 부터 받은 값을 리턴해줌

                } catch (MalformedURLException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(con != null){
                        con.disconnect();
                    }
                    try {
                        if(reader != null){
                            reader.close();//버퍼를 닫아줌
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {//UI 스레드 상에서 실행 doInBackground 종료 후 파라미터를 전달 받음
            super.onPostExecute(result);
            if(result.equals("OK!")) {
                //System.out.println("***************:"+email);

                //AlertDialog.Builder signUpSuccess = new AlertDialog.Builder(getApplicationContext());
                // signUpSuccess.setTitle("가입 완료");
                //signUpSuccess.setMessage("가입이 완료되었습니다.");

                //AlertDialog alert = signUpSuccess.create();
                //alert.show();

                Toast.makeText(getApplicationContext(),"가입완료", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext() , mainUI.class);
                intent.putExtra("user_email",email); //intent로 다음 activity에 전달할 이메일
                //액티비티 시작!
                startActivity(intent);
            }
        }
    }

}