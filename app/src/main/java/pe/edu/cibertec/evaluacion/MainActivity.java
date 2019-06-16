package pe.edu.cibertec.evaluacion;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Button btSearch;
    TextInputEditText etCompany;
    TextView tvTitle, tvDescription, tvCompany;
    ImageView ivLogo;

    ArrayList<Job> items;
    RecyclerView rvJobs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etCompany = findViewById(R.id.etCompany);
        btSearch = findViewById(R.id.btnSearch);
        tvTitle = findViewById(R.id.tvTitle);
        tvDescription = findViewById(R.id.tvDescription);
        tvCompany = findViewById(R.id.tvCompany);
        ivLogo = findViewById(R.id.imageView);

        btSearch.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String job = etCompany.getText().toString();

                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jobs.github.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                JobInterface jobInterface = retrofit.create(JobInterface.class);



                Call<ArrayList<Job>> methodSearch = jobInterface.searchJob(job);

                methodSearch.enqueue(new Callback<ArrayList<Job>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Job>> call, Response<ArrayList<Job>> response) {



                            if (response.isSuccessful()) {
                                ArrayList<Job> job = response.body();
                                items.addAll(job);
                                JobAdapter  jobAdapter = new JobAdapter(items);
                                rvJobs.setAdapter(jobAdapter);
                                rvJobs.setLayoutManager
                                            (new LinearLayoutManager(MainActivity.this));

                            }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Job>> call, Throwable t) {
                        Log.d("Error", t.toString());
                    }
                });

            }
        });
    }


}

