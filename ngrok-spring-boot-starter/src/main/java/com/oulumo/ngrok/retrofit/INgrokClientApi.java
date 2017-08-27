package com.oulumo.ngrok.retrofit;

import com.oulumo.ngrok.retrofit.dto.Tunnel;
import com.oulumo.ngrok.retrofit.dto.TunnelCreationRequest;
import com.oulumo.ngrok.retrofit.dto.TunnelListResponse;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * @author Zsolt Homorodi.
 */
public interface INgrokClientApi {
    @GET("/api/tunnels")
    Call<TunnelListResponse> listTunnels();

    @POST("/api/tunnels")
    Call<Tunnel> createTunnel(@Body TunnelCreationRequest request);

    @DELETE("/api/tunnels/{tunnelName}")
    Call<Void> deleteTunnel(@Path("tunnelName") String tunnelName);
}
