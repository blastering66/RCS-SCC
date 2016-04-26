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
    @SerializedName("data")
    @Expose
    private Data data;

    @SerializedName("response_message")
    @Expose
    private String responseMessage;

    public Data getData() {
        return data;
    }


    /**
     * @return The jsonCode
     */
    public Integer getJsonCode() {
        return jsonCode;
    }

    /**
     * @param jsonCode The json_code
     */
    public void setJsonCode(Integer jsonCode) {
        this.jsonCode = jsonCode;
    }

    /**
     * @return The act
     */
    public Act getAct() {
        return act;
    }

    /**
     * @param act The act
     */
    public void setAct(Act act) {
        this.act = act;
    }


    /**
     * @return The responseMessage
     */
    public String getResponseMessage() {
        return responseMessage;
    }

    /**
     * @param responseMessage The response_message
     */
    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public class Data {

        @SerializedName("site_id")
        @Expose
        private String siteId;

        /**
         * @return The siteId
         */
        public String getSiteId() {
            return siteId;
        }

        /**
         * @param siteId The site_id
         */
        public void setSiteId(String siteId) {
            this.siteId = siteId;
        }

    }
}
