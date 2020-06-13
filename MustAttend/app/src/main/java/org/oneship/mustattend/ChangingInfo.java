package org.oneship.mustattend;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

public class ChangingInfo extends AppCompatActivity implements View.OnClickListener {
    String user_email; //이메일 세션
    String user_password; //비밀번호 세션
    String user_phone;   //전화번호 세션
    int user_birth_year; //생년 세션
    int user_birth_month;  //생월 세션
    int user_birth_day; //생일 세션

    //뷰들의 각 id
    TextView email_id;
    EditText password_id;
    EditText phone_id;
    TextView year_id;
    TextView month_id;
    TextView day_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changing_info);
        user_email = getIntent().getStringExtra("user_email"); //이메일 세션 정보 받기
        user_password = getIntent().getStringExtra("user_password"); //비밀번호 세션
        user_phone = getIntent().getStringExtra("user_phone"); //전화번호 세션
        user_birth_year = getIntent().getIntExtra("user_birth_year", 0); //생년 세션
        user_birth_month = getIntent().getIntExtra("user_birth_month", 0); //생월 세션
        user_birth_day = getIntent().getIntExtra("user_birth_day", 0); //생일 세션

        System.out.println("*********************************");
        System.out.println(user_email+" "+user_password+" "+user_birth_year+" "+user_birth_year+" "+user_birth_month+" "+user_birth_day);
        System.out.println("*********************************");
        //팝업 버튼
        findViewById(R.id.register).setOnClickListener(this);
        email_id =(TextView) findViewById(R.id.email);
        password_id =(EditText) findViewById(R.id.password);
        phone_id=(EditText) findViewById(R.id.phone);
        year_id =(TextView) findViewById(R.id.year);
        month_id =(TextView) findViewById(R.id.month);
        day_id =(TextView) findViewById(R.id.day);

        //값 출력하기
        email_id.setText(user_email) ;
        password_id.setText(user_password);
        phone_id.setText(user_phone);
        year_id.setText(Integer.toString(user_birth_year));
        month_id.setText(Integer.toString(user_birth_month));
        day_id.setText(Integer.toString(user_birth_day));




    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.register:
                user_password =password_id.getText().toString();    //수정한 비밀번호 값 가져오기
                user_phone = phone_id.getText().toString(); //수정한 전화번호 값 가져오기
                new JSONTask().execute("http://192.168.43.231:3000/modifyuser");
                break;


        }
    }
    public class JSONTask extends AsyncTask<String, String, String> {
        //인자(string,string,string)=(doInBackground,onProgressUpdate,onPostExecute)자료형
        @Override
        protected String doInBackground(String... urls) {//파라미터 형 string
            try {
                //JSONObject를 만들고 key value 형식으로 값을 저장해준다.
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("email", user_email);
                jsonObject.put("password", user_password);
                jsonObject.put("phoneNum", user_phone);
                //jsonObject.put("year", user_birth_year);
                //jsonObject.put("month", user_birth_month);
                //jsonObject.put("day", user_birth_day);
                String data = jsonObject.toString();
                HttpURLConnection con = null;
                BufferedReader reader = null;

                //System.out.println("***json 데이터"+data);
                try{
                    URL url = new URL(urls[0]);

                    con = (HttpURLConnection) url.openConnection();//url을 연결할 객체
                    System.out.println("**************1*******************");
                    con.setRequestMethod("POST");//POST방식으로 보냄
                    //Request 헤더 값 설정
                    System.out.println("**************2*******************");
                    con.setRequestProperty("Cache-Control", "no-cache");//캐시 설정
                    con.setRequestProperty("Content-Type", "application/json");//application JSON 형식으로 전송
                    con.setRequestProperty("Accept", "text/html");//서버에 response 데이터를 html로 받음
                    System.out.println("**************3*******************");
                    con.setDoOutput(true);//Outstream으로 post 데이터를 넘겨주겠다는 의미
                    con.setDoInput(true);//Inputstream으로 서버로부터 응답을 받겠다는 의미
                    con.connect();
                    System.out.println("*************4********");
                    //서버로 보내기위해서 스트림 만듬
                    OutputStream outStream = con.getOutputStream();
                    //버퍼를 생성하고 넣음
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outStream));
                    writer.write(jsonObject.toString());
                    writer.flush();
                    writer.close();//버퍼를 받아줌
                    System.out.println("************5********");
                    //서버로 부터 데이터를 받음
                    InputStream stream = con.getInputStream();

                    reader = new BufferedReader(new InputStreamReader(stream));

                    StringBuffer buffer = new StringBuffer();

                    String line = "";
                    while((line = reader.readLine()) != null){
                        buffer.append(line);

                    }

                    System.out.println("*************"+buffer+"*************");
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
            System.out.println("*************여기********");
            System.out.println(result);
            if(result.equals("OK!")) {
                //System.out.println("***************:"+email);
                System.out.println("*************여기2********");
                //new AlertDialog.Builder(getApplicationContext())
                //       .setTitle("가입 완료")
                //       .setMessage("\n가입이 완료되었습니다.")
                //      .show();//팝업창 생성
                Toast.makeText(getApplicationContext(),"개인 정보 수정 완료", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext() , mainUI.class);
                intent.putExtra("user_email",user_email); //intent로 다음 activity에 전달할 이메일
                //액티비티 시작!
                startActivity(intent);
            }
            else{
                //new AlertDialog.Builder(getApplicationContext())
                //    .setTitle("로그인 실패")
                //   .setMessage("\n 아이디 혹은 비밀번호가 올바르지 않습니다.")
                // .show();//팝업창 생성
            }
        }
    }
}