package com.example.lancamentodehoras;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lancamentodehoras.adapter.AtividadeListAdapter;
import com.example.lancamentodehoras.dao.HorasDAO;
import com.example.lancamentodehoras.domain.Horas;

public class WelcomeActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

		String nome = getIntent().getStringExtra("nome");
		String senha = getIntent().getStringExtra("senha");
		
		TextView textview = (TextView)findViewById(R.id.nome); 
		textview.setText("Bem vindo: "+nome);
		
		TextView textview1 = (TextView)findViewById(R.id.senha); 
		textview1.setText("O total de horas no mês é de: "+((senha==null)?"0":senha.length()));
		
	       findViewById(R.id.addAtividade).setOnClickListener(new Button.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(getApplicationContext(), LancamentoActivity.class);
					startActivity(intent);
					
				}
			});

	       findViewById(R.id.closeApp).setOnClickListener(new Button.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					finish();
				}
			});
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		ListView list = (ListView) findViewById(R.id.listView1);
		HorasDAO dao = new HorasDAO(getApplicationContext());
		List<Horas> horas = dao.retrieveHoras();

		list.setAdapter(new AtividadeListAdapter(getApplicationContext(),
				R.layout.horas_item, horas));
	}
}
