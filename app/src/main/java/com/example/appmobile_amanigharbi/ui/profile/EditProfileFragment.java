package com.example.appmobile_amanigharbi.ui.profile;

import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appmobile_amanigharbi.R;
import android.app.DatePickerDialog;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class EditProfileFragment extends Fragment {
    private EditText dateEdt;
    private EditText FirstName;
    private EditText LastName;
    private RadioButton Genre1;
    private RadioButton Genre2;
    private RadioButton Genre3;
    private Button btnEdit;
    private EditProfileViewModel mViewModel;
    private String genre ="M";


    public static EditProfileFragment newInstance() {
        return new EditProfileFragment();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_edit_profile, container, false);
// on below line we are initializing our variables.
        dateEdt = (EditText) view.findViewById(R.id.birth);
        FirstName = (EditText) view.findViewById(R.id.prenom);
        LastName = (EditText) view.findViewById(R.id.nom);
        Genre1 = (RadioButton) view.findViewById(R.id.radioButton);
        Genre2 = (RadioButton) view.findViewById(R.id.radioButton2);
        Genre3 = (RadioButton) view.findViewById(R.id.radioButton3);
        btnEdit =(Button) view.findViewById(R.id.btn_edit);
        // on below line we are adding click listener
        // for our pick date button
        dateEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting
                // the instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our edit text.
                                dateEdt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking if the edit text is empty or not.
                if ((FirstName.getText().toString().isEmpty()) && LastName.getText().toString().isEmpty()) {
                    // displaying a toast message if the edit text is empty.
                    Toast.makeText(getContext(), "Please enter your data..", Toast.LENGTH_SHORT).show();
                    return;
                }
                // calling a method to update data in our API.
                if (Genre1.isChecked()){
                    genre="M";
                }
                if (Genre2.isChecked()){
                    genre="F";
                }
                if (Genre3.isChecked()){
                    genre="A";
                }

                callPUTDataMethod(FirstName.getText().toString(), LastName.getText().toString(),genre,dateEdt.getText().toString());
            }
        });
        return view;
    }
    private void callPUTDataMethod(String FirstName, String LastName,String genre,String birth) {
        String url = "http://api.shield.kaisens.fr/api/child/639/";
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.PATCH, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getContext(), "Data Updated..", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String output = jsonObject.getString("first_name") + "\n" + jsonObject.getString("last_name") + "\n" + jsonObject.getString("birthday") + "\n" + jsonObject.getString("gender");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Fail to update data..", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("first_name", FirstName);
                params.put("last_name", LastName);
                params.put("birthday", dateEdt.toString());
                params.put("gender", genre);
                return params;
            }
        };
        queue.add(request);
    }
            @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(EditProfileViewModel.class);
        // TODO: Use the ViewModel
    }

}