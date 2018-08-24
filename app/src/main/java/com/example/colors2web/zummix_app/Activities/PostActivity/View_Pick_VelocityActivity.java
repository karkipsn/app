package com.example.colors2web.zummix_app.Activities.PostActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.colors2web.zummix_app.Activities.PostActivity.Adapters.Pick_Velocity_BoxAdapter;
import com.example.colors2web.zummix_app.Activities.PostActivity.Adapters.Sales_History_Adapter;
import com.example.colors2web.zummix_app.ItemDecoration.SimpleItemDecoration;
import com.example.colors2web.zummix_app.POJO.PostSearch.Boxes;
import com.example.colors2web.zummix_app.POJO.PostSearch.CustomerItems;
import com.example.colors2web.zummix_app.POJO.PostSearch.MergeBCI;
import com.example.colors2web.zummix_app.POJO.PostSearch.Orders;
import com.example.colors2web.zummix_app.POJO.ProductSearch.CustomerItem;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.SearchFragment;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

public class View_Pick_VelocityActivity extends AppCompatActivity {

    Toolbar toolbar;
    APIInterface apiInterface;
    RecyclerView mrecycleView;
    Pick_Velocity_BoxAdapter PickAdapter;
    List<CustomerItems> ItemsList;
    List<Boxes> BoxList;
    List<MergeBCI>newarray;
    ImageView img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_display);


        apiInterface = APIClient.getClient().create(APIInterface.class);
        newarray = new ArrayList<MergeBCI>();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mrecycleView = findViewById(R.id.recycleview_post);
//        PickAdapter = new Pick_Velocity_BoxAdapter(BoxList,ItemsList);
        PickAdapter = new Pick_Velocity_BoxAdapter(newarray);

        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(this);
//        mrecycleView.setHasFixedSize(true);
        mrecycleView.setLayoutManager(mlayoutManager);

        mrecycleView.addItemDecoration(new SimpleItemDecoration(this));
        mrecycleView.setItemAnimator(new DefaultItemAnimator());
        mrecycleView.setAdapter(PickAdapter);
        loadAdapter();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu2, menu);
        ImageView img;

        img = (ImageView) menu.findItem(R.id.image).getActionView();
        img.setImageResource(android.R.drawable.ic_menu_search);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.main_toolbar, new SearchFragment()).commit();
            }
        });

        return true;
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.image:

                getSupportFragmentManager().beginTransaction().
                        replace(R.id.main_toolbar, new SearchFragment()).addToBackStack(null).commit();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadAdapter() {
        Intent i = getIntent();
        if (i != null) {

            List<CustomerItems> list = (List<CustomerItems>) i.getSerializableExtra("CList");

            List<Boxes> blist = (List<Boxes>) i.getSerializableExtra("BList");

//            PickAdapter.updateAnswers(blist,list);

//            ArrayList<Employee> employees = new ArrayList<Employee>();
//for(Person person : personList) {
//  for(PersonNpi personNpi : npiList) {
//    if (person.getName().equals(personNpi.getName()) &&
//        person.getAddress().checkEquality(...address parts here...)) {
//      employees.add(new Employee(person, personNpi));
//    }
//  }
//}
            for(CustomerItems ci:list){
                for(Boxes bi:blist){

                    if(ci.getItemSkuNumber().equals(bi.getItemSku())){

                      newarray.add(new MergeBCI(ci,bi));
                        PickAdapter.updateAnswers(newarray);}
                    else {

                        newarray.add(new MergeBCI(ci));
                        PickAdapter.updateAnswers(newarray);

                    }
                }

            }

        }
    }
}
