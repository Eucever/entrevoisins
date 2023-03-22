package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DescNeighbourActivity extends AppCompatActivity {

    private Neighbour neighbour;

    private NeighbourApiService mApiService;

    @BindView(R.id.txtprenom)
    public TextView txtprenom;

    @BindView(R.id.prenomphoto)
    public TextView txtprenomPhoto;

    @BindView(R.id.lieuresidence)
    public TextView txtresid;

    @BindView(R.id.numerotel)
    public TextView txtnumtel;

    @BindView(R.id.facebook)
    public TextView txtfaceb;

    @BindView(R.id.txtDesc)
    public TextView txtdesc;

    @BindView(R.id.avatardesc)
    public ImageView avatar;

    @BindView(R.id.buttonFavorite)
    public FloatingActionButton btnfav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc_neighbour);


        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

        neighbour = (Neighbour) getIntent().getSerializableExtra("CLE_VOISIN");
        Log.i("Donnée envoyé", neighbour.getName());

        ButterKnife.bind(this);
        txtprenom.setText(neighbour.getName());
        txtprenomPhoto.setText(neighbour.getName());
        txtresid.setText(neighbour.getAddress());
        txtnumtel.setText(neighbour.getPhoneNumber());
        txtfaceb.setText("www.facebook.fr/"+neighbour.getName().toLowerCase());
        txtdesc.setText(neighbour.getAboutMe());

        Glide.with(avatar.getContext())
                .load(neighbour.getAvatarUrl())
                .centerCrop()
                .into(avatar);

        mApiService= DI.getNeighbourApiService();

        btnfav.setOnClickListener(view -> {
            mApiService.setFavourite(neighbour.getId(), !neighbour.isFavourite());
            neighbour.setFavourite(!neighbour.isFavourite());
            refreshFavouriteIcon();
        });
        refreshFavouriteIcon();
    }

    private void refreshFavouriteIcon() {
        btnfav.setImageResource(neighbour.isFavourite()?R.drawable.ic_star_white_24dp:R.drawable.ic_star_border_white_24dp);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void navigate(Context context, Neighbour n) {
        Intent descIntent = new Intent(context, DescNeighbourActivity.class);
        descIntent.putExtra("CLE_VOISIN", n);
        ActivityCompat.startActivity(context, descIntent,null);
    }

}