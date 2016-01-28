package com.adalbertofjr.minhaviagem.ui;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.adalbertofjr.minhaviagem.R;
import com.adalbertofjr.minhaviagem.ui.fragments.ViagemListFragment;

/**
 * Created by AdalbertoF on 28/01/2016.
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigationMenu;
    private int mOpcaoSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        //Navigation Drawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                mToolbar,
                R.string.app_name,
                R.string.app_name
        );
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        //Navigation Menu
        mNavigationMenu = (NavigationView) findViewById(R.id.navigation_view);
        mNavigationMenu.setNavigationItemSelectedListener(this);

        if(savedInstanceState == null){
            mOpcaoSelecionada = R.id.action_viagem;
        }else{
            mOpcaoSelecionada = savedInstanceState.getInt("menuItem");
        }

        selecionarOpcaoMenu(mNavigationMenu.getMenu().findItem(mOpcaoSelecionada));

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.conteudo, new ViagemListFragment(), "viagens_list");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("menuItem", mOpcaoSelecionada);
    }

    private void selecionarOpcaoMenu(MenuItem item) {
        mOpcaoSelecionada = item.getItemId();
        item.setCheckable(true);
        mDrawerLayout.closeDrawers();

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_viagem){
            Toast.makeText(this, "Minhas viagens", Toast.LENGTH_SHORT).show();
            mDrawerLayout.closeDrawers();
            return true;
        }

        if(item.getItemId() == R.id.action_gasto){
            Toast.makeText(this, "Meus Gastos", Toast.LENGTH_SHORT).show();
            mDrawerLayout.closeDrawers();
            return true;
        }

        if(item.getItemId() == R.id.action_configuracoes){
            Toast.makeText(this, "Configurações", Toast.LENGTH_SHORT).show();
            mDrawerLayout.closeDrawers();
            return true;
        }

        return false;
    }
}
