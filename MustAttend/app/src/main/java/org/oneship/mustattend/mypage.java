package org.oneship.mustattend;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class mypage extends Fragment {

    ViewGroup viewGroup;
    String user_email; //이메일 세션
    String store_name;
    String store_address;
    String store_phone_num;
    String store_license;
    String store_parking;
    int store_maxclientnum;
    Bitmap store_image;
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull  ViewGroup container,
                             @NonNull Bundle savedInstanceState) {
        viewGroup =(ViewGroup)inflater.inflate(R.layout.fragment_mypage, container, false);
        user_email = this.getArguments().getString("user_email");
        System.out.println("**********마이 페이지 화면 : "+user_email+"***********");
        Button button1 = (Button)viewGroup.findViewById(R.id.ReviewButton);
        Button button2 = (Button)viewGroup.findViewById(R.id.ChangeInfoButton);
        Button button3 = (Button)viewGroup.findViewById(R.id.StoreButton); //가게 관리 버튼
        Button button4 = (Button)viewGroup.findViewById(R.id.LogputButton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SubActivity로 가는 인텐트를 생성
                Intent intent = new Intent(getActivity(), ReviewManagment_UserActivity.class);
                intent.putExtra("user_email",user_email); //intent로 activity에 전달할 이메일
                //액티비티 시작!
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SubActivity로 가는 인텐트를 생성
                Intent intent = new Intent(getActivity(), ChangingInfo.class);
                intent.putExtra("user_email",user_email); //intent로 activity에 전달할 이메일
                //액티비티 시작!
                startActivity(intent);
            }
        });
        //가게 관리 버튼을 눌렀을 때
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new JSONTask().execute("http://172.30.1.35:3000/checkowner");


            }
        });
        //로그아웃 눌렀을 때
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SubActivity로 가는 인텐트를 생성
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                //액티비티 시작!
                startActivity(intent);
            }
        });
        return viewGroup;

    }
    public class JSONTask extends AsyncTask<String, String, String> {
        //인자(string,string,string)=(doInBackground,onProgressUpdate,onPostExecute)자료형
        @Override
        protected String doInBackground(String... urls) {//파라미터 형 string
            try {
                //JSONObject를 만들고 key value 형식으로 값을 저장해준다.
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("email", user_email);

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
                    JSONArray result = new JSONArray((String.valueOf(buffer)));
                    //JSONObject result = new JSONObject(); //리턴 받은 DOCUMENT
                    JSONObject obj = result.getJSONObject(0);
                    System.out.println("*************"+obj+"*************");
                    JSONObject obj2 = obj.getJSONObject("store");
                    System.out.println("*************"+obj2+"*************");
                    store_name =  obj2.get("name").toString();  //가게 이름
                    store_address = obj2.get("address").toString(); //가게 주소
                    store_phone_num = obj2.get("phoneNum").toString(); //가게 전화번호
                    //store_license =  obj2.get("license").toString();
                    store_parking = obj2.get("parking").toString();
                    store_maxclientnum = (int)obj2.get("maxclientnum"); //최대 수용 인원
                    store_image = (Bitmap)obj2.get("image"); //가게 사진


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
            if(store_name != null) {
                System.out.println("*************여기2********");

                Toast.makeText(getActivity(),"가게 등록자임", Toast.LENGTH_LONG).show();

                //가게 정보 수정 페이지로 이동
                Intent intent = new Intent(getActivity().getApplicationContext(), Owner_myPage.class);
                intent.putExtra("user_email",user_email); //intent로 mainUI activity에 전달할 사용자 이메일
                intent.putExtra("store_name",store_name); //intent로 mainUI activity에 전달할 가게 이름
                intent.putExtra("store_phone_num",store_phone_num); //intent로 mainUI activity에 전달할 가게 전화번호
                //intent.putExtra("store_license",store_license); //intent로 mainUI activity에 전달할 가게 사업등록자번호
                intent.putExtra("store_parking",store_parking); //intent로 mainUI activity에 전달할 주차 가능 여부
                intent.putExtra("store_maxclientnum",store_maxclientnum); //intent로 mainUI activity에 전달할 최대 수용 인원
                intent.putExtra("store_image", store_image); //가게 사진 intent로 보내기
                startActivity(intent);
            }
            else{
                Toast.makeText(getActivity(),"등록된 가게가 없습니다.!", Toast.LENGTH_LONG).show();
            }
        }
    }
}
