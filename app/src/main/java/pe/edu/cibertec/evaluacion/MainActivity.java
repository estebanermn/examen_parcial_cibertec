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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button btSearch;
    TextInputEditText etJob;
    JobAdapter adapter;
    List<Job> items;
    RecyclerView rvJobs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etJob = findViewById(R.id.etJob);
        btSearch = findViewById(R.id.btnSearch);
        rvJobs = findViewById(R.id.rvJob);


        btSearch.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String url = "https://jobs.github.com/";


                Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();


                JobInterface jobInterface = retrofit.create(JobInterface.class);

                Call<List<Job>> searchMethod = jobInterface.searchJobs(etJob.getText().toString());
                searchMethod.enqueue(new Callback<List<Job>>() {
                    @Override
                    public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {

                        if (response.isSuccessful()) {

                            items = response.body();
                            adapter = new JobAdapter(items);

                            rvJobs.setAdapter(adapter);
                            rvJobs.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            adapter.notifyDataSetChanged();
                            Log.d("TAg", "Success");
                        } else {
                            Toast.makeText(MainActivity.this, "Server returned an error", Toast.LENGTH_SHORT).show();
                            System.out.println("error" + adapter);
                        }
                    }


                    @Override
                    public void onFailure(Call<List<Job>> call, Throwable t) {
                        Log.d("Error", t.toString());
                        //t.printStackTrace();
                        Toast.makeText(MainActivity.this, "network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                        System.out.println(" :::: adapter " + adapter);
                        System.out.println(":::: items " +items);
                    }


                });

            }
        });
    }


}

