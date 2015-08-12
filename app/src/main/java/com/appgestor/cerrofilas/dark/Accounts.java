package com.appgestor.cerrofilas.dark;

import android.content.Intent;
import android.os.Bundle;

import com.appgestor.cerrofilas.Entities.Estudiante;
import com.appgestor.cerrofilas.R;
import com.appgestor.cerrofilas.mockedActivity.Settings;
import com.appgestor.cerrofilas.mockedFragments.FragmentIndex;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;
import it.neokree.materialnavigationdrawer.elements.listeners.MaterialAccountListener;


public class Accounts extends MaterialNavigationDrawer implements MaterialAccountListener {

    @Override
    public void init(Bundle savedInstanceState) {
        // add accounts
        MaterialAccount account = new MaterialAccount(this.getResources(), Estudiante.getEstudiante().getNombre(),
                            Estudiante.getEstudiante().getCorreo(),R.drawable.photo, R.drawable.bamboo);
        this.addAccount(account);

        //MaterialAccount account2 = new MaterialAccount(this.getResources(),"Hatsune Miky","hatsune.miku@example.com",R.drawable.photo2,R.drawable.mat2);
        //this.addAccount(account2);

        // set listener
        this.setAccountListener(this);

        // create sections
        this.addSection(newSection("Perfil", new FragmentIndex()));
        this.addSection(newSection("Tomar turno",new FragmentIndex()));
        this.addSection(newSection("Mapas", new FragmentIndex()));
        this.addSection(newSection("Universidad",new FragmentIndex()));

        //this.addSection(newSection("Section 3",R.mipmap.ic_mic_white_24dp,new FragmentButton()).setSectionColor(Color.parseColor("#9c27b0")));
        //this.addSection(newSection("Section",R.mipmap.ic_hotel_grey600_24dp,new FragmentButton()).setSectionColor(Color.parseColor("#03a9f4")));

        // create bottom section
        this.addBottomSection(newSection("Cerrar Sesión",R.mipmap.ic_settings_black_24dp,new Intent(this,Settings.class)));
    }

    @Override
    public void onAccountOpening(MaterialAccount materialAccount) {

    }

    @Override
    public void onChangeAccount(MaterialAccount materialAccount) {

    }
}
