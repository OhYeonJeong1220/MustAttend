package org.oneship.mustattend;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class OwnerSingUp extends AppCompatActivity  {
    String email;
    String pw;
    String phoneNum;
    int Year;
    int month;
    int day;
    EditText StoreName_id,StoreAddress_id,StorePhone_id,StorePrivateNum_id;
    NumberPicker Capacity_id;
    RadioGroup rg;
    Button upload_btn;
    ImageView ShopImage;
    Bitmap img;
    String StoreName,StoreAddress,StorePhone,StorePrivateNum;
    Integer Capacity;
    String parking;
    byte[] img_byte;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_sing_up);

        //가게 사진
        ShopImage =(ImageView)findViewById(R.id.Shop_imge);
        upload_btn = (Button)findViewById(R.id.upload_button);
        upload_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,1);
            }
        });
        //Toast.makeText(getApplicationContext(),email, Toast.LENGTH_LONG).show();
        //Toast.makeText(getApplicationContext(),pw, Toast.LENGTH_LONG).show();

        //가게 번호에 하이픈 추가
        EditText brand_phone = (EditText) findViewById(R.id.ShopPhone);
        brand_phone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        //수용인원NumberPicker
        final NumberPicker numberPickerCapacity = (NumberPicker) findViewById(R.id.Capacity);

        numberPickerCapacity.setMaxValue(10000);
        numberPickerCapacity.setMinValue(0);
        numberPickerCapacity.setWrapSelectorWheel(false);
        numberPickerCapacity.setValue(0);

        //주차가능 여부 중 선택된 텍스트 가져오기
        rg = (RadioGroup) findViewById(R.id.radioGroup);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id = rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton)findViewById(id);
                parking = rb.getText().toString();
            }
        });

        //SignUp에서 보낸 데이터 받기
        Intent intent = getIntent();
        email = intent.getExtras().getString("email");
        pw =intent.getExtras().getString("passward");
        phoneNum = intent.getExtras().getString("phoneNum");
        Year = intent.getExtras().getInt("Year");
        month = intent.getExtras().getInt("month");
        day = intent.getExtras().getInt("day");
        System.out.println("넘겨온 데이터:"+email+pw+phoneNum+Year+month+day);
        //사용자가 입력한 데이터 id 받기
        StoreName_id = (EditText)findViewById(R.id.ShopName);
        StoreAddress_id = (EditText)findViewById(R.id.ShopAddress);
        StorePhone_id = (EditText)findViewById(R.id.ShopPhone);
        StorePrivateNum_id = (EditText)findViewById(R.id.ShopPrivateNum);
        Capacity_id = (NumberPicker)findViewById(R.id.Capacity);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                try {
                    // 선택한 이미지에서 비트맵 생성
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    img = BitmapFactory.decodeStream(in);
                    //bitmap을 byte로 변환
                    img_byte =bitmapToByteArray(img);
                    in.close();
                    // 이미지 띄우기
                    ShopImage.setImageBitmap(img);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Bitmap을 String로 변환
    public static String BitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 70, baos);
        byte[] bytes = baos.toByteArray();
        String temp = Base64.encodeToString(bytes, Base64.DEFAULT);
        return temp;
    }
    //String을 Bitmap으로 변환
    public static Bitmap StringToBitmap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
    //Bitmap을 Byte로 변환
    public byte[] bitmapToByteArray( Bitmap $bitmap ) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream() ;
        $bitmap.compress( Bitmap.CompressFormat.JPEG, 100, stream) ;
        byte[] byteArray = stream.toByteArray() ;
        return byteArray ;
    }

    //Byte를 Bitmap으로 변환
    //등록완료 버튼이 눌렸을 때
    public void AllregisterCliked(View v){
        new JSONTask().execute("http://172.30.1.35:3000/register");
        //사용자가 입력한 정보 텍스트로 변환
        StoreName = StoreName_id.getText().toString();
        StoreAddress= StoreAddress_id.getText().toString();
        StorePhone = StorePhone_id.getText().toString();
        StorePrivateNum = StorePrivateNum_id.getText().toString();
        Capacity = Capacity_id.getValue();

    }

    public class JSONTask extends AsyncTask<String, String, String> {
        //인자(string,string,string)=(doInBackground,onProgressUpdate,onPostExecute)자료형

        @Override
        protected String doInBackground(String... urls) {//파라미터 형 string
            try {
                //JSONObject를 만들고 key value 형식으로 값을 저장해준다.
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("email", email);
                jsonObject.put("passward", pw);
                jsonObject.put("phoneNum", phoneNum);
                jsonObject.put("Year", Year);
                jsonObject.put("month", month);
                jsonObject.put("day", day);
                jsonObject.put("StoreName", StoreName);//가게이름
                jsonObject.put("StoreAddress", StoreAddress);//가게주소
                jsonObject.put("StorePhone", StorePhone);//가게 전화번호
                jsonObject.put("StorePrivateNum", StorePrivateNum);//사업자 등록 번호
                jsonObject.put("parking",parking);//주차 여부
                jsonObject.put("Capacity", Capacity);//수용인원
                jsonObject.put("image", img);//이미지

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
                intent.putExtra("user_email",email); //intent로 mainUI activity에 전달할 이메일
                //액티비티 시작!
                startActivity(intent);
            }
        }
    }
}