package com.example.lancamentodehoras;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
//	private int onDestroy;
//	private int onStart;
//	private int onStop;
//	private int onPause;
	HashMap<String, String> map = new HashMap<String, String>();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        saveStringToDatabase();
        setContentView(R.layout.activity_login);
        SharedPreferences settings = getPreferences(Context.MODE_PRIVATE);
		EditText nome = (EditText) findViewById(R.id.editText1);
		EditText senha = (EditText) findViewById(R.id.editText2);

		nome.setText(settings.getString("username", null));
		senha.setText(settings.getString("senha", null));

        findViewById(R.id.button1).setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText nome = (EditText) findViewById(R.id.editText1);
				EditText senha = (EditText) findViewById(R.id.editText2);
				
				if(nome.getText().toString().isEmpty() || senha.getText().toString().isEmpty()){
					Toast.makeText(getApplicationContext(), "Nome e senha obrigat—rio!",  Toast.LENGTH_LONG).show();
					return;
				}
				
                FileInputStream inputStream = null;
                try {
                    inputStream = openFileInput("horas.txt");
                    byte[] reader = new byte[inputStream.available()];
                    while (inputStream.read(reader) != -1) {}
                    String loadText = new String(reader);
                    parse(loadText);
                    String senhaDefault = getValue("senha");
                    String nomeDefault = getValue("username");
                    if(!nomeDefault.equals(nome.getText().toString()) || !senhaDefault.equals(senha.getText().toString())){
                    	Toast.makeText(getApplicationContext(), getValue("msg"),  Toast.LENGTH_LONG).show();
    					return;
                    }
                    
                    System.out.println(map);
                } catch(IOException e) {
                    Log.e("!", e.getMessage());
                } finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {

                            Log.e("!", e.getMessage());
                        }
                    }
                }

				
//				Toast.makeText(getApplicationContext(), "Nome: "+nome.getText() + " Senha: "+senha.getText(),
//			  			   Toast.LENGTH_LONG).show();
				Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
				intent.putExtra("nome", ""+nome.getText());
				intent.putExtra("senha", ""+senha.getText());
				
				SharedPreferences settings = getPreferences(Context.MODE_PRIVATE);
				settings.edit().putString("username", nome.getText().toString()).commit();
				settings.edit().putString("senha", senha.getText().toString()).commit();
				startActivity(intent);
				finish();
			}
		});
    }

    public void parse(String foo) {
    	  String foo2 = foo.substring(0, foo.length());  // hack off braces
    	  StringTokenizer st = new StringTokenizer(foo2, "\n");
    	  while (st.hasMoreTokens()) {
    	    String thisToken = st.nextToken();
    	    StringTokenizer st2 = new StringTokenizer(thisToken, "=");

    	    map.put(st2.nextToken(), st2.nextToken());
    	  }
    	}

    String getValue(String key) {
    	  return map.get(key).toString();
    }
    	
    private void saveStringToDatabase(){
    	String FILENAME = "horas.txt";
    	String data = "username=rafael\nsenha=sperling\nmsg=Usu‡rio ou senha inv‡lidos!";
    	try {
			FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
			fos.write(data.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
//    @Override
//    protected void onStart() {
//    	super.onStart();
//    	onStart +=1;
//    	Toast.makeText(getApplicationContext(), "onStart: "+onStart,
//  			   Toast.LENGTH_LONG).show();
//    }
//    
//    @Override
//    protected void onStop() {
//    	super.onStop();
//    	onStop +=1;
//    	Toast.makeText(getApplicationContext(), "onStop: "+onStop,
//  			   Toast.LENGTH_LONG).show();
//    }
//    
//    @Override
//    protected void onDestroy() {
//    	super.onDestroy();
//    	onDestroy +=1;
//    	Toast.makeText(getApplicationContext(), "onDestroy: "+onDestroy,
// 			   Toast.LENGTH_LONG).show();
//    }
//    
//    @Override
//    protected void onPause() {
//    	super.onPause();
//    	onPause +=1;
//    	Toast.makeText(getApplicationContext(), "onPause: "+onPause,
//    			   Toast.LENGTH_LONG).show();
//    }
}
