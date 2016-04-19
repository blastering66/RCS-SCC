package id.ols.models;

/**
 * Created by macbook on 4/19/16.
 */

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PojoResponseInsert {

    @SerializedName("json_code")
    @Expose
    private Integer jsonCode;
    @SerializedName("act")
    @Expose
    private Act act;

    @SerializedName("response_message")
    @Expose
    private String responseMessage;

    /**
     *
     * @return
     * The jsonCode
     */
    public Integer getJsonCode() {
        return jsonCode;
    }

    /**
     *
     * @param jsonCode
     * The json_code
     */
    public void setJsonCode(Integer jsonCode) {
        this.jsonCode = jsonCode;
    }

    /**
     *
     * @return
     * The act
     */
    public Act getAct() {
        return act;
    }

    /**
     *
     * @param act
     * The act
     */
    public void setAct(Act act) {
        this.act = act;
    }



    /**
     *
     * @return
     * The responseMessage
     */
    public String getResponseMessage() {
        return responseMessage;
    }

    /**
     *
     * @param responseMessage
     * The response_message
     */
    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
