package id.ols.models;

/**
 * Created by macbook on 4/19/16.
 */

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PojoRegionsCity {
    @SerializedName("json_code")
    @Expose
    private Integer jsonCode;
    @SerializedName("act")
    @Expose
    private Act act;
    @SerializedName("data")
    @Expose
    private List<Datum> data = new ArrayList<Datum>();
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
     * The data
     */
    public List<Datum> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(List<Datum> data) {
        this.data = data;
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

    public class Datum {

        @SerializedName("region_id")
        @Expose
        private String regionId;
        @SerializedName("region_code")
        @Expose
        private String regionCode;
        @SerializedName("region_name")
        @Expose
        private String regionName;
        @SerializedName("region_idparent")
        @Expose
        private String regionIdparent;

        /**
         *
         * @return
         * The regionId
         */
        public String getRegionId() {
            return regionId;
        }

        /**
         *
         * @param regionId
         * The region_id
         */
        public void setRegionId(String regionId) {
            this.regionId = regionId;
        }

        /**
         *
         * @return
         * The regionCode
         */
        public String getRegionCode() {
            return regionCode;
        }

        /**
         *
         * @param regionCode
         * The region_code
         */
        public void setRegionCode(String regionCode) {
            this.regionCode = regionCode;
        }

        /**
         *
         * @return
         * The regionName
         */
        public String getRegionName() {
            return regionName;
        }

        /**
         *
         * @param regionName
         * The region_name
         */
        public void setRegionName(String regionName) {
            this.regionName = regionName;
        }

        /**
         *
         * @return
         * The regionIdparent
         */
        public String getRegionIdparent() {
            return regionIdparent;
        }

        /**
         *
         * @param regionIdparent
         * The region_idparent
         */
        public void setRegionIdparent(String regionIdparent) {
            this.regionIdparent = regionIdparent;
        }

    }
}
