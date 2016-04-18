package id.ols.models;

/**
 * Created by macbook on 4/18/16.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PojoResponseLogin {

    @SerializedName("json_code")
    @Expose
    private Integer jsonCode;
    @SerializedName("login")
    @Expose
    private Integer login;
    @SerializedName("Auth-Key")
    @Expose
    private String AuthKey;
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
     * The login
     */
    public Integer getLogin() {
        return login;
    }

    /**
     *
     * @param login
     * The login
     */
    public void setLogin(Integer login) {
        this.login = login;
    }

    /**
     *
     * @return
     * The AuthKey
     */
    public String getAuthKey() {
        return AuthKey;
    }

    /**
     *
     * @param AuthKey
     * The Auth-Key
     */
    public void setAuthKey(String AuthKey) {
        this.AuthKey = AuthKey;
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
