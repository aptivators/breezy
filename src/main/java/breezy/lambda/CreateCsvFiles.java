package breezy.lambda;


import breezy.models.Candidate;
import breezy.modules.WebClientModule;
import com.amazonaws.services.lambda.runtime.Context;
import org.apache.http.HttpStatus;


import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class CreateCsvFiles {

    public List<Candidate> handler(Object input, Context context) throws IOException {
        context.getLogger().log("Input: " + input);
        var breezyService = WebClientModule.getBreezyService();

        var positions = breezyService
                .getPositionsForCompany("a009c456ae81").execute().body();
        context.getLogger().log(String.format("Retrieved list of positions: %s", positions));


        var abrCandidatesWithPositionId = positions
                .stream()
                .flatMap(p -> {
                    try {
                        var pos = breezyService.getAllAbrCandidatesForPosition("a009c456ae81", p._id).execute().body();
                        context.getLogger().log(String.format("is null? %s", pos == null));
                        context.getLogger().log(String.format("results of positions call %s", pos.toString()));
                             return   pos
                                .stream()
                                .map(c -> {
                                            c.setPositionId(p._id);
                                            return c;
                                        }
                                );
                    } catch (IOException e) {
                        e.printStackTrace();
                        return Stream.of(new Candidate());
                    }
                })
                .collect(Collectors.toList());


        context.getLogger().log(String.format("Retrieved list of candidates for positions: %s", abrCandidatesWithPositionId));

        var fullCandidateObjects = abrCandidatesWithPositionId
                .stream()
                .limit(110)
                .filter(Objects::nonNull)
                .filter(c -> c.positionId != null)
                .map(candidate -> {
                    try {
                        var response = breezyService.getCandidateByIdAndPosition("a009c456ae81", candidate.positionId,
                                candidate._id).execute();
                        context.getLogger().log(String.format("results of getCandidate by id and pos is null? %s", response.body() == null));

                        if (response.code() == 429) {
                            context.getLogger().log("Exceeded 100 requests per minute 1");
                            Thread.sleep(30000);
                            return null;
                        }
                       var result = response.body();

                        result.setPositionId(candidate.positionId);

                        return result;

                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                        return new Candidate();
                    }

                })
                .collect(Collectors.toList());

        context.getLogger().log("anything");

        var candidatesWithQuestionnaires = fullCandidateObjects
                .stream()
                .limit(110)
                .filter(Objects::nonNull)
                .map(c ->  {context.getLogger().log(String.format("Candidate is not null filter %s ", c.toString()));
                return c;})
                .filter(c -> c.positionId != null)
                .map(c ->  {context.getLogger().log(String.format("Candidate position id filter %s ", c.toString()));
                    return c;})
                .map(candidate -> {
                    try {
                        context.getLogger().log(String.format("Candidate is %s ", candidate.toString()));
                        var response = breezyService.getCandidateQuestionnaires("a009c456ae81", candidate.positionId,
                                candidate._id).execute();
                        if (response.code() == 429) {
                            context.getLogger().log("Exceeded 100 requests per minute 2");
                            Thread.sleep(30000);
                            return candidate;
                        }
                        var questionnaire = response.body();
                        context.getLogger().log(String.format("this is the questionnaire thing %s", questionnaire));

                        candidate.setCandidateQuestionnnaire(questionnaire);

                        return candidate;

                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                        return new Candidate();
                    }
                }).collect(Collectors.toList());

        context.getLogger().log("anything");
        return candidatesWithQuestionnaires;

    }

}
