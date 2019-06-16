package pe.edu.cibertec.evaluacion;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JobInterface {

    @GET("/positions.json?")
    Call<ArrayList<Job>> searchJob(@Query("description") String job);
}
