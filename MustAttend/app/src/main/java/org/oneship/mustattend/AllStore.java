package org.oneship.mustattend;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

// 전체 가게 출력하는

public class AllStore extends AppCompatActivity {
    String user_email; //이메일 세션
    RecyclerView recyclerView;
    AllStoreAdapter adapter; // 어댑터 설정

    // 가게 선택하면 넘겨줄 아이템 변수들
    String email;
    String sel_store_name; // 가게이름
    String sel_store_phonenum; // 전화번호
    String sel_store_parking; // 주차여부
    String sel_store_maxclientnum; // 수용인원

    JsonObject store;

    Bitmap bitmap = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_store);

        recyclerView = findViewById(R.id.recyclerView);

        // 리사이클러뷰 모양 설정
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        //이메일 세션
        Intent intent = getIntent();
        user_email = intent.getExtras().getString("user_email");
        System.out.println("**********AllStoreActivity : "+user_email+"***********");

        //서버랑 연결
        new JSONTask().execute("http://192.168.43.175:3000/allstore");

        // 어댑터 설정
        adapter = new AllStoreAdapter();
    }

    public class JSONTask extends AsyncTask<String, String, String> {
        //인자(string,string,string)=(doInBackground,onProgressUpdate,onPostExecute)자료형
        @Override
        protected String doInBackground(String... urls) {//파라미터 형 string
            try {
                //JSONObject를 만들고 key value 형식으로 값을 저장해준다.
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", "name");

                //String data = jsonObject.toString();
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
                    con.connect(); // 연결
                    System.out.println("**************3*******************");

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

            // 받은 문자열 파싱해서 JsonArray로
            // 파싱이란? JSON 문자열을 해석해서 자바 객체로 만드는 과정
            JsonParser jsonParser = new JsonParser();
            JsonArray jsonArray = (JsonArray) jsonParser.parse(result);

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject object = (JsonObject) jsonArray.get(i);
                System.out.println("***************가게정보만 뽑아지나************");
                store = object.get("store").getAsJsonObject();

                // json객체를 json배열로 만들기
                jsonarr = new JSONArray();
                jsonarr.put(store);

                System.out.println(store); // 가게 속성 출력
                System.out.println(store.get("name")); // 가게 이름만 출력
                System.out.println("***************가게사진형식************");
                System.out.println(store.get("image")); // 일단 가게사진
                System.out.println("***************data type************");
                System.out.println(store.get("image").getClass().getName()); // json객체

                //.replace("\"","")
                //문자열에서 특정 문자를 바꾸라는 함수
                adapter.addItem(store.get("name").toString().replace("\"", ""));
                // 가게 이름이 json객체니까 string으로 바꿔서

                // 클릭한 가게 찾기위해 잠시
                adapter.addName(store.get("name").toString());
                adapter.addphoneNum(store.get("phoneNum").toString());
                adapter.addparking(store.get("parking").toString());
                adapter.addmaxclientnum(store.get("maxclientnum").toString());

                recyclerView.setAdapter(adapter); // 리사이클러뷰에 어댑터설정

                // 아이템 클릭하면
                adapter.setOnItemClickListener(new OnStoreItemClickListener() {
                    @Override
                    public void onItemClick(AllStoreAdapter.ViewHolder holder, View view, int position) {
                        sel_store_name = adapter.getNames(position); // 가게이름 문자열로
                        sel_store_phonenum = adapter.getPhones(position); // 전화번호 문자열로
                        sel_store_parking = adapter.getParks(position); // 주차여부 문자열로
                        sel_store_maxclientnum = adapter.getNums(position); // 수용인원 문자열로

                        // 가게 페이지로
                        Intent intent = new Intent(AllStore.this, StoreActivity.class);
                        intent.putExtra("user_email",user_email); //intent로 다음 activity에 전달할 이메일
                        intent.putExtra("store_name",sel_store_name); //intent로 다음 activity에 전달할 가게이름
                        intent.putExtra("store_phonenum",sel_store_phonenum); //intent로 다음 activity에 전달할 전화번호
                        intent.putExtra("store_parking",sel_store_parking); //intent로 다음 activity에 전달할 주차여부
                        intent.putExtra("store_maxclientnum",sel_store_maxclientnum); //intent로 다음 activity에 전달할 수용인원
                        //AllStore.this.startActivity(intent);
                        startActivity(intent);
                    }
                });
            }

        }
    }

}

