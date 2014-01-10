package com.example.lancamentodehoras;

import com.example.lancamentodehoras.dao.HorasDAO;
import com.example.lancamentodehoras.domain.Horas;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LancamentoActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancamento);
        
	       findViewById(R.id.saveButton).setOnClickListener(new Button.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					EditText data = (EditText) findViewById(R.id.editText1);
					EditText dataFinal = (EditText) findViewById(R.id.EditText01);
					EditText desc = (EditText) findViewById(R.id.editText2);
					
					if(data.getText().toString().isEmpty() 
							|| dataFinal.getText().toString().isEmpty()
							|| desc.getText().toString().isEmpty()){
						Toast.makeText(getApplicationContext(), "Data e descrição obrigatórios!",  Toast.LENGTH_LONG).show();
						return;
					}
					
					HorasDAO dao = new HorasDAO(getApplicationContext());
					Horas hora = new Horas(null, data.getText().toString(), dataFinal.getText().toString(),desc.getText().toString());
					dao.saveHoras(hora);

					dao.showLogHoras();
					
					finish();	
				}
			});

	}
	
}
