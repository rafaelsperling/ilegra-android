package com.example.lancamentodehoras.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lancamentodehoras.R;
import com.example.lancamentodehoras.domain.Horas;

public class AtividadeListAdapter extends ArrayAdapter<Horas> {

	public List<Horas> itens;
	
	public AtividadeListAdapter(Context context, int resource,
			List<Horas> itens) {
		super(context, resource, itens);
		this.itens = itens;
	}

	@Override
	public int getCount() {
		return itens.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View gridView = convertView;
		if (convertView == null) { // if it's not recycled, initialize some
			// attributes
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			gridView = inflater.inflate(R.layout.horas_item, parent, false);
		}
		Horas hora = itens.get(position);
		((TextView) gridView.findViewById(R.id.dataInicial)).setText(hora.getData());
		((TextView) gridView.findViewById(R.id.descricao)).setText(hora.getDesc());
		((TextView) gridView.findViewById(R.id.dataFinal)).setText(hora.getDataFinal());
//		((ImageView) gridView.findViewById(R.id.menu_icon)).setBackgroundResource(menu.getImageResourse());
		return gridView;
	}

}
