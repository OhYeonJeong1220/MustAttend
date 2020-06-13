package org.oneship.mustattend;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class LoginActivity extends AppCompatActivity {
    EditText username_id;
    EditText password_id;
    String email;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username_id = (EditText)findViewById(R.id.username); //xml에서 이메일 값 가져오기
        password_id = (EditText)findViewById(R.id.password); //xml에서 비밀번호 값 가져오기

        Button loginButton = (Button)findViewById(R.id.loginButton); //'login' 버튼 id 가져오기
        TextView resisterButton = (TextView) findViewById(R.id.resisterButton); //'회원가입' 버튼 id 가져오기
        resisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUp.class);
                LoginActivity.this.startActivity(intent);
            }
        });

    }
    public void loginButtonClicked(View v){ //'login' 버튼을 눌렀다면

        //node.js에 아이디, 비밀번호 값 전송
        // node.js로부터 올바르게 로그인 성공 여부 값 전달받기
        //성공했으면 mainUI로 intent 시작
        //아니면 팝업
        new JSONTask().execute("http://192.168.43.231:3000/login");
        email = username_id.getText().toString();   //email String 문자열로 저장
        //Toast.makeText(getApplicationContext(),email, Toast.LENGTH_LONG).show();
        password = password_id.getText().toString();    //password String 문자열로 저장
        //Toast.makeText(getApplicationContext(),pw, Toast.LENGTH_LONG).show();

    }
    public class JSONTask extends AsyncTask<String, String, String> {
        //인자(string,string,string)=(doInBackground,onProgressUpdate,onPostExecute)자료형
        @Override
        protected String doInBackground(String... urls) {//파라미터 형 string
            try {
                //JSONObject를 만들고 key value 형식으로 값을 저장해준다.
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("email", email);
                jsonObject.put("password", password);

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
            if(result.equals("1")) {
                //System.out.println("***************:"+email);
                System.out.println("*************여기2********");
                //new AlertDialog.Builder(getApplicationContext())
                //       .setTitle("가입 완료")
                //       .setMessage("\n가입이 완료되었습니다.")
                //      .show();//팝업창 생성
                Toast.makeText(getApplicationContext(),"로그인 성공", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext() , mainUI.class);
                intent.putExtra("user_email",email); //intent로 다음 activity에 전달할 이메일
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
    /*
    public void SignUpClicked(View v)
    {
        //SubActivity로 가는 인텐트를 생성
        Intent intent = new Intent(this, SignUp.class);
        //액티비티 시작!
        startActivity(intent);
    }
    */
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"앱이 종료됩니다.", Toast.LENGTH_LONG).show();
        android.os.Process.killProcess(android.os.Process.myPid());
        finishAffinity();
        System.runFinalization();
        System.exit(0);
    }
}