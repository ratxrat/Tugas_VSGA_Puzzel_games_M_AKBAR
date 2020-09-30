package ratsoft.android.vsgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    /*
    * tugas M Akbar mebuat game puzzel Alfabet
    * */
   //deklarasi global variable
    public  int tombol1 ;
    public  int tombol2 ;
    public  int baris1 ;
    public  int baris2;
    public  String val1;
    public  String val2;
    public  Button v1;
    public  Button v2;
    public  String[] bahanAcak ={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O"};
    public  String[] jawaban ={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O"};
    public  int[] buttonId = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
    public  int pergerakan = 0;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater pilih = getMenuInflater();
        pilih.inflate(R.menu.mainmenu,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  getSupportActionBar().setTitle("M AKBAR");
        acakHuruf();


    }
    //fungsi button
    public  void cekbtn(View view){
    //    Context ct = getApplicationContext();

        //parsing Charsquence ke string agar bisa di ambil 2 angka terakhir
        String q = "" + view.getParent();

        //mendapatkan parent untuk pergerakan horizontal dan vertical
        String p = q.substring(q.length() - 2);

        //mendapatkan angka parent agar lebih mudah di bandingkan
        p =String.valueOf(p.charAt(0)) ;

        //get tag button
        String bId = "" +view.getTag();

        // mengambil text value button
        Button bt = (Button) view;
        String btnText = bt.getText().toString();

        //toast untuk debug saja

      //  int durasi = Toast.LENGTH_SHORT;

     //   Toast tt = Toast.makeText(ct,(CharSequence)btnText,durasi);
     //   tt.show();

    // masukan input button pertama & kedua
        if(baris1 == 0){
            baris1 = Integer.parseInt(p);
            tombol1 = Integer.parseInt(bId);
            v1 = bt;
            val1 = btnText;
        }else{
            baris2 = Integer.parseInt(p);
            tombol2 = Integer.parseInt(bId);
            v2 = bt;
            val2 = btnText;
        }


        //cek jika semua value sudah di dapatkan
        if(baris1 != 0 && baris2 != 0 && tombol1 != 0 && tombol2 != 0){
           bergerak(baris1,baris2,tombol1,tombol2,val1,val2,v1,v2);
        }

    }
    //fungsi untuk menentukan pergerakan
    public  void  bergerak(int row1, int row2,int btn1 ,int btn2 , String aText , String bText,Button b1,Button b2 ){
        int cekRow = row1 - row2;
        int cekBtn = btn1 - btn2;
        // mengambil color default button
        Button defbtn=new Button(this);
        // deklarasi textview untuk pergerakan
        TextView tv = (TextView)findViewById(R.id.pergerakan);
        if(aText.equals("*") || bText.equals("*")) {
            if (cekRow == 1 || cekRow == -1) {
                //pergerakan vertikal
                if (cekBtn == 0) {
                    // pindahkan value text
                    pergerakan ++;
                    tv.setText("Pergerakan : " + pergerakan);
                    b1.setText(bText);
                    b2.setText(aText);

                    //reset variable
                    resetVariable();
                    cekFinish();
                    if(b1.getText().equals("*")){
                        b1.setBackgroundColor(Color.GREEN);
                        b2.setBackground(defbtn.getBackground());
                    }else {
                        b2.setBackgroundColor(Color.GREEN);
                        b1.setBackground(defbtn.getBackground());
                    }
                } else {
                    //invalid move
                    Context ct = getApplicationContext();
                    CharSequence t = " Pergerakan Tidak Diijinkan! ";
                    int durasi = Toast.LENGTH_SHORT;
                    Toast tt = Toast.makeText(ct, t, durasi);
                    tt.show();
                    resetVariable();
                }


            }
            //pergerakan horizontal
            else if (cekRow == 0) {
                if (cekBtn == 1 || cekBtn == -1) {
                    // pindahkan value text
                    pergerakan ++;
                    tv.setText("Pergerakan : " + pergerakan);
                    b1.setText(bText);
                    b2.setText(aText);
                    //reset variable
                    resetVariable();
                    cekFinish();
                    if(b1.getText().equals("*")){
                        b1.setBackgroundColor(Color.GREEN);
                        b2.setBackground(defbtn.getBackground());
                    }else {
                        b2.setBackgroundColor(Color.GREEN);
                        b1.setBackground(defbtn.getBackground());
                    }
                } else {

                    Context ct = getApplicationContext();
                    CharSequence t = " Pergerakan Tidak Diijinkan ";
                    int durasi = Toast.LENGTH_SHORT;
                    Toast tt = Toast.makeText(ct, t, durasi);
                    tt.show();
                }
                resetVariable();
            } else {
                //pergerakan tidak di ijinkan
                // aktifkan toast
                Context ct = getApplicationContext();
                CharSequence t = " Pergerakan Tidak Diijinkan! ";
                int durasi = Toast.LENGTH_SHORT;
                Toast tt = Toast.makeText(ct, t, durasi);
                tt.show();
                resetVariable();
            }
        }else{
            Context ct = getApplicationContext();
            CharSequence t = " Pergerakan Tidak Diijinkan! harus di mulai dari MOVE";
            int durasi = Toast.LENGTH_SHORT;
            Toast tt = Toast.makeText(ct, t, durasi);
            tt.show();
            resetVariable();
        }
    }

    public  void  resetVariable(){
        tombol1 = 0;
        tombol2 = 0;
        baris2 = 0;
        baris1 = 0;
        val1 = null;
        val2 = null;
        v1 = null;
        v2 = null;
    }
    public void  acakHuruf(){
        pergerakan = 0;
        TextView tv = (TextView) findViewById(R.id.pergerakan);
        tv.setText("Pergerakan : 0");
        Button defbtn=new Button(this);
        List<String> huruf = Arrays.asList(bahanAcak);
        Collections.shuffle(huruf);
        String[] acak = huruf.toArray(bahanAcak);
        Button btnKosong = (Button) findViewById(R.id.b16);
        btnKosong.setText("*");
        btnKosong.setBackgroundColor(Color.GREEN);
        for(int a = 0 ; a< 15 ; a++){
            int fix = a+1;
            String iden = "b"+ fix;
            buttonId[a]= getResources().getIdentifier(iden,"id",this.getPackageName());
            Button bb = (Button) findViewById(buttonId[a]);
            bb.setText(acak[a]);
            bb.setBackground(defbtn.getBackground());


            //debug
            Log.i("test", "acakHuruf: " + acak[a]);
            Log.i("test", "id: " + getResources().getIdentifier(iden,"id",this.getPackageName()));
            Log.i("test", "jawaban: " + jawaban[a]);
        }

    }
    //cek apakah game sudah finis atau belum
    public void cekFinish(){
        int jawabanBenar = 0;
        for(int a = 0; a<15 ; a++){
           Button btnhasil = (Button) findViewById(buttonId[a]);
           String hasil = (String) btnhasil.getText();
           if(hasil.equals(jawaban[a])){
               jawabanBenar ++;
           }
            Log.i("test", "jawaban Benar: " + jawabanBenar);
           if(jawabanBenar == 15){
               // game finish show dialog mulai kembali atau keluar game
              dialogManager("Selesai","You Win it, apakah kamu mau bermain lagi?");

           }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.m1){
            acakHuruf();
        }else if(item.getItemId() == R.id.m2){
            dialogManagerExit();
           // System.exit(0);
        }
        return true ;
    }
    /*
    public void menuAcak(View v){
        //acakHuruf();
    }
    public void menuKeluar(View v){
      //  dialogManager("test", "apakah berhasil?");
    }*/
    public void  dialogManager(String judul, String isi){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(judul);
        builder.setMessage(isi);
       builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"Here we Go Again!",Toast.LENGTH_SHORT).show();
                //mulai kembali
                acakHuruf();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //tidak mau main lagi
                Toast.makeText(MainActivity.this,"Main Lagi Ya Kalo ada waktu :* ",Toast.LENGTH_SHORT).show();
                System.exit(0);
            }
        });
        builder.show();
    }
    //dialog untuk keluar aplikasi
    public void  dialogManagerExit(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Keluar?");
        builder.setMessage("Apakah Kamu Yakin Mau Keluar dari sini?");
        builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               // Toast.makeText(MainActivity.this,"Here we Go Again!",Toast.LENGTH_SHORT).show();
                //mulai kembali
                System.exit(0);
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //tidak mau main lagi
                Toast.makeText(MainActivity.this,"yay ayo main lagi saja",Toast.LENGTH_SHORT).show();

            }
        });
        builder.show();
    }


}