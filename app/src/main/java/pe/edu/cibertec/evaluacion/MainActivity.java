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
import java.util.List;

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
    JobAdapter jobAdapter;
    List<Job> items;
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


                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jobs.github.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                JobInterface jobInterface = retrofit.create(JobInterface.class);


                Call<List<Job>> methodSearch = jobInterface.searchJob( etCompany.getText().toString());

                methodSearch.enqueue(new Callback<List<Job>>() {
                    @Override
                    public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {


                        if (response.isSuccessful()) {
                            items = response.body();
                            jobAdapter = new JobAdapter(items);
                            rvJobs.setAdapter(jobAdapter);
                            rvJobs.setLayoutManager
                                    (new LinearLayoutManager(MainActivity.this));

                        }
                    }

                    @Override
                    public void onFailure(Call<List<Job>> call, Throwable t) {
                        Log.d("Error", t.toString());
                    }


                });

            }
        });
    }


}

