/*
 * This is a class GENERATED by the TransportGenerator maven plugin. DON'T MODIFY IT.
 * IF you modify it, your work may be lost: the class will be overwritten automatically
 * when the maven plugin is re-executed for any reasons.
 */
package de.fhg.iais.roberta.generated.restEntities;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * the request description for the /program/userGroupMembers/names REST request, response is: ListingNamesResponse<br><br>
 * Version: 1<br>
 * Datum: 2020-06-15
 */
public class UserGroupProgramListRequest extends BaseRequest {
    protected String groupName;
    
    /**
     * the request description for the /program/userGroupMembers/names REST request, response is: ListingNamesResponse
     */
    public static UserGroupProgramListRequest make() {
        return new UserGroupProgramListRequest();
    }
    
    /**
     * the request description for the /program/userGroupMembers/names REST request, response is: ListingNamesResponse
     */
    public static UserGroupProgramListRequest makeFromString(String jsonS) {
        try {
            JSONObject jsonO = new JSONObject(jsonS);
            return make(jsonO);
        } catch (JSONException e) {
            throw new RuntimeException("JSON parse error when parsing: " + jsonS, e);
        }
    }
    
    /**
     * the request description for the /program/userGroupMembers/names REST request, response is: ListingNamesResponse
     */
    public static UserGroupProgramListRequest makeFromProperties(String cmd,String groupName) {
        UserGroupProgramListRequest entity = new UserGroupProgramListRequest();
        entity.setCmd(cmd);
        entity.setGroupName(groupName);
        entity.immutable();
        return entity;
    }
    
    /**
     * the request description for the /program/userGroupMembers/names REST request, response is: ListingNamesResponse
     */
    public static UserGroupProgramListRequest make(JSONObject jsonO) {
        return make().merge(jsonO).immutable();
    }
    
    /**
     * merge the properties of a JSON-object into this bean. The bean must be "under construction".
     * The keys of the JSON-Object must be valid. The bean remains "under construction".<br>
     * Throws a runtime exception if inconsistencies are detected.
     */
    public UserGroupProgramListRequest merge(JSONObject jsonO) {
        try {
            for (String key : JSONObject.getNames(jsonO)) {
                if ("_version".equals(key)) {
                } else if ("cmd".equals(key)) {
                    setCmd(jsonO.optString(key));
                } else if ("groupName".equals(key)) {
                    setGroupName(jsonO.getString(key));
                } else {
                    throw new RuntimeException("JSON parse error. Found invalid key: " + key + " in " + jsonO);
                }
            }
            return this;
        } catch (Exception e) {
            throw new RuntimeException("JSON parse / casting error when parsing: " + jsonO, e);
        }
    }
    
    /**
     * moves a bean from state "under construction" to state "immutable".<br>
     * Checks whether all required fields are set. All lists are made immutable.<br>
     * Throws a runtime exception if inconsistencies are detected.
     */
    public UserGroupProgramListRequest immutable() {
        if (this.immutable) {
            return this;
        }
        this.immutable = true;
        return validate();
    }
    
    /**
     * Checks whether all required fields are set.<br>
     * Throws a runtime exception if inconsistencies are detected.
     */
    private UserGroupProgramListRequest validate() {
        String _message = null;
        if ( !this.immutable ) {
            _message = "UserGroupProgramListRequest-object is already immutable: " + toString();
        }
        if ( groupName == null) {
            _message = "required property groupName of UserGroupProgramListRequest-object is not set: " + toString();
        }
        if ( _message != null ) {
            this.immutable = false;
            throw new RuntimeException(_message);
        }
        return this;
    }
    
    /**
     * GET groupName. Object must be immutable. Never return null or an undefined/default value.
     */
    public String getGroupName() {
        if (!this.immutable) {
            throw new RuntimeException("no groupName from an object under construction: " + toString());
        }
        return this.groupName;
    }
    
    /**
     * SET groupName. Object must be mutable.
     */
    public UserGroupProgramListRequest setGroupName(String groupName) {
        if (this.immutable) {
            throw new RuntimeException("groupName assigned to an immutable object: " + toString());
        }
        this.groupName = groupName;
        return this;
    }
    
    /**
     * generates a JSON-object from an immutable bean.<br>
     * Throws a runtime exception if inconsistencies are detected.
     */
    public JSONObject toJson() {
        if (!this.immutable) {
            throw new RuntimeException("no JSON from an object under construction: " + toString());
        }
        JSONObject jsonO = new JSONObject();
        try {
            jsonO.put("_version", "1");
            if (this.cmd != null) {
                jsonO.put("cmd", this.cmd);
            }
            jsonO.put("groupName", this.groupName);
        } catch (JSONException e) {
            throw new RuntimeException("JSON unparse error when unparsing: " + this, e);
        }
        return jsonO;
    }
    
    @Override
    public String toString() {
        return "UserGroupProgramListRequest [immutable=" + this.immutable + ", cmd=" + this.cmd + ", groupName=" + this.groupName + " ]";
    }
    @Override
    public int hashCode() {
        throw new RuntimeException("no hashCode from transport beans!");
    }
    
    @Override
    public boolean equals(Object obj) {
        throw new RuntimeException("no equals from transport beans!");
    }
    
}
