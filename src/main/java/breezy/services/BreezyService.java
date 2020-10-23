package breezy.services;

import breezy.models.Candidate;
import breezy.models.Position;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

import java.util.List;

public interface BreezyService {

//https://api.breezy.hr/v3/company/:company_id/positions
//Retrieve all positions for given company in given state (status)
    @GET("company/{companyId}/positions")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<List<Position>> getPositionsForCompany(@Path("companyId") String companyId);

//https://api.breezy.hr/v3/company/:company_id/position/:position_id/candidates
//Retrieve candidates for given position with an abbreviated candidate object
    @GET("company/{companyId}/position/{positionId}/candidates")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<List<Candidate>> getAllAbrCandidatesForPosition(@Path("companyId") String companyId, @Path("positionId") String positionId);

//https://api.breezy.hr/v3/company/:company_id/position/:position_id/candidate/:candidate_id
//Retrieve candidate by id for given position
    @GET("company/{companyId}/position/{positionId}/candidate/{candidateId}")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<Candidate> getCandidateByIdAndPosition(@Path("companyId") String companyId, @Path("positionId") String positionId,
                                         @Path("candidateId") String candidateId);


//https://api.breezy.hr/v3/company/:company_id/position/:position_id/candidate/:candidate_id/questionnaires
//Retrieve candidate questionnaires
@GET("company/{companyId}/position/{positionId}/candidate/{candidateId}/questionnaires")
@Headers({ "Content-Type: application/json;charset=UTF-8"})
Call<Object> getCandidateQuestionnaires(@Path("companyId") String companyId, @Path("positionId") String positionId,
                                            @Path("candidateId") String candidateId);

}
