package com.ynov.cours_ynov_navigation.network;

import com.ynov.cours_ynov.models.ApiResponse;
import com.ynov.cours_ynov.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ShokoApiService {

    // region GETTER ALL
    // region Used
    @GET("/products")
     Call<ApiResponse<List<Product>>> getProducts();

    // endregion Used
    // endregion GETTER ALL


    // region PATCH
//    @PATCH("/api/Reassort/{id}")
//    Call<List<Reassort>> getReassortById(@Path("id") Integer id, Reassort reassort);
    // endregion PATCH
}
