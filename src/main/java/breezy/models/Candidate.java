package breezy.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import java.util.Date;
import java.util.List;

@JsonIgnoreProperties
public class Candidate {

    @JsonIgnore
    public String positionId;
    public String _id;
    public String meta_id;
    public String address;
    public AssignedTo assigned_to;
    public String cover_letter;
    public String creation_date;
    public List<Object> education;
    public String email_address;
    public List<String> followedByUserId;
    public String headline;
    public String initial;
    public String name;
    public String origin;
    public OverallScore overall_score;
    public String phone_number;
    public String profile_photo_url;
    public List<Questionnaire> questionnaire;
    public RecruitedBy recruitedBy;
    public RecruitedBy referredBy;
    public RecruitedBy sourced_by;
    public Resume resume;
    public List<SocialProfile> socialProfiles;
    public IdNameType source;
    @JsonIgnore
    public IdNameType stage;
    public String summary;
    public List<String> tags;
    public Date updateDate;
    public List<WorkHistory> workHistory;
    public List<Object> customAttributes;
    public Date dispositionDate;
    public DispositionReason disposition_reason;
    public Object candidateQuestionnnaire;

    public void setPositionId(String positionId) {
         this.positionId = positionId;
    }

    public void setCandidateQuestionnnaire(Object candidateQuestionnnaire) {
        this.candidateQuestionnnaire = candidateQuestionnnaire;
    }


    @Override
    public String toString() {
        return "Candidate{" +
                "positionId='" + positionId + '\'' +
                ", _id='" + _id + '\'' +
                ", meta_id='" + meta_id + '\'' +
                ", address='" + address + '\'' +
                ", assigned_to=" + assigned_to +
                ", cover_letter='" + cover_letter + '\'' +
                ", creation_date='" + creation_date + '\'' +
                ", education=" + education +
                ", email_address='" + email_address + '\'' +
                ", followedByUserId=" + followedByUserId +
                ", headline='" + headline + '\'' +
                ", initial='" + initial + '\'' +
                ", name='" + name + '\'' +
                ", origin='" + origin + '\'' +
                ", overall_score=" + overall_score +
                ", phone_number='" + phone_number + '\'' +
                ", profile_photo_url='" + profile_photo_url + '\'' +
                ", questionnaire=" + questionnaire +
                ", recruitedBy=" + recruitedBy +
                ", referredBy=" + referredBy +
                ", sourced_by=" + sourced_by +
                ", resume=" + resume +
                ", socialProfiles=" + socialProfiles +
                ", source=" + source +
                ", stage=" + stage +
                ", summary='" + summary + '\'' +
                ", tags=" + tags +
                ", updateDate=" + updateDate +
                ", workHistory=" + workHistory +
                ", customAttributes=" + customAttributes +
                ", dispositionDate=" + dispositionDate +
                ", disposition_reason=" + disposition_reason +
                ", candidateQuestionnnaire=" + candidateQuestionnnaire +
                '}';
    }
}
