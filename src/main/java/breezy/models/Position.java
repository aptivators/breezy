package breezy.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties
public class Position {
    public String _id;
    public IdNameType type;
    public String state;
    public String name;
    public String friendly_id;
    public IdNameType experience;
    public Location location;
    public String education;
    public String department;
    public String requisition_id;
    public String description;
    public IdNameType category;
    public ApplicationForm application_form;
    public String creator_id;
    public String creation_date;
    public String updated_date;
    public String questionnaire_id;
    public String scorecard_id;
    public List<String> all_users;
    public List<String> all_admins;
    public String pipeline_id;
    public String candidate_type;
    public String org_type;
    public List<Object> custom_attributes;
    public List<String> tags;
//    public PendingApproval pending_approval;
}
