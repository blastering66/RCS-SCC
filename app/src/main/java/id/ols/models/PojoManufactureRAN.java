package id.ols.models;

/**
 * Created by macbook on 4/26/16.
 */
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PojoManufactureRAN {
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

        @SerializedName("ranmanufacturer_id")
        @Expose
        private String ranmanufacturerId;
        @SerializedName("ranmanufacturer_name")
        @Expose
        private String ranmanufacturerName;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("created_at")
        @Expose
        private String createdAt;

        /**
         *
         * @return
         * The ranmanufacturerId
         */
        public String getRanmanufacturerId() {
            return ranmanufacturerId;
        }

        /**
         *
         * @param ranmanufacturerId
         * The ranmanufacturer_id
         */
        public void setRanmanufacturerId(String ranmanufacturerId) {
            this.ranmanufacturerId = ranmanufacturerId;
        }

        /**
         *
         * @return
         * The ranmanufacturerName
         */
        public String getRanmanufacturerName() {
            return ranmanufacturerName;
        }

        /**
         *
         * @param ranmanufacturerName
         * The ranmanufacturer_name
         */
        public void setRanmanufacturerName(String ranmanufacturerName) {
            this.ranmanufacturerName = ranmanufacturerName;
        }

        /**
         *
         * @return
         * The updatedAt
         */
        public String getUpdatedAt() {
            return updatedAt;
        }

        /**
         *
         * @param updatedAt
         * The updated_at
         */
        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        /**
         *
         * @return
         * The createdAt
         */
        public String getCreatedAt() {
            return createdAt;
        }

        /**
         *
         * @param createdAt
         * The created_at
         */
        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

    }
}
