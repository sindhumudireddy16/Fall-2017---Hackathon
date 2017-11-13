package com.umkc.medspace.api;


import com.umkc.medspace.Constants.Constants;
import com.umkc.medspace.api.objects.Details;
import com.umkc.medspace.api.request.Doctor_Login_Request;
import com.umkc.medspace.api.request.Doctor_SignUp_Request;
import com.umkc.medspace.api.request.Patient_SignUp_Request;
import com.umkc.medspace.api.request.Slot_Request;
import com.umkc.medspace.api.response.Doctor_Login_Response;
import com.umkc.medspace.api.response.Doctor_SignUp_Response;
import com.umkc.medspace.api.response.Hospitals;
import com.umkc.medspace.api.response.Patient_Login_Response;
import com.umkc.medspace.api.response.Patient_SignUp_Response;
import com.umkc.medspace.api.response.Slot_response;
import com.umkc.medspace.api.response.Specialists;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServices {

    @POST(Constants.REGISTER_URL)
    Call<Doctor_SignUp_Response> getRegistered(@Body Doctor_SignUp_Request request);

    @POST(Constants.DOCTOR_LOGIN)
    Call<Doctor_Login_Response> doctorLogin(@Body Doctor_Login_Request request);

    @POST(Constants.PATIENT_LOGIN)
    Call<Patient_Login_Response> patientLogin(@Body Doctor_Login_Request request);

    @GET(Constants.HOSPITALS)
    Call<Hospitals> getHospitals();

    @GET(Constants.SPECIALISTS)
    Call<Specialists> getSpecialists();

    @GET(Constants.DETAILS)
    Call<Details> getDetails();

    @POST(Constants.SLOTS)
    Call<Slot_response> setslot(@Body Slot_Request request);

    @POST(Constants.PATIENT_SIGNUP)
    Call<Patient_SignUp_Response> psignup(@Body Patient_SignUp_Request request);

}
