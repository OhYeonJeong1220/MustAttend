package org.oneship.mustattend;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import org.json.JSONArray;
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

// 예약신청하는

public class BookingActivity extends AppCompatActivity {
    String user_email; //이메일 세션
    String store_name;
    // 입력값
    EditText res_purpose;
    NumberPicker month_id;
    NumberPicker day_id;
    NumberPicker hour_id;
    NumberPicker min_id;
    NumberPicker num_id;

    // 변수값
    String purpose;
    int dateMonth;
    int dateDay;
    int timeHour;
    int timeMin;
    int numberOfPeople;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        res_purpose = (EditText)findViewById(R.id.respurpose);
        num_id = (NumberPicker)findViewById(R.id.num);
        month_id = (NumberPicker)findViewById(R.id.month);
        day_id = (NumberPicker)findViewById(R.id.day);
        hour_id = (NumberPicker)findViewById(R.id.hour);
        min_id = (NumberPicker)findViewById(R.id.minute);
        num_id = (NumberPicker)findViewById(R.id.num);

        //이메일 세션
        Intent intent = getIntent();
        user_email = intent.getExtras().getString("user_email");
        store_name = getIntent().getStringExtra("store_name"); //activity->activity로 값 전달 받을 때
        System.out.println("**********BookingActivity : "+user_email+"***********");

        final NumberPicker monthPicker = findViewById(R.id.month);
        final NumberPicker dayPicker = findViewById(R.id.day);

        final NumberPicker hourPicker = findViewById(R.id.hour);
        final NumberPicker minutePicker = findViewById(R.id.minute);

        final NumberPicker numPicker = findViewById(R.id.num);

        monthPicker.setMinValue(1);
        monthPicker.setMaxValue(12);

        dayPicker.setMinValue(1);
        dayPicker.setMaxValue(31);

        numPicker.setMinValue(0);
        numPicker.setMaxValue(30);

        hourPicker.setMinValue(0);
        hourPicker.setMaxValue(23);

        minutePicker.setMinValue(0);
        minutePicker.setMaxValue(59);

        Button button = (Button) findViewById(R.id.btn_request);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new JSONTask().execute("http://192.168.1.221:3000/reservereq");
                purpose = res_purpose.getText().toString();
                dateMonth = month_id.getValue();
                dateDay = day_id.getValue();
                timeHour = hour_id.getValue();
                timeMin = min_id.getValue();
                numberOfPeople = num_id.getValue();

                showMessage(); // 메세지띄워주고

            }
        });
    }

    public class JSONTask extends AsyncTask<String, String, String> {
        //인자(string,string,string)=(doInBackground,onProgressUpdate,onPostExecute)자료형
        @Override
        protected String doInBackground(String... urls) {//파라미터 형 string
            try {
                //JSONObject를 만들고 key value 형식으로 값을 저장해준다.
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("user_email", user_email);
                jsonObject.put("store_name", store_name);
                jsonObject.put("dateMonth", dateMonth);
                jsonObject.put("dateDay", dateDay);
                jsonObject.put("timeHour", timeHour);
                jsonObject.put("timeMin", timeMin);
                jsonObject.put("purpose", purpose);
                jsonObject.put("numberOfPeople", numberOfPeople);

                String data = jsonObject.toString();
                HttpURLConnection con = null;
                BufferedReader reader = null;

                System.out.println("***json 데이터"+data);
                try{
                    URL url = new URL(urls[0]);

                    con = (HttpURLConnection) url.openConnection();//url을 연결할 객체
                    System.out.println("**************1*******************");
                    con.setRequestMethod("POST");//POST방식으로 보냄
                    //Request 헤더 값 설정
                    con.setRequestProperty("Cache-Control", "no-cache");//캐시 설정
                    con.setRequestProperty("Content-Type", "application/json");//application JSON 형식으로 전송
                    con.setRequestProperty("Accept", "text/html");//서버에 response 데이터를 html로 받음
                    System.out.println("**************2*******************");
                    con.setDoOutput(true);//Outstream으로 post 데이터를 넘겨주겠다는 의미
                    con.setDoInput(true);//Inputstream으로 서버로부터 응답을 받겠다는 의미
                    con.connect(); // 연결
                    System.out.println("**************3*******************");
                    //서버로 보내기위해서 스트림 만듬
                    OutputStream outStream = con.getOutputStream();
                    //버퍼를 생성하고 넣음
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outStream));
                    writer.write(jsonObject.toString());
                    writer.flush();
                    writer.close();//버퍼를 받아줌

                    System.out.println("**************4*******************");
                    //서버로 부터 데이터를 받음
                    InputStream stream = con.getInputStream(); // 입력스트림

                    reader = new BufferedReader(new InputStreamReader(stream));

                    StringBuffer buffer = new StringBuffer(); // 데이터 받는 곳

                    String line = "";
                    while((line = reader.readLine()) != null){
                        buffer.append(line); // 데이터 가져오기
                    }

                    System.out.println("*************"+buffer+"*************");
                    return buffer.toString();//서버로 부터 받은 값을 string 형변환

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
            System.out.println("*************여기********");
            System.out.println(result);

            JSONArray jsonarr = null;

            System.out.println("***************data type************");
            System.out.println(result.getClass().getName());

        }
    }

    public void showMessage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("예약 요청 완료");
        builder.setMessage("사장님 요청 대기중!");
        builder.setIcon(R.drawable.logo);

        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 홈화면으로 돌아가기
                Intent intent = new Intent(getApplicationContext() , mainUI.class);
                intent.putExtra("user_email",user_email); //intent로 다음 activity에 전달할 이메일
                //액티비티 시작!
                startActivity(intent);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}