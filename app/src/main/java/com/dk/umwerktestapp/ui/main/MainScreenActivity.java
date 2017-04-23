package com.dk.umwerktestapp.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.dk.umwerktestapp.App;
import com.dk.umwerktestapp.R;
import com.dk.umwerktestapp.ui.main.list.UsersAdapter;
import com.dk.umwerktestapp.ui.main.model.UiBaseUser;
import com.dk.umwerktestapp.ui.user.UserDetailActivity;
import com.dk.umwerktestapp.utils.networking.InternetConnection;
import com.dk.umwerktestapp.utils.view.dialogs.MaterialDialogListener;
import com.dk.umwerktestapp.utils.view.dialogs.MaterialProgressDialog;
import com.dk.umwerktestapp.utils.view.dialogs.Toaster;
import com.dk.umwerktestapp.utils.view.recycler.DividerItemDecoration;
import com.dk.umwerktestapp.utils.view.recycler.RecyclerClickListener;

import java.util.List;

import javax.inject.Inject;

public class MainScreenActivity extends AppCompatActivity implements MainScreenMvp.View,
        RecyclerClickListener, MaterialDialogListener {

    @Inject MainScreenMvp.Presenter presenter;
    @Inject MaterialProgressDialog progressDialog;

    RecyclerView recyclerView;
    private UsersAdapter adapter;
    private List<UiBaseUser> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeDagger();

        if (!InternetConnection.isNetworkAvailable(this)) {
            progressDialog.showErrorDialog();
        } else {

            progressDialog.showChooserDialog();

            adapter = new UsersAdapter(this, this);
            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext()));
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.new_search) {
            progressDialog.showChooserDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showUsers(List<UiBaseUser> items) {
        this.items = items;
        adapter.setData(items);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onRowClick(ImageView imageView, int position) {
        openUserDetails(items.get(position), imageView);
    }
    @Override
    public void openUserDetails(UiBaseUser data, ImageView imageView) {
        String transitionName = ViewCompat.getTransitionName(imageView);

        Pair<View, String> p1 = Pair.create(imageView, transitionName);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                .makeSceneTransitionAnimation(this,  p1);
        startActivity(UserDetailActivity.getIntent(getApplicationContext(), data, transitionName), optionsCompat.toBundle());
    }

    @Override
    public void showBlockingLoading() {
        progressDialog.showBlockingLoading();
    }

    @Override
    public void hideBlockingLoading() {
        progressDialog.hideBlockingLoading();
    }

    @Override
    public void onIncorrectEntry() {
        Toaster.showErrorToast(this, getString(R.string.error_message_invalid_query));
        progressDialog.showChooserDialog();
    }

    @Override
    public void showErrorMessage(String message) {
        Toaster.showErrorToast(this, message);
    }

    private void initializeDagger() {
        DaggerMainScreenComponent.builder()
                .networkComponent(((App) getApplicationContext()).getNetworkComponent())
                .mainScreenModule(new MainScreenModule(this, this, this))
                .build().inject(this);
    }

    @Override
    public void onInput(MaterialDialog dialog, CharSequence input) {
        showBlockingLoading();
        presenter.loadUsers(input.toString());
    }

    @Override
    public void onCloseApp(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
        finish();
    }
}
