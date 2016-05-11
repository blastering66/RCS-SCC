package id.ols.models;

/**
 * Created by macbook on 5/11/16.
 */
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PojoSite {

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

        @SerializedName("site_id")
        @Expose
        private String siteId;
        @SerializedName("site_codeid")
        @Expose
        private String siteCodeid;
        @SerializedName("site_name")
        @Expose
        private String siteName;

        /**
         *
         * @return
         * The siteId
         */
        public String getSiteId() {
            return siteId;
        }

        /**
         *
         * @param siteId
         * The site_id
         */
        public void setSiteId(String siteId) {
            this.siteId = siteId;
        }

        /**
         *
         * @return
         * The siteCodeid
         */
        public String getSiteCodeid() {
            return siteCodeid;
        }

        /**
         *
         * @param siteCodeid
         * The site_codeid
         */
        public void setSiteCodeid(String siteCodeid) {
            this.siteCodeid = siteCodeid;
        }

        /**
         *
         * @return
         * The siteName
         */
        public String getSiteName() {
            return siteName;
        }

        /**
         *
         * @param siteName
         * The site_name
         */
        public void setSiteName(String siteName) {
            this.siteName = siteName;
        }

    }
}
