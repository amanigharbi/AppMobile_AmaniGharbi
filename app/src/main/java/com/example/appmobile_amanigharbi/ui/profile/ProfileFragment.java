package com.example.appmobile_amanigharbi.ui.profile;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appmobile_amanigharbi.R;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpGet;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.DefaultHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;
    RecyclerView rv;
    List<ModelData> dataSource;
    LinearLayoutManager linearLayoutManager;
    MyRvAdapter myRvAdapter;
    private RequestQueue mQueue;
    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_profile, container, false);
        rv = (RecyclerView) view.findViewById(R.id.horizontalRv);

        //Setting the data source
        List<ModelData> dataSource = new ArrayList<>();
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,"http://api.shield.kaisens.fr/api/users/raphael1992/childs/",null,new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i=0;i<response.length();i++){
                        JSONObject jsonObject = response.getJSONObject(i);
                        String FirstName = jsonObject.getString("first_name");
                        String LastName = jsonObject.getString("last_name");
                        String Gender = jsonObject.getString("gender");
                        int Id = jsonObject.getInt("id");
                        String Age = "";

                        try {
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                            Date Birthday = format.parse(jsonObject.getString("birthday"));
                            Calendar cal = Calendar.getInstance();
                            int CurrentYear=cal.get(Calendar.YEAR);
                            cal.setTime(Birthday);
                            int BirthYear=cal.get(Calendar.YEAR);
                            Age= String.valueOf(CurrentYear-BirthYear);

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
ModelData obj=new ModelData(Id, Gender,FirstName, LastName,Age);
                        dataSource.add(obj);
                    }
                    linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    myRvAdapter = new MyRvAdapter(dataSource);
                    rv.setLayoutManager(linearLayoutManager);
                    rv.setAdapter(myRvAdapter);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("my-api",error.getMessage());
            }
        });
        requestQueue.add(jsonArrayRequest);
        requestQueue.start();
return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        // TODO: Use the ViewModel
    }

}